Special informations for uDraw(Graph) on MacOSX
===============================================

We have tried to have as little difference as possible between the
UNIX and the Mac OSX release.  In this file we will describe the
differences that couldn't be avoided and give some useful hints.  It
is recommended to read this file carefully before using uDraw(Graph) for
Mac OSX.

1. Platform Requirements
------------------------

The binaries have been compiled and tested on Mac OSX 10.3.8, it is not
known, if uDraw(Graph) is working on other than this release. Please
inform us if it does!

To use some features of uDraw(Graph) especially in the grapheditor, a
3-button mouse is needed. We have noticed that middle and right mouse
buttons are misbehaving for some products, because middle mouse button
(or scrolling wheel) triggers the right mouse button event and the right
mouse button triggers the middle mouse button event.

uDraw(Graph) 3.1.1 is the first release on Mac OSX. Therefore the integration
into the operating system is not as perfect as one could expect. Currently
it is only a UNIX application ported to Mac OSX. We will try to make a
uDraw(Graph) bundle with icons etc. but this will take some time. Till
than it is recommended to use uDraw(Graph) from command-line via the
Terminal utility.

2. Installation
---------------

Currently uDraw(Graph) is delivered as a gzip compressed tar file. This
can be easily unpacked by tools like StuffIt or by using the command "tar"
as described in README.txt from command line in the Utility Terminal. It
unpacks in a directory uDrawGraph-3.1, from which it can be directly used
by executing the commands in the bin directories, which are wrappers for
the real binaries in the bin/bin.osx directory.

If you've got problems starting uDraw(Graph), please check the setting
of the UDG_HOME environment variable in the bin/.uDrawGraph-wrapper file.
Maybe it is better to set it to an absolute pathname there.

3. Documentation
----------------

The  documentation orientates on the  UNIX  version   of  uDraw(Graph).
Therefore  the appearance of all screenshots  maybe not the
same as you'll  see it on your Mac OSX  machine. This means especially
the colors, the  fonts, the window borders, the menu bar, the  appearance
of uDraw(Graph) in  the   iconified  state   and  the   uDraw(Graph)
icon.  Despite  of  that  the  manual contains  all  Mac OSX  specific
information.


4. Browsing the documentation
-----------------------------

To use the documentation, you need a web browser installed. We didn't
find out how to start the default web browser out of the program.
Therefore uDraw(Graph) tries to start a program called
/Applications/Mozilla.app/Contents/MacOS/mozilla-bin
to browse the documentation by default. If this isn't installed or
you want to use another browser, you have to specify it in the
Options->General Settings... dialog. You have to specify the path
to the executable binary, not the bundle as done for Mozilla above.

We didn't find out how to show a new webpage in an already running
mozilla application. Therefore it may happen that the Help system will
not work as expected.

5. Help menu
------------

The 'Help' menu described in the documentation is not placed at the 
right border of the menu bar, instead it is right next to the 'Options'
menu.

6. Error messages
-----------------

On UNIX uDraw(Graph) will display error messages on command-line,
if a wrong  command-line option has been used or  any of the necessary
files can't be  found. If you are using uDraw(Graph) by double-clicking
it's executable (what is currently not recommended), you will not see
these messages. If uDraw(Graph) doesn't start as expected, please start
it from command-line and see, if there are messages.

7. File selector dialogs
------------------------

On Mac OSX  the file  selector has been  replaced by the  native Mac OSX
file  selector  for  all  places  where uDraw(Graph)  uses  file
selector dialogs:

  - File->Open
  - File->Save As
  - File->Print (Browse)
  - File->Connect Application
  - API category window, command 'file_browser'

Unfortunately the dialogs seem to appear in  English, which
may be a little confusing, if the language of uDraw(Graph) is set
to German.

8. Known limitations
--------------------
- uDraw(Graph) lacks some features if a one button mouse is used
- the grapheditor edge attribute dialog doesn't show the images of
  the arrow heads and directions, instead it writes only "(image)",
  but the actual selected value is displayed correctly. Therefore
  you have to choose a value and wait for it to be displayed to
  find the right one.
- printing has not been tested but should work with the command-line
  print commands (lp, lr, ...)

