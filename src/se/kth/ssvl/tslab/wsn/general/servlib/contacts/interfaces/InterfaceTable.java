
package se.kth.ssvl.tslab.wsn.general.servlib.contacts.interfaces;

import java.util.Iterator;

import se.kth.ssvl.tslab.wsn.general.bpf.BPF;
import se.kth.ssvl.tslab.wsn.general.servlib.config.settings.InterfacesSetting.InterfaceEntry;
import se.kth.ssvl.tslab.wsn.general.servlib.conv_layers.ConvergenceLayer;
import se.kth.ssvl.tslab.wsn.general.servlib.conv_layers.connection.TCPConvergenceLayer;
import se.kth.ssvl.tslab.wsn.general.systemlib.util.List;

/**
 * The list of interfaces
 * 
 * @author Maria Jose Peroza Marval (mjpm@kth.se)
 */
public class InterfaceTable {

	/**
	 * TAG for Android Logging mechanism
	 */
	private static final String TAG = "InterfaceTable";

	/**
	 * Singleton pattern for InterfaceTable
	 */

	private static InterfaceTable instance_ = null;

	public static InterfaceTable getInstance() {
		if (instance_ == null) {
			instance_ = new InterfaceTable();
		}
		return instance_;
	}

	/**
	 * Parsing the interface's parameters specified in the configuration file
	 * (config). Create he interfaces and add them to the InterfaceTable.
	 */
	public static void init() {

		List<InterfaceEntry> EntriesList = BPF.getInstance().getConfig()
				.interfaces_setting().interface_entries();
		Iterator<InterfaceEntry> i = EntriesList.iterator();
		Interface.set_iface_counter(0);

		while (i.hasNext()) {

			InterfaceEntry element = i.next();

			String conv_layer_type_ = element.conv_layer_type().getCaption();
			String id = element.id();
			short local_port = element.local_port();
			boolean fixed_local_port_ = element.fixed_local_port();
			BPF.getInstance().getBPFLogger().debug(TAG, conv_layer_type_ + " - " + id + " - " + local_port);

			if (!fixed_local_port_) {

				local_port = TCPConvergenceLayer.TCPCL_DEFAULT_PORT;
			}

			ConvergenceLayer cl = ConvergenceLayer
					.find_clayer(conv_layer_type_);
			if (cl == null) {
				BPF.getInstance().getBPFLogger()
						.error(TAG, "can't find convergence layer for " + id);
			}
			cl.set_local_port(local_port);

			if (!InterfaceTable.getInstance().add(id, cl, conv_layer_type_)) {
				BPF.getInstance().getBPFLogger()
						.debug(TAG, "error adding interface %s" + id);
			}

		}

	}

	/**
	 * Constructor
	 */
	public InterfaceTable() {

		iflist_ = new InterfaceList();

	}

	/**
	 * "Add a new interface to the table. Returns true if the interface is
	 * successfully added, false if the interface specification is invalid (or
	 * it already exists)"[DTN2].
	 */

	public boolean add(String name, ConvergenceLayer cl, String proto) {

		Iterator<Interface> iter = iflist_.iterator();

		if (find(name, iter)) {
			String text = String.format("Interface %s already exists", name);
			BPF.getInstance().getBPFLogger().error(TAG, text);
			return false;
		}

		String text = String.format("adding interface " + name, proto);
		BPF.getInstance().getBPFLogger().info(TAG, text);

		Interface iface = new Interface(name, proto, cl);
		if (!cl.interface_up(iface)) {
			BPF.getInstance()
					.getBPFLogger()
					.error(TAG,
							"convergence layer error adding interface" + name);
			return false;
		}

		iflist_.add(iface);
		return true;
	}

	/**
	 * "Remove the specified interface"[DTN2].
	 */

	public void shutdown() {

		Iterator<Interface> iter = iflist_.iterator();
		Interface iface;

		BPF.getInstance().getBPFLogger().info(TAG, "removing interfaces");

		while (iter.hasNext()) {
			iface = iter.next();
			iter.remove();
			if (iface.clayer().interface_down(iface)) {
				BPF.getInstance().getBPFLogger()
						.info(TAG, "shutdown interface " + iface.name());
			} else {
				BPF.getInstance()
						.getBPFLogger()
						.error(TAG,
								"error deleting interfaces from the convergence layer");

			}
		}

		instance_ = null;
	}

	/**
	 * "List the current interfaces" [DTN2].
	 */
	public void list(StringBuffer buf) {

		Iterator<Interface> iter = iflist_.iterator();
		Interface iface;

		while (iter.hasNext()) {
			iface = iter.next();
			String text = String.format("%s: Convergence Layer: %s\n",
					iface.name(), iface.proto());
			buf.append(text);

			iface.clayer().dump_interface(iface, buf);

		}
	}

	/**
	 * "All interfaces are tabled in-memory in a flat list. It's non-obvious
	 * what else would be better since we need to do a prefix match on demux
	 * strings in matching_interfaces" [DTN2].
	 */
	protected List<Interface> iflist_;

	/**
	 * "Internal method to find the location of the given interface in the list"
	 * [DTN2].
	 */
	protected boolean find(String name, Iterator<Interface> iter) {
		Interface iface;

		while (iter.hasNext()) {
			iface = iter.next();

			if (iface.name() == name) {
				return true;
			}
		}

		return false;
	}

}
