package se.kth.ssvl.tslab.wsn.general.servlib.bundling.event;

import se.kth.ssvl.tslab.wsn.general.servlib.contacts.attributes.AttributeVector;
import se.kth.ssvl.tslab.wsn.general.servlib.conv_layers.ConvergenceLayer;

/**
 * Event class for DP-originated CLA parameter change requests.
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public class CLASetParamsRequest extends BundleEvent {
	/**
	 * main constructor
	 * 
	 * @param cla
	 * @param parameters
	 */
	public CLASetParamsRequest(ConvergenceLayer cla, AttributeVector parameters) {
		super(event_type_t.CLA_SET_PARAMS);
		parameters_ = parameters;
		cla_ = cla;
		// should be processed only by the daemon
		daemon_only_ = true;

	}

	/**
	 * CL to change
	 */
	private ConvergenceLayer cla_;

	/**
	 * Set of key, value pairs
	 */
	private AttributeVector parameters_;

	/**
	 * Getter for the CL
	 * 
	 * @return the cla_
	 */
	public ConvergenceLayer cla() {
		return cla_;
	}

	/**
	 * Setter for the CL
	 * 
	 * @param cla
	 *            the cla_ to set
	 */
	public void set_cla(ConvergenceLayer cla) {
		cla_ = cla;
	}

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