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

package se.kth.ssvl.tslab.wsn.general.servlib.security;

import se.kth.ssvl.tslab.wsn.general.bpf.BPF;
import se.kth.ssvl.tslab.wsn.general.servlib.bundling.blocks.BlockInfo;
import se.kth.ssvl.tslab.wsn.general.servlib.bundling.blocks.BlockInfoVec;
import se.kth.ssvl.tslab.wsn.general.servlib.bundling.bundles.Bundle;
import se.kth.ssvl.tslab.wsn.general.servlib.contacts.links.Link;

/**
 * This class initializes everything that is needed for security processing. It
 * also contains prepare_out_blocks and will decide which security block is to
 * be prepared.
 */

public class Security {

	private static String TAG = "Security";

	public Security() {
		Ciphersuite.init_default_ciphersuites();

		BPF.getInstance().getBPFLogger().debug(TAG, "Initialized.");
	}

	// This method will be called from BundleProtocol.prepare_blocks()
	public static void prepare_out_blocks(Bundle bundle, Link link,
			BlockInfoVec xmit_blocks) {
		int err = 0;

		// Do we have to use PCB?
		if (BPF.getInstance().getConfig().security_setting().use_pcb()
				&& !bundle.dest().getService().contains("epidemic")) {
			Ciphersuite bp = Ciphersuite.find_suite(Ciphersuite_C3.CSNUM_C3);
			assert (bp != null);
			BPF.getInstance().getBPFLogger()
					.debug(TAG, "Now preparing security blocks (PCB)...");
			err = bp.prepare(bundle, xmit_blocks, null, link,
					BlockInfo.list_owner_t.LIST_NONE);
		}

		// Do we have to use PIB?
		if (BPF.getInstance().getConfig().security_setting().use_pib()) {
			BPF.getInstance()
					.getBPFLogger()
					.error(TAG, "PIB blocks are not supported yet! Skipping...");
			// TODO: Add PIB support!
		}

		if (err != 0) {
			BPF.getInstance().getBPFLogger()
					.error(TAG, "Error while preparing security blocks!");
		} else {
			BPF.getInstance().getBPFLogger()
					.debug(TAG, "Blocks have been prepared.");
		}
	}

}
