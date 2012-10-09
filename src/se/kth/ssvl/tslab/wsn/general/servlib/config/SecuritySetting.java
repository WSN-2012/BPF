package se.kth.ssvl.tslab.wsn.general.servlib.config;

public class SecuritySetting {
	
	private String ks_path_;
	private String ks_password_;
	private String use_pcb_;
	private String use_pib_;
	
	//Accessors
	public String ks_path() {
		return ks_path_;
	}
	
	public String ks_password() {
		return ks_password_;
	}
	
	public String use_pcb() {
		return use_pcb_;
	}
	
	public String use_pib() {
		return use_pib_;
	}
	
	//Setters
	public void set_ks_path(String ksPath) {
		ks_path_ = ksPath;
	}
	
	public void set_ks_password(String ksPassword) {
		ks_password_ = ksPassword;
	}
	
	public void set_use_pcb(String usePCB) {
		use_pcb_ = usePCB;
	}
	
	public void set_use_pib(String usePIB) {
		use_pib_ = usePIB;
	}
	
}
