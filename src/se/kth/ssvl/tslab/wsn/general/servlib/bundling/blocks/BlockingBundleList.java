package se.kth.ssvl.tslab.wsn.general.servlib.bundling.blocks;

import se.kth.ssvl.tslab.wsn.general.servlib.bundling.bundles.Bundle;
import se.kth.ssvl.tslab.wsn.general.systemlib.thread.MsgBlockingQueue;

/**
 * A BlockingBundleList implemented by the BlockingQueue
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public class BlockingBundleList extends MsgBlockingQueue<Bundle> {
	/**
	 * Serial UID to support Java Serializable
	 */
	private static final long serialVersionUID = 9185559969497265215L;

	public BlockingBundleList(int capacity, boolean fair) {
		super(capacity, fair);
	}

};
