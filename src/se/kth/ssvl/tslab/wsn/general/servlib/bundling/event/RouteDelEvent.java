package se.kth.ssvl.tslab.wsn.general.servlib.bundling.event;

import se.kth.ssvl.tslab.wsn.general.servlib.naming.endpoint.EndpointIDPattern;

/**
 * Event class for route delete events
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public class RouteDelEvent extends BundleEvent {
	public RouteDelEvent(final EndpointIDPattern dest) {
		super(event_type_t.ROUTE_DEL);
		dest_ = dest;
	}

	/**
	 * The destination eid to be removed
	 */
	private EndpointIDPattern dest_;

	/**
	 * Getter for the destination eid to be removed
	 * 
	 * @return the dest_
	 */
	public EndpointIDPattern dest() {
		return dest_;
	}

	/**
	 * Setter for the destination eid to be removed
	 * 
	 * @param dest
	 *            the dest_ to set
	 */
	public void set_dest(EndpointIDPattern dest) {
		dest_ = dest;
	}
};
