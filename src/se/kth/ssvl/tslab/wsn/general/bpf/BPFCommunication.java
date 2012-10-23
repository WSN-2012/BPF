package se.kth.ssvl.tslab.wsn.general.bpf;

import java.net.InetAddress;

public interface BPFCommunication {

	/**
	 * This method should get the ip-address that the device has.
	 * Note: This can be a bit tricky, make sure you get the right address.
	 * @return The ip address of the device.
	 */
	public abstract InetAddress getDeviceIP();
	
	/**
	 * Get the broadcast address for which to send the discovery packets on.
	 * @return
	 */
	public abstract InetAddress getBroadcastAddress();
	
}
