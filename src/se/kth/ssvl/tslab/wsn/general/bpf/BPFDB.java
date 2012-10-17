package se.kth.ssvl.tslab.wsn.general.bpf;

import java.util.List;
import java.util.Map;

import se.kth.ssvl.tslab.wsn.general.bpf.exceptions.BPFDBException;

public interface BPFDB {

	/**
	 * Create or open an existing database
	 * 
	 * @param query
	 * 			A string containing the SQL command to create or open an existing database
	 * @return True if the database is created else throw a database Exception
	 * 
	 */
	public abstract boolean createDatabase(String query)throws BPFDBException;

	
	/**
	 * Add new record to database table
	 * 
	 * @param query
	 * 			A string containing the SQL command to add a record in the appropriate table
	 * @return If new row successfully added then return the newly added id
	 *         else throw a database Exception
	 */
	public abstract int addRecord(String query)throws BPFDBException;

	
	/** 
	 * Update record in database table
	 * 
	 * @param query
	 * 			A string containing the SQL command to update a record in the appropriate table
	 * @return The amount of rows updated else throw a database Exception
	 */
	public abstract int updateRecord(String query)throws BPFDBException;

	
	/**
	 * @param query
	 * 			A string containing the SQL command to get records from the appropriate table
	 * @return 	Returns a List of Map Objects (where each map is a row) else throw a database Exception.
	 * 			The String in the map contains the column name and the Object is the value
	 *         	of the column.
	 */
	public abstract List<Map<String, Object>> getRecords(String query)throws BPFDBException;	

	
	/**
	 * Get the row count based on conditions.
	 * 
	 * @param query
	 * 			A string containing the SQL command to count the rows based on conditions
	 * @return Total number of rows else throw a database Exception
	 */
	public abstract int getRowCount(String query)throws BPFDBException;

	/**
	 * Get the ids of all the bundles in a list.
	 * 
	 * @param query
	 * 			A string containing the SQL command to the ids of all bundles
	 * @return Return List of bundle ids else throw a database Exception
	 */
	public abstract List<Integer> getAllBundles(String query)throws BPFDBException;

	
	/**
	 * Delete record from database based on conditions.
	 * 
	 * @param query
	 * 			A string containing the SQL command to delete a record from 
	 * 			the database based on conditions
	 * @return True if successfully deleted else throw a database Exception
	 */
	public abstract boolean deleteRecord(String query) throws BPFDBException;


	/**
	 * Delete table from database.
	 * 
	 * @param query
	 * 			A string containing the SQL command to delete a table from 
	 * 			the database
	 * @return True if successfully deleted else throw a database Exception
	 */
	public abstract boolean dropTable(String query) throws BPFDBException;


	/**
	 * Create new table in database.
	 * 
	 * @param query
	 * 			A string containing the SQL command to create a table in the database
	 * @return True if successfully deleted else throw a database Exception
	 */
	public abstract boolean createTable(String query) throws BPFDBException;

	
	/**
	 * Find if record exist in the table or not based on conditions provided.
	 * 
	 * @param query
	 * 			A string containing the SQL command to find if a record exist in the
	 * 			appropriate table based on conditions
	 * @return True if record found else throw a database Exception
	 */
	public abstract boolean findRecord(String query)throws BPFDBException;

	/**
	 * Close database connection at the end of application
	 * Throws a database exception if an error occurs
	 */
	public abstract void close() throws BPFDBException;

}
