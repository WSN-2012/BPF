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

package se.kth.ssvl.tslab.wsn.general.servlib.bundling.event;

/**
 * Base event class for querying and reporting to CL
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */

public class CLAQueryReport extends BundleEvent {
	/**
	 * query unique Identifier String
	 */
	private String query_id_;

	/**
	 * Basic Constructor
	 */
	public CLAQueryReport(event_type_t type, String query_id)

	{
		super(type);
		query_id_ = query_id;
		daemon_only_ = false;

	}

	/**
	 * Constructor specifying also the daemon only
	 */
	public CLAQueryReport(event_type_t type, String query_id,
			boolean daemon_only)

	{
		super(type);
		query_id_ = query_id;
		daemon_only_ = daemon_only;

	}

	/**
	 * Getter for the query unique Identifier String
	 * 
	 * @return the query_id_
	 */
	public String query_id() {
		return query_id_;
	}

	/**
	 * Setter for the query unique Identifier String
	 * 
	 * @param query_id
	 *            the query_id_ to set
	 */
	public void set_query_id(String query_id) {
		query_id_ = query_id;
	}

}
