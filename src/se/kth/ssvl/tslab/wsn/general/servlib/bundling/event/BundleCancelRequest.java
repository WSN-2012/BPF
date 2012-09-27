/*
 *	  This file is part of the Bytewalla Project
 *    More information can be found at "http://www.tslab.ssvl.kth.se/csd/projects/092106/".
 *    
 *    Copyright 2009 Telecommunication Systems Laboratory (TSLab), Royal Institute of Technology, Sweden.
 *    
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 * 
 *        http://www.apache.org/licenses/LICENSE-2.0
 * 
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *    
 */
package se.kth.ssvl.tslab.wsn.general.servlib.bundling.event;

import se.kth.ssvl.tslab.wsn.general.servlib.bundling.Bundle;
import se.kth.ssvl.tslab.wsn.general.servlib.contacts.Link;

/**
 * Event class for canceling a bundle transmission
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public class BundleCancelRequest extends BundleEvent {
	/**
	 * Constructor
	 */
	public BundleCancelRequest() {
		super(event_type_t.BUNDLE_CANCEL);
		// should be processed only by the daemon
		daemon_only_ = true;

	}

	/**
	 * Constructor
	 */
	public BundleCancelRequest(Bundle bundle, Link link) {
		super(event_type_t.BUNDLE_CANCEL);

		bundle_ = bundle;
		link_ = link;
		// should be processed only by the daemon
		daemon_only_ = true;
	}

	/**
	 * Bundle to be cancelled
	 */
	private Bundle bundle_;

	/**
	 * Link where the bundle is going to
	 */
	private Link link_;

	/**
	 * Accessor for the Bundle to be cancelled
	 * 
	 * @return the bundle_
	 */
	public Bundle bundle() {
		return bundle_;
	}

	/**
	 * Setter for the Bundle to be cancelled
	 * 
	 * @param bundle
	 *            the bundle_ to set
	 */
	public void set_bundle(Bundle bundle) {
		bundle_ = bundle;
	}

	/**
	 * Accessor for the Link where the bundle is going to
	 * 
	 * @return the link_
	 */
	public Link link() {
		return link_;
	}

	/**
	 * Setter for the Link where the bundle is going to
	 * 
	 * @param link
	 *            the link_ to set
	 */
	public void set_link(Link link) {
		link_ = link;
	}
};
