/******************************************************************************
 *  Name: Kyle Drum
 *
 *  Operating system: Mac OS X
 *  Compiler: Eclipse
 *  Text editor / IDE: Eclipse
 *  Hours to complete assignment (optional):
 ******************************************************************************/


/******************************************************************************
 *  Explain briefly how you implemented the board data type.
 *****************************************************************************/
I used a 2d array (even though it's slower) to represent the tiles. To get the 
hamming sum, I made a method called xyto1D that finds the hypothetical 1 dimensional 
index of a tile in order to tell if they are out of order. For the manhattan sum 
I initialized it as an instance variable at -5, and checked if it was -5 when 
calling the manhattan method, and if so, computes it. Otherwise it simply returns 
the cached sum. To compute the sum, it goes through each tile and (as long as it's 
not in its place or the empty space) computes the absolute value of the distance 
between the tile and its goal position, and then adds that to the total. To check 
if the board is the goal board I made a method that creates the goal board and a 
method that compares every tile of two sets of tiles. The isGoal method compares 
every tile of the board to the goal board. To check if the board is solvable, it 
first counts the number of inversions by iterating through each tile and comparing 
it to each subsequent tile. Then the method checks whether the board has even or 
odd dimensions. If even, it adds the inversion count to the row number containing 
the blank space. If that sum is odd, then the puzzle is solvable. If the board has 
odd dimensions it checks to see if the inversion count is even or odd. If the count 
is even, then the puzzle is solvable. To find the neighbor boards it first finds the 
location of the blank space. Then, for each direction from the blank space it checks 
to see if there is a neighboring space. If so, it adds a new board with the blank 
space and the neighboring space swapped to a stack.




/******************************************************************************
 *  Explain briefly how you represented a search node
 *  (board + number of moves + previous search node).
 *****************************************************************************/
My SearchNode class has 4 instance variables: a Board called node, an int called 
moves, a SearchNode called previous, and an int called priority. All of these 
variables are set by arguments given to the constructor except priority, which 
is initialized to -1. The priority method computes the priority by adding moves 
to the board's manhattan sum if priority is -1. Otherwise, it returns the cached 
priority. The compareTo method simply compares the priority's of the SearchNode's.






/******************************************************************************
 *  Explain briefly how you detected unsolvable puzzles. What is the
 *  order of growth of the runtime of your isSolvable() method?
 *****************************************************************************/
I described how I detected them in the first question because I didn't read ahead. 
the order of growth is ~N^4.







/******************************************************************************
 *  For each of the following instances, give the minimal number of
 *  moves to reach the goal state. Also give the amount of time
 *  it takes the A* heuristic with the Hamming and Manhattan
 *  priority functions to find the solution. If it can't find the
 *  solution in a reasonable amount of time (say, 5 minutes) or memory,
 *  indicate that instead. Note that you may be able to solve
 *  puzzlexx.txt even if you can't solve puzzleyy.txt even if xx > yy.
 *****************************************************************************/


                  number of          seconds
     instance       moves      Hamming     Manhattan
   ------------  ----------   ----------   ----------
   puzzle28.txt  29          1964701607 ns 97285838 ns
   puzzle30.txt  31          4813670174 ns 154900301 ns
   puzzle32.txt  33          out of memory 1173745459 ns
   puzzle34.txt  35          out of memory 424373811 ns
   puzzle36.txt  37          out of memory 4944681471 ns
   puzzle38.txt  39          out of memory 4272086339 ns
   puzzle40.txt  41          out of memory 1409212690 ns
   puzzle42.txt  43          out of memory 11684115465 ns



/******************************************************************************
 *  If you wanted to solve random 4-by-4 or 5-by-5 puzzles, which
 *  would you prefer:  a faster computer (say, 2x as fast), more memory
 *  (say 2x as much), a better priority queue (say, 2x as fast),
 *  or a better priority function (say, one on the order of improvement
 *  from Hamming to Manhattan)? Why?
 *****************************************************************************/
priority function: because using the hamming function, my computer ran out of 
memory long before it did with the manhattan function, and it took my computer 
almost 5 minutes to drun out of memory, so even if I did have more memory, it would 
still take a long time.





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
  I had trouble with the isSolvable method, but my main problem was with debugging 
  the neighbors method, and the solution ended up being much simpler than I expected. 
  I think the reason my program runs out of memory when using the hamming function 
  is that how it checks to see if the current board is the goal board is by creating 
  the goal board and comparing the two. This means it creates a new goal board for every 
  board it checks. I'm still not sure how to get around that within the limitations of 
  the API, however. I know that it would only theoretically have to make the goal board 
  once, but because the isGoal method is in the board class, I can't cache it, because 
  the method can only use the objects within the board class.



 /******************************************************************************
  *  List any other comments here. Feel free to provide any feedback
  *  on how much you learned from doing the assignment, and whether
  *  you enjoyed doing it.
  *****************************************************************************/
