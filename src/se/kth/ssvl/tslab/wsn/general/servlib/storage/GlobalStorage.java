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


package se.kth.ssvl.tslab.wsn.general.servlib.storage;

import se.kth.ssvl.tslab.wsn.general.bpf.BPF;
import se.kth.ssvl.tslab.wsn.general.servlib.bundling.bundles.Bundle;

/**
 * GlobalStorage class is implemented as Singleton to keep the total application
 * size.
 * 
 * @author Sharjeel Ahmed (sharjeel@kth.se)
 */

public class GlobalStorage {

	/**
	 * Singleton instance Implementation of the GlobalStorage
	 */
	private static GlobalStorage instance_ = null;

	/**
	 * TAG for Android Logging
	 */
	private static String TAG = "GlobalStore";

	/**
	 * Singleton Implementation Getter function
	 * 
	 * @return an singleton instance of GlobalStorage
	 */
	public static GlobalStorage getInstance() {
		if (instance_ == null) {
			instance_ = new GlobalStorage();
		}
		return instance_;
	}

	/**
	 * This function initiate the Global Storage and calculate the total size
	 * application size.
	 * 
	 * @param context
	 *            The application context for getting for application paths
	 * @param config
	 *            Get the application configuration to get the memory usage
	 *            parameters
	 * @return returns true on success
	 */

	public boolean init() {
		impt_storage_ = new Storage<Bundle>("global");

		// Total bundles size will be calculated in BundleStorge and added here
		total_size_ += impt_storage_.get_directory_size();
		BPF.getInstance().getBPFLogger().debug(TAG,
				"Total Size of DTN Folder:" + total_size_);
		
		//TODO: Is the below needed?
//		total_size_ += impt_storage_.get_file_size(path_database);  
//		BPF.getInstance().getBPFLogger().debug(TAG,
//				"Total Size of DTN Folder:" + total_size_);

		return true;
	}

	/**
	 * Private constructor for Singleton Implementation of the BundleStore
	 */
	private GlobalStorage() {
		total_size_ = 0;
	}

	/**
	 * This function is the setter of total_size_
	 */
	public void set_total_size(long size) {
		total_size_ = size;
		StatsManager.getInstance().update("size", (int)total_size_);
	}

	/**
	 * Close any opened connection
	 */
	public void close() {
		instance_ = null;
	}

	/**
	 * This function is the getter of total_size_
	 * 
	 * @return Total storage currently using.
	 */

	public long get_total_size() {
		return total_size_;
	}

	/**
	 * Add size to the total_size
	 * 
	 * @param size
	 *            Number of bytes to add
	 */
	public void add_total_size(long size) {
		total_size_ += size;
		StatsManager.getInstance().update("size", (int)total_size_);
	}

	/**
	 * Remove size from the total_size
	 * 
	 * @param size
	 *            Number of bytes to delete from total size
	 */

	public void remove_total_size(long size) {
		total_size_ -= size;
		StatsManager.getInstance().update("size", (int)total_size_);
	}

	/**
	 * Total memory consumption
	 */

	private long total_size_;

	/**
	 * StorageImplementation object to use with bundle
	 */

	private static Storage<Bundle> impt_storage_;
}