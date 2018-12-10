This directory contains some example graphs which can be visualized
with the interactive graph visualization system uDraw(Graph)
version 2.0 and above.

The *.udg files are term representations of graphs (i.e. ASCII
text).  Term representations are structures of graphs without any
layout information. They can be modified or created with an ordinary
text editor or with the graph editor of uDraw(Graph) (refer to
documentation for details). One can load term representations in
uDraw(Graph) by using menu 'File/Open...'.

The *.status files are status files which covers the structure of a
graph, the layout and the current user interface settings of
uDraw(Graph). One can load status files in uDraw(Graph) by
using the menu 'File/Open...'.  Note: status files saved by a later
version of uDraw(Graph) cannot be loaded in an earlier release
of the system, but the opposite is true.

Both term representations and status files can be loaded in uDraw(Graph)
at start-up time by using the filename as command line argument.

README.txt
----------
is this file.

all_features_tooltips.udg
-------------------------
is a small graph to show nearly all node and edge attributes supported
by uDraw(Graph) since version 3.0.

all_features_tooltips_de.udg
----------------------------
is a german version of all_features_tooltips.udg.

conceptual_graph.udg
--------------------
is a type of graph used in knowledge representation. In fact this one is a 
tree. It represents properties of a publication.

cyclic.udg
--------------
is a small cyclic graph with five nodes. 

edge_labels.udg
---------------
is a small graph where edge labels are simulated by using the graphical
object 'text'. 

example_rowe.udg
----------------
is a call graph taken from an article of Lawrence A. Rowe et al (Rowe, 
Davis, Messinger, Meyer: A Browser for Directed Graphs; Software -
Practice and Experience; Vol. 17(1); pp. 61-76; January 1987).

font_test.udg
-------------
is a term representation of a graph which uses all fonts supported by 
uDraw(Graph) since version 3.0.

graph_example.udg
-----------------
is a graph without any semantic, taken from a prospect of a commercial 
graph editor. The colours do not have any significance, they are just 
looking nice.

graph_example.status
--------------------
is a status file of the graph above. The layout was improved manually by 
using fine tuning operations.

hiding_subgraphs.udg
--------------------
is a small graph where the initial abstraction feature (i.e. attribute
HIDDEN) is demonstrated. 

languages.udg
-------------
is a term representation of a graph which shows dependencies in the
development of some programming languages. This graph was taken from an
article.

languages.status
----------------
is a status file of the graph above.

multiline.udg
-------------
is a graph with only one node where the multiline feature for strings
is demonstrated. 

multiple_edges.udg
------------------
is a term representation of a small graph with a multiple edge (i.e.
more than one edge between two nodes).

pseudo_cyclic.udg
-----------------
is a term representation of a cyclic graph which is in fact an acyclic 
graph. Some edges in this acyclic graph structure are inverted in the
visualization by using the edge attribute '_DIR', so the graph seems 
to be cyclic.

self_refering_edges.udg
-----------------------
is a graph with multiple- and self-refering edges.

small_coloured.udg
------------------
is a small test graph using different colors. 

smaller.udg
-----------
is even smaller than small_coloured.udg (with only two nodes and 
one edge). You will not be impressed after loading this graph.

sublanguages.udg
----------------
is a very nice graph which will have a symetrical layout in uDraw(Graph).
The true color feature of uDraw(Graph) version 2.0 and above is used here
to get different shades of yellow. The graph shows
the relations of some programming language properties.

xerox_star.udg
--------------
is a term representation of a graph, representing the development of
the Xerox Star, the first computer with a graphical user
interface. The graph shows how systems in the context of the Star have
influenced eachother. In this graph, colors and shapes are used for
the visualization of nodes to represent differend classes of systems:
Green ellipses are used for hardware, red circles are used for
programming languages resp. environments, blue boxes are used for
other software and yellow rhombs are used for immaterial systems which
were only described in papers. This graph was taken from the article
"The Xerox Star: A Retrospective" of Johnson et al. in IEEE Computer,
pp. 11-29, September 1989.

xerox_star.status
-----------------
is a status file of the graph above. The layout was improved manually by 
using fine tuning operations.
