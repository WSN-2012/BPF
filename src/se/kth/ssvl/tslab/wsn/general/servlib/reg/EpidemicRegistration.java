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

package se.kth.ssvl.tslab.wsn.general.servlib.reg;

import se.kth.ssvl.tslab.wsn.general.bpf.BPF;
import se.kth.ssvl.tslab.wsn.general.servlib.bundling.bundles.Bundle;
import se.kth.ssvl.tslab.wsn.general.servlib.bundling.bundles.BundleDaemon;
import se.kth.ssvl.tslab.wsn.general.servlib.bundling.bundles.BundleProtocol.status_report_reason_t;
import se.kth.ssvl.tslab.wsn.general.servlib.bundling.event.BundleDeleteRequest;
import se.kth.ssvl.tslab.wsn.general.servlib.bundling.event.BundleDeliveredEvent;
import se.kth.ssvl.tslab.wsn.general.servlib.contacts.links.Link;
import se.kth.ssvl.tslab.wsn.general.servlib.naming.endpoint.EndpointIDPattern;
import se.kth.ssvl.tslab.wsn.general.servlib.routing.routers.EpidemicBundleRouter;

public class EpidemicRegistration extends Registration {
 
	private static final long serialVersionUID = 7357669816785009652L;
	private static final String TAG = "EpidemicRegistration";
	private EpidemicBundleRouter router_;

	public EpidemicRegistration(EpidemicBundleRouter router_) {
		
		super(EPIDEMIC_REGID, new EndpointIDPattern(BundleDaemon.getInstance().local_eid().str()
				+ "/epidemic"), Registration.failure_action_t.DEFER, 0, 0, "");
		
		this.router_ = router_;
		set_active(true);
	}

	@Override
	public void deliver_bundle(Bundle bundle, Link link) {
		BPF.getInstance().getBPFLogger().info(TAG,
				"Epidemic bundle from " + bundle.source());
		router_.deliver_bundle(bundle, link);
		BPF.getInstance().getBPFLogger().debug(TAG,
				"deliver_bundle done, going to post delivered event and deleterequest event");
		BundleDaemon.getInstance().post(
				new BundleDeliveredEvent(bundle, this));
		BundleDaemon.getInstance().post(
				new BundleDeleteRequest(bundle,
						status_report_reason_t.REASON_NO_ADDTL_INFO));
	}

}
