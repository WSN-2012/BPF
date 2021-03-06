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


package se.kth.ssvl.tslab.wsn.general.servlib.bundling.bundles;


/**
 * Class to represent a temporary bundle -- i.e. one that exists only in memory
 * and never goes to the persistent store.
 * 
 * @author Sharjeel Ahmed (sharjeel@kth.se)
 */
public class TempBundle extends Bundle {

	/**
	 * SerialVersionID to Support Serializable.
	 */
	private static final long serialVersionUID = 3950490472303810726L;

	/**
	 * Constructor which forces the payload location to memory.
	 */
	public TempBundle() {
		super(BundlePayload.location_t.MEMORY);
	}

}
