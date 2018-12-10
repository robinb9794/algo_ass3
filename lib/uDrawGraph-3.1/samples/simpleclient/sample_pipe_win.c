/* Sample application to illustrate using the uDraw(Graph) API
   via pipes under Windows.

   Copyright (C) 2005 Universität Bremen 
*/

#include <windows.h>
#include <stdio.h>
#include <io.h>
#include <fcntl.h>
#include <errno.h>
#include <process.h>

#define   READ_HANDLE 0
#define   WRITE_HANDLE 1

int main(int argc, char **argv)
{
  /* A pipe is a pair of file descriptors connected with each
     other. The first one is used to read from the pipe. The second
     one is used to write to the pipe. Everything written to the
     second file descriptor will be read from the first file
     descriptor. The file descriptors correspond to the both ends of a
     pipe.

     We need two pipes (that are four file descriptors) for the
     communication with uDraw(Graph):

     The pipe to_udg is used to send commands from the application
     to uDraw(Graph).

     The pipe from_udg is used to receive events from uDraw(Graph).

     The sample application spawns uDraw(Graph) as a child to
     communicate with it using the pipe. Each of parent and child must
     have only one handle open on a pipe. Otherwise the communication
     between both would not work. So we must not inherit all the
     pipes' file descriptors from parent to child. Instead only the
     standard streams (stdin and stdout) are inherited: We replace
     stdin and stdout before spawning the child with the child's ends
     of the pipes.

     The parent (our sample application) writes to the pipe to_udg
     and reads from the pipe from_udg. The child (uDraw(Graph))
     reads from the pipe to_udg and writes to the pipe
     to_udg.  */

  int to_udg[2];
  int from_udg[2];
  int fd_stdout, fd_stdin;
  HANDLE hProcess;
  
  /* Create the pipes without EOL translation (O_BINARY). Don't
     inherit file descriptors automatically to child (O_NOINHERIT).  
  */
  if (_pipe(to_udg, 4096, O_BINARY | O_NOINHERIT) < 0 || 
      _pipe(from_udg, 4096, O_BINARY | O_NOINHERIT) < 0)
  {
    /* Bail on error. */
    bail("Can't create pipes");
  }

  /* Duplicate stdout handle. The next line will close original: We
     have to replace the original stdout with the writer's side of
     from_udg. This way the child gets its side of
     from_udg. */
  fd_stdout = _dup(_fileno(stdout));

  /* Duplicate write end of pipe to stdout handle. */
  if(_dup2(from_udg[WRITE_HANDLE], _fileno(stdout)) != 0)
    return   2;

  /* Close original write end of pipe. The child gets it via stdout,
     the parent doesn't need it at all. */
  close(from_udg[WRITE_HANDLE]);
  
  /* Duplicate stdin handle. The next line will close original: We
     have to replace the original stdin with the reader's side of
     to_udg. This way the child gets its side of to_udg. */
  fd_stdin = _dup(_fileno(stdin));

  /* Duplicate read end of pipe to stdin handle. */
  if(_dup2(to_udg[READ_HANDLE], _fileno(stdin)) != 0)
    return   2;

  /* Close original read end of to_udg. The child gets it via
     stdin, the parent doesn't need it at all. */
  close(to_udg[READ_HANDLE]);
  
  /* Spawn process. */
  hProcess = (HANDLE)spawnlp( P_NOWAIT, "uDrawGraph", "uDrawGraph", "-pipe", "-log", "log", NULL);
  if (!hProcess)
  {
    /* Bail on error. */
    bail("Can't spawn");
  }

  /* After spawning the child we can close the copies of the child's
     pipe ends too. */
  fclose(stdin);
  fclose(stdout);

  /* Use the pipes to communicate with uDraw(Graph): */
  run(from_udg[READ_HANDLE], to_udg[WRITE_HANDLE]);

  exit(0);
}
