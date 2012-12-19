package se.kth.ssvl.tslab.wsn.general.servlib.bundling.event;

import se.kth.ssvl.tslab.wsn.general.servlib.contacts.attributes.AttributeNameVector;
import se.kth.ssvl.tslab.wsn.general.servlib.conv_layers.ConvergenceLayer;

/**
 * BundleEvent to query parameters of the Convergence Layer
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public class CLAParametersQueryRequest extends CLAQueryReport {

	/**
	 * Constructor
	 * 
	 * @param query_id
	 *            unique identifier
	 * @param cla
	 *            the Convergence Layer in question
	 * @param parameter_names
	 */
	public CLAParametersQueryRequest(final String query_id,
			ConvergenceLayer cla, AttributeNameVector parameter_names) {

		super(event_type_t.CLA_PARAMS_QUERY, query_id, true);
		cla_ = cla;
		parameter_names_ = parameter_names;

	}

	/**
	 * Convergence layer for which the given parameters are requested.
	 */
	private ConvergenceLayer cla_;

	/**
	 * Convergence layer parameters requested by name.
	 */
	private AttributeNameVector parameter_names_;

	/**
	 * Getter for the Convergence Layer in question
	 * 
	 * @return the cla_
	 */
	public ConvergenceLayer cla() {
		return cla_;
	}

	/**
	 * Setter for the Convergence Layer in question
	 * 
	 * @param cla
	 *            the cla_ to set
	 */
	public void set_cla(ConvergenceLayer cla) {
		cla_ = cla;
	}

	/**
	 * Getter for the Convergence layer parameters requested by name.
	 * 
	 * @return the parameter_names_
	 */
	public AttributeNameVector parameter_names() {
		return parameter_names_;
	}

	/**
	 * Setter for the Convergence layer parameters requested by name.
	 * 
	 * @param parameterNames
	 *            the parameter_names_ to set
	 */
	public void set_parameter_names(AttributeNameVector parameterNames) {
		parameter_names_ = parameterNames;
	}
};
