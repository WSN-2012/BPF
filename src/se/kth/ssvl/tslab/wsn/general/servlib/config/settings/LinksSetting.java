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
package se.kth.ssvl.tslab.wsn.general.servlib.config.settings;

import se.kth.ssvl.tslab.wsn.general.servlib.config.types.conv_layer_type_t;
import se.kth.ssvl.tslab.wsn.general.servlib.contacts.links.Link;
import se.kth.ssvl.tslab.wsn.general.systemlib.util.List;

/**
 * The class represents links setting in the configuration file.
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public class LinksSetting {
	/**
	 * Class represents individual link entry in the links
	 */
	public static class LinkEntry {
		private String id_;
		private String dest_;
		private Link.link_type_t type_;
		private conv_layer_type_t conv_layer_type_;

		/**
		 * @return the id_
		 */
		public String id() {
			return id_;
		}

		/**
		 * @param id
		 *            the id_ to set
		 */
		public void set_id(String id) {
			id_ = id;
		}

		/**
		 * @return the dest_
		 */
		public String dest() {
			return dest_;
		}

		/**
		 * @param dest
		 *            the dest_ to set
		 */
		public void set_dest(String dest) {
			dest_ = dest;
		}

		/**
		 * @return the type_
		 */
		public Link.link_type_t type() {
			return type_;
		}

		/**
		 * @param type
		 *            the type_ to set
		 */
		public void set_type(Link.link_type_t type) {
			type_ = type;
		}

		/**
		 * @return the conv_layer_type_
		 */
		public conv_layer_type_t conv_layer_type() {
			return conv_layer_type_;
		}

		/**
		 * @param convLayerType
		 *            the conv_layer_type_ to set
		 */
		public void set_conv_layer_type(conv_layer_type_t conv_layer_type) {
			conv_layer_type_ = conv_layer_type;
		}

	}

	/**
	 * Empty Constructor
	 */
	public LinksSetting() {
		link_entries_ = new List<LinkEntry>();
	}

	/**
	 * The list maintaining the links inside this setting
	 */
	private List<LinkEntry> link_entries_;
	
	/**
	 * A boolean wether to use proactive fragmentation or not 
	 */
	private boolean proactive_framgmentation; 
	
	/**
	 * The fragmentation MTU.
	 */
	private int fragmentation_mtu;

	private int retryInterval;
	

	/**
	 * Accessor for the Link Entries inside this Link Setting
	 * 
	 * @return the link_entries_
	 */
	public List<LinkEntry> link_entries() {
		return link_entries_;
	}

	/**
	 * Setter for the Link Entries inside this Link Setting
	 * 
	 * @param linkEntries
	 *            the link_entries_ to set
	 */
	public void set_link_entries(List<LinkEntry> link_entries) {
		link_entries_ = link_entries;
	}
	
	public void set_proactive_fragmentation(boolean pf) {
		proactive_framgmentation = pf;
	}
	
	public boolean proactive_fragmentation() {
		return proactive_framgmentation;
	}
	
	public void set_fragmentation_mtu(int mtu) {
		fragmentation_mtu = mtu;
	}
	
	public int fragmentation_mtu() {
		return fragmentation_mtu;
	}
	
	public int retryInteval() {
		return retryInterval;
	}
	
	public void setRetryInterval(int interval) {
		retryInterval = interval;
	}
}
