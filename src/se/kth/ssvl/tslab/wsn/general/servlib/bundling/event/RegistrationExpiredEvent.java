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

import se.kth.ssvl.tslab.wsn.general.servlib.reg.Registration;

/**
 * Registration Expiration Event
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 * 
 */
public class RegistrationExpiredEvent extends BundleEvent {
	public RegistrationExpiredEvent(Registration registration) {
		super(event_type_t.REGISTRATION_EXPIRED);
		registration_ = registration;

	}

	/**
	 * The expired Registration
	 */
	private Registration registration_;

	/**
	 * Getter for the expired Registration
	 * 
	 * @return the registration_
	 */
	public Registration registration() {
		return registration_;
	}

	/**
	 * Setter for the expired Registration
	 * 
	 * @param registration
	 *            the registration_ to set
	 */
	public void set_registration(Registration registration) {
		registration_ = registration;
	}
};
