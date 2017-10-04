package Deque;

import java.util.Iterator;

import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {
	private Node first;
	private Node last;
	private int N;
	
	public class Node {
		Item item;
		Node next;
		Node prev;
	}
	
	public Deque() {
		this.first = null;
		this.last = null;
		this.N = 0;
	}
	
	public boolean isEmpty() {
		return (first == null || last == null);
	}
	
	public int size() {
		return (N);
	}
	
	public void addFirst(Item item) {
		if (item == null) {
			throw new java.lang.NullPointerException();
		}
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.prev = null;
			if (isEmpty()) { last = first; }
			else {
		first.next = oldfirst;
		oldfirst.prev = first;
			}
		N++;
	}
	public void addLast(Item item) {
		if (item == null) { throw new java.lang.NullPointerException(); }
		Node oldlast = last;
		last = new Node();
		last.item = item;
		if(isEmpty()) { first = last; }
		else {
			last.prev = oldlast;
			oldlast.next = last;
		}
		N++;
	}
	public Item removeFirst() {
		if(isEmpty()){ throw new java.util.NoSuchElementException(); }
		Item item = first.item;
		first = first.next;
		if(isEmpty()) {
			last = null;
		}
		N--;
		return item;
	}
	public Item removeLast() {
		if(isEmpty()){ throw new java.util.NoSuchElementException(); }
		Item item = last.item;
		last = last.prev;
		if(isEmpty()) { first = null; }
		N--;
		return item;
	}
	
	public Iterator<Item> iterator() {
		return new ListIterator();
	}
	private class ListIterator implements Iterator<Item> {
		private Node current = first;
		public boolean hasNext() { return (current != null); }
		public void remove () { throw new java.lang.UnsupportedOperationException(); }
		public Item next() {
			if (hasNext()) {
			Item item = current.item;
			current = current.next;
			return item;
			}
			else { throw new java.util.NoSuchElementException(); }
		}
	}
	public static void main(String[] args) {
		Deque<String> deck = new Deque<String>();
		deck.addFirst("s");
		deck.addFirst("t");
		deck.addLast("o");
		deck.addLast("p");
		for (String s : deck)
			StdOut.print(s);
		StdOut.println();
		int size = deck.size();
		for (int i = 0; i < size; i++) {
			deck.removeLast();
		}
		deck.addFirst("k");
		deck.addLast("y");
		deck.addFirst("l");
		deck.addLast("e");
		for (String s : deck)
			StdOut.print(s);
		StdOut.println();
		size = deck.size();
		for (int i = 0; i < size; i++) {
			deck.removeLast();
		}
		deck.addFirst("k");
		deck.removeLast();
		deck.addLast("k");
		deck.addLast("y");
		deck.addLast("l");
		deck.addLast("e");
		for (String s: deck) {
			for(String t : deck) {
				StdOut.println(s + t);
			}
		}
		
	}
}