package se.kth.ssvl.tslab.wsn.general.systemlib.thread;

import java.io.Serializable;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * MsgBlockingQueue class for Thread communication in Android DTN
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public class MsgBlockingQueue<E> extends ArrayBlockingQueue<E> implements
		Serializable {

	/**
	 * Constructor by taking capacity as input
	 * 
	 * @param capacity
	 */
	public MsgBlockingQueue(int capacity) {
		super(capacity);

	}

	/**
	 * Constructor by taking both capacity and whether the queue will be fair
	 * 
	 * @param capacity
	 * @param fair
	 */
	public MsgBlockingQueue(int capacity, boolean fair) {
		super(capacity, fair);

	}

	/**
	 * Serial version UID to support Java Serializable function
	 */
	private static final long serialVersionUID = -7139268179135110061L;

}
