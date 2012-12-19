
package se.kth.ssvl.tslab.wsn.general.servlib.contacts.interfaces;

import java.net.ServerSocket;

import se.kth.ssvl.tslab.wsn.general.servlib.conv_layers.CLInfo;
import se.kth.ssvl.tslab.wsn.general.servlib.conv_layers.ConvergenceLayer;

/**
 * "Abstraction of a local dtn interface.
 * 
 * Generally, interfaces are created by the configuration file / console"
 * [DTN2].
 * 
 * @author Maria Jose Peroza Marval (mjpm@kth.se)
 */

public class Interface {

	// Accessors
	public String name() {
		return name_;
	}

	public String proto() {
		return proto_;
	}

	public ConvergenceLayer clayer() {
		return clayer_;
	}

	public CLInfo cl_info() {
		return cl_info_;
	}

	public ServerSocket socket() {
		return socket_;
	}

	public static int iface_counter() {
		return iface_counter_;
	}

	public static void set_iface_counter(int ifaceCounter) {
		iface_counter_ = ifaceCounter;
	}

	/**
	 * Store the ConvergenceLayer specific state.
	 */
	public void set_cl_info(CLInfo cl_info) {
		assert cl_info_ == null && cl_info != null : cl_info_ != null
				&& cl_info == null;

		cl_info_ = cl_info;
	}

	public void set_socket(ServerSocket socket) {
		socket_ = socket;
	}

	public Interface(String name, String proto, ConvergenceLayer clayer) {

		name_ = name;
		proto_ = proto;
		clayer_ = clayer;
		cl_info_ = null;
		socket_ = null;

	}

	protected String name_; // /< Name of the interface
	protected String proto_; // /< What type of CL
	protected ConvergenceLayer clayer_; // /< Convergence layer to use
	protected CLInfo cl_info_; // /< Convergence layer specific state
	protected ServerSocket socket_; // /< Socket of the interface
	private static int iface_counter_; // /< Number of interfaces

}
