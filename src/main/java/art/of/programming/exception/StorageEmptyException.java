package art.of.programming.exception;

public class StorageEmptyException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public StorageEmptyException(String msg) {
		super(msg);
	}

}
