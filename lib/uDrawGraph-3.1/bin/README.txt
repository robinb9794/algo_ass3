This directory contains the binary files for the interactive graph
visualization system uDraw(Graph).

The term tools are described in detail in the online documentation
(chapter Reference / uDraw(Graph) Tools) which is available in
the uDraw(Graph) Help menu.

README.txt
----------
is this file.

checkterm
---------
is a syntactical analysis for the (external) term representation format of 
uDraw(Graph) graphs which can be loaded from a file. 'checkterm' checks the
file specified by the commandline argument.

uDrawGraph
----------
is the binary file of uDraw(Graph). Refer to the uDraw(Graph) online
documentation for details about using uDraw(Graph). 

flattenterm
-----------
is used for undoing pretty-printing of a graph file in term representation 
format. Pretty-printing can be done with tool formatterm, see below. 
flattenterm canceles all whitespace in the graph file specified by the 
commandline argument. This tool is used for preparing formated terms to be 
send to the uDraw(Graph) API which does not allow whitespaces (especially
return characters).

formatterm
----------
is a pretty printer for a graph file in term representations format. Each 
node and edge in the file specified by the commandline argument is placed on
a separate line. Different indents for the nodes and edges are used to show 
the parent/child relationsships. A user can specify this indent with the -i 
option (default is 8).

gml2udg
-------
is a converter from the GML format to the uDraw(Graph) format. 

grapheditor
-----------
is the binary file of the grapheditor. The grapheditor has to be
connected to the uDraw(Graph) API. Everything about the grapheditor is
described in the online documentation (in chapter 'Reference') which is
available in the uDraw(Graph) Help menu.
