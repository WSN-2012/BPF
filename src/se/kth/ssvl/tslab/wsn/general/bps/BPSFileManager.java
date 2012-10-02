package se.kth.ssvl.tslab.wsn.general.bps;

import java.io.File;

public interface BPSFileManager {

	/**
	 * A method which is used by the library to read files 
	 * @param filename The filename that the library wants to read
	 * @return A string with the data read
	 */
	public abstract String read(String filename);
	
	
	/**
	 * A method which is used by the library to write files
	 * @param filename The name of the file to write to
	 * @param content The content to write to that file
	 * @return True if everything went ok, false if it didn't.
	 */
	public abstract boolean write(String filename, String content);
	
	
	/**
	 * A method which is used by the library to create a temporary file. 
	 * Note: It is up to you to store it somewhere nice!
	 * @see java.io.File.createTempFile()
	 * @param prefix The prefix string to use when generating the file
	 * @param suffix The suffix of the file generated
	 * @return A file with the created temporary file. 
	 */
	public abstract File createTempFile(String prefix, String suffix);
}
