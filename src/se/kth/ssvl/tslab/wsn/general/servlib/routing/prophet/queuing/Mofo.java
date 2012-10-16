package se.kth.ssvl.tslab.wsn.general.servlib.routing.prophet.queuing;

import se.kth.ssvl.tslab.wsn.general.servlib.bundling.bundles.Bundle;
import se.kth.ssvl.tslab.wsn.general.servlib.storage.BundleStore;
import se.kth.ssvl.tslab.wsn.general.bpf.BPF;

public class Mofo extends ProphetQueuing {

	private static final String TAG = "ForwardedTimes";

	/**
	 * SQLiteImplementation object
	 */
	private SQLiteDatabase db = BundleStore.getImpt_sqlite_().getDb();

	public int getLastBundle() {
		Cursor cursor = db.query("bundles", null, null, null, null, null,
				"forwarded_times Desc, id Desc", null);
		int forwardColumn = cursor.getColumnIndex("forwarded_times");
		int fieldColumn = cursor.getColumnIndex("id");
		if (cursor == null) {
			BPF.getInstance().getBPFLogger().debug(TAG, "Row not found!");
			return -1;
		}

		if (!cursor.moveToFirst()) {
			return -1;
		}

		BPF.getInstance().getBPFLogger().info("Queuing",
				"Deleting bundle ft: " + cursor.getInt(forwardColumn));
		int result = cursor.getInt(fieldColumn);
		cursor.close();

		return result;
	}
}