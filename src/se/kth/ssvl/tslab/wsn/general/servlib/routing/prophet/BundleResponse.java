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

package se.kth.ssvl.tslab.wsn.general.servlib.routing.prophet;

import java.util.ArrayList;

public class BundleResponse {

	public byte flags;
	public short length;
	public short offerCount;
	public short reserve;
	public ArrayList<BundleResponseEntry> entries = new ArrayList<BundleResponseEntry>();

	public String toString() {
		String st = String.format("Flags %x\n" + "Lenght %d\n"
				+ "OfferCount %d\n" + "Reserve %d\n", flags, length,
				offerCount, reserve);

		for (BundleResponseEntry e : entries)
			st += e.toString();

		return st;
	}

}
