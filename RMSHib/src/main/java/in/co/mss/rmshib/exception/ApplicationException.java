package in.co.mss.rmshib.exception;

/**
 * ApplicationException is propogated from Service classes when an business
 * logic exception occurered.
 * 
 * @author Session Facade
 * @version 1.0
 *
 * 
 */

public class ApplicationException extends Exception {
	
	
	public ApplicationException(String msg) {
		super(msg);
	}

}
