package se.kth.ssvl.tslab.wsn.general.servlib.bundling.event;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Class for specifying source of the Bundle Event
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public enum event_source_t {
	EVENTSRC_PEER("peer", 1), // /< a peer dtn forwarder
	EVENTSRC_APP("application", 2), // /< a local application
	EVENTSRC_STORE("dataStore", 3), // /< the data store
	EVENTSRC_ADMIN("admin", 4), // /< the admin logic
	EVENTSRC_FRAGMENTATION("fragmentation", 5), // /< the fragmentation engine
	EVENTSRC_ROUTER("router", 6) // /< the routing logic

	;

	private static final Map<event_source_t, String> captionLookup = new HashMap<event_source_t, String>();
	private static final Map<event_source_t, Integer> codeLookup = new HashMap<event_source_t, Integer>();

	static {
		for (event_source_t event : EnumSet.allOf(event_source_t.class)) {
			captionLookup.put(event, event.getCaption());
			codeLookup.put(event, event.getCode());
		}
	}

	private String caption;
	private int code = -1; // -1 = undefined

	private event_source_t(String caption) {
		this.caption = caption;
	}

	private event_source_t(String caption, int code) {
		this.caption = caption;
		this.code = code;
	}

	public String getCaption() {
		return caption;
	}

	// Keep the name similarity in DTN2
	public String event_to_str() {
		return caption;
	}

	public Integer getCode() {
		return code;
	}

	public static String get(event_source_t event) {
		return captionLookup.get(event);
	}

}
