package bpf;

import java.net.InetAddress;
import java.net.UnknownHostException;

import se.kth.ssvl.tslab.wsn.general.bpf.BPFCommunication;

public class Communication implements BPFCommunication {

	public InetAddress getBroadcastAddress() {
		try {
			return InetAddress.getByAddress(new byte[] {(byte) 255,(byte) 255,(byte) 255,(byte) 255});
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return null;
	}

	public InetAddress getDeviceIP() {
		try {
			return InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return null;
	}

}
