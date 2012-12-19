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

import se.kth.ssvl.tslab.wsn.general.servlib.contacts.Contact;

/**
 * Event class for contact down events.
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public class ContactDownEvent extends ContactEvent {

	/**
	 * main constructor
	 * 
	 * @param contact
	 * @param reason
	 */
	public ContactDownEvent(final Contact contact, reason_t reason) {

		super(event_type_t.CONTACT_DOWN, reason);
		contact_ = contact;
	}

	/**
	 * The contact that is up
	 */
	private Contact contact_;

	/**
	 * Getter for the contact that is up
	 * 
	 * @return
	 */
	public Contact contact() {
		return contact_;
	}

	/**
	 * Setter for the contact that is up
	 * 
	 * @param contact
	 */
	public void set_contact(Contact contact) {
		contact_ = contact;
	}
};
