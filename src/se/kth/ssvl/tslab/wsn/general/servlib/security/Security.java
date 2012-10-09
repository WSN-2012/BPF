package se.kth.ssvl.tslab.wsn.general.servlib.security;

import se.kth.ssvl.tslab.wsn.general.servlib.bundling.BlockInfoVec;
import se.kth.ssvl.tslab.wsn.general.servlib.bundling.Bundle;
import se.kth.ssvl.tslab.wsn.general.servlib.contacts.Link;
import se.kth.ssvl.tslab.wsn.general.servlib.config.DTNConfiguration;

/**
 * This class is used as entry point to all security classes.
 * 
 */

//Access point to the security part
public class Security {
	
	private static Security instance;
	
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
	
	/**
	 * This method will return an singleton instance of Security
	 * @return The Security singleton
	 */
	public static Security getInstance() {
		if (instance == null) {
			instance = new Security();
		}
		return instance;
	}
	
	public void init(DTNConfiguration config_){//TODO
		//init SPD?
		//init KeyDB?
		Ciphersuite.init_default_ciphersuites();
		
		//here we get everything that we need from the config file and store it in local variables
		ks_path_ = config_.security_setting().ks_path();
		ks_password_ = config_.security_setting().ks_password();
		use_pcb_ = config_.security_setting().use_pcb();
		use_pib_ = config_.security_setting().use_pib();
		
	}
	
	public void prepare_out_blocks(Bundle bundle, Link link, BlockInfoVec xmit_blocks){//TODO
		
		SPD.getInstance().prepare_out_blocks(bundle, link, xmit_blocks); 
	}
	
}
