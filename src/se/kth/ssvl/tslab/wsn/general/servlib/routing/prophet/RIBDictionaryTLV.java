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

import java.util.HashMap;
import java.util.Iterator;

import se.kth.ssvl.tslab.wsn.general.servlib.bundling.SDNV;
import se.kth.ssvl.tslab.wsn.general.servlib.bundling.bundles.BundleDaemon;
import se.kth.ssvl.tslab.wsn.general.systemlib.util.IByteBuffer;

public class RIBDictionaryTLV extends BaseTLV {
	/*
	 * 0 1 2 3 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
	 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+ | TLV
	 * type=0xA0 | Flags | Length |
	 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+ | RIBD
	 * Entry Count (SDNV) | Reserved |
	 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+ ~ ~ ~
	 * Variable Length Routing Address Strings ~ ~ ~
	 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
	 */

	/*
	 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+ |
	 * String ID 1 (SDNV) | Length (SDNV) | Resv |
	 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+ ~
	 * Endpoint Identifier 1 (variable) ~ | |
	 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+ | . | ~
	 * . ~ | . |
	 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+ |
	 * String ID n (SDNV) | Length (SDNV) | Resv |
	 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+ | | ~
	 * Endpoint Identifier n (variable) ~ | |
	 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
	 */
	public static void createTLV(IByteBuffer buf,
			HashMap<String, ProphetNeighbor> neighbors) {
		buf.put(TLVType.RIBDICTIONARY.getCode());
		int start = buf.position();
		buf.put((byte) 0);
		int lenpos = buf.position();
		SDNV.encode(4, buf, 2);

		SDNV.encode(neighbors.keySet().size() + 1, buf, 2);
		buf.putShort((short) 0);

		String local_eid = BundleDaemon.localEid();

		short strId = 0;
		SDNV.encode(strId, buf, 2);
		// length
		SDNV.encode(local_eid.length(), buf, 1);
		// reserve
		buf.put((byte) 0);
		// eid
		buf.put(local_eid.getBytes());

		Iterator<String> ns = neighbors.keySet().iterator();
		while (ns.hasNext()) {
			String eid = ns.next();
			ProphetNeighbor pn = neighbors.get(eid);

			SDNV.encode(pn.stringID(), buf, 2);
			// length
			SDNV.encode(pn.remote_eid().length(), buf, 1);
			// reserve
			buf.put((byte) 0);
			// eid
			buf.put(eid.getBytes());
		}

		int end = buf.position();
		buf.position(lenpos);
		SDNV.encode(end - start, buf, 2);
		buf.position(end);
	}
}
