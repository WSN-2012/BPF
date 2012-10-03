package se.kth.ssvl.tslab.wsn.general.bps;

public interface BPSService {
	
	public abstract BPSCommunication getBPSCommunication();
	
	public abstract BPSLogger getBPSLogger();
	
	public abstract BPSNotificationReceiver getBPSNotificationReceiver();
	
}
