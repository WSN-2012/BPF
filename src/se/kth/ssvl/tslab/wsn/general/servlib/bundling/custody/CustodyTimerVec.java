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

package se.kth.ssvl.tslab.wsn.general.servlib.bundling.custody;

import java.io.Serializable;

import se.kth.ssvl.tslab.wsn.general.systemlib.util.List;

/**
 * Class for a list of custody timers
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public class CustodyTimerVec extends List<CustodyTimer> implements Serializable {

	/**
	 * Serial UID to support Java Serializable
	 */
	private static final long serialVersionUID = 3869708062408698968L;

};