package mock;

import java.net.InetAddress;
import java.sql.ResultSet;
import java.util.Map;

import se.kth.ssvl.tslab.wsn.general.bpf.BPFActionReceiver;
import se.kth.ssvl.tslab.wsn.general.bpf.BPFCommunication;
import se.kth.ssvl.tslab.wsn.general.bpf.BPFDB;
import se.kth.ssvl.tslab.wsn.general.bpf.BPFLogger;
import se.kth.ssvl.tslab.wsn.general.bpf.BPFService;
import se.kth.ssvl.tslab.wsn.general.bpf.exceptions.BPFDBException;
import se.kth.ssvl.tslab.wsn.general.servlib.bundling.bundles.Bundle;

public class Service implements BPFService {

	public BPFCommunication getBPFCommunication() {
		return new BPFCommunication() {
			
			public InetAddress getDeviceIP() {
				return null;
			}
			
			public InetAddress getBroadcastAddress() {
				return null;
			}
		};
	}

	public BPFLogger getBPFLogger() {
		return new BPFLogger() {
			
			public String warning(String TAG, String message) {
				return null;
			}
			
			public String info(String TAG, String message) {
				return null;
			}
			
			public String error(String TAG, String message) {
				return null;
			}
			
			public String debug(String TAG, String message) {
				return null;
			}
		};
	}

	public BPFActionReceiver getBPFActionReceiver() {
		return new BPFActionReceiver() {
			
			public void notify(String header, String description) {
				
			}
			
			public void bundleReceived(Bundle bundle) {
				
			}
		};
	}

	public BPFDB getBPFDB() {
		return new BPFDB() {
			
			public int update(String table, Map<String, Object> values, String where,
					String[] whereArgs) throws BPFDBException {
				return 0;
			}
			
			public ResultSet query(String table, String[] columns, String selection,
					String[] selectionArgs, String groupBy, String having,
					String orderBy, String limit) throws BPFDBException {
				return null;
			}
			
			public int insert(String table, Map<String, Object> values)
					throws BPFDBException {
				return 0;
			}
			
			public void execSQL(String sql) throws BPFDBException {
				
			}
			
			public int delete(String table, String whereClause, String[] whereArgs)
					throws BPFDBException {
				return 0;
			}
			
			public void close() {
				
			}
		};
	}

	
	
}
