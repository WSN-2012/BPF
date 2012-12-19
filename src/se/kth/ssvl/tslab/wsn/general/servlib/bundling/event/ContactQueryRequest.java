
package se.kth.ssvl.tslab.wsn.general.servlib.bundling.event;

/**
 * Event class to query Contact Event
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public class ContactQueryRequest extends BundleEvent {
	public ContactQueryRequest() {
		super(event_type_t.CONTACT_QUERY);
		daemon_only_ = true;

	}
};
