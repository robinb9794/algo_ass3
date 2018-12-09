Special informations for uDraw(Graph) for Windows
=================================================

We have tried to have as little difference as possible between the
UNIX and the Windows release.  In this file we will describe the
differences that couldn't be avoided and give some useful hints.  It
is recommended to read this file carefully before using uDraw(Graph) for
Windows.

1. Platform Requirements
------------------------

If you want to print graphs without having a PostScript(R) printer you
should use some software that is able to print PostScript(R)
(e.g. ghostscript). Otherwise, uDraw(Graph) does not depend on
particular software, it works "out of the box".

2. Documentation
----------------

The  documentation orientates on the  UNIX  version   of  uDraw(Graph).
Therefore  the appearance of all screenshots  maybe not the
same as you'll  see it on your Windows  machine. This means especially
the colors, the  fonts, the window borders, the  appearance of uDraw(Graph)
in  the   iconified  state   and  the   uDraw(Graph)
icon.  Despite  of  that  the  manual contains  all  Windows  specific
information.

3. Browsing the documentation
-----------------------------

To use the documentation, you need a web browser installed. If you
have multiple web browsers installed, the default web browser is used.

4. Help menu
------------

The 'Help' menu described in the documentation has been renamed to '?'
on Windows  for convenience and is  not placed at the  right border of
the window, instead it is right next to the 'Options' menu.

5. Error messages
-----------------

On UNIX uDraw(Graph) will display error messages on command-line,
if a wrong  command-line option has been used or  any of the necessary
files can't be  found. This doesn't work on  Windows and therefore you
know, that something is wrong,  if uDraw(Graph) doesn't start but
it will be more difficult to find out what is wrong. Please check:

- if the registry has been correctly setup. Rerun the installer if not
  sure.

- if you  have write  permissions for your  home directory. By default
  your user profile directory is used, but you can change that by setting
  the environment variable $HOME.

- if the  command line  options are correctly  used by  comparing them
  with the documentation (in  this case uDraw(Graph) should work,
  if you don't specify any command-line options).

6. File selector dialogs
------------------------

On Windows  the file  selector has been  replaced by a  native Windows
file  selector  for  all  places  where uDraw(Graph)  uses  file
selector dialogs:

  - File->Open
  - File->Save As
  - File->Print (Browse)
  - File->Connect Application
  - API category window, command 'file_browser'

The dialogs  will appear in  the language selected for  Windows, which
may be a little confusing, if the language of uDraw(Graph) is set
to a different language.

7. Printing directly from the Print-Dialog
------------------------------------------

Printing directly from the  'File->Print' dialog by specifying a print
command will work as follows on Windows, if you've connected a printer
directly to  your system or there  is a network  printer available for
your machine.

Please  enter  'print /d  <device>',  if  you've  a printer  connected
directly to LPT* or  COM* or 'print /d//<printserver/<printername>' of
there is a printer connected to  your network. This is just the MS-DOS
command 'print'.  Please refer to  its documentation to find  out more
about printing.

If this doesn't work for you, you  should print to a file and send the
generated PostScript file to your printer.

If your printer does not understand PostScript the above will not work
and you  have to look for  a program like  ghostscript (free software)
which will  convert the postscript output of  uDraw(Graph) before
sending it to your printer.  Refer to the documentation of the tool of
your  choice  to know  what  command has  been  specified  to get  the
expected result.
