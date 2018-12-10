/* Sample application to illustrate using the uDraw(Graph) API
   via pipes under UNIX.

   Copyright (C) 2005 Universitaet Bremen 
*/

#include <stdio.h>
#include <unistd.h>
#include <signal.h>
#include <errno.h>

/* Signal handler for SIGCHLD */
void sighandler(int signum)
{
  /* We exit if our child exits. */
  fprintf(stderr,"Child has exited\n");
  exit(0);
}

#ifndef READ_HANDLE
#define READ_HANDLE 0
#endif
#ifndef WRITE_HANDLE
#define WRITE_HANDLE 1
#endif

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

     When we fork all the file descriptors exist in both the child and
     the parent process afterwards. The parent and the child first
     close the ends of the pipes they don't need. The child replaces
     stdin and stdout with the respective ends of the pipes and
     executes uDraw(Graph) afterward.

     The parent (our sample application) writes to the pipe to_udg
     and reads from the pipe from_udg. The child (uDraw(Graph))
     reads from the pipe to_udg and writes to the pipe
     to_udg.  */

  int to_udg[2];
  int from_udg[2];
  int pid;
  
  if (pipe(to_udg) < 0 || pipe(from_udg) < 0)
  {
    /* Bail on error. */
    bail("Can't create pipe");
  }
  
  if (signal(SIGCHLD, sighandler) == SIG_ERR)
  {
    /* Bail on error. */
    bail("Can't register signal handler");
  }

  pid = fork();
  
  if (pid > 0) 
  {
    /* On success, the PID of the child process is returned in the
       parent's thread of execution. */

    /* This thread only needs the writer's end of the pipe to_udg. */
    close(to_udg[READ_HANDLE]);

    /* This thread only needs the reader's end of the pipe
       from_udg. */
    close(from_udg[WRITE_HANDLE]);

    /* Use the pipes to communicate with uDraw(Graph): */
    run(from_udg[READ_HANDLE], to_udg[WRITE_HANDLE]);
  } 
  else if (pid == 0)
  {
    /* On success, 0 is returned in the child's thread of
       execution. */

    /* Replace STDIN with the reader's end of the pipe to_udg. */
    dup2(to_udg[READ_HANDLE], 0);

    /* Replace STDOUT with the writer's end of the pipe from_udg. */
    dup2(from_udg[WRITE_HANDLE], 1);

    /* Keep STDERR so error messages will find their way. */

    /* Start uDrawGraph -pipe -log log
       -pipe enables API communication via pipe
       -log log will write all API communications to file log
    */
    execlp("uDrawGraph", "uDrawGraph", "-pipe", "-log", "log", NULL);
    /* If we get here, execlp failed. */
    bail("Can't execute uDrawGraph");
  }
  else 
  {
    /* If fork returns < 0, an error has occured. */
    bail("Can't fork");
  }

  exit(0);
}
