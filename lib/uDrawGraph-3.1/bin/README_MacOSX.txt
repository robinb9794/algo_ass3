This directory contains the binary files for the interactive graph
visualization system uDraw(Graph).

The term tools are described in detail in the online documentation
(chapter Reference / uDraw(Graph) Tools) which is available in
the uDraw(Graph) Help menu.

All executable files in this directory are only scripts to call the
wrapper script .uDrawGraph-wrapper which itself calls the binaries in
bin.osx. The wrapper sets the environment variables before calling the
binaries.

This file describes only the files that are not described in README.txt.

README_MacOSX.txt
-----------------
is this file.

.uDrawGraph-wrapper
-------------------
Wrapper script to call the binaries in bin.osx. It sets the environment
variables UDG_HOME, UDG_ICONDIR and UDG_LANG. If there are problems in
starting uDraw(Graph), this file is a good place to check, if UDG_HOME
is set correctly.

If you want to switch the language of uDraw(Graph) to german, edit the
variable UDG_LANG in this file (i.e. set it to "de").

bin.osx/
--------
Directory that contains the binaries of uDraw(Graph). These binaries can
only be called directly, if the environment variable UDG_HOME has been set
before.

Frameworks/
-----------
Directory that contains some libraries needed to run the uDraw(Graph)
applications.
