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

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseTLV {

	public static enum TLVType {
		BUNDLEOFFER("BUNDLEOFFER", (byte) 0xA2), BUNDLERESPONSE(
				"BUNDLERESPONSE", (byte) 0xA3), ERROR("ERROR", (byte) 0x02), HELLO(
				"HELLO", (byte) 0x01), RIBDICTIONARY("RIBDICTIONARY",
				(byte) 0xA0), RIBINFORMATIONBASE("RIBINFORMATIONBASE",
				(byte) 0xA1);

		private static final Map<String, TLVType> caption_map = new HashMap<String, TLVType>();
		private static final Map<Byte, TLVType> lookup = new HashMap<Byte, TLVType>();

		static {
			for (TLVType s : EnumSet.allOf(TLVType.class)) {
				lookup.put(s.getCode(), s);
				caption_map.put(s.getCaption(), s);
			}

		}

		public static TLVType get(byte code) {
			return lookup.get(code);
		}

		private String caption_;

		private byte code_;

		private TLVType(String caption, byte code) {
			this.caption_ = caption;
			this.code_ = code;
		}

		public String getCaption() {
			return caption_;
		}

		public byte getCode() {
			return code_;
		}
	}

}
