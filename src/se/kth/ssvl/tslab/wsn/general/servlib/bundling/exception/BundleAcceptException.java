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

package se.kth.ssvl.tslab.wsn.general.servlib.bundling.exception;

import se.kth.ssvl.tslab.wsn.general.servlib.bundling.bundles.BundleProtocol;

/**
 * Event Acception for testing purpose
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public class BundleAcceptException extends Exception {

	/**
	 * constructor
	 * 
	 * @param reason
	 */
	public BundleAcceptException(BundleProtocol.status_report_reason_t reason) {
		reason_ = reason;
	}

	/**
	 * Serializable UID to support Java Serializable
	 */
	private static final long serialVersionUID = -2164795958129422712L;
	/**
	 * internal reason report
	 */
	private BundleProtocol.status_report_reason_t reason_;

	/**
	 * Getter for the internal reason report
	 * 
	 * @return
	 */
	public BundleProtocol.status_report_reason_t get_reason() {
		return reason_;
	}

}
