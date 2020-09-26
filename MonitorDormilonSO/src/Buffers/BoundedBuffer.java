package Buffers;

/**
 * 
 * @author Douglas Lopez, Christian Cobo, Duvan Garcia
 *
 * @param <E>
 */
public class BoundedBuffer<E> {
	
	private static final int BUFFER_SIZE=3;
	
	private int count, in, out;
	
	private E[] buffer;
	
	public BoundedBuffer() {
		// TODO Auto-generated constructor stub
		count = 0;
		in = 0;
		out = 0;
		
		buffer = (E[]) new Object[BUFFER_SIZE];
	}
	
	public synchronized void insert(E item) {
		while(count == BUFFER_SIZE) {
			try {
				wait();				
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
		
		buffer[in] = item;
		in = (in + 1) % BUFFER_SIZE;
		count++;
		
		notify();
	}
	
	public synchronized E remove() {
		return null;
	}
	
}
