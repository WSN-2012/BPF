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

import se.kth.ssvl.tslab.wsn.general.servlib.naming.EndpointIDPattern;

/**
 * Event class for route delete events
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public class RouteDelEvent extends BundleEvent {
	public RouteDelEvent(final EndpointIDPattern dest) {
		super(event_type_t.ROUTE_DEL);
		dest_ = dest;
	}

	/**
	 * The destination eid to be removed
	 */
	private EndpointIDPattern dest_;

	/**
	 * Getter for the destination eid to be removed
	 * 
	 * @return the dest_
	 */
	public EndpointIDPattern dest() {
		return dest_;
	}

	/**
	 * Setter for the destination eid to be removed
	 * 
	 * @param dest
	 *            the dest_ to set
	 */
	public void set_dest(EndpointIDPattern dest) {
		dest_ = dest;
	}
};
