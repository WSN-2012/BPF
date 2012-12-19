
package se.kth.ssvl.tslab.wsn.general.servlib.naming;

import java.util.HashMap;

/**
 * The table of registered Schemes. This table is implemented as Singleton
 */
public class SchemeTable {

	/**
	 * Singleton instance Implementation of the SchemeTable
	 */
	private static SchemeTable instance_ = null;

	/**
	 * Private constructor for Singleton Implementation of the SchemeTable
	 */
	private SchemeTable() {
		DTNScheme dtnScheme = DTNScheme.getInstance();
		table_ = new HashMap<String, Scheme>();
		register_scheme("dtn", dtnScheme);

		// Exists only to defeat instantiation
	}

	/**
	 * Singleton Implementation Getter function
	 * 
	 * @return an singleton instance of SchemeTable
	 */
	public static SchemeTable getInstance() {
		if (instance_ == null) {
			instance_ = new SchemeTable();
		}
		return instance_;
	}

	// End Singleton Implementation of the SchemeTable

	/**
	 * Register the given scheme.
	 */
	public void register_scheme(String scheme_str, Scheme scheme) {
		table_.put(scheme_str, scheme);
	}

	/**
	 * Find the appropriate Scheme instance based on the URI scheme of the
	 * endpoint id scheme.
	 * 
	 * @return the instance if it exists or NULL if there's no match
	 */
	public Scheme lookup(String scheme_str) {

		return table_.get(scheme_str);
	}

	/**
	 * Internal hashmap to hold the Scheme
	 */
	protected HashMap<String, Scheme> table_;

}
