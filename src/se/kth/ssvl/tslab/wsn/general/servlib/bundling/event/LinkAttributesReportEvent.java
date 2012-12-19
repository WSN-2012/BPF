package se.kth.ssvl.tslab.wsn.general.servlib.bundling.event;

import se.kth.ssvl.tslab.wsn.general.servlib.contacts.attributes.AttributeVector;

/**
 * Link Attributes Report Class
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public class LinkAttributesReportEvent extends CLAQueryReport {
	public LinkAttributesReportEvent(String query_id, AttributeVector attributes) {

		super(event_type_t.CLA_LINK_ATTRIB_QUERY, query_id);
		attributes_ = attributes;
	}

	/**
	 * Link attribute values given by name.
	 */
	private AttributeVector attributes_;

	/**
	 * Getter for the Link attribute values given by name.
	 * 
	 * @return the attributes_
	 */
	public AttributeVector attributes() {
		return attributes_;
	}

	/**
	 * Setter for the Link attribute values given by name.
	 * 
	 * @param attributes
	 *            the attributes_ to set
	 */
	public void set_attributes(AttributeVector attributes) {
		attributes_ = attributes;
	}
};