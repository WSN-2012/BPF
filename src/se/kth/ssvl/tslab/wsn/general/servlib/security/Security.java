package se.kth.ssvl.tslab.wsn.general.servlib.security;

import se.kth.ssvl.tslab.wsn.general.bpf.BPF;
import se.kth.ssvl.tslab.wsn.general.servlib.bundling.BlockInfo;
import se.kth.ssvl.tslab.wsn.general.servlib.bundling.BlockInfoVec;
import se.kth.ssvl.tslab.wsn.general.servlib.bundling.Bundle;
import se.kth.ssvl.tslab.wsn.general.servlib.contacts.Link;
import se.kth.ssvl.tslab.wsn.general.servlib.config.DTNConfiguration;
import se.kth.ssvl.tslab.wsn.general.servlib.security.SPD.spd_policy_t;

/**
 * TODO
 * 
 */

//Access point to the security part
public class Security {
	
	private static String TAG = "Security";
	/*
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
	*/
	
	/**
	 * Constructor: read config instance and store security settings locally. Other security classes will access these.
	 * It also initializes default ciphersuites.
	 */
	public Security(){
		//init SPD?
		//init KeyDB?
		Ciphersuite.init_default_ciphersuites();
		
		/*
		ks_path_ = DTNConfiguration.getInstance().security_setting().ks_path();
		ks_password_ = DTNConfiguration.getInstance().security_setting().ks_password();
		use_pcb_ = DTNConfiguration.getInstance().security_setting().use_pcb();
		use_pib_ = DTNConfiguration.getInstance().security_setting().use_pib();
		*/
		BPF.getInstance().getBPFLogger().debug(TAG, "Initialized.");
	}
	
	//This method will be called from BundleProtocol.prepare_blocks()
	public void prepare_out_blocks(Bundle bundle, Link link, BlockInfoVec xmit_blocks){
		int err = 0;
		
		//Do we have to use PCB?
		if(DTNConfiguration.getInstance().security_settings().use_pcb().equals("true")) 
		{
			Ciphersuite bp =  Ciphersuite.find_suite(Ciphersuite_C3.CSNUM_C3);
			assert(bp != null);
			err = bp.prepare(bundle, xmit_blocks, null, link, BlockInfo.list_owner_t.LIST_NONE);
		}
		
		//Do we have to use PIB?
		if(DTNConfiguration.getInstance().security_settings().use_pib().equals("true"))
		{
			BPF.getInstance().getBPFLogger().error(TAG, "PIB blocks are not supported yet!");
			//TODO: Add PIB support!
		}
		
		if (err != 0)
			BPF.getInstance().getBPFLogger().error(TAG, "Error while preparing security blocks!");
		else
			BPF.getInstance().getBPFLogger().debug(TAG, "Blocks have been prepared.");
	}
	
}
