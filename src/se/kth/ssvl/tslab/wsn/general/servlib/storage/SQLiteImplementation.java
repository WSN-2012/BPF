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

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import se.kth.ssvl.tslab.wsn.general.systemlib.util.Logger;

/**
 * This class is the implementation of SQLite. This class directly interact with
 * SQLite and add, delete, get and update records.
 * 
 * @author Sharjeel Ahmed (sharjeel@kth.se)
 */

public class SQLiteImplementation {

	/**
	 * TAG for Android Logging
	 */
	private static final String TAG = "SQLiteImplementation";

	/**
	 * Database name for storing the records.
	 */

	private static final String DATABASE_NAME = "dtn";

	/**
	 * SQLiteDatabase object
	 */

	private SQLiteDatabase db;

	/**
	 * @return the db
	 */
	public SQLiteDatabase getDb() {
		return db;
	}

	/**
	 * Construct
	 * 
	 * @param ctx
	 *            Application context to open the database file
	 * @param table
	 *            Table base table name to do all the operations
	 */

	public SQLiteImplementation(Context ctx, String table) {
		try {
			db = ctx.openOrCreateDatabase(DATABASE_NAME, 1, null);

			init(table);

			Logger.getInstance().debug(TAG, "Can open database");

		} catch (SQLiteException e) {
			Logger.getInstance().error(TAG, "SQLite Exception in Constructor");
		}
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

	public int add(String table, ContentValues values) {
		try {
			Logger.getInstance().debug(TAG, "Adding Row");
			return (int) db.insert(table, null, values);
		} catch (SQLiteException e) {
			Logger.getInstance().error(TAG,
					"SQLite Exception while adding a row");
			return -1;
		}

	}

	/**
	 * Increment forwarded times
	 * 
	 * @param bundleid
	 */
	public void incForwardedTimes(int bundleid) {
		/*
		 * ContentValues cv = new ContentValues();
		 * cv.putNull("forwarded_times = forwarded_times+1");
		 * //cv.put("forwarded_times", "forwarded_times+1"); update("bundles",
		 * cv, "id = "+bundleid, null);
		 */
		// db.execSQL("UPDATE bundles Set forwarded_times = forwarded_times+1 WHERE id = "+bundleid);
		Logger.getInstance().info(TAG, "bundle " + bundleid + " incremented");
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
	 * @return True If new row successfully updated else return false otherwise
	 *         return -1
	 */

	public boolean update(String table, ContentValues values, String where,
			String[] whereArgs) {
		try {
			Logger.getInstance().debug(TAG, "Updating Row");
			db.update(table, values, where, whereArgs);
			return true;
		} catch (SQLiteException e) {
			Logger.getInstance().error(TAG,
					"SQLite Exception while updating a row");
		}
		return false;

	}

	/**
	 * Get record from database based on condition. This function is used to
	 * only one record from database
	 * 
	 * @param table
	 *            Name of table in which record already exist
	 * @param condition
	 *            Get record where this condition matches
	 * @param field
	 *            Only get this field from resulted row
	 * @param orderBy
	 *            Orderby Clause for SQLite Query
	 * @param limit
	 *            Number of row get
	 * @return Return required field value if found otherwise return -1
	 */

	public int get_record(String table, String condition, String field,
			String orderBy, String limit) {

		try {
			Cursor cursor = db.query(table, null, condition, null, null, null,
					orderBy, limit);

			int fieldColumn = cursor.getColumnIndex(field);

			if (cursor != null) {
				if (cursor.moveToFirst()) {
					int result = cursor.getInt(fieldColumn);
					cursor.close();
					return result;
				}
			} else {
				Logger.getInstance().debug(TAG, "Row not found!");
			}
			cursor.close();
		} catch (IndexOutOfBoundsException e) {
			Logger.getInstance().error(TAG, "Id Already deleted");
		} catch (SQLiteException e) {
			Logger.getInstance().error(TAG, "Coundn't run the query");
		} catch (Exception e) {
			Logger.getInstance().error(TAG, "General Exception");
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
			Cursor cursor = db.query(table, null, condition, null, null, null,
					null, null);

			int idColumn = cursor.getColumnIndex(field);

			if (cursor != null) {
				if (cursor.moveToFirst()) {
					do {
						list.add(cursor.getInt(idColumn));
						Logger.getInstance().debug(TAG,
								"Found it@:" + cursor.getInt(idColumn));
					} while (cursor.moveToNext());
				}
			} else {
				Logger.getInstance().debug(TAG, "Row not found!");
			}
			cursor.close();
		} catch (IndexOutOfBoundsException e) {
			Logger.getInstance().error(TAG, "Id Already deleted");
		} catch (SQLiteException e) {
			Logger.getInstance().error(TAG, "Coundn't run the query");
		} catch (Exception e) {
			Logger.getInstance().error(TAG, "General Exception");
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
			Cursor cursor = db.query(table, field, condition, null, null, null,
					null, null);

			if (cursor != null) {
				if (cursor.moveToFirst()) {
					count = cursor.getInt(0);
					Logger.getInstance().debug(TAG,
							"Records count @:" + cursor.getInt(0));
				}
			} else {
				Logger.getInstance().debug(TAG, "Row not found!");
			}
			cursor.close();
		} catch (IndexOutOfBoundsException e) {
			Logger.getInstance().error(TAG, "Id Already deleted");
		} catch (SQLiteException e) {
			Logger.getInstance().error(TAG, "Coundn't run the query");
		} catch (Exception e) {
			Logger.getInstance().error(TAG, "General Exception");
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

			Cursor cursor = db.query("bundles", null, null, null, null, null,
					null, null);
			Logger.getInstance().debug(TAG, "Reading Row");

			int idColumn = cursor.getColumnIndex("id");

			if (cursor != null) {
				if (cursor.moveToFirst()) {
					do {
						list.add(cursor.getInt(idColumn));
						Logger.getInstance().debug(TAG,
								"Found it@:" + cursor.getInt(idColumn));
					} while (cursor.moveToNext());
				}
			} else {
				Logger.getInstance().debug(TAG, "Row not found!");
				// return "Not Found";
			}
			cursor.close();
		} catch (IndexOutOfBoundsException e) {
			Logger.getInstance().error(TAG, "Id Already deleted");
			// return "Not Found";
		} catch (SQLiteException e) {
			Logger.getInstance().error(TAG, "Coundn't run the query");
			// return "Not Found";
		} catch (Exception e) {
			Logger.getInstance().error(TAG, "General Exception");
			// return "Not Found";
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
			int temp = db.delete(table, condition, null);

			if (temp == 0) {
				Logger.getInstance().debug(TAG, "Already Deleted");
				return false;
			} else {
				Logger.getInstance().debug(TAG, "Deleted");
				return true;
			}
		} catch (SQLiteException e) {
			Logger.getInstance().error(TAG, "Coundn't delete");
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

			db.execSQL("DROP TABLE " + tableName);

			Logger.getInstance().debug(TAG, "Table Deleted: " + tableName);
			return true;

		} catch (SQLiteException e) {
			Logger.getInstance().error(TAG, "Coundn't delete table");
			return false;
		}
	}

	/**
	 * Create new table in database.
	 * 
	 * @param create_table_query
	 *            Create new tabled using this query.
	 */

	public void init(String create_table_query) {

		try {
			db.execSQL(create_table_query);

			Logger.getInstance().debug("DB:", "Creating Table");
		} catch (SQLiteException e) {
			Logger.getInstance().error(TAG, "Coundn't open table");
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
			db.execSQL(create_table_query);

			Logger.getInstance().debug("DB:", "Creating Table");
			return true;
		} catch (SQLiteException e) {
			Logger.getInstance().error(TAG, "Coundn't open table");
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
	@SuppressWarnings("null")
	public boolean find_record(String table, String condition) {

		try {

			Cursor cursor = db.query(table, null, condition, null, null, null,
					null, null);
			Logger.getInstance().debug(TAG, "Finding Row");
			if (cursor != null) {
				cursor.moveToFirst();
				Logger.getInstance().debug(TAG, "Found it");
				cursor.close();
				return true;
			} else {
				cursor.close();
				return false;
			}
		} catch (IndexOutOfBoundsException e) {
			Logger.getInstance().error(TAG, "Id Already deleted");
			return false;
		} catch (SQLiteException e) {
			Logger.getInstance().error(TAG, "Coundn't run the query");
			return false;
		} catch (Exception e) {
			Logger.getInstance().error(TAG, "General Exception");
			return false;
		}
	}

	/**
	 * Close database connection at the end of application
	 */
	public void close() {
		db.close();
	}

}
