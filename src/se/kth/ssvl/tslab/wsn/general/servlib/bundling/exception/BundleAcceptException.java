package se.kth.ssvl.tslab.wsn.general.servlib.bundling.exception;

import se.kth.ssvl.tslab.wsn.general.servlib.bundling.bundles.BundleProtocol;

/**
 * Event Acception for testing purpose
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public class BundleAcceptException extends Exception {

	/**
	 * constructor
	 * 
	 * @param reason
	 */
	public BundleAcceptException(BundleProtocol.status_report_reason_t reason) {
		reason_ = reason;
	}

	/**
	 * Serializable UID to support Java Serializable
	 */
	private static final long serialVersionUID = -2164795958129422712L;
	/**
	 * internal reason report
	 */
	private BundleProtocol.status_report_reason_t reason_;

	/**
	 * Getter for the internal reason report
	 * 
	 * @return
	 */
	public BundleProtocol.status_report_reason_t get_reason() {
		return reason_;
	}

}
