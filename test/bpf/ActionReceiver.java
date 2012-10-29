package bpf;

import se.kth.ssvl.tslab.wsn.general.bpf.BPFActionReceiver;
import se.kth.ssvl.tslab.wsn.general.servlib.bundling.bundles.Bundle;
import se.kth.ssvl.tslab.wsn.general.servlib.bundling.exception.BundleLockNotHeldByCurrentThread;

public class ActionReceiver implements BPFActionReceiver {

	private static final String TAG = "Actions";
	
	private Logger logger;
	
	
	public ActionReceiver(Logger logger) {
		this.logger = logger;
	}
	
	public void bundleReceived(Bundle bundle) {
		logger.debug(TAG, "Received bundle! Reading:");
		try {
			logger.debug(TAG, new String(bundle.payload().memory_buf()));
		} catch (BundleLockNotHeldByCurrentThread e) {
			e.printStackTrace();
		}
	}

	public void notify(String header, String description) {
		logger.debug(TAG, String.format("[{0}] {1}", header, description));
	}

	
	
}
