package se.kth.ssvl.tslab.wsn.general.servlib.routing.prophet;

public class RIBInformationBaseEntry {
	short flags;
	float pValue;
	short stringID;

	@Override
	public String toString() {
		return String.format("StringID %d/PValue %f/Flags %x\n", stringID,
				pValue, flags);
	}
}
