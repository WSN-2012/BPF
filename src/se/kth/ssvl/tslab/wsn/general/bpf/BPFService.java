package se.kth.ssvl.tslab.wsn.general.bpf;

import se.kth.ssvl.tslab.wsn.general.servlib.storage.Stats;

public interface BPFService {
	
	public abstract BPFCommunication getBPFCommunication();
	
	public abstract BPFLogger getBPFLogger();
	
	public abstract BPFActionReceiver getBPFActionReceiver();
	
	public abstract BPFDB getBPFDB();
	
	public abstract void updateStats(Stats stats);
	
}
