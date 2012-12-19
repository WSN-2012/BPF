package se.kth.ssvl.tslab.wsn.general.servlib.routing.routers;

import se.kth.ssvl.tslab.wsn.general.servlib.bundling.bundles.BundleActions;
import se.kth.ssvl.tslab.wsn.general.servlib.bundling.bundles.BundleList;
import se.kth.ssvl.tslab.wsn.general.servlib.reg.Registration;

/**
 * This is a non-abstract version of TableBasedRouter.
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public class StaticBundleRouter extends TableBasedRouter {

	public StaticBundleRouter(BundleActions actions, BundleList pendingBundles, BundleList custodyBundles) {
		super(actions, pendingBundles, custodyBundles);
	}
	
	@Override
	public Registration getRegistration() {
		// Static bundle router doesn't use registrations
		return null;
	}

}
