package se.kth.ssvl.tslab.wsn.general.bpf;

public interface BPFService {
	
	public abstract BPFCommunication getBPFCommunication();
	
	public abstract BPFLogger getBPFLogger();
	
	public abstract BPFNotificationReceiver getBPFNotificationReceiver();
	
	public abstract BPFDB getBPFDB();
	
}
