package se.kth.ssvl.tslab.wsn.general.servlib.routing.prophet.queuing;

import se.kth.ssvl.tslab.wsn.general.systemlib.util.Logger;
import se.kth.ssvl.tslab.wsn.general.servlib.bundling.Bundle;
import se.kth.ssvl.tslab.wsn.general.servlib.config.DTNConfiguration;
import se.kth.ssvl.tslab.wsn.general.servlib.routing.BundleRouter.Config;
import se.kth.ssvl.tslab.wsn.general.servlib.storage.BundleStore;
import se.kth.ssvl.tslab.wsn.general.servlib.storage.GlobalStorage;

public abstract class ProphetQueuing {

	/**
	 * Bundle Store
	 */
	private BundleStore bundleStore = BundleStore.getInstance();

	/**
	 * TAG for Android Logging
	 */
	private static String TAG = "Queuing";

	/**
	 * Singleton instance Implementation of the BundleStore
	 */
	private static ProphetQueuing instance_ = null;

	/**
	 * Queuing Type
	 */
	private static String policy = "Fifo";

	/**
	 * @return the policy
	 */
	public static String getPolicy() {
		return policy;
	}

	/**
	 * @param policy
	 *            the policy to set
	 */
	public static void setPolicy(String policy) {
		ProphetQueuing.policy = policy;
	}

	/**
	 * Initialization function called by the DTNServer upon, the start service
	 * is requested
	 */
	public static void init(DTNConfiguration dtn) {
		setPolicy(dtn.routes_setting().getQueuing_policy());
	}

	/**
	 * Singleton Implementation Getter function
	 * 
	 * @return an singleton instance of BundleStore
	 */
	public static ProphetQueuing getInstance() {
		if (instance_ == null) {
			Class myClass;
			try {
				myClass = Class
						.forName("se.kth.ssvl.tslab.bytewalla.androiddtn.servlib.routing.prophet.queuing."
								+ policy);
				instance_ = (ProphetQueuing) myClass.newInstance();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Logger.getInstance().error(TAG, "Wrong policy");
				e.printStackTrace();
			}
		}
		return instance_;
	}

	public abstract int getLastBundle();

	public boolean removeNextBundle() {
		int bundleID = this.getLastBundle();
		if (bundleID != -1) {
			this.delete(bundleID);
			return true;
		} else
			return false;
	}

	public void delete(int bundleid) {
		Logger.getInstance().info(TAG, "Deleting bundle: " + bundleid);
		bundleStore.del(bundleid);
	}

	public void maintainQuota() {
		BundleStore bs = BundleStore.getInstance();
		while (bs.quota() != 0
				&& (GlobalStorage.getInstance().get_total_size() > bs.quota())) {
			if (!this.removeNextBundle())
				break;
		}
	}
}
