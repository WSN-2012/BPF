
package se.kth.ssvl.tslab.wsn.general.servlib.conv_layers;

import se.kth.ssvl.tslab.wsn.general.servlib.bundling.bundles.Bundle;
import se.kth.ssvl.tslab.wsn.general.systemlib.util.DataBitmap;

/**
 * "Class used to record bundles that are in the process of being received along
 * with their transmission state and relevant acknowledgement data"[DTN2].
 * 
 * @author Maria Jose Peroza Marval (mjpm@kth.se)
 */

public class IncomingBundle {

	/**
	 * Constructor
	 */
	public IncomingBundle(Bundle b) {
		bundle_ = b;
		total_length_ = 0;
		acked_length_ = 0;
		rcvd_data_ = new DataBitmap();
		// ack_data_ = new DataBitmap();
	}

	/**
	 * Incoming bundle instance
	 */
	private Bundle bundle_;

	/**
	 * Getter of the incoming bundle
	 */
	public Bundle bundle() {
		return bundle_;
	}

	/**
	 * Length of the incoming bundle
	 */
	private int total_length_;

	/**
	 * Getter and setter of the length of the incoming bundle
	 */
	public int total_length() {
		return total_length_;
	}

	public void set_total_length(int totalLength) {
		total_length_ = totalLength;
	}

	/**
	 * Length of the acked data
	 */
	private int acked_length_;

	/**
	 * Getter and setter of the acked data length
	 */
	public int acked_length() {
		return acked_length_;
	}

	public void set_acked_length(int ackedLength) {
		acked_length_ = ackedLength;
	}

	/**
	 * DataBitmap of the received data
	 */
	private DataBitmap rcvd_data_;

	/**
	 * Getter of the DataBitmap of the received data
	 */
	public DataBitmap rcvd_data() {

		return rcvd_data_;
	}

}
