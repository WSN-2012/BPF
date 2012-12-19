package se.kth.ssvl.tslab.wsn.general.servlib.bundling.custody;

import java.io.Serializable;

import se.kth.ssvl.tslab.wsn.general.bpf.BPF;
import se.kth.ssvl.tslab.wsn.general.servlib.bundling.bundles.Bundle;

/**
 * Class specify timeout according to parameters. The parameters include
 * expiration percentage, minimum, and maximum.
 * 
 * <br/>
 * "The current basic scheme calculates the timer as: timer = min((min_ + (lifetime_pct_ * bundle->lifetime_ / 100)), max_)"
 * [DTN2].
 * 
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public class CustodyTimerSpec implements Serializable {

	/**
	 * String TAG for using with Android Logging system
	 */
	private static String TAG = "CustodyTimerSpec";
	/**
	 * Serial UID to support Java Serializable
	 */
	private static final long serialVersionUID = 8680978172298149419L;
	/**
	 * default values
	 */
	private static CustodyTimerSpec defaults_;

	static {
		defaults_ = new CustodyTimerSpec(5, 25, 60);
	}

	/**
	 * Singleton interface for getting an instance
	 * 
	 * @return
	 */
	public static CustodyTimerSpec getDefaultInstance() {
		return defaults_;
	}

	/**
	 * Constructor
	 */
	public CustodyTimerSpec(int min, int lifetime_pct, int max) {
		min_ = min;
		lifetime_pct_ = lifetime_pct;
		max_ = max;
	}

	/**
	 * Calculate the timeout
	 */
	public final int calculate_timeout(final Bundle bundle) {
		int timeout = min_;
		timeout += (int) ((lifetime_pct_ * bundle.expiration() / 100.0));

		if (max_ != 0) {
			timeout = Math.min(timeout, max_);
		}

		BPF.getInstance().getBPFLogger().error(
				TAG,
				String.format("calculate_timeout: "
						+ "min %d, lifetime_pct %d,"
						+ "expiration %d, max %d: " + "timeout %d", min_,
						lifetime_pct_, bundle.expiration(), max_, timeout));
		return timeout;
	}

	/**
	 * minimum value of the timer in seconds
	 */
	private int min_;
	/**
	 * percentage of expiration time
	 */
	private int lifetime_pct_;

	/**
	 * maximum value of the timer in seconds
	 */
	private int max_;

	/**
	 * Getter for the minimum value of the timer in seconds
	 * 
	 * @return the min_
	 */
	public int min() {
		return min_;
	}

	/**
	 * Setter for the minimum value of the timer in seconds
	 * 
	 * @param min
	 *            the min_ to set
	 */
	public void set_min(int min) {
		min_ = min;
	}

	/**
	 * Getter for the percentage of expiration time
	 * 
	 * @return the lifetime_pct_
	 */
	public int lifetime_pct() {
		return lifetime_pct_;
	}

	/**
	 * Setter for the percentage of expiration time
	 * 
	 * @param lifetimePct
	 *            the lifetime_pct_ to set
	 */
	public void set_lifetime_pct(int lifetime_pct) {
		lifetime_pct_ = lifetime_pct;
	}

	/**
	 * Getter for the maximum value of the timer in seconds
	 * 
	 * @return the max_
	 */
	public int max() {
		return max_;
	}

	/**
	 * Setter for the maximum value of the timer in seconds
	 * 
	 * @param max
	 *            the max_ to set
	 */
	public void set_max(int max) {
		max_ = max;
	}
};