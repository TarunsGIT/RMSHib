package in.co.mss.rmshib.exception;

/**
 * DatabaseException is propogated by DAO classes when an unhandled Database
 * exception occurred
 * 
 * @author Session Facade
 * @version 1.0
 *
 * 
 */

 
public class DatabaseException extends Exception {

	public DatabaseException(String msg) {
		super(msg);
	}

}
