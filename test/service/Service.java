package service;

import java.io.File;
import java.io.FileNotFoundException;

import se.kth.ssvl.tslab.wsn.general.bpf.BPF;
import se.kth.ssvl.tslab.wsn.general.bpf.BPFActionReceiver;
import se.kth.ssvl.tslab.wsn.general.bpf.BPFCommunication;
import se.kth.ssvl.tslab.wsn.general.bpf.BPFDB;
import se.kth.ssvl.tslab.wsn.general.bpf.BPFLogger;
import se.kth.ssvl.tslab.wsn.general.bpf.BPFService;
import se.kth.ssvl.tslab.wsn.general.bpf.exceptions.BPFException;
import bpf.ActionReceiver;
import bpf.Communication;
import bpf.DB;
import bpf.Logger;

public class Service implements BPFService {
	
	private static String TAG = "Service";
	
	private Logger logger; 
	private ActionReceiver action;
	private Communication comm;
	private DB db;
	
	public static void main(String args[]) {
		new Service(args);
	}
	
	public Service(String args[]) {
		// Init some stuff first
		init(args);
		
		if (args.length == 0) {
			logger.debug(TAG, "No argmunets means listening mode");
		} else if (args.length == 4) {
			
		} else {
			usage();
			System.exit(-1);
		}
	}
	
	private void init(String args[]) {
		// Init a logger first of all
		logger = new Logger();
		
		// Init the action receiver
		action = new ActionReceiver(logger);
		
		// Init the communications object
		comm = new Communication();
		
		// Init the DB object
		db = new DB(new File("build/database.db"), logger);

		// Try to init the BPF
		try {
			BPF.init(this, args[0]);
		} catch (BPFException e) {
			logger.error(TAG, "Couldn't initialize the BPF, exception: " + e.getMessage());
			System.exit(-1);
		}
	}
	
	private void usage() {
		logger.error(TAG, "This is some cool usage");
	}

	/* ***************************** */
	
	public BPFCommunication getBPFCommunication() {
		return comm;
	}

	public BPFDB getBPFDB() {
		return db;
	}

	public BPFLogger getBPFLogger() {
		return logger;
	}

	public BPFActionReceiver getBPFActionReceiver() {
		return action;
	}
	
}
