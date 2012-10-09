/*
 *	  This file is part of the Bytewalla Project
 *    More information can be found at "http://www.tslab.ssvl.kth.se/csd/projects/092106/".
 *    
 *    Copyright 2009 Telecommunication Systems Laboratory (TSLab), Royal Institute of Technology, Sweden.
 *    
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 * 
 *        http://www.apache.org/licenses/LICENSE-2.0
 * 
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *    
 */

package se.kth.ssvl.tslab.wsn.general.servlib.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import se.kth.ssvl.tslab.wsn.general.bpf.BPF;

/**
 * This class is the implementation of Storage. This class directly interact
 * with memory and write, read, delete and update files. This class is generic
 * so It can write multiple types of objects.
 * 
 * @author Sharjeel Ahmed (sharjeel@kth.se)
 */

public class StorageImplementation<Type> {

	/**
	 * TAG for Android Logging
	 */
	public static String TAG = "StorageImplementation";
	
	/**
	 * The fileManager that is used to manage files
	 */
	private FileManager fileManager;

	/**
	 * Construct
	 * 
	 */
	public StorageImplementation(String subDir) {
		try {
			fileManager = new FileManager(subDir, TAG);
		} catch (FileNotFoundException e) {
			BPF.getInstance().getBPFLogger().error(TAG, "Coulnd't init StorageImplementation since the subdir used couldn't be created");
		}
	}

	/**
	 * Write received object in new file
	 * 
	 * @param object
	 *            Object to write in file
	 * @param filename
	 *            Filename of file.
	 * @return True If file successfully stored on the memory else false
	 */
	public boolean add_object(Type object, String filename) {
		try {

			BPF.getInstance().getBPFLogger().debug(TAG, "Going to open a file:" + filename);

			File f = fileManager.createFile(filename); //TODO: Check that this method is working correctly.

			BPF.getInstance().getBPFLogger().debug(TAG, "File Open:" + filename);
			OutputStream out = new FileOutputStream(f);// Context..openFileOutput("test.txt");

			BPF.getInstance().getBPFLogger().debug(TAG, "Writing in file:" + filename);
			ObjectOutputStream objStream = new ObjectOutputStream(out);
			objStream.writeObject(object);

			objStream.flush();
			objStream.close();

			out.flush();
			out.close();

			BPF.getInstance().getBPFLogger().debug(TAG, "File Written" + filename);

			return true;
		} catch (Throwable t) {
			BPF.getInstance().getBPFLogger().error(TAG,
					"Writing in file " + filename + ":" + t.toString());
			return false;
		}
	}

	/**
	 * Get stored object from file.
	 * 
	 * @param filename
	 *            Read file with filename
	 * @return Read object from file and return that
	 */

	@SuppressWarnings("unchecked")
	public Type get_object(String filename) {

		Type object = null;
		ObjectInputStream objStream = null;
		InputStream in = null;
		try {
			File f = fileManager.createFile(filename);
			in = new FileInputStream(f);

			objStream = new ObjectInputStream(in);
			if (in != null) {
				object = (Type) objStream.readObject();
				objStream.close();
				in.close();
				return object;
			}
		} catch (java.io.FileNotFoundException e) {
			BPF.getInstance().getBPFLogger().debug(TAG, "Get Object:" + e.toString());
			// that's OK, we probably haven't created it yet
		} catch (Throwable t) {
			BPF.getInstance().getBPFLogger().error(TAG, "Unable to read file");
		}
		
		// Something went wrong, try to close the streams before returning
		try {
			if (objStream != null) {
				objStream.close();
			}
			if (in != null) {
				in.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * Create new file in the current path
	 * 
	 * @param filename
	 *            Create new file with filename
	 * @return True if file successfully created else false
	 */

	public boolean create_file(String filename) {
		File f = fileManager.createFile(filename);
		
		if (f != null) {
			return true;
		}
		return false;
	}

	/**
	 * Open file and return the file handler
	 * 
	 * @param filename
	 *            Name of the file
	 * @return File object
	 */

	public File get_file(String filename) {
		return fileManager.getFile(filename);
	}

	/**
	 * Delete file with filename in current path.
	 * 
	 * @param filename
	 *            Name of the file
	 * @return True if deleted successfully else false.
	 */

	public boolean delete_file(String filename) {
		return fileManager.deleteFile(filename);
	}

	/**
	 * Delete all the files inside the path directory
	 * 
	 * @param path
	 *            Path to delete all the files
	 * @return True if deleted successfully else false.
	 */

	public boolean delete_dir(String path) {
		try {
			File dir = new File(path);

			if (dir.exists()) {
				File[] files = dir.listFiles();
				for (int i = 0; i < files.length; i++) {
					files[i].delete();
				}
			}
			return true;
		} catch (Throwable t) {
			BPF.getInstance().getBPFLogger().error(TAG, "Unable to delete file");
			return false;
		}
	}

	/**
	 * Check if file with filename exist in the current directory.
	 * 
	 * @param filename
	 *            Filename to check if exist or not
	 * @return True if file exist else false.
	 */

	public boolean is_bundle_file(String filename) {
		try {
			File f = new File(dir_, filename);
			if (f.exists()) {
				return true;
			}
		} catch (Throwable t) {
			BPF.getInstance().getBPFLogger().error(TAG, "There is no bundle");
		}
		return false;
	}

	/**
	 * Create application path. If current path doesn't exist then make
	 * directories.
	 * 
	 * @param path
	 *            Create the current file.
	 * @return True if path successfully created
	 */

	public boolean create_dir(String path) {
		try {
			File dir = new File(path);
			if (!dir.isDirectory()) {
				if (dir.mkdirs()) {
					BPF.getInstance().getBPFLogger().debug(TAG,
							"Dir Created:" + dir.getAbsolutePath());
					return true;
				}
				BPF.getInstance().getBPFLogger().debug(TAG,
						"Dir Not Created:" + dir.getAbsolutePath());
				return false;
			}
			dir_ = dir;
			return true;
		} catch (Throwable t) {
			BPF.getInstance().getBPFLogger().error(TAG, "Unable to Create DIR");
		}
		return false;
	}

	/**
	 * Get total size of path.
	 * 
	 * @param path
	 *            Path to check the total size
	 * @return Total size of path.
	 */

	public long get_data_total_size(String path) {

		File dir = new File(path);
		if (dir.exists()) {
			BPF.getInstance().getBPFLogger().error(TAG, "Size Found");
			return dir.length();
		}
		BPF.getInstance().getBPFLogger().error(TAG, "Size Not Found");
		return 0;
	}

	/**
	 * Get the total size of directory including all the sub files.
	 * 
	 * @param path
	 *            Directory path to check the total size
	 * @return Total size of directory.
	 */

	public long get_directory_size(String path) {
		long result = 0;

		File dir = new File(path);

		if (dir.exists()) {
			File[] files = dir.listFiles();
			for (int i = 0; i < files.length; i++) {
				result += files[i].length();
			}
		}
		return result;
	}

	/**
	 * Get the total size of file
	 * 
	 * @param path
	 *            File path to check the total size
	 * @return Total size of file
	 */

	public long get_file_size(String path) {
		long result = 0;

		File dir = new File(path);
		if (dir.exists()) {
			result = dir.length();
		}
		return result;
	}

	/**
	 * Get the total size of file in current path
	 * 
	 * @param filename
	 *            File name to check the total size in current path
	 * @return Total size of file
	 */

	public long get_file_size_with_name(String filename) {
		long result = 0;
		File f = new File(dir_, filename);
		if (f.exists()) {
			result = f.length();
		}
		return result;
	}


}
