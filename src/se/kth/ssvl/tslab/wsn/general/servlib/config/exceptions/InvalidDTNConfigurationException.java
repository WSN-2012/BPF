package se.kth.ssvl.tslab.wsn.general.servlib.config.exceptions;

/**
 * The exception represents the DTNConfiguration file is wrongly configured.
 * This normally is threw when parsing the DTNConfiguration file in the
 * DTNConfiguration Parser
 */
public class InvalidDTNConfigurationException extends ConfigurationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6918489246720371568L;

	public InvalidDTNConfigurationException(String message) {
		super(message);
	}
}
