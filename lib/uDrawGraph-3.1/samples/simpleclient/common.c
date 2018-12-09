/* Sample application to illustrate using the uDraw(Graph) API
   via pipes/sockets.

   Copyright (C) 2005 Universitaet Bremen 
*/

#include <stdio.h>
#ifdef _WIN32
#include <winsock2.h>
#endif
#include <stdarg.h>

void bail(const char *msg)
{
  /* An error has occured. Print error message ... */
#ifndef _WIN32
  perror(msg);
#else
  char buffer[1000];
  sprintf(buffer, "%s, error #%d\n", msg, WSAGetLastError());
  MessageBox(NULL, buffer, "sample client", MB_OK);
#endif
  /* and terminate. */
  exit(1);
}

/* fgets emulation for file descriptor. Can't use fdopen and fgets,
 * because under some Windows versions fdopen doesn't work with
 * sockets.
 *
 * This function clearly needs read-ahead tuning, but it should do for
 * an example.
 */
char *my_fgets(char *buffer, int size, int fd)
{
  int count;
  char *tmp = buffer;
  
  while(size > 1) {
    count = read(fd, tmp, 1);
    if (count < 0) {
      return NULL;
    }
    if (count < 1) {
      break;
    }
    if (*tmp == '\r') {
      continue;
    }
    size--;
    if (*tmp++ == '\n') {
      break;
    }
  }

  *tmp = '\0';
  return tmp > buffer ? buffer : NULL;
}

/* Secure fprintf emulation for file descriptor.
 */
void my_fprintf(int fd, const char *format, ...) 
{
  va_list args;
  int count;
  static int size = 1024;
  static char *buffer = NULL;
  if (buffer == NULL) {
    buffer = (char*)malloc(size);
  }
  if (buffer == NULL) {
    fprintf(stderr, "Out of memory\n");
    exit(1);
  }
  while (1) {
    va_start(args, format);
    count = vsnprintf(buffer, size, format, args);
    va_end(args);
    /* Some libc implementations return -1, some return the number of
     * required bytes minus 1, if buffer is too short. */
    if (count < 0) {
      size *= 2;// can only guess on space requirements
    } else if (count > size - 1) {
      size = count + 1;
    } else {
      break;
    }
    buffer = (char*)realloc(buffer, size);
    if (buffer == NULL) {
      fprintf(stderr, "Out of memory\n");
      exit(1);
    }
  }
  write(fd, buffer, strlen(buffer));
}

/* Do something with uDraw(Graph). */
void run(int in, int out)
{
  char buffer[1000];
  int i = 0;
  
  /* Wait for initial ok so we can be sure the API is ready for
     commands. */
  while(my_fgets(buffer, sizeof(buffer), in))
  {
    const char *ok_cmd = "ok\n";
    if (strcmp(buffer, ok_cmd) == 0) 
    {
      break;
    }
  }
  
  my_fprintf(out, "window(show_message(\"Connection established.\"))\n");

  my_fprintf(out, "app_menu(create_menus([menu_entry(\"id_my_menu\",\"Make another node\")]))\n");

  my_fprintf(out, "app_menu(activate_menus([\"id_my_menu\"]))\n");
  
  while(my_fgets(buffer, sizeof(buffer), in))
  {
    const char *sel_cmd = "menu_selection(\"id_my_menu\")\n";
    const char *quit_cmd = "quit\n";

    if (strcmp(buffer, quit_cmd) == 0)
    {
      break;
    }
    
    if (strcmp(buffer, sel_cmd) == 0)
    {
      my_fprintf(out, "graph(update([new_node(\"id%d\",\"\",[a(\"OBJECT\",\"Node %d\")])],[]))\n", i, i);
      i++;
    }
  }
}

