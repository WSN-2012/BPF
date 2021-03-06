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

package se.kth.ssvl.tslab.wsn.general.servlib.bundling.event;

/**
 * Event class for querying Bundle information
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public class BundleQueryRequest extends BundleEvent {
	public BundleQueryRequest() {
		super(event_type_t.BUNDLE_QUERY);
		// should be processed only by the daemon
		daemon_only_ = true;

	}
};