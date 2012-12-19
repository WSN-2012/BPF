package se.kth.ssvl.tslab.wsn.general.servlib.bundling.event;

/**
 * Route Report Event
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public class RouteReportEvent extends BundleEvent {
	public RouteReportEvent() {
		super(event_type_t.ROUTE_REPORT);
	}
};