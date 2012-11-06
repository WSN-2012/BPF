/*
 *	  This file is part of the Bytewalla Project
 *    More information can be found at "http://www.tslab.ssvl.kth.se/csd/projects/092106/".
 *    
 *    Copyright 2009 Telecommunication Systems Laboratory (TSLab), Royal Institute of Technology, Sweden.
 *    
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 * 
 *        http://www.apache.org/licenses/LICENSE-2.0
 * 
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *    
 */

package se.kth.ssvl.tslab.wsn.general.servlib.storage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import se.kth.ssvl.tslab.wsn.general.bpf.BPF;
import se.kth.ssvl.tslab.wsn.general.bpf.exceptions.BPFDBException;

/**
 * This class is the implementation of SQLite. This class directly interact with
 * SQLite and add, delete, get and update records.
 * 
 * @author Sharjeel Ahmed (sharjeel@kth.se)
 */

public class DatabaseManager {

	/**
	 * TAG for Android Logging
	 */
	private static final String TAG = "DatabaseManager";

	/**
	 * Database name for storing the records.
	 */

	/**
	 * Construct
	 * 
	 * @param table
	 *            Table base table name to do all the operations
	 * @throws BPFDBException
	 *             Throws this when it cannot open the database
	 */

	public DatabaseManager(String table) throws BPFDBException {
		// Init the table
		create_table(table);
		BPF.getInstance().getBPFLogger()
				.debug(TAG, "Database initialized and opened");
	}

	/**
	 * Add new record to database table
	 * 
	 * @param table
	 *            Name of table in which function will add record
	 * @param values
	 *            ContentValues object contain all the values that this function
	 *            will add.
	 * @return If new row successfully added then return the newly added id
	 *         otherwise return -1
	 */

	public int add(String table, Map<String, Object> values) {
		try {
			BPF.getInstance().getBPFLogger()
					.debug(TAG, "Adding Row to table " + table);
			return BPF.getInstance().getBPFDB().insert(table, values);
		} catch (BPFDBException e) {
			BPF.getInstance().getBPFLogger()
					.error(TAG, "SQLite Exception while adding a row: " + e.getMessage());
			return -1;
		}

	}

	/**
	 * Increment forwarded times
	 * 
	 * @param bundleid
	 */
	public void incForwardedTimes(int bundleid) throws BPFDBException {
		// Get the bundle's forwarded times
		//TODO: Increment forward times needs some better implementation!
//		Map<String, Object> bundle = BPF.getInstance().getBPFDB().query("bundles", new String[]{"forwarded_times"},
//					"id=" + bundleid , null, null, null, null, null).get(0);
//		
//		Map<String, Object> updateValues = new HashMap<String, Object>(1);
//		updateValues.put("forwarded_times", ((Integer)bundle.get("forwarded_times")) + 1);
//		if (update("bundles", updateValues, "id=" + bundleid, null)) {
//			BPF.getInstance().getBPFLogger().debug(TAG, "Updated the forwarded times for bundle " + bundleid + 
//					" to " + ((Integer)bundle.get("forwarded_times")) + 1);
//		}
	}

	/**
	 * Update record in database table
	 * 
	 * @param table
	 *            Name of table in which record already exist
	 * @param values
	 *            ContentValues object contain all the updated values
	 * @param where
	 *            Condition: Update record if this condition match
	 * @param whereArgs
	 * 			  Values to the be meet in the condition
	 * @return True If new row successfully updated else return false otherwise
	 *         return -1
	 */

	public boolean update(String table, Map<String, Object> values,
			String where, String[] whereArgs) {
		try {
			BPF.getInstance().getBPFLogger()
					.debug(TAG, "Updating Row in table " + table);
			BPF.getInstance().getBPFDB()
					.update(table, values, where, whereArgs);
			return true;
		} catch (BPFDBException e) {
			BPF.getInstance().getBPFLogger()
					.error(TAG, e.getMessage());
		}
		return false;

	}

