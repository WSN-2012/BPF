
package se.kth.ssvl.tslab.wsn.general.servlib.reg;

import java.io.FileNotFoundException;

import se.kth.ssvl.tslab.wsn.general.bpf.BPF;
import se.kth.ssvl.tslab.wsn.general.servlib.naming.endpoint.EndpointID;
import se.kth.ssvl.tslab.wsn.general.servlib.naming.endpoint.EndpointIDPattern;
import se.kth.ssvl.tslab.wsn.general.servlib.storage.FileManager;
import se.kth.ssvl.tslab.wsn.general.servlib.storage.RegistrationStore;
import se.kth.ssvl.tslab.wsn.general.systemlib.thread.Lock;
import se.kth.ssvl.tslab.wsn.general.systemlib.thread.VirtualTimerTask;

/**
 * This class is implemented as Singleton to store Registrations in memory.
 * RegistrationTable is used to store the registrations but registration storage
 * changes are also made persistent using RegistrationStore class
 * 
 * 
 * @author Sharjeel Ahmed (sharjeel@kth.se)
 */

public class RegistrationTable {

	/**
	 * Constructor to initialize RegistrationTable
	 */

	public RegistrationTable() {
		lock_ = new Lock();
		reglist_ = new RegistrationList();

		try {
			//TODO: Changed this into its own folder instead of "api_temp". Is that a bad idea?
			fileManager = new FileManager("registration", TAG); 
		} catch (FileNotFoundException e) {
			BPF.getInstance().getBPFLogger().error(TAG, "Couldn't create a folder for the registrations");
		}
		// Clean up in there before we go on
		cleanup_api_temp_folder();
	}

	private FileManager fileManager;
	
	/**
	 * Singleton instance Implementation of the RegistrationTable
	 */

	private static RegistrationTable instance_;

	/**
	 * TAG for Android Logging
	 */

	private static final String TAG = "RegistrationTable";

	/**
	 * This function clear all the data on application shutdown
	 */
	public void shutdown() {
		reglist_.clear();
		cleanup_api_temp_folder();
		instance_ = null;
	}

	/**
	 * Singleton Implementation to get the instance of RegistrationTable class
	 * 
	 * @return an singleton instance of RegistrationStore
	 */

	public static RegistrationTable getInstance() {
		if (instance_ == null) {
			instance_ = new RegistrationTable();
		}
		reg_store_ = RegistrationStore.getInstance();
		return instance_;
	}

	/**
	 * Cleanup function to clean API Temp Folder
	 */
	private void cleanup_api_temp_folder() {
		if (!fileManager.deleteFiles()) {
			BPF.getInstance().getBPFLogger().error(TAG, "Couldn't clean up the registration folder");
		} else {
			BPF.getInstance().getBPFLogger().debug(TAG, "Clean up API Temp Folder Success");
		}
		
	}

	/**
	 * Add a new registration to RegistrationList.
	 * 
	 * @param reg
	 *            Registration to save
	 * @param add_to_store
	 *            If true then add and store to database else just add to table
	 * @return True If successfully added else false.
	 */
	public boolean add(Registration reg, boolean add_to_store) {

		lock_.lock();

		try {
			reglist_.add(reg);

			// don't store (or log) default registrations
			if (!add_to_store || reg.regid() <= Registration.MAX_RESERVED_REGID) {
				return true;
			}

			// now, all we should get are API registrations
			Registration api_reg = reg;
			BPF.getInstance().getBPFLogger().debug(
					TAG,
					String.format("adding registration %s/%s", reg.regid(), reg
							.endpoint().str()));

			if (!RegistrationStore.getInstance().add(api_reg)) {
				BPF.getInstance().getBPFLogger().error(TAG,
								String.format(
										"error adding registration %d/%s: error in persistent store",
										reg.regid(), reg.endpoint().str()));
				return false;
			}

			return true;

		} finally {
			lock_.unlock();
		}
	}

	/**
	 * Get a Registration from RegistrationTable based on its registration id
	 * 
	 * @param regid
	 *            Find a registration based on regid
	 * @return If registration found then return registration else null
	 */
	public final Registration get(int regid) {

		return this.find(regid);
	}

	/**
	 * Get a Registration from RegistrationTable if endpoint id is matching in
	 * Table.
	 * 
	 * @param eid
	 *            Find a registration based on eid
	 * @return If registration found then return registration else null
	 */
	public final Registration get(final EndpointIDPattern eid) {

		Registration reg;

		for (int i = 0; i < reglist_.size(); i++) {
			reg = reglist_.get(i);

			if (reg.endpoint().equals(eid)) {
				return reg;
			}
		}
		return null;
	}

