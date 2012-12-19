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

import se.kth.ssvl.tslab.wsn.general.servlib.routing.prophet.BaseTLV.TLVType;

public class ProphetBundle {
	// private static final String TAG = "ProphetBundle";

	byte code;
	Hello hello = new Hello();
	short length;
	byte protocol;
	short receiver;
	byte result;
	RIBDictionary rIBDictionary = new RIBDictionary();
	RIBInformationBase rIBInformationBase = new RIBInformationBase();
	BundleOffer bundleOffer = new BundleOffer();
	BundleResponse bundleResponse = new BundleResponse();
	short sender;
	short submessage;

	int trans_id;
	TLVType type;
	byte versionFlags;

	@Override
	public String toString() {
		String st = String.format("Protocol %x\n" + "VersionFlags %x\n"
				+ "Result %x\n" + "Code %x\n" + "Receiver %d\n" + "Sender %d\n"
				+ "Transaction ID %d\n" + "Submessage %x\n" + "Length %d\n"
				+ "Type %s\n", protocol, versionFlags, result, code, receiver,
				sender, trans_id, submessage, length, type.getCaption());

		switch (type) {
		case HELLO:
			st += hello.toString();
			break;
		case ERROR:
			break;

		case RIBDICTIONARY:
			st += rIBDictionary.toString();
			break;

		case RIBINFORMATIONBASE:
			st += rIBInformationBase.toString();
			break;
		case BUNDLEOFFER:
			st += bundleOffer;
			break;
		case BUNDLERESPONSE:
			st += bundleResponse;
			break;
		}
		return st;
	}
}
