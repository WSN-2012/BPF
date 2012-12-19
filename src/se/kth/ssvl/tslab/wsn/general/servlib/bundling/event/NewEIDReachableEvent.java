package se.kth.ssvl.tslab.wsn.general.servlib.bundling.event;

import se.kth.ssvl.tslab.wsn.general.servlib.contacts.interfaces.Interface;

/**
 * Event class for discovery of a new EID.
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public class NewEIDReachableEvent extends BundleEvent {
	public NewEIDReachableEvent(Interface iface, String endpoint) {

		super(event_type_t.CLA_EID_REACHABLE);
		iface_ = iface;
		endpoint_ = endpoint;

	}

	// /< The interface the peer was discovered on
	private Interface iface_;

	// /> The EID of the discovered peer
	private String endpoint_;

	/**
	 * @return the iface_
	 */
	public Interface iface() {
		return iface_;
	}

	/**
	 * @param iface
	 *            the iface_ to set
	 */
	public void set_iface(Interface iface) {
		iface_ = iface;
	}

	/**
	 * @return the endpoint_
	 */
	public String endpoint() {
		return endpoint_;
	}

	/**
	 * @param endpoint
	 *            the endpoint_ to set
	 */
	public void set_endpoint(String endpoint) {
		endpoint_ = endpoint;
	}
};
