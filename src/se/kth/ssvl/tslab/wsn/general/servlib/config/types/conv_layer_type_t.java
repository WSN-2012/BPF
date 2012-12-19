package se.kth.ssvl.tslab.wsn.general.servlib.config.types;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * The enum of Convergence Layer type
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public enum conv_layer_type_t {
	TCP("tcp");
	private static final Map<String, conv_layer_type_t> lookupCaption = new HashMap<String, conv_layer_type_t>();
	static {
		for (conv_layer_type_t s : EnumSet.allOf(conv_layer_type_t.class)) {

			lookupCaption.put(s.getCaption(), s);
		}
	}

	private String caption;

	private conv_layer_type_t(String caption) {

		this.caption = caption;
	}

	public String getCaption() {
		return caption;
	}
}
