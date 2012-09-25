package se.kth.ssvl.tslab.wsn.general.servlib.routing.prophet;

public class RIBDictionaryEntry {
	String eid;
	byte length;
	byte reserved;
	short stringID;

	@Override
	public String toString() {
		return String.format("StringID %d/" + "Length %d/" + "Reserved %x/"
				+ "EID %s\n", stringID, length, reserved, eid);
	}
}