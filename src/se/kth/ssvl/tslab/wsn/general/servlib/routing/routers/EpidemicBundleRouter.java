package se.kth.ssvl.tslab.wsn.general.servlib.routing.routers;

import java.util.Date;

import se.kth.ssvl.tslab.wsn.general.bpf.BPF;
import se.kth.ssvl.tslab.wsn.general.servlib.bundling.ForwardingInfo;
import se.kth.ssvl.tslab.wsn.general.servlib.bundling.bundles.Bundle;
import se.kth.ssvl.tslab.wsn.general.servlib.bundling.bundles.BundleDaemon;
import se.kth.ssvl.tslab.wsn.general.servlib.bundling.bundles.Bundle.priority_values_t;
import se.kth.ssvl.tslab.wsn.general.servlib.bundling.bundles.BundlePayload.location_t;
import se.kth.ssvl.tslab.wsn.general.servlib.bundling.custody.CustodyTimerSpec;
import se.kth.ssvl.tslab.wsn.general.servlib.bundling.event.ContactUpEvent;
import se.kth.ssvl.tslab.wsn.general.servlib.contacts.links.Link;
import se.kth.ssvl.tslab.wsn.general.servlib.naming.endpoint.EndpointID;
import se.kth.ssvl.tslab.wsn.general.servlib.reg.Registration;
import se.kth.ssvl.tslab.wsn.general.servlib.storage.BundleStore;

public class EpidemicBundleRouter extends TableBasedRouter {

	private final static String TAG = "EpidemicBundleRouter";

	@Override
	public Registration getRegistration() {
		return null;
	}

	protected void handle_contact_up(ContactUpEvent event) {
		super.handle_contact_up(event);
		Link link = event.contact().link();
		sendList(link);
	}

	public void sendList(Link link) {

		String list[] = BundleStore.getInstance().getHashList();
		if(list == null){ //do we have anything to send?
			return;
		}
		
		// "check if the link is not open
		if (!link.isopen()) {
			BPF.getInstance()
					.getBPFLogger()
					.warning(
							TAG,
							"Trying to send list but link " + link.name()
									+ " is not open!");
			return;
		}

		// "check if the link queue doesn't have space
		if (!link.queue_has_space()) {
			BPF.getInstance()
					.getBPFLogger()
					.warning(
							TAG,
							"Trying to send list but link " + link.name()
									+ " does not have space in queue!");
			return;
		}

		// create bundle containing list
		Bundle bundle = new Bundle(location_t.MEMORY); //TODO: verify that MEMORY type does not cause problems
		bundle.set_dest(new EndpointID(link.remote_eid() + "/epidemic"));
		bundle.set_source(new EndpointID(BundleDaemon.getInstance().local_eid().str() + "/epidemic"));
		bundle.set_prevhop(BundleDaemon.getInstance().local_eid());
		bundle.set_custodian(EndpointID.NULL_EID());
		bundle.set_replyto(new EndpointID(BundleDaemon.getInstance().local_eid().str() + "/epidemic"));
		bundle.set_singleton_dest(true);
		bundle.set_expiration(60);
		bundle.set_priority(priority_values_t.COS_EXPEDITED);

		//format the payload -> [hash1]-[hash2]-[hash3]...
		String payload = "";
		for(int i = 0; i < list.length; i++){
			payload += list[i];
			if(i != list.length - 1)	payload += "-";
		}
		
		bundle.payload().set_data(payload.getBytes());

		ForwardingInfo info = new ForwardingInfo(ForwardingInfo.state_t.NONE,
				ForwardingInfo.action_t.FORWARD_ACTION, link.name_str(),
				0xffffffff, link.remote_eid(),
				CustodyTimerSpec.getDefaultInstance());

		// send bundle
		actions_.queue_bundle(bundle, link, info.action(), info.custody_spec());
	}

	public void deliver_bundle(Bundle bundle) {

	}
}
