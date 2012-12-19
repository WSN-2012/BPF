package se.kth.ssvl.tslab.wsn.general.servlib.bundling.event;

import se.kth.ssvl.tslab.wsn.general.servlib.bundling.custody.CustodySignal;

/**
 * Event class for custody transfer signal arrivals.
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */

public class CustodySignalEvent extends BundleEvent {
	public CustodySignalEvent(CustodySignal.data_t data) {
		super(event_type_t.CUSTODY_SIGNAL);
		data_ = data;

	}

	/**
	 * The parsed data from the custody transfer signal
	 */
	private CustodySignal.data_t data_;

	/**
	 * Getter for the parsed data from the custody transfer signal
	 * 
	 * @return the data_
	 */
	public CustodySignal.data_t data() {
		return data_;
	}

	/**
	 * Setter for the parsed data from the custody transfer signal
	 * 
	 * @param data
	 *            the data_ to set
	 */
	public void set_data(CustodySignal.data_t data) {
		data_ = data;
	}
};
