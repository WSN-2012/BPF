package se.kth.ssvl.tslab.wsn.general.bpf;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/*
 * I would like to provide some functionalities in the abstract methods before the developer can use them
 * How can I do that???
 */
public interface BPFDB {
	
	
	/**Create or open an existing database
	 * (Current creation in BundleStore->103, SQLiteImplementation constructor->71)
	 * (How can I specify that this method will have to call init() inside to create the tables?)
	 * 
	 * @param db_name
	 *            Name of database to be created or opened
	 *            
	 * @param create_tables
	 *            Query in order to create the tables in the database for the first time
	 *  
	 * @return True if the database is created else return false
	 * 
	 */
	public abstract boolean createdb(String db_name, String create_tables);
	
	
	/**
	 * Add new record to database table
	 * 
	 * @param table_name
	 *            Name of table in which function will add record
	 * @param mp
	 *            Map object contain String and Object pairs (String pointing the column name and 
	 *            Object pointing the value to be stored) containing all the values to be stored 
	 *            in the table 
	 * @return If new row successfully added then return the newly added id
	 *         otherwise return -1
	 */
	public abstract int add_record(String table_name, Map<String, Object> mp);
	
	
	/** Update record in database table
	 * (I deleted the String where parameter from the previous code because I think it is not useful)
	 * 
	 * @param table_name
	 *            Name of table in which record already exist
	 *@param mp
	 *            Map object contain String and Object pairs (String pointing the column name and 
	 *            Object pointing the value to be updated) containing all the values to be updated 
	 *            in the table 
	 * @param condition
	 *            containing the conditions based on which the table should be
	 *            updated (where part of a sql command)
	 * @return True If new row successfully updated otherwise return false
	 */
	public abstract boolean update_record(String table_name, Map<String, Object> mp, String[] condition);
	
	
	/**
	 * Get record from database based on condition. This function is used to
	 * only one record from database
	 * (I would like to merge the method get_record() with the method get_records() because I think it is useless to have both of them
	 * keeping only the later)
	 * (The return value is integer in the Bytewalla implementation. Is it necessary for us or we could return String instead?)
	 * 
	 * @param table_name
	 *            Name of table in which record already exist
	 * @param condition
	 *            Get record where these conditions match
	 *            (where part of sql command)
	 * @param field
	 *            Only get this field from resulted row
	 * @param orderBy
	 *            OrderBy Clause for database query
	 * @param limit
	 *            Number of row to get
	 * @return If found then return the list of field values otherwise return -1
	 */
	public abstract List<Integer> get_record(String table_name, String[] condition, String field, String orderBy, String limit);	
	
	
	/**
	 * Get the row count based on condition.
	 * 
	 * @param table_name
	 *            Name of table in which record exists
	 * @param condition
	 *            Get record where these conditions match
	 *            (where part of sql command)
	 * @param field
	 *            Only get this field from resulted row
	 * @return Total numbers rows
	 */
	public abstract int get_row_count(String table_name, String[] condition, String field);
	
	
	/**
	 * Get the ids of all the bundles in a list.
	 * 
	 * @return Return List of bundle ids
	 */
	public abstract List<Integer> get_all_bundles();
	
	
	/**
	 * Delete record from database based on condition.
	 * 
	 * @param table_name
	 *            Name of table in which record already exist
	 * @param condition
	 *            Get record where these conditions match
	 *            (where part of sql command)
	 * @return True if successfully deleted else return false
	 */
	public abstract boolean delete_record(String table_name, String[] condition);
	
	
	/**
	 * Delete table from database.
	 * 
	 * @param table_name
	 *            of table
	 * @return True if successfully deleted else return false
	 */
	public abstract boolean drop_table(String table_name);
	
	
	/**
	 * Create new table in database.
	 * (I have merged the init() and create_table_query() methods existing in the SQLiteImplementation)
	 * 
	 * @param query
	 *            Create new table using this query.
	 * @return True if successfully deleted else return false
	 */
	public abstract boolean create_table(String query);
	
	
	/**
	 * Find if record exist in the table or not based on condition provided.
	 * 
	 * @param table_name
	 *            Name of table in which check if record exist
	 * @param condition
	 *            Get record where these conditions match
	 *            (where part of sql command)
	 * @return True if record found else false
	 */
	public abstract boolean find_record(String table_name, String[] condition);
	
	
	/**
	 * Close database connection at the end of application
	 */
	public abstract void close();
	

}
