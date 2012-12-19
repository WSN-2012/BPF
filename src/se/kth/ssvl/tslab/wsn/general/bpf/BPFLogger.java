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

package se.kth.ssvl.tslab.wsn.general.bpf;

public interface BPFLogger {

	/**
	 * Method to log messages classed as info
	 * 
	 * @param TAG
	 *            The TAG to recognize the log message
	 * @param message
	 *            The message to print
	 * @return A string representing the TAG and the message. Note: Used by
	 *         asserts
	 */
	public abstract String error(String TAG, String message);

	/**
	 * Method to log messages classed as debug
	 * 
	 * @param TAG
	 *            The TAG to recognize the log message
	 * @param message
	 *            The message to print
	 * @return A string representing the TAG and the message. Note: Used by
	 *         asserts
	 */
	public abstract String debug(String TAG, String message);

	/**
	 * Method to log messages classed as info
	 * 
	 * @param TAG
	 *            The TAG to recognize the log message
	 * @param message
	 *            The message to print
	 * @return A string representing the TAG and the message. Note: Used by
	 *         asserts
	 */
	public abstract String info(String TAG, String message);

	/**
	 * Method to log messages classed as warning
	 * 
	 * @param TAG
	 *            The TAG to recognize the log message
	 * @param message
	 *            The message to print
	 * @return A string representing the TAG and the message. Note: Used by
	 *         asserts
	 */
	public abstract String warning(String TAG, String message);
}
