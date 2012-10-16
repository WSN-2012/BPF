package se.kth.ssvl.tslab.wsn.general.servlib.routing.prophet.queuing;

import se.kth.ssvl.tslab.wsn.general.bpf.BPF;

import se.kth.ssvl.tslab.wsn.general.servlib.bundling.bundles.Bundle;
import se.kth.ssvl.tslab.wsn.general.servlib.storage.BundleStore;

public class Fifo extends ProphetQueuing {

	private static final String TAG = "Fifo";

	/**
	 * SQLiteImplementation object
	 */
	private SQLiteDatabase db = BundleStore.getImpt_sqlite_().getDb();

	@Override
	public int getLastBundle() {
		Cursor cursor = db.query("bundles", null, null, null, null, null,
				"id Asc", null);
		int fieldColumn = cursor.getColumnIndex("id");
		if (cursor == null) {
			BPF.getInstance().getBPFLogger().debug(TAG, "Row not found!");
			return -1;
		}

		if (!cursor.moveToFirst()) {
			return -1;
		}

		int result = cursor.getInt(fieldColumn);
		cursor.close();

		return result;
	}
}
