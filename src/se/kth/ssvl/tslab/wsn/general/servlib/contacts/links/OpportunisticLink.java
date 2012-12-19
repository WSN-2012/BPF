
package se.kth.ssvl.tslab.wsn.general.servlib.contacts.links;

import se.kth.ssvl.tslab.wsn.general.servlib.conv_layers.ConvergenceLayer;

/**
 * "Abstraction for a OPPORTUNISTIC link. It has to be opened everytime one
 * wants to use it. It has by definition only -one contact- that is associated
 * with the current opportunity. The difference between opportunistic link and
 * ondemand link is that the ondemand link does not have a queue of its own"
 * [DTN2].
 * 
 * @author Maria Jose Peroza Marval (mjpm@kth.se)
 */

public class OpportunisticLink extends Link {

	/**
	 * Unique identifier according to Java Serializable specification
	 */
	private static final long serialVersionUID = -4403911477784247936L;

	/**
	 * Constructor
	 */
	public OpportunisticLink(String name, ConvergenceLayer cl, String nexthop) {

		super(name, Link.link_type_t.OPPORTUNISTIC, cl, nexthop);
	}

}
