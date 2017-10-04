package autocomplete;

import java.util.Comparator;

import edu.princeton.cs.algs4.StdOut;

public class Term implements Comparable<Term> {
	private String query;
	private long weight;
	
	public Term(String query, long weight) {
		if (query == null) { throw new java.lang.NullPointerException(); }
		if (weight < 0) { throw new java.lang.IllegalArgumentException(); }
		this.query = query;
		this.weight = weight;
	}
	
	public String toString() {
		return (Long.toString(weight) + "\t" + query);
	}
	
	public int compareTo(Term that) {
		return this.query.compareTo(that.query);
	}
	
	public static Comparator<Term> byReverseWeightOrder() {
		return new byReverseWeightOrder();
	}
	
	private static class byReverseWeightOrder implements Comparator<Term> {
		public int compare(Term a, Term b) {
			if (a.weight > b.weight) 
				return -1;
			if (a.weight < b.weight)
				return 1;
			else 
				return 0;
		}
	}
	
	public static Comparator<Term> byPrefixOrder(int r) {
		if (r < 0) { throw new java.lang.IllegalArgumentException(); }
		return new byPrefixOrder(r);
	}
	
	private static class byPrefixOrder implements Comparator<Term> {
		private int r;
		public byPrefixOrder(int r) {
			this.r = r;
		}
		// returns 
		public int compare(Term a, Term b) {
			// following two lines of code adapted from Stephen C on Stackoverflow URL: http://stackoverflow.com/questions/1583940/up-to-first-n-characters
			String archar = a.query.substring(0, Math.min(a.query.length(), r));
			String brchar = b.query.substring(0, Math.min(b.query.length(), r));
			return archar.compareTo(brchar);
		}

	}
	public static void main(String[] args) {
		Term myterm1 = new Term("hello", 5);
		StdOut.println(myterm1.toString());
		Term myterm2 = new Term("goodbye", 10);
		StdOut.println(myterm2.toString());
		StdOut.println(myterm1.compareTo(myterm2));
		Comparator<Term> reverse = byReverseWeightOrder();
		StdOut.println(reverse.compare(myterm1, myterm2));
		Comparator<Term> prefix = byPrefixOrder(6);
		StdOut.println(prefix.compare(myterm1, myterm2));
		
		
	}
}