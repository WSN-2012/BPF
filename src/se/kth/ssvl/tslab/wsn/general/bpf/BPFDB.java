package se.kth.ssvl.tslab.wsn.general.bpf;

import java.util.List;
import java.util.Map;
import se.kth.ssvl.tslab.wsn.general.utils.*;

public interface BPFDB {

	/**
	 * Create or open an existing database
	 * 
	 * @param databaseName
	 *            Name of database to be created or opened         
	 * @return True if the database is created else return false
	 * 
	 */
	public abstract boolean createDatabase(String databaseName);

	
	/**
	 * Add new record to database table
	 * 
	 * @param tableName
	 *            Name of table in which to add a record
	 * @param mp
	 *            Map object contain String and Object pairs (String pointing the column name and 
	 *            Object pointing the value to be stored) comprising all the values to be stored 
	 *            in the table 
	 * @return If new row successfully added then return the newly added id
	 *         otherwise return -1
	 */
	public abstract int addRecord(String tableName, Map<String, Object> mp);

	
	/** 
	 * Update record in database table
	 * 
	 * @param tableName
	 *            Name of table in which record already exist
	 * @param mp
	 *            Map object contain String and Object pairs (String pointing the column name and 
	 *            Object pointing the value to be updated) comprising all the values to be updated 
	 *            in the table 
	 * @param conditions
	 *            Map object contain String and Object pairs (String holding the column name and 
	 *            Object holding the filter for this column) comprising the conditions based on which the table should be
	 *            updated 
	 * @return True If new row successfully updated otherwise return false
	 */
	public abstract boolean updateRecord(String tableName, Map<String, Object> mp, Map<String, Object> conditions);

	
	/**
	 * Get records from database based on condition
	 * 
	 * @param tableName
	 *            Name of table in which records already exist
	 * @param conditions
	 *            Map object contain String and Object pairs (String holding the column name and 
	 *            Object holding the filter for this column) comprising the conditions to return the records
	 * @param field
	 *            Only get this field from resulted row
	 * @param orderBy
	 *            OrderBy Clause to order the record results based on specified columns and order
	 * @param limit
	 *            Number of row to get
	 * @return Returns a List of Maps (where each map is a row) and the String
	 *         in the map contains the column name and the Object is the value
	 *         of the column
	 */
	public abstract List<Map<String, Object>> getRecords(String tableName, Map<String, Object> conditions, Object field, String orderBy, String limit);	

	
	/**
	 * Get the row count based on condition.
	 * 
	 * @param tableName
	 *            Name of table in which record exists
	 * @param conditions
	 *            Map object contain String and Object pairs (String holding the column name and 
	 *            Object holding the filter for this column) comprising the conditions for row counting
	 * @param field
	 *            Only get this field from resulted row
	 * @return Total numbers rows
	 */
	public abstract int getRowCount(String tableName, Map<String, Object> conditions, Object field);

	/**
	 * Get the ids of all the bundles in a list.
	 * 
	 * @return Return List of bundle ids
	 */
	public abstract List<Integer> get_all_bundles();

	
	/**
	 * Delete record from database based on condition.
	 * 
	 * @param tableName
	 *            Name of table in which record already exist
	 * @param conditions
	 *            Map object contain String and Object pairs (String holding the column name and 
	 *            Object holding the filter for this column) comprising the conditions for deleting a row
	 * @return True if successfully deleted else return false
	 */
	public abstract boolean deleteRecord(String tableName, Map<String, Object> conditions);


	/**
	 * Delete table from database.
	 * 
	 * @param tableName
	 *            Name of the table to be deleted
	 * @return True if successfully deleted else return false
	 */
	public abstract boolean dropTable(String tableName);


	/**
	 * Create new table in database.
	 * 
	 * @param tableName
	 *            Create new table using that
	 * @param columns
	 * 			  Map object contain String and String pairs (String holding the column name and
	 * 			  and a pair of Strings in which the first String represents the data type and 
	 * 			  the second the constraints) comprising the structure of the table to be created
	 * @return True if successfully deleted else return false
	 */
	public abstract boolean createTable(String tableName, Map<String, Pair<String,String>> columns);

	
	/**
	 * Find if record exist in the table or not based on condition provided.
	 * 
	 * @param tableName
	 *            Name of table in which check if record exist
	 * @param conditions
	 *            Map object contain String and Object pairs (String holding the column name and 
	 *            Object holding the filter for this column) comprising the conditions for finding a record
	 * @return True if record found else false
	 */
	public abstract boolean findRecord(String tableName, Map<String, Object> conditions);

	/**
	 * Close database connection at the end of application
	 */
	public abstract void close();

}
