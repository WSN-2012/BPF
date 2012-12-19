package se.kth.ssvl.tslab.wsn.general.servlib.reg;

import se.kth.ssvl.tslab.wsn.general.bpf.BPF;
import se.kth.ssvl.tslab.wsn.general.servlib.bundling.bundles.Bundle;
import se.kth.ssvl.tslab.wsn.general.servlib.bundling.bundles.BundleDaemon;
import se.kth.ssvl.tslab.wsn.general.servlib.bundling.bundles.BundlePayload.location_t;
import se.kth.ssvl.tslab.wsn.general.servlib.bundling.event.BundleDeliveredEvent;
import se.kth.ssvl.tslab.wsn.general.servlib.bundling.event.BundleReceivedEvent;
import se.kth.ssvl.tslab.wsn.general.servlib.bundling.event.event_source_t;
import se.kth.ssvl.tslab.wsn.general.servlib.contacts.links.Link;
import se.kth.ssvl.tslab.wsn.general.servlib.naming.endpoint.EndpointID;
import se.kth.ssvl.tslab.wsn.general.servlib.naming.endpoint.EndpointIDPattern;

/**
 * Internal registration for the dtnping application.
 * 
 * @author Sharjeel Ahmed (sharjeel@kth.se)
 */

public class PingRegistration extends Registration {

	/**
	 * SerialVersionID to Support Serializable.
	 */

	private static final long serialVersionUID = -5238042388837219144L;

	/**
	 * TAG for Android Logging
	 */

	private static String TAG = "PingRegistration";

	/**
	 * Constructor to initialize this class.
	 */

	public PingRegistration(final EndpointID eid) {

		super(PING_REGID, new EndpointIDPattern(eid),
				Registration.failure_action_t.DEFER, 0, 0, "");
		set_active(true);
	}

	/**
	 * Delivery bundle process forward the bundle to BundleDaemon
	 * 
	 * @param bundle
	 *            Bundle to process.
	 */

	@Override
	public void deliver_bundle(Bundle bundle, Link link) {

		int payload_len = bundle.payload().length();

		BPF.getInstance().getBPFLogger().debug(
				TAG,
				String.format("%d byte ping from %s", payload_len, bundle
						.source().str()));

		Bundle reply = new Bundle(location_t.MEMORY);

		reply.source().assign(endpoint_);
		reply.dest().assign(bundle.source());
		reply.replyto().assign(EndpointID.NULL_EID());
		reply.custodian().assign(EndpointID.NULL_EID());
		reply.set_expiration(bundle.expiration());

		reply.payload().set_length(payload_len);
		reply.payload().write_data(bundle.payload(), 0, payload_len, 0);

		BundleDaemon.getInstance().post_at_head(
				new BundleDeliveredEvent(bundle, this));
		BundleDaemon.getInstance().post_at_head(
				new BundleReceivedEvent(reply, event_source_t.EVENTSRC_ADMIN));

	}
}
