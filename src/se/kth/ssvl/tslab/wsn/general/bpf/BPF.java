package se.kth.ssvl.tslab.wsn.general.bpf;

import se.kth.ssvl.tslab.wsn.general.bpf.exceptions.BPFException;
import se.kth.ssvl.tslab.wsn.general.dtnapi.DTNAPIImplementation;
import se.kth.ssvl.tslab.wsn.general.dtnapi.exceptions.DTNAPIFailException;
import se.kth.ssvl.tslab.wsn.general.dtnapi.exceptions.DTNOpenException;
import se.kth.ssvl.tslab.wsn.general.dtnapi.types.DTNAPICode.dtn_bundle_payload_location_t;
import se.kth.ssvl.tslab.wsn.general.dtnapi.types.DTNAPICode.dtn_bundle_priority_t;
import se.kth.ssvl.tslab.wsn.general.dtnapi.types.DTNBundleID;
import se.kth.ssvl.tslab.wsn.general.dtnapi.types.DTNBundleSpec;
import se.kth.ssvl.tslab.wsn.general.dtnapi.types.DTNEndpointID;
import se.kth.ssvl.tslab.wsn.general.dtnapi.types.DTNHandle;
import se.kth.ssvl.tslab.wsn.general.dtnapi.types.DTNAPICode.dtn_api_status_report_code;
import se.kth.ssvl.tslab.wsn.general.servlib.bundling.bundles.BundleDaemon;
import se.kth.ssvl.tslab.wsn.general.servlib.bundling.bundles.BundlePayload;
import se.kth.ssvl.tslab.wsn.general.servlib.bundling.bundles.BundlePayload.location_t;
import se.kth.ssvl.tslab.wsn.general.servlib.config.Configuration;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class BPF {

	/* ********* MEMBER VARIABLES ********** */
	private static BPF instance;
	private static BPFService service;
	private DTNAPIImplementation dtn;

	/* ********* INITIALIZATION AND CONSTRUCTOR ********** */
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
	public void init(BPFService _service) throws BPFException {
		if (_service == null) {
			throw new BPFException(
					"BPFService cannot be null, we really need this guy to work!");
		}
		instance = new BPF(_service);
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
		dtn = new DTNAPIImplementation();
	}

	/* ********* GETTER METHODS FOR BPF CLASSES ************** */
	/**
	 * Gets the configurations object
	 * 
	 * @return The Configurations object filled with configuration parameters
	 */
	public Configuration getConfig() {
		throw new NotImplementedException();
	}

	/* ********* GETTER METHODS FOR BPF API CLASSES ********** */
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
	public BPFNotificationReceiver getBPFNotificationReceiver() {
		return service.getBPFNotificationReceiver();
	}

	/**
	 * Gets the BPFDB object passed in from the BPFService.
	 * 
	 * @return The BPFDB object in the BPFService
	 */
	public BPFDB getBPFDB() {
		return service.getBPFDB();
	}

	/* **************** DTN API METHODS ********************* */
	/**
	 * Use this method to send data using DTN.
	 * 
	 * @param receiver
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
	public dtn_api_status_report_code send(DTNEndpointID receiver,
			int lifetime, byte[] data) throws DTNOpenException {
		// Create Bundle payload and store it in a file
		BundlePayload payload = new BundlePayload(location_t.DISK);
		payload.set_data(data);

		// Create a handle and check if it can be opened
		DTNHandle handle = new DTNHandle();
		if (dtn.dtn_open(handle) != dtn_api_status_report_code.DTN_SUCCESS) {
			throw new DTNOpenException();
		}

		// Set some configuration for the Bundle
		DTNBundleSpec spec = new DTNBundleSpec();
		spec.set_dest(receiver);
		spec.set_source(new DTNEndpointID(BundleDaemon.getInstance()
				.local_eid().toString()));
		spec.set_expiration(lifetime);
		spec.set_dopts(0);
		spec.set_priority(dtn_bundle_priority_t.COS_NORMAL);

		// Send and store results
		DTNBundleID bundleId = new DTNBundleID();
		dtn_api_status_report_code result = dtn.dtn_send(handle, spec,
				payload, bundleId);

		// Close the handler
		dtn.dtn_close(handle);
		
		return result;
	}
}
