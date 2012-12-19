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

package se.kth.ssvl.tslab.wsn.general.servlib.routing.prophet.queuing;

import java.util.List;
import java.util.Map;

import se.kth.ssvl.tslab.wsn.general.bpf.BPF;
import se.kth.ssvl.tslab.wsn.general.bpf.exceptions.BPFDBException;

public class Mofo extends ProphetQueuing {

	private static final String TAG = "Mofo";

	public int getLastBundle() {
		List<Map<String, Object>> dbResult;	
		
		try {
			dbResult = BPF.getInstance().getBPFDB().query("bundles", null, null, null, null, null,
							"forwarded_times Desc, id Desc", null);

			if (dbResult.size() > 0) {
				BPF.getInstance().getBPFLogger().info("Queuing",
						"Deleting bundle ft: " + dbResult.get(0).get("forwarded_times"));
				return (Integer) dbResult.get(0).get("id");
			}
			
			BPF.getInstance().getBPFLogger().warning(TAG, "Couldn't find the last bundle");
		} catch (BPFDBException e) {
			BPF.getInstance().getBPFLogger().error(TAG, e.getMessage());
		}

		return -1;
	}
}