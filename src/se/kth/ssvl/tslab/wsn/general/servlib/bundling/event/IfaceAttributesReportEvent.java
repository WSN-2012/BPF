package se.kth.ssvl.tslab.wsn.general.servlib.bundling.event;

import se.kth.ssvl.tslab.wsn.general.servlib.contacts.attributes.AttributeVector;

/**
 * Class for reporting Interface attributes
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public class IfaceAttributesReportEvent extends CLAQueryReport {
	public IfaceAttributesReportEvent(String query_id,
			AttributeVector attributes) {
		super(event_type_t.CLA_IFACE_ATTRIB_QUERY, query_id);
		attributes_ = attributes;

	}

	/**
	 * Interface attribute values by name.
	 */
	private AttributeVector attributes_;

	/**
	 * Getter for the Interface attribute values by name.
	 * 
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
};