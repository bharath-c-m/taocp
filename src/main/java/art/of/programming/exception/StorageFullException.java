package art.of.programming.exception;

public class StorageFullException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StorageFullException(String msg) {
		super(msg);
	}
}
