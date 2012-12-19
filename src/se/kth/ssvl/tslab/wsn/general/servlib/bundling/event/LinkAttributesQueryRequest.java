package se.kth.ssvl.tslab.wsn.general.servlib.bundling.event;

import se.kth.ssvl.tslab.wsn.general.servlib.contacts.attributes.AttributeNameVector;
import se.kth.ssvl.tslab.wsn.general.servlib.contacts.links.Link;

/**
 * Event for Link Attribute Query Request
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public class LinkAttributesQueryRequest extends CLAQueryReport {
	/**
	 * Constructor
	 * 
	 * @param query_id
	 * @param link
	 * @param attribute_names
	 */
	public LinkAttributesQueryRequest(String query_id, Link link,
			AttributeNameVector attribute_names) {
		super(event_type_t.CLA_LINK_ATTRIB_QUERY, query_id, true);
		link_ = link;
		attribute_names_ = attribute_names;

	}

	/**
	 * Link for which the given attributes are requested.
	 */
	private Link link_;

	/**
	 * Link attributes requested by name.
	 */
	private AttributeNameVector attribute_names_;

	/**
	 * Getter for the Link for which the given attributes are requested.
	 * 
	 * @return the link_
	 */
	public Link link() {
		return link_;
	}

	/**
	 * Setter for the Link for which the given attributes are requested.
	 * 
	 * @param link
	 *            the link_ to set
	 */
	public void set_link(Link link) {
		link_ = link;
	}

	/**
	 * Getter for the Link attributes requested by name.
	 * 
	 * @return the attribute_names_
	 */
	public AttributeNameVector attribute_names() {
		return attribute_names_;
	}

	/**
	 * Setter for the Link attributes requested by name.
	 * 
	 * @param attributeNames
	 *            the attribute_names_ to set
	 */
	public void set_attribute_names(AttributeNameVector attributeNames) {
		attribute_names_ = attributeNames;
	}
};