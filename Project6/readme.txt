/******************************************************************************
 *  Name: Kyle Drum
 *
 *  Operating system: Mac OS X
 *  Compiler: Eclipse
 *  Text editor / IDE: Eclipse
 *  Hours to complete assignment (optional):
 ******************************************************************************/


/******************************************************************************
 *  Describe concisely your algorithm to compute the horizontal and
 *  vertical seam.
 *****************************************************************************/
It uses a priority queue to hold the nodes to be searched and a 2d array to keep 
the pseudo distance values of each of the nodes. it initializes the dist values 
for the first row of the array as their energy, and then adds all the of the 
searchnodes (searchnodes have x, y, previous node, and dist variables) for the 
first row into the array. While the priority queue is not empty, it polls the first 
node from the queue and for each of the three lower nodes checks if the value of the 
current x,y coordinate on the 2d array plus the energy of the lower node is less 
than the value of the lower node's x,y on the 2d array. If so, it reassigns the 
distance 2d array value of the lower node to the current node's value plus the 
lower node's energy (like the cost of a link), and makes a new searchnode to add 
to the priority queue out of the lower node. It then adds all the seams to an array 
by backtracking through the previous variables of the searchnodes in the priority 
queue (That was for vertical seams by the way). For horizontal seams, it does 
basically the same thing but searching starting from the first column and going 
right.


/******************************************************************************
 *  Describe what makes an image ideal for this seamCarving algorithm and what
 *   kind of image would not work well.
 *****************************************************************************/
One that does not have too much variation between pixels at regular intervals. 
An image that would not work well would be an image where every single pixel 
was set to a randomized rgb value.




/******************************************************************************
 *  Give a formula (using tilde notation) for the running time
 *  (in seconds) required to reduce image size by one row and a formula
 *  for the running time required to reduce image size by one column.
 *  Both should be functions of W and H. Removal should involve exactly
 *  one call to the appropriate find method and one call to the
 *  appropriate remove method. The randomPicture() method in SCUtility
 *  may be useful.
 *
 *  Justify your answer with sufficient data using large enough
 *  W and H values. To dampen system effects, you may wish to perform
 *  many trials for a given value of W and H and average the results.
 *
 *  Be sure to give the leading coefficient and the value of the exponents
 *  as a fraction with 2 decimal places.
 *****************************************************************************/

(keep W constant) W = 250

 H           Row removal time (seconds)     Column removal time (seconds)
--------------------------------------------------------------------------
...250       .00628                         .00742
...500       .01164                         .01016
...1000      .02236                         .0139  
...2000      .03462                         .02364  


(keep H constant) H = 250

 W           Row removal time (seconds)     Column removal time (seconds)
--------------------------------------------------------------------------
...250       .00628                         .00742
...500       .01394                         .00846   
...1000      .0205                          .0152  
...2000      .03398                         .02384  
(row and column removal times are an average over 50 trials)

Running time to remove one row as a function of both W and H:  ~



Running time to remove one column as a function of both W and H:  ~






/******************************************************************************
 *  Known bugs / limitations.
 *****************************************************************************/




/******************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and exercises, but do
 *  include any help from people (including classmates and friends) and
 *  attribute them by name.
 *****************************************************************************/


/******************************************************************************
 *  Describe any serious problems you encountered.
 *****************************************************************************/




/******************************************************************************
 *  List any other comments here. Feel free to provide any feedback
 *  on how much you learned from doing the assignment, and whether
 *  you enjoyed doing it.
 *****************************************************************************/
