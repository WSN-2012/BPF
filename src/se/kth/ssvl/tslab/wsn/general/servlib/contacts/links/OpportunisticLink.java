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

package se.kth.ssvl.tslab.wsn.general.servlib.contacts.links;

import se.kth.ssvl.tslab.wsn.general.servlib.conv_layers.ConvergenceLayer;

/**
 * "Abstraction for a OPPORTUNISTIC link. It has to be opened everytime one
 * wants to use it. It has by definition only -one contact- that is associated
 * with the current opportunity. The difference between opportunistic link and
 * ondemand link is that the ondemand link does not have a queue of its own"
 * [DTN2].
 * 
 * @author Maria Jose Peroza Marval (mjpm@kth.se)
 */

public class OpportunisticLink extends Link {

	/**
	 * Unique identifier according to Java Serializable specification
	 */
	private static final long serialVersionUID = -4403911477784247936L;

	/**
	 * Constructor
	 */
	public OpportunisticLink(String name, ConvergenceLayer cl, String nexthop) {

		super(name, Link.link_type_t.OPPORTUNISTIC, cl, nexthop);
	}

}
