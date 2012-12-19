package se.kth.ssvl.tslab.wsn.general.servlib.bundling.event;

import se.kth.ssvl.tslab.wsn.general.servlib.contacts.attributes.AttributeVector;
import se.kth.ssvl.tslab.wsn.general.servlib.contacts.links.Link;

/**
 * Event class for reconfiguring an existing link.
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public class LinkReconfigureRequest extends BundleEvent {
	/**
	 * main constructor
	 * 
	 * @param link
	 * @param parameters
	 */
	public LinkReconfigureRequest(Link link, AttributeVector parameters)

	{
		super(event_type_t.LINK_RECONFIGURE);
		link_ = link;
		parameters_ = parameters;

		daemon_only_ = true;
	}

	/**
	 * The link to be changed
	 */
	private Link link_;

	/**
	 * Set of key, value pairs
	 */
	private AttributeVector parameters_;

	/**
	 * Getter for the link to be changed
	 * 
	 * @return the link_
	 */
	public Link link() {
		return link_;
	}

	/**
	 * Setter for the link to be changed
	 * 
	 * @param link
	 *            the link_ to set
	 */
	public void set_link(Link link) {
		link_ = link;
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
