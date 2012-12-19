package se.kth.ssvl.tslab.wsn.general.servlib.bundling.event;

/**
 * Event classes for static route queries and responses
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public class RouteQueryRequest extends BundleEvent {
	/**
	 * main constructor
	 */
	public RouteQueryRequest() {
		super(event_type_t.ROUTE_QUERY);
		// should be processed only by the daemon
		daemon_only_ = true;
	}
};
