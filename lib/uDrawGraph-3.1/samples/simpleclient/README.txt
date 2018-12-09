This directory contains two samples for communication with the uDraw(Graph)
API; one using pipes, one using sockets.

To build the samples under UN*X issue:
gmake -f Makefile

To build the samples under Windows issue:
nmake -f Makefile.win32

See your MSVC++ documentation for how to build on the command-line.

This will create two executables sample_pipe(.exe) and
sample_socket(.exe).

For running the pipe example you must have uDraw(Graph) in your PATH. For
running the socket example uDraw(Graph) must be running on the
localhost listening to the default port.
