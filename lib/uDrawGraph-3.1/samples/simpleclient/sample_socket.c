/* Sample application to illustrate using the uDraw(Graph) API
   via sockets.

   Copyright (C) 2005 Universitaet Bremen 
*/

#ifdef _WIN32
#include <windows.h>
#else
#include <sys/types.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#endif

#include <stdio.h>

int main(int argc, char **argv)
{
  int tcp_socket;
  struct sockaddr_in addr_server;
   
#ifdef _WIN32
  WSADATA wsaData;
  WORD wVersionRequested = MAKEWORD( 2, 2 );
  if (WSAStartup(wVersionRequested, &wsaData) != 0)
  {
    bail("Can't initialize Windows Library");
  }
#endif
  
  /* Create a TCP/IP socket. */
  tcp_socket = socket(PF_INET, SOCK_STREAM, 0);
  
  if (tcp_socket < 0) {
    bail("Can't create socket");
  }

  /* Create address for uDraw(Graph) server. */
  /* Family for TCP/IP is "Internet". */
  addr_server.sin_family = AF_INET;
  /* IANA registered port for uDraw(Graph). The following two
     values have to be converted from host byte order to network byte
     order. */
  addr_server.sin_port = htons(2542);
  /* Use IP address of localhost assuming uDraw(Graph) is running locally. */
  /* TODO: This has to be extend to show an example for remote
     communication too. */
  addr_server.sin_addr.s_addr = htonl(INADDR_LOOPBACK);

  if (connect(tcp_socket, (struct sockaddr *)&addr_server, sizeof(addr_server)) < 0)
  {
    fprintf(stderr, "Can't connect to localhost:2542, please start using this commandline:\n"
            "uDrawGraph -server\n");
    bail("Can't connect to uDraw(Graph)");
  }

  run(tcp_socket, tcp_socket);

#ifdef _WIN32
  WSACleanup();
#endif  

  exit(0);
}
