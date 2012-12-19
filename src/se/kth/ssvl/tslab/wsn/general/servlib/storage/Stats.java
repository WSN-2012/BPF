/*
 * Copyright 2012 KTH
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */

package se.kth.ssvl.tslab.wsn.general.servlib.storage;

public class Stats {
	private int size;
	private int stored;
	private int transmitted;
	private int received;
	
	public Stats (int totSize, int storedBundles, int transmittedBundles, int receivedBundles) {
		size = totSize;
		stored = storedBundles;
		transmitted = transmittedBundles;
		received = receivedBundles;
	}

	public int totalSize() {
		return size;
	}
	
	public int storedBundles() {
		return stored;
	}
	
	public int transmittedBundles() {
		return transmitted;
	}
	
	public int receivedBundles() {
		return received;
	}
}
