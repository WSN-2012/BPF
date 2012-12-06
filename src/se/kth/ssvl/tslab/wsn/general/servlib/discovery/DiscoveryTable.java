/*
 *	  This file is part of the Bytewalla Project
 *    More information can be found at "http://www.tslab.ssvl.kth.se/csd/projects/092106/".
 *    
 *    Copyright 2009 Telecommunication Systems Laboratory (TSLab), Royal Institute of Technology, Sweden.
 *    
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 * 
 *        http://www.apache.org/licenses/LICENSE-2.0
 * 
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *    
 */

package se.kth.ssvl.tslab.wsn.general.servlib.discovery;

import java.util.Iterator;

import se.kth.ssvl.tslab.wsn.general.bpf.BPF;
import se.kth.ssvl.tslab.wsn.general.servlib.config.Configuration;
import se.kth.ssvl.tslab.wsn.general.servlib.config.settings.DiscoveriesSetting.AnnounceEntry;
import se.kth.ssvl.tslab.wsn.general.servlib.config.settings.DiscoveriesSetting.DiscoveryEntry;
import se.kth.ssvl.tslab.wsn.general.systemlib.util.List;

/**
 * This class represents a table where the Discoveries instance are stored.
 */

public class DiscoveryTable {

	/**
	 * TAG for Android Logging mechanism
	 */
	private static final String TAG = "DiscoveryTable";

	/**
	 * Singleton Implementation of the DiscoveryTable
	 */
	private static DiscoveryTable instance_ = null;

	public static DiscoveryTable getInstance() {
		if (instance_ == null) {
			instance_ = new DiscoveryTable();
		}

		return instance_;
	}

	private Configuration config_;

	/**
	 * Parse, at a boot time, all the parameters needed to create discoveries
	 * instances. This parameter are parsed from the configuration file. The
	 * discoveries instances are stored in the table.
	 */
	public void init() {
		config_ = BPF.getInstance().getConfig();
		List<DiscoveryEntry> discovery_entries = config_.discoveries_setting()
				.discovery_entries();
		Iterator<DiscoveryEntry> i = discovery_entries.iterator();

		while (i.hasNext()) {

			DiscoveryEntry element = i.next();
			String name_id = element.id();
			String afamily = element.address_family().getCaption();
			int port = element.port();
			add(name_id, afamily, (short) port);
			BPF.getInstance().getBPFLogger().debug(TAG, name_id + " - " + afamily + " - " + port);
		}

	}

	public void start() {
		try {
			List<AnnounceEntry> announce_entries = config_
					.discoveries_setting().announce_entries();
			Iterator<AnnounceEntry> it = announce_entries.iterator();

			while (it.hasNext()) {

				AnnounceEntry element = it.next();
				String AnnounceID = element.interface_id();
				String DiscoveryID = element.discovery_id();
				int interval = element.interval();
				String ClType = element.conv_layer_type().getCaption();
				int code;
				if (ClType.compareTo("tcp") == 0) {
					code = 1;
				} else {
					code = 0;
				}

				Iterator<Discovery> iter = dlist_.iterator();

				Discovery disc = find(DiscoveryID, iter);
				if (disc.equals(null)) {
					String text = String
							.format("error adding announce %s to %s: no such discovery agent",
									AnnounceID, DiscoveryID);
					BPF.getInstance().getBPFLogger().debug(TAG, text);
					return;
				}

				if (!disc.announce(AnnounceID, code, ClType, interval)) {
					BPF.getInstance()
							.getBPFLogger()
							.debug(TAG,
									"Error creting the Announce" + AnnounceID);
				}
				disc.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Add a new discovery instance to the table
	 */
	public boolean add(String name, String addr_family, short port) {

		Iterator<Discovery> iter = dlist_.iterator();

		Discovery disc = find(name, iter);
		if (disc != null) {
			BPF.getInstance().getBPFLogger()
					.error(TAG, "agent exists with that name");
			return false;
		}

		disc = Discovery.create_discovery(name, addr_family, port);

		if (disc == null) {
			return false;
		}

		BPF.getInstance().getBPFLogger()
				.info(TAG, "adding discovery agent" + name);

		dlist_.add(disc);
		return true;
	}

	public boolean del(String name) {

		Iterator<Discovery> iter = dlist_.iterator();

		BPF.getInstance().getBPFLogger()
				.info(TAG, "removing discovery agent" + name);

		Discovery disc = find(name, iter);

		if (disc == null) {
			BPF.getInstance().getBPFLogger()
					.error(TAG, "error removing agent" + name);
			return false;
		}

		iter.remove();

		return true;

	}

	/**
	 * Clear the table
	 */
	public void shutdown() {

		Iterator<Discovery> i = dlist_.iterator();

		while (i.hasNext()) {
			Discovery d = i.next();
			d.shutdown();
			i.remove();
		}
		dlist_.clear();

		instance_ = null;

	}

	/**
	 * Constructor
	 */
	protected DiscoveryTable() {
		dlist_ = new DiscoveryList();
	}

	/**
	 * Find a discory instance for name
	 */
	protected Discovery find(String name, Iterator<Discovery> iter) {

		Discovery disc;
		// iter = dlist_.iterator();
		while (iter.hasNext()) {
			disc = iter.next();
			if (disc.name().equals(name))
				return disc;
		}
		disc = null;
		return disc;

	}

	protected DiscoveryList dlist_; // / List of Discoveries

}
