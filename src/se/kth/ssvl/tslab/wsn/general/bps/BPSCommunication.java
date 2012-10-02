package se.kth.ssvl.tslab.wsn.general.bps;

import java.net.InetAddress;

public interface BPSCommunication {

	/**
	 * This method should get the ip-address that the device has.
	 * Note: This can be a bit tricky, make sure you get the right address.
	 * @return The ip address of the device.
	 */
	public abstract InetAddress getDeviceIP();
	
}
