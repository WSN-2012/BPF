package se.kth.ssvl.tslab.wsn.general.servlib.storage;

public class Stats {
	private int size;
	private int stored;
	private int transmitted;
	private int received;
	
	public Stats (int totSize, int storedBundles, int transmittedBundles, int receivedBundles) {
		size = totSize;
		stored = storedBundles;
		transmitted = transmittedBundles;
		received = receivedBundles;
	}

	public int totalSize() {
		return size;
	}
	
	public int storedBundles() {
		return stored;
	}
	
	public int transmittedBundles() {
		return transmitted;
	}
	
	public int receivedBundles() {
		return received;
	}
}
