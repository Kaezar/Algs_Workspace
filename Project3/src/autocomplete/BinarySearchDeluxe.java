package autocomplete;

import java.util.Comparator;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MergeX;
import edu.princeton.cs.algs4.StdOut;

public class BinarySearchDeluxe {
	
	// this class should not be instantiated
	private BinarySearchDeluxe() { }
	
	public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
		if (a == null || key == null || comparator == null) { throw new java.lang.NullPointerException(); }
		int lo = 0;
		int hi = a.length-1;
		int first = -5;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			// if key < a[mid]
			if (comparator.compare(key, a[mid]) < 0) hi = mid-1;
			// if key > a[mid]
			else if (comparator.compare(key, a[mid]) > 0) lo = mid+1;
			else {
				first = mid;
				hi = mid-1;
			}
				
			
		}
		if (first != -5) {
			return first;
			}
			else return -1;
		
	}
	public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
		if (a == null || key == null || comparator == null) { throw new java.lang.NullPointerException(); }
		int lo = 0;
		int hi = a.length-1;
		int last = -5;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			// if key < a[mid]
			if (comparator.compare(key, a[mid]) < 0) hi = mid-1;
			// if key > a[mid]
			else if (comparator.compare(key, a[mid]) > 0) lo = mid+1;
			else {
				last = mid;
				lo = mid+1;
			}
				
			
		}
		if (last != -5) {
		return last;
		}
		else return -1;
	}
	
	public static void main(String[] args) {
		if (args == null) { throw new java.lang.NullPointerException(); }
		 String filename = args[0];
		    In in = new In(filename);
		    int N = in.readInt();
		    Term[] terms = new Term[N];
		    for (int i = 0; i < N; i++) {
		        long weight = in.readLong();           // read the next weight
		        in.readChar();                         // scan past the tab
		        String query = in.readLine();          // read the next query
		        terms[i] = new Term(query, weight);    // construct the term
		    }
		    String searchString = args[1];
		    int searchStringLength = searchString.length();
		    Comparator<Term> prefix = Term.byPrefixOrder(searchStringLength);
		    MergeX.sort(terms);
		    for (int i = 0; i < terms.length; i++) {
		    	StdOut.println(terms[i].toString());
		    }
		    Term searchTerm = new Term(searchString, 10);
		    StdOut.println(firstIndexOf(terms, searchTerm, prefix));
		    StdOut.println("First term: " + terms[firstIndexOf(terms, searchTerm, prefix)].toString());
		    StdOut.println("Last term:  " + terms[lastIndexOf(terms, searchTerm, prefix)].toString());
	}
}