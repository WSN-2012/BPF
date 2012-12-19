/*
 * Copyright 2012 KTH
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */

package se.kth.ssvl.tslab.wsn.general.servlib.config;

import se.kth.ssvl.tslab.wsn.general.servlib.config.settings.DiscoveriesSetting;
import se.kth.ssvl.tslab.wsn.general.servlib.config.settings.InterfacesSetting;
import se.kth.ssvl.tslab.wsn.general.servlib.config.settings.LinksSetting;
import se.kth.ssvl.tslab.wsn.general.servlib.config.settings.RoutesSetting;
import se.kth.ssvl.tslab.wsn.general.servlib.config.settings.SecuritySetting;
import se.kth.ssvl.tslab.wsn.general.servlib.config.settings.StorageSetting;

/**
 * Class represents DTNConfiguration that user can setup in the configuration
 * user interface
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 * 
 */
public class Configuration {

	/**
	 * Constructor
	 */
	public Configuration() {
		security_setting_ = new SecuritySetting();
		storage_setting_ = new StorageSetting();
		interfaces_setting_ = new InterfacesSetting();
		links_setting_ = new LinksSetting();
		routes_setting_ = new RoutesSetting();
		discoveries_setting_ = new DiscoveriesSetting();
	}

	/**
	 * internal security setting object
	 */
	private SecuritySetting security_setting_;
	
	/**
	 * internal storage setting object
	 */
	private StorageSetting storage_setting_;

	/**
	 * internal interfaces setting
	 */
	InterfacesSetting interfaces_setting_;

	/**
	 * internal links setting
	 */
	private LinksSetting links_setting_;

	/**
	 * internal routes setting
	 */
	private RoutesSetting routes_setting_;

	/**
	 * internal discoveries setting
	 */
	private DiscoveriesSetting discoveries_setting_;

	/**
	 * Accessor for the SecuritySetting of this DTNConfiguration
	 * 
	 * @return the security_setting_
	 */
	public SecuritySetting security_setting() {
		return security_setting_;
	}
	
	/**
	 * Setter for the SecuritySetting of this DTNConfiguration
	 * 
	 * @param SecuritySetting
	 *            the security_setting_ to set
	 */
	public void set_security_setting(SecuritySetting security_setting) {
		security_setting_ = security_setting;
	}
	
	/**
	 * Accessor for the StorageSetting of this DTNConfiguration
	 * 
	 * @return the storage_setting_
	 */
	public StorageSetting storage_setting() {
		return storage_setting_;
	}

	/**
	 * Setter for the StorageSetting of this DTNConfiguration
	 * 
	 * @param storageSetting
	 *            the storage_setting_ to set
	 */
	public void set_storage_setting(StorageSetting storage_setting) {
		storage_setting_ = storage_setting;
	}

	/**
	 * Accessor for the InterfacesSetting of this DTNConfiguration
	 * 
	 * @return the interfaces_setting_
	 */
	public InterfacesSetting interfaces_setting() {
		return interfaces_setting_;
	}

	/**
	 * Setter for the InterfacesSetting of this DTNConfiguration
	 * 
	 * @param interfacesSetting
	 *            the interfaces_setting_ to set
	 */
	public void set_interfaces_setting(InterfacesSetting interfaces_setting) {
		interfaces_setting_ = interfaces_setting;
	}

	/**
	 * Accessor for the LinksSetting of this DTNConfiguration
	 * 
	 * @return the links_setting_
	 */
	public LinksSetting links_setting() {
		return links_setting_;
	}

	/**
	 * Setter for the LinksSetting of this DTNConfiguration
	 * 
	 * @param linksSetting
	 *            the links_setting_ to set
	 */
	public void set_links_setting(LinksSetting links_setting) {
		links_setting_ = links_setting;
	}

	/**
	 * Accessor for the RoutesSetting of this DTNConfiguration
	 * 
	 * @return the routes_setting_
	 */
	public RoutesSetting routes_setting() {
		return routes_setting_;
	}

	/**
	 * Setter for the RoutesSetting of this DTNConfiguration
	 * 
	 * @param routesSetting
	 *            the routes_setting_ to set
	 */
	public void set_routes_setting(RoutesSetting routes_setting) {
		routes_setting_ = routes_setting;
	}

	/**
	 * Accessor for the DiscoveriesSetting of this DTNConfiguration
	 * 
	 * @return the discoveries_setting_
	 */
	public DiscoveriesSetting discoveries_setting() {
		return discoveries_setting_;
	}

	/**
	 * Setter for the DiscoveriesSetting of this DTNConfiguration
	 * 
	 * @param discoveriesSetting
	 *            the discoveries_setting_ to set
	 */
	public void set_discoveries_setting(DiscoveriesSetting discoveries_setting) {
		discoveries_setting_ = discoveries_setting;
	}

}
