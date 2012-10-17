package se.kth.ssvl.tslab.wsn.general.servlib.routing.prophet.queuing;

import se.kth.ssvl.tslab.wsn.general.bpf.BPF;
import se.kth.ssvl.tslab.wsn.general.servlib.bundling.bundles.Bundle;
import se.kth.ssvl.tslab.wsn.general.servlib.config.Configuration;
import se.kth.ssvl.tslab.wsn.general.servlib.routing.routers.BundleRouter.Config;
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
	 * @return the policy
	 */
	public static QueueingType getPolicy() {
		return policy;
	}

	/**
	 * An enum to hold the different queueing types
	 */
	public enum QueueingType { FIFO, MOFO };
	
	
	/**
	 * Queuing Type
	 */
	private static QueueingType policy = QueueingType.FIFO;

	
	/**
	 * @param policy
	 *            the policy to set
	 */
	public static void setPolicy(QueueingType policy) {
		ProphetQueuing.policy = policy;
	}

	/**
	 * Initialization function called by the DTNServer upon, the start service
	 * is requested
	 */
	public static void init(Configuration dtn) {
		setPolicy(dtn.routes_setting().getQueuing_policy());
	}

	/**
	 * Singleton Implementation Getter function
	 * 
	 * @return an singleton instance of BundleStore
	 */
	public static ProphetQueuing getInstance() {
		if (instance_ == null) {
			switch (policy) {
				case FIFO:
					instance_ = (ProphetQueuing) new Fifo();
					break;
				case MOFO:
					instance_ = (ProphetQueuing) new Mofo();
					break;
				default:
					BPF.getInstance().getBPFLogger().error(TAG, "Wrong policy in prophet routing type");
					break;
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
		BPF.getInstance().getBPFLogger().info(TAG, "Deleting bundle: " + bundleid);
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
