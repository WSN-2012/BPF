
package se.kth.ssvl.tslab.wsn.general.servlib.discovery;

import java.net.InetAddress;

import se.kth.ssvl.tslab.wsn.general.servlib.discovery.announce.IPDiscovery;

/**
 * "On-the-wire (radio, whatever) representation of IP address family's
 * advertisement beacon" [DTN2].
 * 
 * @author Maria Jose Peroza Marval (mjpm@kth.se)
 */

public class DiscoveryHeader {

	/**
	 * Constructor
	 */
	public DiscoveryHeader() {

		cl_type = (byte) IPDiscovery.cl_type_t.UNDEFINED.getCode();
		interval = 0;
		length = 0;
		inet_addr = null;
		inet_port = 0;
		name_len = 0;
		sender_name = "";

	}

	private byte cl_type; // Type of CL offered
	private byte interval; // 100ms units
	private short length; // total length of packet
	private InetAddress inet_addr; // IPv4 address of CL
	private short inet_port; // IPv4 port of CL
	private short name_len; // length of EID
	private String sender_name; // DTN URI of beacon sender

	/**
	 * Accessors
	 */
	public byte cl_type() {
		return cl_type;
	}

	public void set_cl_type(byte clType) {
		this.cl_type = clType;
	}

	public byte interval() {
		return interval;
	}

	public void set_interval(byte interval) {
		this.interval = interval;
	}

	public short length() {
		return length;
	}

	public void set_length(short length) {
		this.length = length;
	}

	public InetAddress inet_addr() {
		return inet_addr;
	}

	public void set_inet_addr(InetAddress inetAddr) {
		inet_addr = inetAddr;
	}

	public short inet_port() {
		return inet_port;
	}

	public void set_inet_port(short inetPort) {
		inet_port = inetPort;
	}

	public short name_len() {
		return name_len;
	}

	public void set_name_len(short nameLen) {
		name_len = nameLen;
	}

	public String sender_name() {
		return sender_name;
	}

	public void set_sender_name(String senderName) {
		sender_name = senderName;
	}

}
