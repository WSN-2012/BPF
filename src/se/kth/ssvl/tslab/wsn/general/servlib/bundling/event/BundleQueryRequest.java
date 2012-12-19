package se.kth.ssvl.tslab.wsn.general.servlib.bundling.event;

/**
 * Event class for querying Bundle information
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public class BundleQueryRequest extends BundleEvent {
	public BundleQueryRequest() {
		super(event_type_t.BUNDLE_QUERY);
		// should be processed only by the daemon
		daemon_only_ = true;

	}
};