package se.kth.ssvl.tslab.wsn.general.servlib.routing.routers;

import se.kth.ssvl.tslab.wsn.general.servlib.bundling.bundles.Bundle;
import se.kth.ssvl.tslab.wsn.general.servlib.bundling.event.ContactUpEvent;
import se.kth.ssvl.tslab.wsn.general.servlib.reg.Registration;

public class EpidemicBundleRouter extends TableBasedRouter {

	@Override
	public Registration getRegistration() {
		return null;
	}

	protected void handle_contact_up(ContactUpEvent event) {
		
	}
	
	
	public void deliver_bundle(Bundle bundle) {
		
	}
}
