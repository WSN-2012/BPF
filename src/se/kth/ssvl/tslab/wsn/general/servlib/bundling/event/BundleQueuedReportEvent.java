package se.kth.ssvl.tslab.wsn.general.servlib.bundling.event;

/**
 * BundleEvent report class to response for the BundleQueuedQueryRequest
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public class BundleQueuedReportEvent extends CLAQueryReport {
	/**
	 * main Constructor
	 * 
	 * @param query_id
	 * @param is_queued
	 */
	public BundleQueuedReportEvent(String query_id, boolean is_queued) {
		super(event_type_t.CLA_BUNDLE_QUEUED_REPORT, query_id);
		is_queued_ = is_queued;

	}

	/**
	 * Flag indicating whether the specific bundle was queued on the given link;
	 */
	private boolean is_queued_;

	/**
	 * Accessor for the flag indicating whether the specific bundle was queued
	 * on the given link;
	 * 
	 * @return the is_queued_
	 */
	public boolean is_queued() {
		return is_queued_;
	}

	/**
	 * Setter for the flag indicating whether the specific bundle was queued on
	 * the given link;
	 * 
	 * @param isQueued
	 *            the is_queued_ to set
	 */
	public void set_is_queued(boolean is_queued) {
		is_queued_ = is_queued;
	}
};