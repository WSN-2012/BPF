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