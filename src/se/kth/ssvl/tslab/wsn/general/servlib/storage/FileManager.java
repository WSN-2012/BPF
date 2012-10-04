package se.kth.ssvl.tslab.wsn.general.servlib.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileManager {
	
	/**
	 * The directory for which this instance will operate within
	 */
	private File dir;
	
	
	/**
	 * The constructor will create a class which can be used by any object to manage its files.
	 * @param subDir The directory to operate within, which is a subdir to the root path configured in the configuration.
	 * @throws FileNotFoundException Constructor will throw this when the directory wasn't able to be created. 
	 */
	public FileManager(String subDir) throws FileNotFoundException {
		dir = new File(subDir);
		
		if (!dir.exists()) {
			if (!dir.mkdir()) {
				throw new FileNotFoundException("Couldn't create the subdirectory");
			}
		}
	}

	/**
	 * This method will create a file within this instance's directory. (passed in the constructor)
	 * @param filename The filename of the file created.
	 * @return The file that will be created. If there was an error or if the file exists, the method will return null
	 */
	public File createFile(String filename) {
		File f = new File(dir, filename);
		try {
			if (f.createNewFile()) {
				return f;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
