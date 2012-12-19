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

package se.kth.ssvl.tslab.wsn.general.bpf;

import se.kth.ssvl.tslab.wsn.general.servlib.bundling.bundles.Bundle;

public interface BPFActionReceiver {

	/**
	 * This method is called when the framework has a notification. This is usually used for telling the user about events that are occuring.
	 * @param header A string describing the header of the notificaiton
	 * @param description A string for describing the event further
	 */
	public abstract void notify(String header, String description);
	
	/**
	 * This method is called from the framework when there was a bundle received for the local device. 
	 * @param bundle The bundle which was received
	 */
	public abstract void bundleReceived(Bundle bundle);
		
}
