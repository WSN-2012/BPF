/*
 * Copyright 2012 KTH
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */

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
