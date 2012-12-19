package se.kth.ssvl.tslab.wsn.general.servlib.config.exceptions;

/**
 * The DTNConfigurationException for using inside the DTNConfiguration package
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public class ConfigurationException extends Exception {

	/**
	 * Java Serial version UID for Serializable
	 */
	private static final long serialVersionUID = 8329956192337811316L;

	/**
	 * Constructor with message
	 */
	public ConfigurationException(String message) {
		super(message);
	}

	/**
	 * Default Constructor
	 */
	public ConfigurationException() {

	}

}
