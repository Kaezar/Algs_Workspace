/******************************************************************************
 *  Name: Kyle Drum
 *
 *  Operating system: Mac OS X
 *  Compiler: Eclipse
 *  Text editor / IDE: Eclipse
 *  Hours to complete assignment (optional):
 ******************************************************************************/



/******************************************************************************
 *  Describe the Node data type you used to implement the
 *  2d-tree data structure.
 *****************************************************************************/
The node has several instance variables. They are as follows: a Point2D called p 
a Value called value, a RectHV called rect, 2 Nodes called lb and rt (left and 
right), and a boolean called xory. When the constructor is called, it uses the 
arguments to assign the p, value, rect, and xory variables. The lb and rt variables 
are assigned through the recursive calls to the put method.
/******************************************************************************
 *  Describe your method for range search in a kd-tree.
 *****************************************************************************/
The public range method creates a stack of Point2Ds called points and then calls 
the private range method with the root, points, and the original rect argument 
as arguments. That method first checks to see if the given node is not null and 
if its rect variable intersects the rect argument. If so, then it pushes the given 
node's point onto the points stack and recursively calls range on the given node's 
left and right child nodes.

/******************************************************************************
 *  Describe your method for nearest neighbor search in a kd-tree.
 *****************************************************************************/
The nearest method creates a Queue of Nodes called queue and a Point2D called nearest. 
It sets nearest = root and enqueues root. While queue is not empty: it dequeues a node 
and calls it tempNode. If tempnode's point is closer to the given p than nearest, 
then it sets updates nearest to that point. It then compares the x or y coordinate 
of tempnode's point to the given point based on the level of tempnode. if tempnode's 
point is less than the given point, it enqueues the right child (provided that it 
is possible for a point in the right child's rectangle to be closer than nearest) 
and then enqueues the left child (provided the same conditions). if greater than, 
it does the same thing but queues the left child first.


/******************************************************************************
 *  Using the 64-bit memory cost model from the textbook and lecture,
 *  give the total memory usage in bytes of your 2d-tree data structure
 *  as a function of the number of points N. Use tilde notation to
 *  simplify your answer (i.e., keep the leading coefficient and discard
 *  lower-order terms).
 *
 *  Include the memory for all referenced objects (including
 *  Node, Point2D, and RectHV objects) except for Value objects
 *  (because the type is unknown). Also, include the memory for
 *  all referenced objects.
 *
 *  Justify your answer below.
 *
 *****************************************************************************/

bytes per Point2D:

bytes per RectHV:

bytes per KdTree of N points:   ~





/******************************************************************************
 *  How many nearest neighbor calculations can your brute-force
 *  implementation perform per second for input100K.txt (100,000 points)
 *  and input1M.txt (1 million points), where the query points are
 *  random points in the unit square? Explain how you determined the
 *  operations per second. (Do not count the time to read in the points
 *  or to build the 2d-tree.)
 *
 *  Repeat the question but with the 2d-tree implementation.
 *****************************************************************************/

                       calls to nearest() per second
                     brute force               2d-tree
                     ---------------------------------
input100K.txt         267                       124765

input1M.txt           12                        7405

Made a stopwatch object and an int called queries. while stopwatch < 1.0 it finds 
the nearest point to a random point with x/y values between 0 and 1, and then 
increments queries.


/******************************************************************************
 *  Known bugs / limitations.
 *****************************************************************************/




/******************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and exercises, but do
 *  include any help from people (including classmates and friends) and
 *  attribute them by name.
 *****************************************************************************/
I had help from Jeff Klow and Kat Sayrs with regards to how to best determine 
the level of the current node (in the put method) so that the compareToXY method 
can choose which values to compare.

/******************************************************************************
 *  Describe any serious problems you encountered.
 *****************************************************************************/




/******************************************************************************
 *  List any other comments here. Feel free to provide any feedback
 *  on how much you learned from doing the assignment, and whether
 *  you enjoyed doing it.
 *****************************************************************************/
