package se.kth.ssvl.tslab.wsn.general.bpf;

import java.util.List;
import java.util.Map;

import se.kth.ssvl.tslab.wsn.general.bpf.exceptions.BPFDBException;

public interface BPFDB {

	/**
	 * Convenience method for inserting a row into the database.
	 * 
	 * @param table
	 *            A string containing the table name to insert the row into
	 * @param values
	 *            This map should contain the initial column values for the row.
	 *            The keys should be the column names and the values the column
	 *            values
	 * @return the row ID of the newly inserted row or throw an BPFDBException
	 */
	public abstract int insert(String table, Map<String, Object> values)
			throws BPFDBException;

	/**
	 * Convenience method for updating rows in the database.
	 * 
	 * @param table
	 *            A string containing the table name to update in
	 * @param values
	 *            This map should contain the initial column values for the row.
	 *            The keys should be the column names and the values the column
	 *            values
	 * @param where
	 *            A string with the prepared WHERE statement. Add '?' (without
	 *            quotes) to specify a parameter. Passing null will update all
	 *            rows.
	 * @param whereArgs
	 *            A string array containing the values to meet the where string
	 * @return the number of rows affected
	 * @throws BPFDBException Throws this when there was a problem in updating
	 */
	public abstract int update(String table, Map<String, Object> values, String where,
			String[] whereArgs) throws BPFDBException;

	/**
	 * Query the given table for data
	 * 
	 * @param table
	 *            A string containing the table name to compile the query
	 *            against.
	 * @param columns
	 *            An array string containing a list of which columns to return.
	 *            Passing null will return all columns, which is discouraged to
	 *            prevent reading data from storage that isn't going to be used.
	 * @param selection
	 *            A filter declaring which rows to return, formatted as an SQL
	 *            WHERE clause (excluding the WHERE itself). Passing null will
	 *            return all rows for the given table.
	 * @param selectionArgs
	 *            You may include ?s in selection, which will be replaced by the
	 *            values from selectionArgs, in order that they appear in the
	 *            selection. The values will be bound as Strings.
	 * @param groupBy
	 *            A filter declaring how to group rows, formatted as an SQL
	 *            GROUP BY clause (excluding the GROUP BY itself). Passing null
	 *            will cause the rows to not be grouped.
	 * @param having
	 *            A filter declare which row groups to include in the cursor, if
	 *            row grouping is being used, formatted as an SQL HAVING clause
	 *            (excluding the HAVING itself). Passing null will cause all row
	 *            groups to be included, and is required when row grouping is
	 *            not being used.
	 * @param orderBy
	 *            How to order the rows, formatted as an SQL ORDER BY clause
	 *            (excluding the ORDER BY itself). Passing null will use the
	 *            default sort order, which may be unordered.
	 * @param limit
	 *            Limits the number of rows returned by the query, formatted as
	 *            LIMIT clause. Passing null denotes no LIMIT clause.
	 * @return A list with a map for each row.
	 * 
	 * @throws BPFDBException
	 *             This is thrown when there was an error in fetching the data
	 */
	public abstract List<Map<String, Object>> query(String table, String[] columns,
			String selection, String[] selectionArgs, String groupBy,
			String having, String orderBy, String limit) throws BPFDBException;

	/**
	 * Convenience method for deleting rows in the database.
	 * 
	 * @param table
	 *            A string containing the table name to delete from
	 * @param whereClause
	 *            A string containing the optional WHERE clause to apply when
	 *            deleting. Passing null will delete all rows.
	 * @param whereArgs
	 *            A string array containing the values to meet the whereClause
	 *            string
	 * @return the number of rows affected if a whereClause is passed in, 0
	 *         otherwise or throws BPFDBException
	 */
	public abstract int delete(String table, String whereClause,
			String[] whereArgs) throws BPFDBException;

	/**
	 * Execute a single SQL statement that is NOT a SELECT or any other SQL
	 * statement that returns data
	 * 
	 * @param sql
	 *            A string containing the SQL statement to be executed
	 */
	public abstract void execSQL(String sql) throws BPFDBException;

	/**
	 * Releases a reference to the object, closing the object if the last
	 * reference was released.
	 */
	public abstract void close();

}
