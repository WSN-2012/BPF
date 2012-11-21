package se.kth.ssvl.tslab.wsn.general.bpf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import se.kth.ssvl.tslab.wsn.general.bpf.exceptions.BPFException;
import se.kth.ssvl.tslab.wsn.general.dtnapi.DTN;
import se.kth.ssvl.tslab.wsn.general.dtnapi.exceptions.DTNAPIFailException;
import se.kth.ssvl.tslab.wsn.general.dtnapi.exceptions.DTNOpenException;
import se.kth.ssvl.tslab.wsn.general.dtnapi.types.DTNAPICode.dtn_api_status_report_code;
import se.kth.ssvl.tslab.wsn.general.dtnapi.types.DTNAPICode.dtn_bundle_priority_t;
import se.kth.ssvl.tslab.wsn.general.dtnapi.types.DTNAPICode.dtn_reg_flags_t;
import se.kth.ssvl.tslab.wsn.general.dtnapi.types.DTNBundleID;
import se.kth.ssvl.tslab.wsn.general.dtnapi.types.DTNBundleSpec;
import se.kth.ssvl.tslab.wsn.general.dtnapi.types.DTNEndpointID;
import se.kth.ssvl.tslab.wsn.general.dtnapi.types.DTNHandle;
import se.kth.ssvl.tslab.wsn.general.dtnapi.types.DTNRegistrationInfo;
import se.kth.ssvl.tslab.wsn.general.servlib.bundling.bundles.BundleDaemon;
import se.kth.ssvl.tslab.wsn.general.servlib.bundling.bundles.BundlePayload;
import se.kth.ssvl.tslab.wsn.general.servlib.bundling.bundles.BundlePayload.location_t;
import se.kth.ssvl.tslab.wsn.general.servlib.config.Configuration;
import se.kth.ssvl.tslab.wsn.general.servlib.config.ConfigurationParser;
import se.kth.ssvl.tslab.wsn.general.servlib.config.exceptions.InvalidDTNConfigurationException;
import se.kth.ssvl.tslab.wsn.general.servlib.contacts.ContactManager;
import se.kth.ssvl.tslab.wsn.general.servlib.contacts.interfaces.InterfaceTable;
import se.kth.ssvl.tslab.wsn.general.servlib.conv_layers.ConvergenceLayer;
import se.kth.ssvl.tslab.wsn.general.servlib.conv_layers.TestDataLogger;
import se.kth.ssvl.tslab.wsn.general.servlib.discovery.DiscoveryTable;
import se.kth.ssvl.tslab.wsn.general.servlib.routing.prophet.queuing.ProphetQueuing;
import se.kth.ssvl.tslab.wsn.general.servlib.routing.routers.BundleRouter;
import se.kth.ssvl.tslab.wsn.general.servlib.storage.BundleStore;
import se.kth.ssvl.tslab.wsn.general.servlib.storage.GlobalStorage;
import se.kth.ssvl.tslab.wsn.general.servlib.storage.RegistrationStore;
import se.kth.ssvl.tslab.wsn.general.systemlib.thread.VirtualTimerTask;
import se.kth.ssvl.tslab.wsn.general.systemlib.util.List;

public class BPF {

	private static final String TAG = "BPF";

	/* ******************************************************* */
	/* ***************** MEMBER VARIABLES ******************** */
	/* ******************************************************* */
	private static BPF instance;
	private static BPFService service;
	private static DTN dtn;
	private static Timer timer_;
	private static HashMap<VirtualTimerTask, TimerTask> timer_tasks_map_;
	private static Configuration config;

	/* ******************************************************* */
	/* ********* INITIALIZATION AND CONSTRUCTOR ************** */
	/* ******************************************************* */
	/**
	 * This method will return an singleton instance of BPF, which is used as a
	 * main entry point to the library. Note: Must be called after init() method
	 * 
	 * @return The BPF singleton
	 * @throws BPFException
	 *             Throws and exception if BPF has not been initialized using
	 *             init() method
	 */
	public static BPF getInstance() {
		if (instance == null) {
			if (service == null) {
				return null;
			}
			instance = new BPF(service);
		}

		return instance;
	}