	/**
	 * Get record from database based on condition. This function is used to
	 * get only one record from database
	 * 
	 * @param table
	 *            Name of table in which record already exist
	 * @param condition
	 *            Get record where this condition matches
	 * @param field
	 *            Only get this field from resulted row
	 * @param orderBy
	 *            Orderby Clause for SQLite Query
	 * @return Return required field value if found otherwise return -1
	 */

	public int get_record(String table, String condition, String field,
			String orderBy) {
		
		List<Map<String, Object>> dbResult; 
		try {
			dbResult = BPF.getInstance().getBPFDB().query(
					table, null, condition, null, null, null, orderBy, null);

			if (dbResult == null) {
				BPF.getInstance().getBPFLogger()
						.warning(TAG, "Get record: Database query resulted in null");
				return -1;
			}

			if (dbResult.size() > 0) {			
				int result = (Integer) dbResult.get(0).get(field);
				return result;
			}
			
			BPF.getInstance().getBPFLogger().warning(TAG, 
					"get_record: didn't find anything with condition: " + condition);
			
		} catch (IndexOutOfBoundsException e) {
			BPF.getInstance().getBPFLogger().error(TAG, "get_record: Id Already deleted");
		} catch (BPFDBException e) {
			BPF.getInstance().getBPFLogger()
					.error(TAG, "get_record: " + e.getMessage());
		}
		
		return -1;
	}

	/**
	 * Get multiple record from database based on condition.
	 * 
	 * @param table
	 *            Name of table in which record already exist
	 * @param condition
	 *            Get records where this condition matches
	 * @param field
	 *            Only get this field from resulted row
	 * @return If found then return the list of required field values otherwise
	 *         return -1
	 */

	public List<Integer> get_records(String table, String condition,
			String field) {
		List<Integer> list = new ArrayList<Integer>();
		List<Map<String, Object>> dbResult; 
		
		try {
			dbResult = BPF.getInstance().getBPFDB().query(
					table, null, condition, null, null, null, null, null);

			if (dbResult == null) {
				BPF.getInstance().getBPFLogger().error(TAG,
						"get_records: Database query resulted in null");
				return null;
			}

			Map<String, Object> item;
			Iterator<Map<String, Object>> iterator = dbResult.iterator();
			
			while (iterator.hasNext()) {
				item = iterator.next();
				list.add((Integer) item.get(field));
				BPF.getInstance().getBPFLogger().debug(TAG,
						"Found it@:" + item);
			}
		} catch (IndexOutOfBoundsException e) {
			BPF.getInstance().getBPFLogger()
					.error(TAG, "get_records: Id Already deleted");
		} catch (BPFDBException e) {
			BPF.getInstance().getBPFLogger()
					.error(TAG, "get_records: " + e.getMessage());
		}
		
		return list;
	}

	/**
	 * Get the row count based on condition.
	 * 
	 * @param table
	 *            Name of table in which record already exist
	 * @param condition
	 *            Get record where this condition matches
	 * @param field
	 *            Only get this field from resulted row
	 * @return Total numbers rows
	 */
	public int get_count(String table, String condition, String[] field) {
		List<Map<String, Object>> dbResult; 
		try {
			dbResult = BPF.getInstance().getBPFDB().query(
					table, field, condition, null, null, null, null, null);

			if (dbResult == null) {
				BPF.getInstance().getBPFLogger()
						.debug(TAG, "get_count:" + "Database result was null");
				return 0;
			}

			if (dbResult.size() < 0) {
				BPF.getInstance().getBPFLogger().warning(TAG, "get_count: Resulted in 0");
			} else {
				BPF.getInstance().getBPFLogger().debug(
						TAG, "get_count: Counted: " + dbResult.size());
			}
		} catch (IndexOutOfBoundsException e) {
			BPF.getInstance().getBPFLogger().error(TAG, "get_count: Id Already deleted");
		} catch (BPFDBException e) {
			BPF.getInstance().getBPFLogger()
					.error("get_count: " + TAG, e.getMessage());
		}
		
		return 0;
	}

