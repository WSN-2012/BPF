
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
