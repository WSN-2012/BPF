package se.kth.ssvl.tslab.wsn.general.servlib.bundling.event;

import se.kth.ssvl.tslab.wsn.general.servlib.conv_layers.ConvergenceLayer;

/**
 * BundleEvent to update CLA's parameter.
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public class CLAParamsSetEvent extends BundleEvent {
	/**
	 * main constructor
	 * 
	 * @param cla
	 * @param name
	 */
	public CLAParamsSetEvent(ConvergenceLayer cla, String name) {
		super(event_type_t.CLA_PARAMS_SET);
		cla_ = cla;
		name_ = name;

	}

	/**
	 * CL that changed
	 */
	private ConvergenceLayer cla_;

	/**
	 * Name of CL
	 */
	private String name_;

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
	 * Getter for the Name of CL
	 * 
	 * @return the name_
	 */
	public String name() {
		return name_;
	}

	/**
	 * Setter for the Name of CL
	 * 
	 * @param name
	 *            the name_ to set
	 */
	public void set_name(String name) {
		name_ = name;
	}
};