	/**
	 * The init method will take in a BPFService which needs to implement
	 * methods for getting different BPF classes (e.g BPFLogger). The library
	 * will use the different classes as callbacks, since the implementation
	 * must be implemented outside the BPF library.
	 * 
	 * @param service
	 *            The BPFService implementation.
	 * @throws BPFException
	 */
	public static void init(BPFService _service, String configPath) throws BPFException {
		// Error checking
		if (_service == null) {
			throw new BPFException(
					"BPFService cannot be null, we really need this guy to work!");
		}
		// Create a new instance
		instance = new BPF(_service);

		// Set up the timers
		timer_   = new Timer();
    	timer_tasks_map_ = new HashMap<VirtualTimerTask,TimerTask>();
    	
    	// Parse the config before continuing
    	try {
			config = ConfigurationParser.parse_config_file(new FileInputStream(configPath));
		} catch (InvalidDTNConfigurationException e) {
			throw new BPFException("There was an error in the configuration: " + e.getMessage());
		} catch (FileNotFoundException e) {
			throw new BPFException("Configuration file was not found");
		}
		
		// Initialize all objects used by the BPF
    	dtn = new DTN();
    	ConvergenceLayer.init_clayers();
    	DiscoveryTable.getInstance().init();
    	BundleDaemon.getInstance().init();
    	ContactManager.getInstance().init();
    	InterfaceTable.init();
    	BundleRouter.init();
    	ProphetQueuing.init();
    	TestDataLogger.getInstance().init();

    	RegistrationStore.getInstance().init();
    	BundleStore.getInstance().init();
    	GlobalStorage.getInstance().init();
    	
    	// Start the bundle daemon
    	BundleDaemon.getInstance().start();
    	BPF.getInstance().getBPFLogger().info(TAG, "Started the BundleDaemon");
    	DiscoveryTable.getInstance().start();
    	BPF.getInstance().getBPFLogger().info(TAG, "Started the discovery");
    	
    	// Print a separating line
    	BPF.getInstance().getBPFLogger().debug(TAG, 
    			"\n**************************************************\n" + 
    			"******************* BPF initialized **************\n" +
    			"**************************************************");
	}

	/**
	 * Constructor for BPF, which is private since BPF is a singleton.
	 * 
	 * @param service
	 *            The BPFService implementation that the BPF library will use
	 *            for device-specific methods.
	 */
	private BPF(BPFService _service) {
		service = _service;
	}

	/* ******************************************************* */
	/* ********* GETTER METHODS FOR BPF CLASSES ************** */
	/* ******************************************************* */
	/**
	 * Gets the configurations object
	 * 
	 * @return The Configurations object filled with configuration parameters
	 */
	public Configuration getConfig() {
		return config;
	}
	
	/**
	 * Getter for internal Timer
	 * @return
	 */
	public Timer timer() {
		return timer_;
	}
	
	/**
	 * Getter for the HashMap control timer and virtual timer task
	 * @return the internal HashMap
	 * @see VirtualTimerTask, TimerTask
	 */
	public HashMap<VirtualTimerTask,TimerTask> timer_tasks_map() {
		return timer_tasks_map_;
	}

	/* ******************************************************* */
	/* ********* GETTER METHODS FOR BPF API CLASSES ********** */
	/* ******************************************************* */
	/**
	 * Gets the BPFCommunication object passed in from the BPFService.
	 * 
	 * @return The BPFCommunication object in the BPFService
	 */
	public BPFCommunication getBPFCommunication() {
		return service.getBPFCommunication();
	}

	/**
	 * Gets the BPFLogger object passed in from the BPFService.
	 * 
	 * @return The BPFLogger object in the BPFService
	 */
	public BPFLogger getBPFLogger() {
		return service.getBPFLogger();
	}

	/**
	 * Gets the BPFNotificationReceiver object passed in from the BPFService.
	 * 
	 * @return The BPFNotificationReceiver object in the BPFService
	 */
	public BPFActionReceiver getBPFActionReceiver() {
		return service.getBPFActionReceiver();
	}

	/**
	 * Gets the BPFDB object passed in from the BPFService.
	 * 
	 * @return The BPFDB object in the BPFService
	 */
	public BPFDB getBPFDB() {
		return service.getBPFDB();
	}

