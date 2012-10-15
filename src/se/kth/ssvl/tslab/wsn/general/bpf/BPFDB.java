package se.kth.ssvl.tslab.wsn.general.bpf;

import java.util.List;
import java.util.Map;

public interface BPFDB {

	/**
	 * Create or open an existing database
	 * 
	 * @param db_name
	 *            Name of database to be created or opened
	 * 
	 * @param create_tables
	 *            Query in order to create the tables in the database for the
	 *            first time
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
	 *            Map object contain String and Object pairs (String pointing
	 *            the column name and Object pointing the value to be stored)
	 *            containing all the values to be stored in the table
	 * @return If new row successfully added then return the newly added id
	 *         otherwise return -1
	 */
	public abstract int add_record(String table_name, Map<String, Object> mp);

	/**
	 * Update record in database table
	 * 
	 * @param table_name
	 *            Name of table in which record already exist
	 * @param mp
	 *            Map object contain String and Object pairs (String pointing
	 *            the column name and Object pointing the value to be updated)
	 *            containing all the values to be updated in the table
	 * @param conditions
	 *            The conditionss to be met in the SQL command. The String
	 *            points to the column name and the Object is the conditions it
	 *            has to equal
	 * @return The amount of rows updated
	 */
	public abstract int update_record(String table_name,
			Map<String, Object> mp, Map<String, Object> conditions);

	/**
	 * Get records from database based on conditions
	 * 
	 * @param table_name
	 *            Name of table in which records already exist
	 * @param conditions
	 *            The conditionss to be met in the SQL command. The String
	 *            points to the column name and the Object is the conditions it
	 *            has to equal
	 * @param orderBy
	 *            OrderBy clause for database query
	 * @param limit
	 *            The maximum number of rows to fetch
	 * @return Returns a List of Maps (where each map is a row) and the String
	 *         in the map contains the column name and the Object is the value
	 *         of the column
	 */
	public abstract List<Map<String, Object>> get_record(String table_name,
			Map<String, Object> conditions, String orderBy, String limit);

	/**
	 * Get the row count based on conditions.
	 * 
	 * @param table_name
	 *            Name of table in which record exists
	 * @param conditions
	 *            The conditionss to be met in the SQL command. The String
	 *            points to the column name and the Object is the conditions it
	 *            has to equal
	 * @param field
	 *            Only get this field from resulted row
	 * @return Total numbers rows
	 */
	public abstract int get_row_count(String table_name,
			Map<String, Object> conditions, String field);

	/**
	 * Get the ids of all the bundles in a list.
	 * 
	 * @return Return List of bundle ids
	 */
	public abstract List<Integer> get_all_bundles();

	/**
	 * Delete record from database based on conditions.
	 * 
	 * @param table_name
	 *            Name of table in which record already exist
	 * @param conditions
	 *            The conditionss to be met in the SQL command. The String
	 *            points to the column name and the Object is the conditions it
	 *            has to equal
	 * @return True if successfully deleted else return false
	 */
	public abstract boolean delete_record(String table_name,
			Map<String, Object> conditions);

	/**
	 * Delete table from database.
	 * 
	 * @param table_name
	 *            of table
	 * @return True if successfully deleted else return false
	 */
	public abstract boolean drop_table(String table_name);

	/**
	 * Create new table in database. (I have merged the init() and
	 * create_table_query() methods existing in the SQLiteImplementation)
	 * 
	 * @param query
	 *            Create new table using this query.
	 * @return True if successfully deleted else return false
	 */
	public abstract boolean create_table(String query);

	/**
	 * Find if record exist in the table or not based on conditions provided.
	 * 
	 * @param table_name
	 *            Name of table in which check if record exist
	 * @param conditions
	 *            The conditionss to be met in the SQL command. The String
	 *            points to the column name and the Object is the conditions it
	 *            has to equal
	 * @return True if record found else false
	 */
	public abstract boolean find_record(String table_name,
			Map<String, Object> conditions);

	/**
	 * Close database connection at the end of application
	 */
	public abstract void close();

}
