
package se.kth.ssvl.tslab.wsn.general.servlib.conv_layers.connection;

/**
 * "Contact initiation header. Sent at the beginning of a contact" [DTN2].
 * 
 * @author Maria Jose Peroza Marval (mjpm@kth.se)
 */

public class ContactHeader {

	int magic; // /< magic word (MAGIC: "dtn!")
	byte version; // /< cl protocol version
	byte flags; // /< connection flags (see above)
	short keepalive_interval; // /< seconds between keepalive packets

	/**
	 * Default Constructor.
	 */
	public ContactHeader() {
	}

}
