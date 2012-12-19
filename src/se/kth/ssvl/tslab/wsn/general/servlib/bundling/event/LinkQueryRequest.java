package se.kth.ssvl.tslab.wsn.general.servlib.bundling.event;

/**
 * Event classes for link queries and response
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)s
 */
public class LinkQueryRequest extends BundleEvent {
	public LinkQueryRequest() {
		super(event_type_t.LINK_QUERY);
		// should be processed only by the daemon
		daemon_only_ = true;

	}
};