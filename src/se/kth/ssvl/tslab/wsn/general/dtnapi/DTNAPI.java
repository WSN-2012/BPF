package se.kth.ssvl.tslab.wsn.general.dtnapi;

import java.io.File;

import se.kth.ssvl.tslab.wsn.general.dtnapi.exceptions.DTNAPIFailException;
import se.kth.ssvl.tslab.wsn.general.dtnapi.types.DTNAPICode.dtn_api_status_report_code;
import se.kth.ssvl.tslab.wsn.general.dtnapi.types.DTNBundleID;
import se.kth.ssvl.tslab.wsn.general.dtnapi.types.DTNBundleSpec;
import se.kth.ssvl.tslab.wsn.general.dtnapi.types.DTNEndpointID;
import se.kth.ssvl.tslab.wsn.general.dtnapi.types.DTNHandle;
import se.kth.ssvl.tslab.wsn.general.dtnapi.types.DTNRegistrationInfo;
import se.kth.ssvl.tslab.wsn.general.servlib.bundling.bundles.BundlePayload;
import se.kth.ssvl.tslab.wsn.general.systemlib.util.List;

/**
 * an API interface for BP application to communicate with the DTNService
 * 
 * @author Rerngvit Yanggratoke (rerngvit@kth.se)
 */
public interface DTNAPI {

	/**
	 * "biggest in-memory bundle is ~50K" [DTN2]
	 */
	final int DTN_MAX_BUNDLE_MEM = 50 * 1024;

	/**
	 * Get an appropriate EndpointID by appending the input service tag with
	 * Bundle Daemon local_eid
	 */
	dtn_api_status_report_code dtn_build_local_eid(DTNHandle handle,
			DTNEndpointID local_eid, final String service_tag);

	/**
	 * Create an API Registration and put it in the main Registration table
	 */
	dtn_api_status_report_code dtn_register(DTNHandle handle,
			DTNRegistrationInfo reginfo, int newregid)
			throws DTNAPIFailException;

	/**
	 * Delete a dtn registration.
	 */
	dtn_api_status_report_code dtn_unregister(DTNHandle handle, int regid);

	/**
	 * Check for an existing registration on the given endpoint id, returning
	 * DTN_SUCCSS and filling in the registration id if it exists, or returning
	 * ENOENT if it doesn't.
	 */
	dtn_api_status_report_code dtn_find_registrations(DTNHandle handle,
			DTNEndpointID eid, List<Integer> registration_ids);

	/**
	 * Put the registration in "active" mode so others can not close until all
	 * other people finished using it
	 */
	dtn_api_status_report_code dtn_bind(DTNHandle handle, int regid);

	/**
	 * This serves to put the registration back in "passive" mode.
	 */
	dtn_api_status_report_code dtn_unbind(DTNHandle handle, int regid);

	/**
	 * Open DTNHandle and book resource if necessary
	 */
	dtn_api_status_report_code dtn_open(DTNHandle handle);

	/**
	 * Close DTNHandle and free all resources
	 */

	dtn_api_status_report_code dtn_close(DTNHandle handle);

	/**
	 * Send method for sending a byte array
	 */
	dtn_api_status_report_code dtn_send(DTNHandle handle, DTNBundleSpec spec,
			byte[] payload, DTNBundleID dtn_bundle_id);
	
	/**
	 * Send method for sending files
	 */
	dtn_api_status_report_code dtn_send(DTNHandle handle, DTNBundleSpec spec, File payload,
			DTNBundleID dtn_bundle_id);

	/**
	 * Try to receive DTNBundle by block waiting according to input timeout.
	 * Timeout -1 means wait forever. InterruptedExceptio will be thrown if
	 * Timeout occur before the DTNBundle was received
	 * 
	 * @param timeout
	 *            time to wait for Bundle in milliseconds
	 */
	dtn_api_status_report_code dtn_recv(DTNHandle handle, int regid,
			DTNBundleSpec spec, BundlePayload payload, int timeout)
			throws InterruptedException;

}
