
package se.kth.ssvl.tslab.wsn.general.servlib.conv_layers;

import se.kth.ssvl.tslab.wsn.general.systemlib.util.List;

/**
 * "Use a singleton vector to enumerate the convergence layers that are
 * currently implemented" [DTN2].
 * 
 * @author Maria Jose Peroza Marval (mjpm@kth.se)
 */

public class CLVector extends List<ConvergenceLayer> {

	/**
	 * Unique identifier according to Java Serializable specification
	 */
	private static final long serialVersionUID = 2206276110015844640L;

	/**
	 * Singleton pattern for CLVector
	 */
	private static CLVector instance_ = null;

	public static CLVector getInstance() {
		if (instance_ == null) {
			instance_ = new CLVector();
		}
		return instance_;
	}

	public CLVector() {

		super();
	}

}
