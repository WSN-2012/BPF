
package se.kth.ssvl.tslab.wsn.general.servlib.conv_layers.connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import se.kth.ssvl.tslab.wsn.general.bpf.BPF;
import se.kth.ssvl.tslab.wsn.general.servlib.conv_layers.CLInfo;
import se.kth.ssvl.tslab.wsn.general.servlib.conv_layers.ConvergenceLayer;
import se.kth.ssvl.tslab.wsn.general.servlib.conv_layers.connection.TCPConvergenceLayer.TCPLinkParams;

/**
 * "Helper class (and thread) that listens on a registered interface for new
 * connections" [DTN2].
 * 
 * @author Maria Jose Peroza Marval (mjpm@kth.se)
 */

public class TCPListener extends CLInfo implements Runnable {

	/**
	 * Internal Thread
	 */
	private Thread thread_;

	/**
	 * ServerSocket instance
	 */
	private ServerSocket server_socket_;

	/**
	 * TAG for Android Logging mechanism
	 */
	private static final String TAG = "TCPListener";

	/**
	 * Unique identifier according to Java Serializable specification
	 */
	private static final long serialVersionUID = -6949424199702356461L;

	/**
	 * Constructor
	 */
	public TCPListener(ConvergenceLayer convergenceLayer, int port) {

		cl_ = (TCPConvergenceLayer) convergenceLayer;

		try {

			server_socket_ = new ServerSocket(port);

		} catch (IOException e) {
			
			BPF.getInstance().getBPFLogger().debug(TAG, "IOException " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Check is the socket is bound
	 */
	public boolean isBound() {
		return server_socket_.isBound();
	}

	/**
	 * Start the TCPListener thread
	 */
	public void start() {
		thread_ = new Thread(this);
		listening_ = true;
		thread_.start();

	}

	/**
	 * Stop the TCPListener thread
	 */
	public void stop() {
		listening_ = false;
		thread_ = null;
		try {
			server_socket_.close();
		} catch (IOException e) {
			BPF.getInstance().getBPFLogger().debug(TAG,
					"IOException stopping server_socket: " + e.getMessage());
		}
		server_socket_ = null;
	}

	TCPConvergenceLayer cl_; // / The TCPCL instance
	Socket socket; // / The socket

	private boolean listening_ = false; // / Listening flag

	/**
	 * Main loop
	 */
	public void run() {

		while (listening_) {
			try {
				BPF.getInstance().getBPFLogger().debug(TAG, "start accepting connection");
				socket = server_socket_.accept();
			} catch (IOException e) {
				BPF.getInstance().getBPFLogger().debug(TAG, "IOException in accept");
				continue;
			}
			BPF.getInstance().getBPFLogger().debug(TAG, "Connection Accepted");

			TCPConnection tcpconnection;

			try {
				TCPLinkParams tlp = cl_.new TCPLinkParams(true);
				tlp.remote_addr_ = socket.getInetAddress();
				tcpconnection = new TCPConnection(cl_, tlp);
				tcpconnection.set_socket(socket);
				tcpconnection.start();

			} catch (OutOfMemoryError e) {
				BPF.getInstance().getBPFLogger().debug(TAG, "Not enough resources");
			}

		}

	}
}
