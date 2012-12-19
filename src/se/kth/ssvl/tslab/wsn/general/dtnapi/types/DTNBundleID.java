package se.kth.ssvl.tslab.wsn.general.dtnapi.types;

import se.kth.ssvl.tslab.wsn.general.servlib.bundling.bundles.BundleTimestamp;

/**
 * The data structure to get result from the IBinder
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public class DTNBundleID {
	/**
	 * The source EndointID of this Bundle
	 */
	private DTNEndpointID source_;

	/**
	 * The Bundle creation Timestamp for this Bundle
	 */
	private BundleTimestamp creation_ts_;

	/**
	 * The fragmentation offset of this Bundle
	 */
	private int frag_offset_;

	/**
	 * The total application data unit length of this Bundle. This is used when
	 * the Bundle is a fragment.
	 */
	private int orig_length_;

	/**
	 * Accessor for the source EndointID of this Bundle
	 * 
	 * @return the source_
	 */
	public DTNEndpointID source() {
		return source_;
	}

	/**
	 * Setter for the source EndointID of this Bundle
	 * 
	 * @param source
	 *            the source_ to set
	 */
	public void set_source(DTNEndpointID source) {
		source_ = source;
	}

	/**
	 * Getter for the Bundle creation Timestamp for this Bundle
	 * 
	 * @return the creation_ts_
	 */
	public BundleTimestamp creation_ts() {
		return creation_ts_;
	}

	/**
	 * Setter for the Bundle creation Timestamp for this Bundle
	 * 
	 * @param creationTs
	 *            the creation_ts_ to set
	 */
	public void set_creation_ts(BundleTimestamp creation_ts) {
		creation_ts_ = creation_ts;
	}

	/**
	 * Getter for the fragmentation offset of this Bundle
	 * 
	 * @return the frag_offset_
	 */
	public int frag_offset() {
		return frag_offset_;
	}

	/**
	 * Setter for the fragmentation offset of this Bundle
	 * 
	 * @param fragOffset
	 *            the frag_offset_ to set
	 */
	public void set_frag_offset(int frag_offset) {
		frag_offset_ = frag_offset;
	}

	/**
	 * Getter for the total application data unit length of this Bundle. This is
	 * used when the Bundle is a fragment.
	 * 
	 * @return the orig_length_
	 */
	public int orig_length() {
		return orig_length_;
	}

	/**
	 * Setter for the total application data unit length of this Bundle. This is
	 * used when the Bundle is a fragment.
	 * 
	 * @param origLength
	 *            the orig_length_ to set
	 */
	public void set_orig_length(int orig_length) {
		orig_length_ = orig_length;
	}

}
