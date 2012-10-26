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

import java.sql.ResultSet;
import java.sql.SQLException;
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
					.error(TAG, "SQLite Exception while adding a row");
			return -1;
		}

	}

	/**
	 * Increment forwarded times
	 * 
	 * @param bundleid
	 */
	public void incForwardedTimes(int bundleid) throws BPFDBException {
		/*
		 * ContentValues cv = new ContentValues();
		 * cv.putNull("forwarded_times = forwarded_times+1");
		 * //cv.put("forwarded_times", "forwarded_times+1"); update("bundles",
		 * cv, "id = "+bundleid, null);
		 */
		// db.execSQL("UPDATE bundles Set forwarded_times = forwarded_times+1 WHERE id = "+bundleid);
		// BPF.getInstance().getBPFLogger().info(TAG, "bundle " + bundleid +
		// " incremented");
		throw new BPFDBException("incForwardTimes not implemented yet!");
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
		try {
			ResultSet rs = BPF.getInstance().getBPFDB().query(
					table, null, condition, null, null, null, orderBy, null);

			if (rs == null) {
				BPF.getInstance().getBPFLogger()
						.debug(TAG, "Get record: Resultset was null");
				return -1;
			}

			int fieldColumn = rs.findColumn(field);
			if (rs.first()) {
				int result = rs.getInt(fieldColumn);
				rs.close();
				return result;
			}
			
		} catch (IndexOutOfBoundsException e) {
			BPF.getInstance().getBPFLogger().error(TAG, "Id Already deleted");
		} catch (BPFDBException e) {
			BPF.getInstance().getBPFLogger()
					.error(TAG, e.getMessage());
		} catch (SQLException e) {
			BPF.getInstance().getBPFLogger().error(TAG, e.getMessage());
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
		try {
			ResultSet rs = BPF.getInstance().getBPFDB().query(
					table, null, condition, null, null, null, null, null);

			if (rs == null) {
				BPF.getInstance().getBPFLogger().error(TAG, "Resultlist was null");
				return null;
			}
			
			int idColumn = rs.findColumn(field);

			if (rs != null) {
				if (rs.first()) {
					do {
						list.add(rs.getInt(idColumn));
						BPF.getInstance().getBPFLogger().debug(
								TAG, "Found it@:" + rs.getInt(idColumn));
					} while (rs.next());
				}
			}
			rs.close();
			
		} catch (IndexOutOfBoundsException e) {
			BPF.getInstance().getBPFLogger().error(TAG, "Id Already deleted");
		} catch (BPFDBException e) {
			BPF.getInstance().getBPFLogger()
					.error(TAG, e.getMessage());
		} catch (SQLException e) {
			BPF.getInstance().getBPFLogger().error(TAG, e.getMessage());
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
		int count = 0;
		try {
			ResultSet rs = BPF.getInstance().getBPFDB().query(
					table, field, condition, null, null, null, null, null);

			if (rs == null) {
				BPF.getInstance().getBPFLogger()
						.debug(TAG, "Resultset was null");
				return 0;
			}

			if (rs.first()) {
				count = rs.getInt(0);
				BPF.getInstance().getBPFLogger().debug(
						TAG, "Records count @:" + rs.getInt(0));
			}
			rs.close();
		} catch (IndexOutOfBoundsException e) {
			BPF.getInstance().getBPFLogger().error(TAG, "Id Already deleted");
		} catch (BPFDBException e) {
			BPF.getInstance().getBPFLogger()
					.error("There was an error when trying to fetch the count: " + TAG, e.getMessage());
		} catch (SQLException e) {
			BPF.getInstance().getBPFLogger().error(TAG, e.getMessage());
		}
		return count;
	}

	/**
	 * Get the ids of all the bundles in a list.
	 * 
	 * @return Return List of bundle ids
	 */
	public Iterator<Integer> get_all_bundles() {

		List<Integer> list = new ArrayList<Integer>();

		try {

			ResultSet rs = BPF.getInstance().getBPFDB()
					.query("bundles", null, null, null, null, null, null, null);
			
			if (rs == null) {
				BPF.getInstance().getBPFLogger()
						.debug(TAG, "Resultset was null");
				return null;
			}

			int idColumn = rs.findColumn("id");
			if (rs.first()) {
				do {
					list.add(rs.getInt(idColumn));
					BPF.getInstance().getBPFLogger()
							.debug(TAG, "Found bundle with id: " + rs.getInt(idColumn));
				} while (rs.next());
			}
			rs.close();
		} catch (IndexOutOfBoundsException e) {
			BPF.getInstance().getBPFLogger().error(TAG, "Id Already deleted");
		} catch (BPFDBException e) {
			BPF.getInstance().getBPFLogger()
					.error(TAG, "Couldn't get all bundles: " + e.getMessage());
		} catch (SQLException e) {
			BPF.getInstance().getBPFLogger().error(TAG, e.getMessage());
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
					.error(TAG, "Failed in deleting record: " + e.getMessage());
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
					.error(TAG, "Delete table failed: " + e.getMessage());
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
		try {
			ResultSet rs = BPF.getInstance().getBPFDB().query(
					table, null, condition, null, null, null, null, null);
			if (rs == null) {
				BPF.getInstance().getBPFLogger()
						.error(TAG, "Resultset was null");
				return false;
			}

			rs.first();
			BPF.getInstance()
					.getBPFLogger()
					.debug(TAG,
							"Found a row in the table: " + table
									+ " with the condition: " + condition);
			rs.close();
			return true;
			
		} catch (IndexOutOfBoundsException e) {
			BPF.getInstance().getBPFLogger().error(TAG, "Id Already deleted");
			return false;
		} catch (BPFDBException e) {
			BPF.getInstance().getBPFLogger()
					.error(TAG, "Find record failed: " + e.getMessage());
			return false;
		} catch (SQLException e) {
			BPF.getInstance().getBPFLogger().error(TAG, e.getMessage());
			return false;
		}
	}

	/**
	 * Close database connection at the end of application
	 */
	public void close() {
		BPF.getInstance().getBPFDB().close();
	}

}
