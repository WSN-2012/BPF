package se.kth.ssvl.tslab.wsn.general.servlib.bundling.event;

import se.kth.ssvl.tslab.wsn.general.servlib.routing.routerentry.RouteEntry;

/**
 * Route Add Event
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public class RouteAddEvent extends BundleEvent {
	public RouteAddEvent(RouteEntry entry) {
		super(event_type_t.ROUTE_ADD);
		entry_ = entry;
	}

	/**
	 * The route table entry to be added
	 */
	private RouteEntry entry_;

	/**
	 * Getter for the route table entry to be added
	 * 
	 * @return the entry_
	 */
	public RouteEntry entry() {
		return entry_;
	}

	/**
	 * Setter for the route table entry to be added
	 * 
	 * @param entry
	 *            the entry_ to set
	 */
	public void set_entry(RouteEntry entry) {
		entry_ = entry;
	}
}
