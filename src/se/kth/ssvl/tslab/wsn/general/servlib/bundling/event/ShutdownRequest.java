package se.kth.ssvl.tslab.wsn.general.servlib.bundling.event;

/**
 * Event for shutting down daemon
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public class ShutdownRequest extends BundleEvent {
	public ShutdownRequest() {
		super(event_type_t.DAEMON_SHUTDOWN);
		daemon_only_ = true;

	}
};