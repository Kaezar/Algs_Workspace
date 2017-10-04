/******************************************************************************
 *  Name: Kyle Drum
 *
 *  Operating system: Mac OS X
 *  Compiler: Eclipse
 *  Text editor / IDE: Sublime Text/Eclipse
 *  Hours to complete assignment (optional):
 ******************************************************************************/


/******************************************************************************
 *  Describe how you implemented Percolation.java. How did you check
 *  whether the system percolates?
 *****************************************************************************/
I implemented Percolation.java by creating two objects: grid and quickUnion. grid 
is a 2d array of boolean values which represents whether nodes on the system are 
open/closed. quickUnion is a WeightedQuickUnionUF data type with indexes for each 
node in the system as well as two “virtual” nodes at the beginning and end which 
are not present in grid. The class constructor takes an argument of an integer 
called N and creates an NxN grid as well as a quickUnion of length n^2 + 2. It uses 
the WeightedQuickUnionUF union method to connect the first virtual node to every 
node on the first row of the grid, and connect the second virtual node to every 
node on the last row of the grid. It uses a private method called xyto1D to convert 
2D grid coordinates to 1D quickUnion indices. The open method takes an argument of 2D 
grid coordinates (row and column) and first changes that value in grid to be true (if 
it was not already) and increments an int variable called openSites. Then, it checks 
the coordinate locations above, below, left, and right of the argument node (ignoring 
them if there is no such node) to see if they are open. If they are, it creates a 
connection to them using the WeightedQuickUnionUF union method. The isOpen method 
simply returns true if the value at the given coordinates of the grid is true, and 
false otherwise. The numberOfOpenSites method even more simply returns the value of 
the openSites variable from before. The isFull method checks whether the given node 
is open, and if so, if it is connected to the first virtual node (the top) using the
 WeightedQuickUnionUF connected method. To check whether the system percolates, the 
 percolates method checks to see if the first virtual node (the top) is connected to 
 the second virtual node (the bottom) using the connected method.


/******************************************************************************
 *  Using Percolation with QuickFindUF.java,  fill in the table below such that
 *  the N values are multiples of each other.

 *  Give a formula (using tilde notation) for the running time (in seconds) of
 *  PercolationStats.java as a function of both N and T. Be sure to give both
 *  the coefficient and exponent of the leading term. Your coefficients should
 *  be based on empirical data and rounded to two significant digits, such as
 *  5.3*10^-8 * N^5.0 T^1.5.
 *****************************************************************************/

(keep T constant)

 N          time (seconds)
------------------------------
...
...
...
...
...


(keep N constant)

 T          time (seconds)
------------------------------
...
...
...
...
...


running time as a function of N and T:  ~


/******************************************************************************
 *  Repeat the previous question, but use WeightedQuickUnionUF.java.
 *****************************************************************************/

(keep T constant)

 N          time (seconds)
------------------------------
...
...
...
...
...


(keep N constant)

 T          time (seconds)
------------------------------
...
...
...
...
...


running time as a function of N and T:  ~


/**********************************************************************
 *  How much memory (in bytes) does a Percolation object use to store
 *  an N-by-N grid? Use the 64-bit memory cost model from Section 1.4
 *  of the textbook and use tilde notation to simplify your answer.
 *  Briefly justify your answers.
 *
 *  Include the memory for all referenced objects (deep memory).
 **********************************************************************/
4N^2 + 32N + 24 ~4N^2 bytes
24 bytes of overhead for the array of arrays. 8N bytes to reference row 
arrays. 16M bytes of overhead for row arrays. 4N^2 bytes for N int values 
in N rows (4 bytes per int value).








/******************************************************************************
 *  Known bugs / limitations.
 *****************************************************************************/
For some reason, when using the interactive percolation visualizer, if I open one 
of the nodes in the bottom row and then open a node above it, it does not register 
as flowing down to the bottom node. If the above node is open before the one on the 
bottom row it works just fine.



/******************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and exercises, but do
 *  include any help from people (including classmates and friends) and
 *  attribute them by name.
 *****************************************************************************/
Nate helped me understand the confidence value equation so that I could implement 
it in my code.

/******************************************************************************
 *  Describe any serious problems you encountered.
 *****************************************************************************/
Just that I was (and still kind of am) really rusty at Java and had a rough time 
getting back into the swing of things.



/******************************************************************************
 *  List any other comments here. Feel free to provide any feedback
 *  on how much you learned from doing the assignment, and whether
 *  you enjoyed doing it.
 *****************************************************************************/
