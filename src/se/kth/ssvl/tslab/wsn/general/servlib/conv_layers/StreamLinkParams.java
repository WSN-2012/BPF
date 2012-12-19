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


package se.kth.ssvl.tslab.wsn.general.servlib.conv_layers;

import se.kth.ssvl.tslab.wsn.general.servlib.conv_layers.connection.CLConnection;

/**
 * "Link parameters shared among all stream based convergence layers"[DTN2].
 * 
 * @author Maria Jose Peroza Marval (mjpm@kth.se)
 */

public class StreamLinkParams extends LinkParams {

	/**
	 * Unique identifier according to Java Serializable specification
	 */
	private static final long serialVersionUID = -8731290759608601649L;

	/**
	 * Seconds between keepalive packets
	 */
	private int keepalive_interval_;

	/**
	 * Getter and setter for keepalive interval
	 */
	public int keepalive_interval() {
		return keepalive_interval_;
	}

	public void set_keepalive_interval(int keepaliveInterval) {
		keepalive_interval_ = keepaliveInterval;
	}

	/**
	 * Maximum size of transmitted segments
	 */
	private int segment_length_;

	/**
	 * Getter and setter for the length of a segment
	 */
	public int segment_length() {
		return segment_length_;
	}

	public void set_segment_length(int segmentLength) {
		segment_length_ = segmentLength;
	}

	protected StreamLinkParams(boolean init_defaults) {

		super(init_defaults);
		// segment_ack_enabled_ = false;
		// negative_ack_enabled_ = false;
		// segment_ack_enabled_ = true;
		// negative_ack_enabled_ = true;
		keepalive_interval_ = 10;
		// keepalive_interval_ = 10000;

		segment_length_ = CLConnection.DEFAULT_BLOCK_BUFFER_SIZE;
	}

}
