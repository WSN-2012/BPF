package se.kth.ssvl.tslab.wsn.general.bpf;

public class BPF {
	
	/* ********* MEMBER VARIABLES ********** */
	private static BPF instance;
	
	private static BPFService service; 
	
	/* ********* INITIALIZATION AND CONSTRUCTOR********** */
	/**
	 * This method will return an singleton instance of BPF, 
	 * which is used as a main entry point to the library. Note: Must be called after init() method
	 * @return The BPF singleton
	 * @throws BPFException Throws and exception if BPF has not been initialized using init() method
	 */
	public static BPF getInstance() {
		if (instance == null) {
			if (service == null) {
				return null;
			}
			instance = new BPF(service);
		}
		
		return instance;
	}
	
	
	/**
	 * The init method will take in a BPFService which needs to
	 *  implement methods for getting different BPF classes (e.g BPFLogger).
	 * The library will use the different classes as callbacks, since the 
	 * 	implementation must be implemented outside the BPF library.  
	 * @param service The BPFService implementation.
	 * @throws BPFException 
	 */
	public void init(BPFService _service) throws BPFException {
		if (_service == null) {
			throw new BPFException("BPFService cannot be null, we really need this guy to work!");
		}
		instance = new BPF(_service);
	}
	
	/**
	 * Constructor for BPF, which is private since BPF is a singleton.
	 * @param service The BPFService implementation that the BPF library will use for device-specific methods.
	 */
	private BPF(BPFService _service) {
		service = _service;
	}
	
	
	/* ********* GETTER METHODS FOR BPF CLASSES ********** */	
	/**
	 * Gets the BPFCommunication object passed in from the BPFService.
	 * @return The BPFCommunication object in the BPFService
	 */
	public BPFCommunication getBPFCommunication() {
		return service.getBPFCommunication();
	}
	
	/**
	 * Gets the BPFLogger object passed in from the BPFService.
	 * @return The BPFLogger object in the BPFService
	 */
	public BPFLogger getBPFLogger() {
		return service.getBPFLogger();
	}
	
	/**
	 * Gets the BPFNotificationReceiver object passed in from the BPFService.
	 * @return The BPFNotificationReceiver object in the BPFService
	 */
	public BPFNotificationReceiver getBPFNotificationReceiver() {
		return service.getBPFNotificationReceiver();
	}
	
	
	
}
