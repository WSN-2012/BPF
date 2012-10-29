package bpf;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import se.kth.ssvl.tslab.wsn.general.bpf.BPFDB;
import se.kth.ssvl.tslab.wsn.general.bpf.exceptions.BPFDBException;

public class DB implements BPFDB {

	private static final String TAG = "DB";
	
	private Connection connection = null;
	private Logger logger;
	
	public DB(File dbFile, Logger logger) {
		// Create the file if it doesn't exist
		if (!dbFile.exists()) {
			try {
				dbFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// Continue to load the SQLite driver
	    try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	    
	    try {
			connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile.getAbsolutePath());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    // Init the logger
	    this.logger = logger;
	    logger.debug(TAG, "The DB class has been initialized properly");
	}

	
	private String getCommaFromKey(Map<String, Object> map) {
		StringBuilder result = new StringBuilder(100);
		int c = 0;
		for (String key : map.keySet()) {
			if (c < map.size()) {
				result.append(key + ", ");
			} else {
				result.append(key);
			}
			c++;
		}
		
		return result.toString();
	}
	
	private String getCommaFromValue(Map<String, Object> map) {
		StringBuilder result = new StringBuilder(100);
		int c = 0;
		for (Object value : map.values()) {
			if (c < map.size()) {
				result.append(value.toString() + ", ");
			} else {
				result.append(value.toString());
			}
			c++;
		}
		
		return result.toString();
	}
	
	private String getCommaFromArray(String[] array) {
		StringBuilder result = new StringBuilder(100);
		int c = 0;
		for (String item : array) {
			if (c < array.length) {
				result.append(item + ", ");
			} else {
				result.append(item);
			}
			c++;
		}
		return result.toString();
	}
	
	private String getUpdateStringFromMap(Map<String, Object> map) {
		StringBuilder result = new StringBuilder(100);
		int c = 0;
		for (Map.Entry<String, Object> item : map.entrySet()) {
			if (c < map.size()) {
				result.append(item.getKey() + "='" + item.getValue() + "', ");
			} else {
				result.append(item.getKey() + "='" + item.getValue() + "'");
			}
			c++;
		}
		
		return result.toString();
	}
	
	/* *************************** */
	
	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int delete(String table, String whereClause, String[] whereArgs)
			throws BPFDBException {

		PreparedStatement statement = null;

		try {
			if (whereClause != null && !whereClause.isEmpty()) {
				statement = connection.prepareStatement(
						"DELETE FROM " + table + " WHERE " + whereClause);
				for (int i=0; i < whereArgs.length; i++) {
					statement.setString(i + 1, whereArgs[i]);
				}
			} else {
				statement = connection.prepareStatement("DELETE FROM " + table);
			}
			logger.debug(TAG, "Deleting with SQL: " + statement);

			return statement.executeUpdate();
		} catch (SQLException e) {
			throw new BPFDBException("Couldn't delete, there was an SQLException: " + e.getMessage());
		}
	}

	public void execSQL(String sql) throws BPFDBException {
		try {
			Statement statement = connection.createStatement();
			logger.debug(TAG, "Executing SQL: " + sql);
			statement.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int insert(String table, Map<String, Object> values)
			throws BPFDBException {
				
		PreparedStatement statement = null;
		StringBuffer sql = new StringBuffer(150);
		
		// Add the basics to the sql
		sql.append("INSERT INTO ");
		sql.append(table);
		sql.append(" (");
		
		// Add the column names
		sql.append(getCommaFromKey(values));
		
		// Add the values
		sql.append(") VALUES (");
		sql.append(getCommaFromValue(values));
		sql.append(")");
		
		logger.debug(TAG, "INSERT SQL: " + sql.toString());

		try {
			statement = connection.prepareStatement(sql.toString());
			return statement.executeUpdate();
		} catch (SQLException e) {
			throw new BPFDBException("Unable to insert the new row, reason: "
					+ e.getMessage());
		}
	}

	public ResultSet query(String table, String[] columns, String selection,
			String[] selectionArgs, String groupBy, String having,
			String orderBy, String limit) throws BPFDBException {
		
		// Error check
		if (table == null || table.isEmpty()) {
			throw new BPFDBException("The table was null or empty, cannot do this");
		}
		
		// Start building the SQL
		StringBuilder sql = new StringBuilder(150);
		sql.append("SELECT");
		
		// Add selection for the specified columns (if specified)
		if (columns != null && columns.length > 0) {
			sql.append(" " + getCommaFromArray(columns) + " FROM " + table);
		} else {
			sql.append(" * FROM" + table);
		}
		
		// Add the where selection but not they arguments quite yet (prepared statement will do this)
		if (selection != null && !selection.isEmpty()) {
			sql.append(" WHERE");
			sql.append(selection);
		}
		
		// Group by the specified argument (if spcified)
		if (groupBy != null && !groupBy.isEmpty()) {
			sql.append(" GROUP BY ");
			sql.append(groupBy);
		}
		
		// Add the having operator (if specified)
		if (having != null && !having.isEmpty()) {
			sql.append(" HAVING ");
			sql.append(having);
		}
		
		// Add the group by operator (if specified)
		if (orderBy != null && !orderBy.isEmpty()) {
			sql.append(" GROUP BY ");
			sql.append(groupBy);
		}
		
		// Add the limit (if specified)
		if (limit != null && !limit.isEmpty()) {
			sql.append(" LIMIT ");
			sql.append(limit);
		}
		
		PreparedStatement statement = null;
		ResultSet result;
		try {
			statement = connection.prepareStatement(sql.toString());

			// Add all the selection args
			if (selection != null && !selection.isEmpty()) {
				for (int i = 0; i < selectionArgs.length; i++) {
					statement.setString(i + 1, selectionArgs[i]);
				}
			}
			
			logger.debug(TAG, "Query SQL: " + statement);
			
			result = statement.getResultSet();
			if (result == null) {
				throw new BPFDBException("The result was null when querying the database");
			}
		} catch (SQLException e) {
			throw new BPFDBException("There was an error in executing the SQL: " + e.getMessage());
		}
		
		return result;
	}

	public int update(String table, Map<String, Object> values, String where,
			String[] whereArgs) throws BPFDBException {
		
		// Error check
		if (table == null || table.isEmpty()) {
			throw new BPFDBException("The table was null or empty, cannot do this");
		}
		
		PreparedStatement statement = null; 
		StringBuffer sql = new StringBuffer(150);
		
		// Start building the sql
		sql.append("UPDATE " + table + " SET");
		
		// Add the update values
		sql.append(getUpdateStringFromMap(values));
		
		// Add the where statement (if specified)
		if (where != null && !where.isEmpty()) {
			sql.append(" WHERE ");
			sql.append(where);
		}
		
		try {
			statement = connection.prepareStatement(sql.toString());
			
			if (where != null && !where.isEmpty()) {
				for (int i=0; i < whereArgs.length; i++) {
					statement.setString(i + 1, whereArgs[i]);
				}
			}
			logger.debug(TAG, "Updating with SQL: " + statement);

			return statement.executeUpdate();
		} catch (SQLException e) {
			throw new BPFDBException(
					"Couldn't update table, since there was an SQLException: "
							+ e.getMessage());
		}
	}
}
