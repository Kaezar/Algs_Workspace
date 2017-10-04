package autocomplete;

import java.util.Comparator;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MergeX;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Autocomplete {
	private Term[] terms;
	
	
	
	public Autocomplete(Term[] terms) {
		if (terms == null) { throw new java.lang.NullPointerException(); }
		for (int i = 0; i < terms.length; i++) {
			if (terms[i] == null) { throw new java.lang.NullPointerException(); }
		}
		//StdOut.println(terms.length);
		this.terms = new Term[terms.length];
		for(int i = 0; i <terms.length; i++) {
		this.terms[i] = terms[i];
		}
		MergeX.sort(this.terms);
	}
	
	public Term[] allMatches(String prefix) {
		if (prefix == null) { throw new java.lang.NullPointerException(); }
		Comparator<Term> prefixComp = Term.byPrefixOrder(prefix.length());
		Comparator<Term> reverse = Term.byReverseWeightOrder();
		Term prefixTerm = new Term(prefix, 1);
		int first = BinarySearchDeluxe.firstIndexOf(terms, prefixTerm, prefixComp);
		int last = BinarySearchDeluxe.lastIndexOf(terms, prefixTerm, prefixComp);
		if (first < 0 || last < 0) {
			Term[] matches = new Term[0];
			return matches;
		}
		int numberOfMatches = last-first+1;
		Term[] matches = new Term[numberOfMatches];
		int count = 0;
		//StdOut.println(terms.length);
		//StdOut.println(first);
		//StdOut.println(last);
		for (int i = first; i <= last; i++) {
			matches[count] = terms[i];
			count++;
		}
		MergeX.sort(matches, reverse);
		return matches;
	}
	public int numberOfMatches(String prefix) {
		if (prefix == null) { throw new java.lang.NullPointerException(); }
		Comparator<Term> prefixComp = Term.byPrefixOrder(prefix.length());
		Term prefixTerm = new Term(prefix, 1);
		int first = BinarySearchDeluxe.firstIndexOf(terms, prefixTerm, prefixComp);
		int last = BinarySearchDeluxe.lastIndexOf(terms, prefixTerm, prefixComp);
		int numberOfMatches = last-first;
		return numberOfMatches;
	}
	public static void main(String[] args) {
		 // read in the terms from a file
	    String filename = args[0];
	    In in = new In(filename);
	    int N = in.readInt();
	    //StdOut.println(N);
	    Term[] terms = new Term[N];
	    for (int i = 0; i < N; i++) {
	        long weight = in.readLong();           // read the next weight
	        in.readChar();                         // scan past the tab
	        String query = in.readLine();          // read the next query
	        terms[i] = new Term(query, weight);    // construct the term
	    }

	    // read in queries from standard input and print out the top k matching terms
	    int k = Integer.parseInt(args[1]);
	    Autocomplete autocomplete = new Autocomplete(terms);
	    while (StdIn.hasNextLine()) {
	        String prefix = StdIn.readLine();
	        //StdOut.println("Calling allMatches...");
	        Term[] results = autocomplete.allMatches(prefix);
	        //StdOut.println("finished allMatches");
	        for (int i = 0; i < Math.min(k, results.length); i++)
	            StdOut.println(results[i]);
	    }
	}
}