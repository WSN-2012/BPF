package bpf;

import se.kth.ssvl.tslab.wsn.general.bpf.BPFLogger;

public class Logger implements BPFLogger {

	private String log(String text) {
		System.out.println(text);
		return text;
	}
	
	public String debug(String TAG, String text) {
		return log("[DEBUG] " + TAG + ": " + text);
	}

	public String error(String TAG, String text) {
		return log("[ERROR] " + TAG + ": " + text);
	}

	public String info(String TAG, String text) {
		return log("[INFO] " + TAG + ": " + text);
	}

	public String warning(String TAG, String text) {
		return log("[WARN] " + TAG + ": " + text);
	}
	
}
