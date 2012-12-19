package se.kth.ssvl.tslab.wsn.general.servlib.bundling.event;

import se.kth.ssvl.tslab.wsn.general.servlib.contacts.attributes.AttributeVector;
import se.kth.ssvl.tslab.wsn.general.servlib.contacts.links.Link;

/**
 * Event class for a change in link attributes.
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public class LinkAttributeChangedEvent extends ContactEvent {
	public LinkAttributeChangedEvent(Link link, AttributeVector attributes,
			reason_t reason) {
		super(event_type_t.LINK_ATTRIB_CHANGED, reason);
		link_ = link;
		attributes_ = attributes;
	}

	/**
	 * The link that changed
	 */
	private Link link_;

	/**
	 * Attributes that changed
	 */
	private AttributeVector attributes_;

	/**
	 * Getter for the link that changed
	 * 
	 * @return the link_
	 */
	public Link link() {
		return link_;
	}

	/**
	 * Setter for the link that changed
	 * 
	 * @param link
	 *            the link_ to set
	 */
	public void set_link(Link link) {
		link_ = link;
	}

	/**
	 * @return the attributes_
	 */
	public AttributeVector attributes() {
		return attributes_;
	}

	/**
	 * @param attributes
	 *            the attributes_ to set
	 */
	public void set_attributes(AttributeVector attributes) {
		attributes_ = attributes;
	}

	/**
	 * @return the attributes_
	 */
	public AttributeVector getAttributes_() {
		return attributes_;
	}

	/**
	 * @param attributes
	 *            the attributes_ to set
	 */
	public void setAttributes_(AttributeVector attributes) {
		attributes_ = attributes;
	}
};