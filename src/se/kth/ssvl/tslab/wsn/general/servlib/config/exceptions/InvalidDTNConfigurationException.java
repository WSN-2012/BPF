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
package se.kth.ssvl.tslab.wsn.general.servlib.config.exceptions;

/**
 * The exception represents the DTNConfiguration file is wrongly configured.
 * This normally is threw when parsing the DTNConfiguration file in the
 * DTNConfiguration Parser
 */
public class InvalidDTNConfigurationException extends ConfigurationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6918489246720371568L;

	public InvalidDTNConfigurationException(String message) {
		super(message);
	}
}
