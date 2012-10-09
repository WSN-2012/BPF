package se.kth.ssvl.tslab.wsn.general.servlib.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import se.kth.ssvl.tslab.wsn.general.bpf.BPF;

public class FileManager {
	
	/**
	 * The directory for which this instance will operate within
	 */
	private File dir;
	
	/**
	 * The tag for the logger
	 */
	private String TAG;
	
	
	/**
	 * The constructor will create a class which can be used by any object to manage its files.
	 * @param subDir The directory to operate within, which is a subdir to the root path configured in the configuration.
	 * @param tag The tag for the logger when the FileManager is used. Preferably something like: YouClass-FileManager
	 * @throws FileNotFoundException Constructor will throw this when the directory wasn't able to be created. 
	 */
	public FileManager(String subDir, String tag) throws FileNotFoundException {
		dir = new File(subDir);
		TAG = tag;
		
		if (!dir.exists()) {
			if (!dir.mkdir()) {
				throw new FileNotFoundException("Couldn't create the subdirectory");
			}
		}
	}

	/**
	 * This method will create a file within this instance's directory. (passed in the constructor)
	 * @param fileName The filename of the file created.
	 * @return The file that will be created. If there was an error or if the file exists, the method will return null
	 */
	public File createFile(String fileName) {
		File f = new File(dir, fileName);
		try {
			if (f.createNewFile()) {
				BPF.getInstance().getBPFLogger().debug(TAG, "Successfully created file :" + f.getAbsolutePath()); //TODO: Check that this is returning the filename as well
				return f;
			}
		} catch (IOException e) {
			BPF.getInstance().getBPFLogger().error(TAG, e.toString());
		}
		return null;
	}
	
	/**
	 * Create a temporary file.
	 * @param prefix The prefix of the filename
	 * @param suffix The suffix of the filename
	 * @return
	 */
	public File createTempFile(String prefix, String suffix) {
		try {
			return File.createTempFile(prefix, suffix, dir);
		} catch (IOException e) {
			BPF.getInstance().getBPFLogger().error(TAG, e.toString());
		}
		return null;
	}
	
	
	
	
}
