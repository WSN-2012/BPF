package se.kth.ssvl.tslab.wsn.general.servlib.bundling.event;

/**
 * Class for EIDReachable Report Event
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public class EIDReachableReportEvent extends CLAQueryReport {
	/**
	 * main constructor
	 * 
	 * @param query_id
	 * @param is_reachable
	 */
	public EIDReachableReportEvent(String query_id, boolean is_reachable) {
		super(event_type_t.CLA_EID_REACHABLE_REPORT, query_id);
		is_reachable_ = is_reachable;

	}

	/**
	 * Flag indicating if the queried endpoint is reachable via the given
	 * interface
	 */
	private boolean is_reachable_;

	/**
	 * Getter for the Flag indicating if the queried endpoint is reachable via
	 * the given interface
	 * 
	 * @return the is_reachable_
	 */
	public boolean is_reachable() {
		return is_reachable_;
	}

	/**
	 * Setter for the Flag indicating if the queried endpoint is reachable via
	 * the given interface
	 * 
	 * @param isReachable
	 *            the is_reachable_ to set
	 */
	public void set_is_reachable(boolean isReachable) {
		is_reachable_ = isReachable;
	}
};
