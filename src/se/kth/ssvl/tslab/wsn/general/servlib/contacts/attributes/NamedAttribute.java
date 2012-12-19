
package se.kth.ssvl.tslab.wsn.general.servlib.contacts.attributes;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import se.kth.ssvl.tslab.wsn.general.bpf.BPF;
import se.kth.ssvl.tslab.wsn.general.systemlib.util.Builder;

/**
 * "Class for a generic list of attributes/parameters, used for various
 * configuration functions" [DTN2].
 * 
 * @author Maria Jose Peroza Marval (mjpm@kth.se)
 */
public class NamedAttribute {

	/**
	 * TAG for Android Logging mechanism
	 */
	private static final String TAG = "NamedAttribute";

	/**
	 * Attribute Types
	 */
	public enum attribute_type_t {
		ATTRIBUTE_TYPE_INVALID(0), ATTRIBUTE_TYPE_INTEGER(1), ATTRIBUTE_TYPE_BOOLEAN(
				2), ATTRIBUTE_TYPE_STRING(3);

		private static final Map<Integer, attribute_type_t> lookup = new HashMap<Integer, attribute_type_t>();

		static {
			for (attribute_type_t s : EnumSet.allOf(attribute_type_t.class))
				lookup.put(s.getCode(), s);
		}

		private int code;

		private attribute_type_t(int code) {
			this.code = code;
		}

		public int getCode() {
			return code;
		}

		public static attribute_type_t get(int code) {
			return lookup.get(code);
		}

	}

	/**
	 * Attribute type to string conversion.
	 */
	public static String type_to_str(attribute_type_t type) {
		switch (type) {
		case ATTRIBUTE_TYPE_INVALID:
			return "invalid";
		case ATTRIBUTE_TYPE_INTEGER:
			return "integer";
		case ATTRIBUTE_TYPE_BOOLEAN:
			return "boolean";
		case ATTRIBUTE_TYPE_STRING:
			return "string";
		default:
			BPF.getInstance().getBPFLogger().error(TAG,
					"Problem converting attribute_type to string");
			return null;
		}
	}

	/*
	 * Constructors
	 */

	public NamedAttribute(String name, int v) {
		BPF.getInstance().getBPFLogger().debug(TAG, "Creating NamedAttribute" + name);
		name_ = name;
		type_ = attribute_type_t.ATTRIBUTE_TYPE_INTEGER;
		ival_ = v;
		bval_ = false;
		sval_ = "";
	}

	public NamedAttribute(String name, boolean v) {
		BPF.getInstance().getBPFLogger().debug(TAG, "Creating NamedAttribute" + name);
		name_ = name;
		type_ = attribute_type_t.ATTRIBUTE_TYPE_BOOLEAN;
		ival_ = 0;
		bval_ = v;
		sval_ = "";
	}

	public NamedAttribute(String name, String v) {
		BPF.getInstance().getBPFLogger().debug(TAG, "Creating NamedAttribute" + name);
		name_ = name;
		type_ = attribute_type_t.ATTRIBUTE_TYPE_STRING;
		ival_ = 0;
		bval_ = false;
		sval_ = v;
	}

	public NamedAttribute(Builder builder) {
		BPF.getInstance().getBPFLogger().debug(TAG, "Creating UNKNOWN NamedAttribute");
		name_ = "";
		type_ = attribute_type_t.ATTRIBUTE_TYPE_INVALID;
		ival_ = 0;
		bval_ = false;
		sval_ = "";
	}

	/*
	 * Accessors
	 */
	public String name() {
		return Name.name();
	}

	public attribute_type_t type() {
		return type_;
	}

	public int int_val() {
		if (type_ != attribute_type_t.ATTRIBUTE_TYPE_INTEGER) {
			BPF.getInstance().getBPFLogger()
					.debug(TAG,
							"NamedAttribute.int_val: invalid type"
									+ type_to_str(type_));
		}
		return ival_;
	}

	public boolean bool_val() {
		if (type_ != attribute_type_t.ATTRIBUTE_TYPE_BOOLEAN) {
			BPF.getInstance().getBPFLogger().debug(
					TAG,
					"NamedAttribute.bool_val: invalid type"
							+ type_to_str(type_));

		}
		return bval_;
	}

	public String string_val() {
		if (type_ != attribute_type_t.ATTRIBUTE_TYPE_STRING) {
			BPF.getInstance().getBPFLogger().debug(
					TAG,
					"NamedAttribute.string_val: invalid type"
							+ type_to_str(type_));
		}
		return sval_;
	}

	private AttributeName Name;
	protected String name_;
	private attribute_type_t type_;
	private int ival_;
	private boolean bval_;
	private String sval_;

}
