package se.kth.ssvl.tslab.wsn.general.servlib.bundling.event;

import se.kth.ssvl.tslab.wsn.general.servlib.contacts.attributes.AttributeVector;

/**
 * Set Link Default Event
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public class SetLinkDefaultsRequest extends BundleEvent {
	/**
	 * main constructor
	 * 
	 * @param parameters
	 */
	public SetLinkDefaultsRequest(AttributeVector parameters) {
		super(event_type_t.CLA_SET_LINK_DEFAULTS);
		// should be processed only by the daemon
		daemon_only_ = true;
		parameters_ = parameters;
	}

	/**
	 * Set of key, value pairs
	 */
	private AttributeVector parameters_;

	/**
	 * Getter for the Set of key, value pairs
	 * 
	 * @return the parameters_
	 */
	public AttributeVector parameters() {
		return parameters_;
	}

	/**
	 * Setter for the Set of key, value pairs
	 * 
	 * @param parameters
	 *            the parameters_ to set
	 */
	public void set_parameters(AttributeVector parameters) {
		parameters_ = parameters;
	}
};