package se.kth.ssvl.tslab.wsn.general.servlib.bundling.event;

/**
 * An event response to ContactQuery Event
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public class ContactReportEvent extends BundleEvent {

	/**
	 * Constructor
	 */
	public ContactReportEvent() {
		super(event_type_t.CONTACT_REPORT);
	}
};
