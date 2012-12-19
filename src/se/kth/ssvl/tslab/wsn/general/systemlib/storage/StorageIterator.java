
package se.kth.ssvl.tslab.wsn.general.systemlib.storage;

import java.util.Iterator;

import se.kth.ssvl.tslab.wsn.general.servlib.storage.DatabaseManager;

/**
 * Class that implements Iterator to make iterators of the user defined types.
 * 
 * @author Sharjeel Ahmed (sharjeel@kth.se)
 * 
 * @param <Type>
 *            Pass the Type of Iterator eg: Bundle, Registration
 */
public class StorageIterator<Type> implements Iterator<Integer> {

	/**
	 * SQLite object
	 */
	private DatabaseManager impt_sqlite_;

	/**
	 * Pre condition for the iterator query
	 */
	private String pre_condition_;

	/**
	 * Post condition for the iterator query
	 */
	private String post_condition_;

	/**
	 * Condition to init Iterator
	 */
	private String first_condition_;

	/**
	 * Id to store the current id of the iterator
	 */
	private int id;

	/**
	 * Save temp id
	 */
	private int temp_id;

	/**
	 * SQLite table for iterator
	 */
	private String table_;

	/**
	 * Constructor to init Iterator
	 * 
	 * @param impt_sqlite
	 *            SQLite object to get data from the database
	 * @param tableName
	 *            Table Name of the SQLite to init iterator
	 * @param pre_condition
	 *            Pre-condition for the iterator
	 * @param post_condition
	 *            Post condition for the iterator
	 * @param first_condition
	 *            Condition for init the iterator
	 */
	public StorageIterator(DatabaseManager impt_sqlite, String tableName,
			String pre_condition, String post_condition, String first_condition) {
		impt_sqlite_ = impt_sqlite;
		table_ = tableName;
		pre_condition_ = pre_condition;
		post_condition_ = post_condition;
		first_condition_ = first_condition;
		id = -1;
	}

	/**
	 * Check if iterator has next object
	 * 
	 * @return If next object exist then return true else fasle.
	 */
	public boolean hasNext() {
		if (id == -1) {
			String field = "id";
			String orderBy = null;
			temp_id = impt_sqlite_.get_record(table_, first_condition_, field,
					orderBy);

			if (temp_id == -1) {
				return false;
			} else {
				return true;
			}
		} else {
			// int current_id = id;
			String condition = pre_condition_ + id + post_condition_;
			String field = "id";
			String orderBy = null;

			temp_id = impt_sqlite_
					.get_record(table_, condition, field, orderBy);
			if (temp_id == -1) {
				return false;
			} else {
				return true;
			}
		}
	}

	/**
	 * Get the next object of the iterator
	 * 
	 * @return Return the id of the next object
	 */
	public Integer next() {
		if (id == -1) {
			String field = "id";
			String orderBy = null;
			temp_id = impt_sqlite_.get_record(table_, first_condition_, field,
					orderBy);

			if (temp_id == -1) {
				return -1;
			} else {
				id = temp_id;
				return id;
			}
		} else {
			// int current_id = id;
			String condition = pre_condition_ + id + post_condition_;
			String field = "id";
			String orderBy = null;

			temp_id = impt_sqlite_
					.get_record(table_, condition, field, orderBy);
			if (temp_id == -1) {
				return -1;
			} else {
				id = temp_id;
				return id;
			}
		}
	}

	public void remove() {

	}

}