	/**
	 * Get the ids of all the bundles in a list.
	 * 
	 * @return Return List of bundle ids
	 */
	public Iterator<Integer> get_all_bundles() {

		List<Integer> list = new ArrayList<Integer>();
		List<Map<String, Object>> dbResult;
		try {

			dbResult = BPF.getInstance().getBPFDB()
					.query("bundles", null, null, null, null, null, null, null);
			
			if (dbResult == null) {
				BPF.getInstance().getBPFLogger()
						.debug(TAG, "Database result was null");
				return null;
			}

			Iterator<Map<String,Object>> iterator = dbResult.iterator();
			Map<String, Object> item;
			while (iterator.hasNext()) {
				item = iterator.next();
				list.add((Integer) item.get("id"));
				BPF.getInstance().getBPFLogger().debug(TAG, 
						"Found bundle with id: " + item.get("id"));
			}
			
		} catch (IndexOutOfBoundsException e) {
			BPF.getInstance().getBPFLogger().error(TAG, "get_all_bundles: Id Already deleted");
		} catch (BPFDBException e) {
			BPF.getInstance().getBPFLogger()
					.error(TAG, "get_all_bundles: " + e.getMessage());
		}

		return list.iterator();
	}

	/**
	 * Delete record from database based on condition.
	 * 
	 * @param table
	 *            Name of table in which record already exist
	 * @param condition
	 *            Get record where this condition matches
	 * @return True if successfully deleted else return false
	 */

	public boolean delete_record(String table, String condition) {

		try {
			int temp = BPF.getInstance().getBPFDB()
					.delete(table, condition, null);

			if (temp == 0) {
				BPF.getInstance().getBPFLogger().debug(TAG, "Already Deleted");
				return false;
			} else {
				BPF.getInstance().getBPFLogger().debug(TAG, "Deleted");
				return true;
			}
		} catch (BPFDBException e) {
			BPF.getInstance().getBPFLogger()
					.error(TAG, "delete_record: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Delete table from database.
	 * 
	 * @param tableName
	 *            of table
	 * @return True if successfully deleted else return false
	 */

	public boolean drop_table(String tableName) {

		try {

			BPF.getInstance().getBPFDB().execSQL("DROP TABLE " + tableName);

			BPF.getInstance().getBPFLogger()
					.debug(TAG, "Table Deleted: " + tableName);
			return true;

		} catch (BPFDBException e) {
			BPF.getInstance().getBPFLogger()
					.error(TAG, "drop_table: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Create new table in database.
	 * 
	 * @param create_table_query
	 *            Create new tabled using this query.
	 * @return True if successfully deleted else return false
	 */

	public boolean create_table(String create_table_query) {
		try {
			BPF.getInstance().getBPFLogger().debug(TAG, "Creating table");
			BPF.getInstance().getBPFDB().execSQL(create_table_query);
			return true;
		} catch (BPFDBException e) {
			BPF.getInstance().getBPFLogger()
					.error(TAG, "Failed to create table: " + e.getMessage());
		}
		return false;
	}

	/**
	 * Find if record exist in the table or not based on condition provided.
	 * 
	 * @param table
	 *            Name of table in which check if record exist
	 * @param condition
	 *            Get record where this condition matches
	 * @return True if record found else false
	 */
	public boolean find_record(String table, String condition) {
		
		List<Map<String, Object>> dbResult;
		
		try {
			dbResult = BPF.getInstance().getBPFDB().query(
					table, null, condition, null, null, null, null, null);
			if (dbResult == null) {
				BPF.getInstance().getBPFLogger().error(TAG, "Database result was null");
				return false;
			}
			
			if (dbResult.size() > 0) {
				BPF.getInstance().getBPFLogger().debug(TAG, "Found a row in the table: " + table
						+ " with the condition: " + condition);
				return true;
			}
			
			BPF.getInstance().getBPFLogger().warning(TAG,
					"find_record: Didn't find any rows with condition: " + condition);
		} catch (IndexOutOfBoundsException e) {
			BPF.getInstance().getBPFLogger().error(TAG, "find_record: Id Already deleted");
		} catch (BPFDBException e) {
			BPF.getInstance().getBPFLogger()
					.error(TAG, "find_record: Find record failed: " + e.getMessage());
		}

		return false;
	}

	/**
	 * Close database connection at the end of application
	 */
	public void close() {
		BPF.getInstance().getBPFDB().close();
	}

}
