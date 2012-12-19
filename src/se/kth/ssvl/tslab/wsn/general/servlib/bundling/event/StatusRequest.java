package se.kth.ssvl.tslab.wsn.general.servlib.bundling.event;

/**
 * Event class for checking that the daemon is still running.
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public class StatusRequest extends BundleEvent {
	public StatusRequest() {
		super(event_type_t.DAEMON_STATUS);
		daemon_only_ = true;

	}
};