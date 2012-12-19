package se.kth.ssvl.tslab.wsn.general.dtnapi.types;

/**
 * This class represents DTNEndpointID to be used by the Application
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public class DTNEndpointID {

	/**
	 * Empty Constructor
	 */
	public DTNEndpointID() {

	}

	/**
	 * Constructor with input URI
	 * 
	 * @param uri
	 *            input URI for this EndpointID
	 */
	public DTNEndpointID(String uri) {
		uri_ = uri;
	}

	/**
	 * Internal variable for holding URI
	 */
	private String uri_;

	/**
	 * @return the uri_
	 */
	public String uri() {
		return uri_;
	}

	/**
	 * @param uri
	 *            the uri_ to set
	 */
	public void set_uri(String uri) {
		uri_ = uri;
	}

	@Override
	public String toString() {
		return uri_;
	}
}