	/* ******************************************************* */
	/* **************** DTN API METHODS ********************** */
	/* ******************************************************* */
	/**
	 * Use this method to send data using DTN.
	 * 
	 * @param destination
	 *            The receiving endpoint
	 * @param lifetime
	 *            The lifetime of the bundle in seconds
	 * @param data
	 *            The data to be sent (payload)
	 * @return A dtn_api_status_report_code with the result of the sending
	 * @throws DTNOpenException
	 *             This is thrown when there was an error in opening a handle
	 *             for the DTN.
	 */
	public dtn_api_status_report_code send(DTNEndpointID destination,
			int lifetime, byte[] data) throws DTNOpenException {				

		// Create a handle and check if it can be opened
		DTNHandle handle = new DTNHandle();
		if (dtn.dtn_open(handle) != dtn_api_status_report_code.DTN_SUCCESS) {
			throw new DTNOpenException();
		}

		// Set some configuration for the Bundle
		DTNBundleSpec spec = new DTNBundleSpec();
		spec.set_dest(destination);
		spec.set_source(new DTNEndpointID(BundleDaemon.getInstance()
				.local_eid().toString()));
		spec.set_expiration(lifetime);
		spec.set_dopts(0);
		spec.set_priority(dtn_bundle_priority_t.COS_NORMAL);

		// Send and store results
		DTNBundleID bundleId = new DTNBundleID();
		dtn_api_status_report_code result = dtn.dtn_send(handle, spec, data,
				bundleId);

		// Close the handler
		dtn.dtn_close(handle);

		return result;
	}
	
	public dtn_api_status_report_code send(DTNEndpointID destination,
			int lifetime, File f) throws DTNOpenException {				

		// Create a handle and check if it can be opened
		DTNHandle handle = new DTNHandle();
		if (dtn.dtn_open(handle) != dtn_api_status_report_code.DTN_SUCCESS) {
			throw new DTNOpenException();
		}

		// Set some configuration for the Bundle
		DTNBundleSpec spec = new DTNBundleSpec();
		spec.set_dest(destination);
		spec.set_source(new DTNEndpointID(BundleDaemon.getInstance()
				.local_eid().toString()));
		spec.set_expiration(lifetime);
		spec.set_dopts(0);
		spec.set_priority(dtn_bundle_priority_t.COS_NORMAL);

		// Send and store results
		DTNBundleID bundleId = new DTNBundleID();
		dtn_api_status_report_code result = dtn.dtn_send(handle, spec, f,
				bundleId);

		// Close the handler
		dtn.dtn_close(handle);

		return result;
	}

	public void receive(DTNEndpointID destination)
			throws DTNAPIFailException {
		// Get all registrations for the specified destination
		List<Integer> registrations = new List<Integer>();
		DTNHandle handle = new DTNHandle();
		dtn_api_status_report_code find = dtn.dtn_find_registrations(handle,
				destination, registrations);

		// If it wasn't found register it with regid = 0
		if (find != dtn_api_status_report_code.DTN_SUCCESS) {
			DTNRegistrationInfo reginfo = new DTNRegistrationInfo(destination,
					dtn_reg_flags_t.DTN_REG_DEFER.getCode(), 3600, false);
			dtn.dtn_register(handle, reginfo, 0);
			registrations.add(new Integer(0));
		}
		
		// Iterate through the registrations and fetch all bundles
		try {
			Iterator<Integer> iter = registrations.iterator();
			Integer regid;
			while (iter.hasNext()) {
				regid = iter.next();

				// Bind the handle to registration if it's not already bound
				dtn.dtn_bind(handle, regid.intValue());

				// Objects that will hold the received information
				DTNBundleSpec spec = new DTNBundleSpec();
				BundlePayload dtn_payload = new BundlePayload(location_t.DISK);

				// Block Receiving call from API
				dtn_api_status_report_code receive_result = null;
				try {
					do {
						BPF.getInstance().getBPFLogger().debug(TAG,
								"Trying to receive bundle sent to: " + destination.uri());
						receive_result = dtn.dtn_recv(handle, regid.intValue(),
								spec, dtn_payload, 1);
						if (receive_result == dtn_api_status_report_code.DTN_SUCCESS) {
							// TODO: Have some kind of callback here maybe?
							// spec contains the information about the sender
							// etc
							// dtn_payload contains the data
						}
					} while (receive_result == dtn_api_status_report_code.DTN_SUCCESS);
				} catch (InterruptedException e) {
					// If we got more than one result try the next step
					continue;
				} finally {
					dtn.dtn_unbind(handle, regid.intValue());
				}
			}
		} finally {
			// unregister all the found registration
			Iterator<Integer> itr = registrations.iterator();
			while (itr.hasNext()) {
				Integer regid = itr.next();
				dtn.dtn_unregister(handle, regid.intValue());
			}
		}
	}
}
