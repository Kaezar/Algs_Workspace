/******************************************************************************
 *  Name: Kyle Drum
 *
 *  Operating system: Mac OS X
 *  Compiler: Eclipse
 *  Text editor / IDE: Eclipse
 *  Hours to complete assignment (optional):
 ******************************************************************************/



/******************************************************************************
 *  Explain briefly how you implemented the randomized queue and deque.
 *  Which data structure did you choose (array, linked list, etc.)
 *  and why?
 *****************************************************************************/
For deque I used a doubly linked list where each node had a reference to the next 
and previous node. I chose this structure because with a resizing array it would 
be difficult to add/remove values from the front of the array, and it would be 
impossible to do so without reassigning every other value in the array. For the 
randomized queue I used a resizing array because it would allow me to find a random 
item in the array in constant time, as well as easily shuffle the array when using 
the iterator. 

/******************************************************************************
 *  How much memory (in bytes) do your data types use to store N items
 *  in the worst case? Use the 64-bit memory cost model from Section
 *  1.4 of the textbook and use tilde notation to simplify your answer.
 *  Briefly justify your answers and show your work.
 *
 *  Do not include the memory for the items themselves (as this
 *  memory is allocated by the client and depends on the item type)
 *  or for any iterators, but do include the memory for the references
 *  to the items (in the underlying array or linked list).
 *****************************************************************************/

Randomized Queue:   ~  40 + 32N  bytes
40 (24 overhead, 8 for reference instance variable, 4 for int instance variable, 
4 for padding) + 32N (8 bytes of reference per object x 4 in the worst case where 
the array is 4x the number of items)
Deque:              ~ 40 + 48N  bytes
40 (16 for overhead, 8 each for first and last (reference) instance variables, 4 
for N (int) instance variable, and 4 for padding) + 48N (48 for each node (16 
overhead, 8 bytes each for item, next, and prev references, and 8 for extra overhead))



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
 Deleting my Deque java file by accident and having to redo it by memory.
 The iterator on randomized queue was iterating through null values because the 
 actual size of the array was larger than the number of values, so when it got 
 shuffled, it put null values in between the other values. I had to alter the 
 iterator to make the copy of the array (which it would then shuffle) an array 
 of size N (the number of items) instead of the same length as a.

/******************************************************************************
 *  List any other comments here. Feel free to provide any feedback
 *  on how much you learned from doing the assignment, and whether
 *  you enjoyed doing it.
 *****************************************************************************/