	/**
	 * Delete a Registration from RegistrationTable
	 * 
	 * @param regid
	 *            Delete Registration with this registration id
	 * @return True If registration deleted successfully else false
	 */
	public boolean del(int regid) {

		Registration reg;

		lock_.lock();

		try {
			BPF.getInstance().getBPFLogger().debug(TAG,
					String.format("removing registration %s", regid));

			reg = find(regid);
			reg.free_payload();

			if (regid > Registration.MAX_RESERVED_REGID) {
				if (!RegistrationStore.getInstance().del(reg)) {
					BPF.getInstance().getBPFLogger()
							.debug(TAG,
									String.format(
											"error removing registration %s: error in persistent store",
											regid));
					return false;
				}
			}
			reglist_.remove(reg);
			return true;

		} finally {
			lock_.unlock();
		}
	}

	/**
	 * Update the registration in database and RegistrationTable
	 * 
	 * @param reg
	 *            Registration to update
	 * @return True if successfully updated else false
	 */
	public boolean update(Registration reg) {

		lock_.lock();

		try {
			BPF.getInstance().getBPFLogger().debug(
					TAG,
					String.format("updating registration %s/%s", reg.regid(),
							reg.endpoint().str()));

			Registration api_reg = reg;

			if (!RegistrationStore.getInstance().update(api_reg)) {
				BPF.getInstance().getBPFLogger().error(TAG,
								String.format(
										"error updating registration %s/%s: error in persistent store",
										reg.regid(), reg.endpoint().str()));
				return false;
			}

			return true;
		} finally {
			lock_.unlock();
		}
	}

	/**
	 * Find all the registrations with given (eid) endpoint id and add to
	 * registration_list.
	 * 
	 * @param eid
	 *            Endpoint id to find the matching registrations
	 * @param reg_list
	 *            Add all the found registration in this list.
	 * @return Total count of matching registrations
	 */
	public final int get_matching(final EndpointID eid,
			RegistrationList reg_list) {

		int count = 0;

		lock_.lock();

		try {
			Registration reg;

			BPF.getInstance().getBPFLogger().debug(TAG,
					String.format("get_matching %s", eid.str()));

			for (int i = 0; i < reglist_.size(); i++) {
				reg = reglist_.get(i);

				if (reg.endpoint().equals(eid)) {
					count++;
					reg_list.add(reg);
				}
			}

			BPF.getInstance().getBPFLogger().debug(
					TAG,
					String.format("get_matching %s: returned %d matches",
							eid.str(), count));
			return count;

		} finally {
			lock_.unlock();
		}
	}

	/**
	 * Delete any expired registrations
	 * 
	 * @param now
	 *            Current time
	 */
	int delete_expired(final VirtualTimerTask now) {

		return 0;
	}

	/**
	 * Load all the registrations from database
	 * 
	 */
	public void load() {
		reglist_ = reg_store_.load();
	}

	/**
	 * Dump out the registration database.
	 * 
	 * @param buf
	 *            Add all the registration metadata to buf
	 */
	final public void dump(StringBuffer buf) {

		Registration reg;
		for (int i = 0; i < reglist_.size(); i++) {
			reg = reglist_.get(i);

			String dump = String.format("id: %s, eid: %s", reg.regid(), reg
					.endpoint().str());
			BPF.getInstance().getBPFLogger().debug(TAG, dump);
			buf.append(dump);
		}
	}

	/**
	 * Return the reglist_
	 * 
	 * @return Return the reglist_
	 */
	final public RegistrationList reg_list() {
		return reglist_;
	}

	/**
	 * Find the Registration based on its id.
	 * 
	 * @param regid
	 *            Find a registration based on this id
	 */
	Registration find(int regid) {

		Registration reg;
		for (int i = 0; i < reglist_.size(); i++) {
			reg = reglist_.get(i);

			BPF.getInstance().getBPFLogger().debug("TAG", ": " + reglist_.size());
			BPF.getInstance().getBPFLogger().debug("TAG", "id: " + reg.regid());

			if (reg.regid() == regid) {
				return reg;
			}
		}
		return null;
	}

	/**
	 * This function return the total count of registrations.
	 * 
	 * @return Count of registrations
	 */
	public int registration_count() {
		return reglist_.size();
	}

	/**
	 * RegistrationList object to store registration in-memory
	 */
	private static RegistrationList reglist_;

	/**
	 * Lock to protect data.
	 */
	private Lock lock_;

	/**
	 * RegistrationStore object, It uses to store the registrations in the
	 * databae.
	 */
	private static RegistrationStore reg_store_;
}
