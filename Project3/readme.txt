/******************************************************************************
 *  Name: Kyle Drum
 *
 *  Operating system: Mac OS X
 *  Compiler: Eclipse
 *  Text editor / IDE: Eclipse
 *  Hours to complete assignment (optional):
 ******************************************************************************/


/******************************************************************************
 *  Describe how your firstIndexOf() method in BinarySearchDeluxe.java
 *  finds the first index of a key that equals the search key.
 *****************************************************************************/
It makes int variables labeled lo, hi, and first. lo starts at 0, hi starts at 
a.length - 1 and first (which will be used to store the first index, if one is 
found) starts at -5 (because if it does find an index, it will never be -5). while 
lo <= hi, it creates a mid variable that is halfway between lo and hi, and uses the 
comparator passed to it to compare the key and a[mid]. if key < a[mid], then it sets 
hi = mid - 1. if key > a[mid], then it sets lo = mid + 1. else (if the key = a[mid]) 
then it has found a match, but not necessarily the first one. so it stores the index 
of the match in first, and does the same thing that it would do if key < a[mid],because 
the first (if there is an earlier one) will be less than the current mid index. it 
repeats that process (saving a new lowest index to first if it finds one) until lo 
and hi converge and the loop ends. then, if first != -5 (if it has been replaced 
by a new first index) it returns first. otherwise it returns -1 which means no match.

/******************************************************************************
 *  What is the order of growth of the number of compares (in the
 *  worst case) that each of the operations in the Autocomplete
 *  data type make, as a function of the number of terms N and the
 *  number of matching terms M?
 *
 *  Recall that with order-of-growth notation, you should discard
 *  leading coefficients and lower order terms, e.g., M^2 + M log N.
 *****************************************************************************/

constructor:
NlogN
allMatches():
M+logN+MlogM
numberOfMatches():
logN



/******************************************************************************
 *  Known bugs / limitations.
 *****************************************************************************/




/******************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and exercises, but do
 *  include any help from people (including classmates and friends) and
 *  attribute them by name.
 *****************************************************************************/
Stephen C on StackExchange provided a line of code to get the first N characters 
of a string, even if the string has less than N characters (it just returns 
however many characters it can) I put a note in front of my implementation of the 
code in Term.java

/******************************************************************************
 *  Describe any serious problems you encountered.
 *****************************************************************************/




/******************************************************************************
 *  List any other comments here. Feel free to provide any feedback
 *  on how much you learned from doing the assignment, and whether
 *  you enjoyed doing it.
 *****************************************************************************/
I feel like I've been learning a lot about both programming in java and the 
algorithms and data structures being used in the assignments. 