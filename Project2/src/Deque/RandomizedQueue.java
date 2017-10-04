package Deque;
import java.util.Iterator;


import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	
	private Item[] a;
	private int N;
	
	public RandomizedQueue() {
		a = (Item[]) new Object[2];
		N = 0;
	}
	
	public boolean isEmpty() { return N == 0; }
	
	public int size() { return N; }
	
	private int length() {
		int s = a.length;
		return s;
	}
	
	private void resize(int capacity) {
        assert capacity >= N;

        // textbook implementation
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }
	
	public void enqueue(Item item) {
		if (item == null) { 
			throw new java.lang.NullPointerException("cannot add a null item"); }
        if (N == a.length) resize(2*a.length);    // double size of array if necessary
        a[N++] = item;                            // add item
    }
	
	public Item dequeue() {
		if (isEmpty()) throw new java.util.NoSuchElementException("Stack underflow");
		int x = StdRandom.uniform(N);
		Item item = a[x];
		a[x] = a[N-1];
		a[N-1] = null;
		N--;
		if (N > 0 && N == a.length/4) resize(a.length/2);
		return item;
	}
	public Item sample() {
		if (isEmpty()) throw new java.util.NoSuchElementException("Stack underflow");
	
		int x = StdRandom.uniform(N);
		return a[x];
	}
	
	public Iterator<Item> iterator() {
		return new RandomizedQueueIterator();
	}
	
	private class RandomizedQueueIterator implements Iterator<Item> {
		
		private int i;
		private Item[] b;
		
		public RandomizedQueueIterator() {
			this.b = (Item[]) new Object[N];
			for (int i = 0; i < N; i++) {
	            b[i] = a[i];
	        }
			StdRandom.shuffle(b);
			this.i = N-1;
			
		}
		public boolean hasNext() {
			return i >= 0;
		}
		public void remove() {
			throw new java.lang.UnsupportedOperationException();
		}
		public Item next() {
			if (!hasNext()) throw new java.util.NoSuchElementException();
			else {
            return b[i--];
			}
		}
	}
	
	public static void main(String[] args) {
		RandomizedQueue<String> random = new RandomizedQueue<String>();
		
		random.enqueue("a");
		random.enqueue("b");
		random.enqueue("c");
		random.enqueue("d");
	
		for (String s : random) {
			for (String t : random) {
			StdOut.println(s+t);
			}
		}
		int size = random.size();
		for (int i = 0; i < size; i++) {
			StdOut.println(random.dequeue());
		}
		
		random.enqueue("e");
		random.enqueue("f");
		random.enqueue("g");
		random.enqueue("h");
	   
		for (String s : random) {
			for (String t : random) {
			StdOut.println(s+t);
			}
		}
	}

	
}