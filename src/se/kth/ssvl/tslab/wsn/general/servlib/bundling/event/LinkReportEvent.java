package se.kth.ssvl.tslab.wsn.general.servlib.bundling.event;

/**
 * Link Report Event
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public class LinkReportEvent extends BundleEvent {
	/**
	 * main constructor
	 */
	public LinkReportEvent() {

		super(event_type_t.LINK_REPORT);
	}
};