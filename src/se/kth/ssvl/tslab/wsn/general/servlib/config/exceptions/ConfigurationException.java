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

package se.kth.ssvl.tslab.wsn.general.servlib.config.exceptions;

/**
 * The DTNConfigurationException for using inside the DTNConfiguration package
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public class ConfigurationException extends Exception {

	/**
	 * Java Serial version UID for Serializable
	 */
	private static final long serialVersionUID = 8329956192337811316L;

	/**
	 * Constructor with message
	 */
	public ConfigurationException(String message) {
		super(message);
	}

	/**
	 * Default Constructor
	 */
	public ConfigurationException() {

	}

}
