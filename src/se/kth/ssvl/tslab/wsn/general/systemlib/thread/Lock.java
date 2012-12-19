package se.kth.ssvl.tslab.wsn.general.systemlib.thread;

import java.io.Serializable;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock class for handle concurrency in the Android DTN System. This Lock is
 * implemented by extending Android ReentrantLock. This is done instead of using
 * ReentrantLock directly to increase maintainability. For example, if someone
 * would like to change Lock implementation in the future, he can just change it
 * here without modifying all the classes.
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public class Lock extends ReentrantLock implements Serializable {

	/**
	 * Unique identifier according to Java Serializable specification
	 */
	private static final long serialVersionUID = 6472115469714829769L;

}
