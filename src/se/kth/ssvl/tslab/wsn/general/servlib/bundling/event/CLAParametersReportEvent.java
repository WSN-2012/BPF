package se.kth.ssvl.tslab.wsn.general.servlib.bundling.event;

import se.kth.ssvl.tslab.wsn.general.servlib.contacts.attributes.AttributeVector;

/**
 * Report BundleEvent in response to CLAParametersQueryRequest
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public class CLAParametersReportEvent extends CLAQueryReport {
	/**
	 * main constructor
	 * 
	 * @param query_id
	 *            unique identifier specified in the parameter query request
	 * @param parameters
	 *            the result parameters reported
	 */
	public CLAParametersReportEvent(String query_id, AttributeVector parameters) {
		super(event_type_t.CLA_PARAMS_REPORT, query_id);
		parameters_ = parameters;

	}

	/**
	 * Convergence layer parameter values by name.
	 */
	private AttributeVector parameters_;

	/**
	 * Getter for the Convergence layer parameter values by name.
	 * 
	 * @return the parameters_
	 */
	public AttributeVector parameters() {
		return parameters_;
	}

	/**
	 * Setter for the Convergence layer parameter values by name.
	 * 
	 * @param parameters
	 *            the parameters_ to set
	 */
	public void set_parameters(AttributeVector parameters) {
		parameters_ = parameters;
	}
};
