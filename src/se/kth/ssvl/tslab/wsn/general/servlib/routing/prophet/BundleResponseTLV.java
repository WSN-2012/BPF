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

package se.kth.ssvl.tslab.wsn.general.servlib.routing.prophet;

import java.util.ArrayList;

import se.kth.ssvl.tslab.wsn.general.servlib.bundling.SDNV;
import se.kth.ssvl.tslab.wsn.general.systemlib.util.IByteBuffer;

public class BundleResponseTLV extends BaseTLV {
	// TLV Type
	// The TLV Type for a Bundle Offer is 0xA2. The TLV Type for a
	// Bundle Response is 0xA3.

	/*
	 * 0 1 2 3 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
	 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+ | TLV
	 * Type | Flags | Length |
	 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+ |
	 * Bundle Offer Count | Reserved |
	 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+ |
	 * Bundle Dest String Id 1 (SDNV)| B_flags | resv |
	 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+ |
	 * Bundle 1 Creation Timestamp time | | (variable length SDNV) |
	 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+ |
	 * Bundle 1 Creation Timestamp sequence number | | (variable length SDNV) |
	 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+ ~ . ~ ~
	 * . ~ ~ . ~
	 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+ |
	 * Bundle Dest String Id n (SDNV)| B_flags | resv |
	 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+ |
	 * Bundle n Creation Timestamp time | | (variable length SDNV) |
	 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+ |
	 * Bundle n Creation Timestamp sequence number | | (variable length SDNV) |
	 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
	 */

	public static void createTLV(IByteBuffer buf,
			ArrayList<BundleOfferEntry> offers) {
		buf.put(BaseTLV.TLVType.BUNDLERESPONSE.getCode());
		buf.put((byte) 0);
		// length
		SDNV.encode(4, buf, 2);
		// offer count
		SDNV.encode(offers.size(), buf, 2);
		// reserve
		buf.putShort((short) 0);

		try {

			for (BundleOfferEntry o : offers) {
				// ID
				SDNV.encode(o.id, buf, 2);
				// B_flags
				buf.putShort((byte) 1);

				// Creation Timestamp time
				SDNV.encode(o.creationTime, buf, 2);
				// Creation Timestamp sequence number
				SDNV.encode(o.seqNo, buf, 2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
