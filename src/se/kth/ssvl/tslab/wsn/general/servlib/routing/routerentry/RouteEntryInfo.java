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

package se.kth.ssvl.tslab.wsn.general.servlib.routing.routerentry;

/**
 * Abstraction to put algorithm state in particular route entry
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public abstract class RouteEntryInfo {

	/**
	 * Dump information to StringBuffer. This is useful for debugging
	 * 
	 * @param buf
	 *            StrinBuffer
	 */
	public abstract void dump(StringBuffer buf);

	/**
	 * Dump statistics information to StringBuffer
	 * 
	 * @param buf
	 *            StrinBuffer
	 */
	public abstract void dump_stats(StringBuffer buf);
}
