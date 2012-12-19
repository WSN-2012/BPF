
package se.kth.ssvl.tslab.wsn.general.servlib.bundling.event;

import java.util.Date;

import se.kth.ssvl.tslab.wsn.general.servlib.bundling.bundles.BundleDaemon.BundleEventPriorityComparator;
import se.kth.ssvl.tslab.wsn.general.systemlib.thread.MsgBlockingQueue;

/**
 * Bundle Event class used in DTN System for communicating between different
 * components.
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public class BundleEvent implements Comparable<BundleEvent> {
	/**
	 * The enum indicating the Event Type
	 */
	protected event_type_t type_;

	/**
	 * Flag showing whether this particular event should be processed by other
	 * components as well.
	 */
	protected boolean daemon_only_ = false;

	/**
	 * Priority value to sort BundleEvent to be run in the Bundle Daemon
	 */
	private int priority_;

	/**
	 * Notifier to be notified when the event is completed.
	 */
	public MsgBlockingQueue<Integer> processed_notifier_ = null;

	/**
	 * The time the event is posted to BundleDaemon's queue.
	 */
	protected Date posted_time_;

	/**
	 * Used for printing
	 */
	public final String type_str() {
		return type_.getCaption();
	}

	/**
	 * main Constructor
	 * 
	 * @param type
	 */
	public BundleEvent(event_type_t type) {
		type_ = type;
		daemon_only_ = false;
		processed_notifier_ = null;
		priority_ = 0;
	}

	/**
	 * Accessor for the enum indicating the Event Type
	 * 
	 * @return the type_
	 */
	public event_type_t type() {
		return type_;
	}

	/**
	 * Setter for the enum indicating the Event Type
	 * 
	 * @param type
	 *            the type_ to set
	 */
	public void set_type(event_type_t type) {
		type_ = type;
	}

	/**
	 * Accessor for the flag showing whether this particular event should be
	 * processed by other components as well.
	 * 
	 * @return the daemon_only_
	 */
	public boolean daemon_only() {
		return daemon_only_;
	}

	/**
	 * Setter for the flag showing whether this particular event should be
	 * processed by other components as well.
	 * 
	 * @param daemonOnly
	 *            the daemon_only_ to set
	 */
	public void set_daemon_only(boolean daemon_only) {
		daemon_only_ = daemon_only;
	}

	/**
	 * Accessor for the Notifier to be notified when the event is completed
	 * 
	 * @return the processed_notifier_
	 */
	public MsgBlockingQueue<Integer> processed_notifier() {
		return processed_notifier_;
	}

	/**
	 * Setter for the Notifier to be notified when the event is completed
	 * 
	 * @param processedNotifier
	 *            the processed_notifier_ to set
	 */
	public void set_processed_notifier(
			MsgBlockingQueue<Integer> processed_notifier) {
		processed_notifier_ = processed_notifier;
	}

	/**
	 * Accessor for the time the event is posted to BundleDaemon's queue.
	 * 
	 * @return the posted_time_
	 */
	public Date posted_time() {
		return posted_time_;
	}

	/**
	 * Setter for the time the event is posted to BundleDaemon's queue.
	 * 
	 * @param postedTime
	 *            the posted_time_ to set
	 */
	public void set_posted_time(Date postedTime) {
		posted_time_ = postedTime;
	}

	/**
	 * Accessor for the priority value to sort BundleEvent to be run in the
	 * Bundle Daemon
	 * 
	 * @return the priority
	 */
	public int priority() {
		return priority_;
	}

	/**
	 * Setter for the priority value to sort BundleEvent to be run in the Bundle
	 * Daemon
	 * 
	 * @param priority
	 *            the priority to set
	 */
	public void set_priority(int priority) {
		this.priority_ = priority;
	}

	public String toString() {
		return type_str() + " (@" + Integer.toHexString(super.hashCode()) + ") (prio: " + priority_ + ")";
	}

	@Override
	public int compareTo(BundleEvent y) {
		return BundleEventPriorityComparator.getInstance().compare(this, y);
	}
	
}