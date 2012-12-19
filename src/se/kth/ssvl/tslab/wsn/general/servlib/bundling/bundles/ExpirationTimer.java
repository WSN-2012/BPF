package se.kth.ssvl.tslab.wsn.general.servlib.bundling.bundles;

import java.io.Serializable;
import java.util.Date;

import se.kth.ssvl.tslab.wsn.general.servlib.bundling.event.BundleExpiredEvent;
import se.kth.ssvl.tslab.wsn.general.systemlib.thread.VirtualTimerTask;

/**
 * This class handles time expiration of bundle
 * 
 * @author Sharjeel Ahmed (sharjeel@kth.se)
 */

public class ExpirationTimer extends VirtualTimerTask implements Serializable {

	/**
	 * SerialVersionID to Support Serializable.
	 */

	private static final long serialVersionUID = 7128087288863023333L;

	/**
	 * Constructor
	 * 
	 * @param bundle
	 *            Set bundle to monitor its expiration time.
	 */
	public ExpirationTimer(Bundle bundle) {
		bundleref_ = bundle;
	}

	/**
	 * On timeout forward the bundle to BundleDaemon to handle it.
	 * 
	 * @param now
	 *            Current time
	 */
	@Override
	protected void timeout(Date now) {
		bundleref_.set_expiration_timer(null);

		BundleDaemon.getInstance().post_at_head(
				new BundleExpiredEvent(bundleref_));

	}

	private Bundle bundleref_;
}
