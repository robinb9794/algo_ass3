Platform Requirements and Installation
======================================

Platform Requirements
---------------------

uDraw(Graph) 3.1 is available for UNIX(R), Linux(R), FreeBSD,
Mac OSX and Windows(R).  At the moment the following operating systems are
supported:

  * Linux(R) 2.2.x or later on x86 hardware 
  * Sun(R) Solaris(R) 9 or later on SPARC(R) or x86 hardware 
  * FreeBSD 4.11
  * Windows NT(R) 4.0 (>= Service Pack 5) or Windows 2000(R) on x86 hardware
  * Mac OSX 10.3

(For Windows NT(R) and Windows 2000(R) refer to file README_WIN32.txt.)
(For Mac OSX refer to file README_MacOSX.txt.)

uDraw(Graph) has moderate requirements, so you can even use it on older 
hardware. Of course, the larger the graphs are, the faster the computer 
should be. It is possible that uDraw(Graph) will work on slower 
configurations or with fewer memory, but in this case the performance may 
be disappointing. 

  * Hardware requirements (Minimum):
    100 MHz processor
    64MB RAM
    20MB of free space on harddisk

  * Hardware requirements (Recommended):
    300 MHz processor
    128MB RAM
    30MB of free space on harddisk
    network card for floating licenses
    PostScript(R) printer

Basically, uDraw(Graph) does not depend on particular software,
it works "out of the box". On UNIX(R), Linux(R) and FreeBSD, 
X11(TM) R5 or later is required.

Installation
------------

(For Windows NT(R) and Windows 2000(R) refer to file README_WIN32.txt.)
(For Mac OSX refer to file README_MacOSX.txt.)

Overview:

  1. Get uDraw(Graph) by ftp or on CD. 
  2. Install the distribution. 
  3. Set $UDG_HOME to the installation directory. 
  4. Be sure to have a browser for the help system (Mozilla preferred). 
  5. Start executable file "uDrawGraph" in the "bin" directory. 
  6. Read and accept the license conditions. 

Detail:

First you have to download a uDraw(Graph) distribution by ftp,
see http://www.informatik.uni-bremen.de/uDrawGraph.

If your distribution comes as a *.tar.gz file, you open a shell, use
"gunzip *.tar.gz" and then "tar -xf *tar" to install the distribution.
This creates the directory "uDrawGraph-3.1". uDraw(Graph)
can be placed in any directory. To place the software at another
location, you need to move this directory completely to some other
directory.

For Linux(R) you can alternatively download the distribution as an RPM
file. To install, just issue "rpm -i *.rpm" as user root. This places
uDraw(Graph) in the directory "/opt/uDrawGraph".
To put it, for example, into "/usr/local/uDrawGraph",
you have to issue "rpm --prefix /usr/local -i *.rpm".

Before starting uDraw(Graph), you have to set the environment
variable UDG_HOME to the absolute file name of the
uDraw(Graph) directory on your computer. The Linux(R) RPM sets this
environment variable automatically.  Be careful with changing the
location of the distribution after users have saved their options.

For example, if you have installed the product in your home directory
"/home/me", then the environment variable can be set using the
following command (try "echo $SHELL" to see the kind of shell you
currently use):

  * In (t)csh: setenv UDG_HOME /home/me/uDrawGraph-3.1
  * In (ba)sh: export UDG_HOME=/home/me/uDrawGraph-3.1

You should make these settings permanent by putting this command into file 
~/.cshrc (for (t)csh users) or ~/.profile (for (ba)sh users). Finally, make 
sure that you have installed a web browser to work with the online 
documentation and help system directly from the uDraw(Graph) user
interface. Mozilla is preferred in order to 
reuse an open browser when consecutive calls to the help system are invoked 
by the user. If you have to use another browser like KDE Konqueror or Opera, 
then uDraw(Graph) must open a new browser window for each consecutive 
call to the help system. 

The command you have to start is "uDrawGraph" in the "bin"
directory.

A window for license agreement appears when the 
user starts uDraw(Graph) for the first time. Please read the terms of 
the agreement carefully. You can get the full text of the license with the 
Show License button. The license text is also available in file "LICENSE.txt" 
in the installation directory. By confirming the license window, you accept 
and agree to become bound by all terms of this license. Please press the 
"Quit" button in the license agreement window if you do not agree to this 
license. 



UNIX is a registered trademark of X/Open Company, Ltd. X Window System and X11 
are trademarks of The Open Group. Sun and Solaris are registered trademarks 
of Sun Microsystems, Inc. SPARC is a registered trademarks of SPARC 
International, Inc. Linux is a registered trademark of Linus Torvalds. Red 
Hat is a registered trademark of Red Hat, Inc. SuSE is a registered trademark 
of SuSE AG. Microsoft, Windows, Windows NT, Windows 2000 and Internet Explorer
are trademarks or registered trademarks of Microsoft Corporation. Apple, Mac
and Mac OS are trademarks or registered trademarks of Apple Computers, Inc.
Adobe and PostScript are registered trademarks of Adobe Systems, Inc.
Netscape and Navigator are registered trademarks of Netscape Communications
Corporation. All other trademarks mentioned herein are the property of their
respective owners. Information in this document is subject to change without
notice. 

-- end of file --
