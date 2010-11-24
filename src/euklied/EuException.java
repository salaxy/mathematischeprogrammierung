package euklied;

/**
 * 
 * Exception-Klasse
 *
 */
public class EuException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Konstruktor der EuException
	 */
	public EuException() {
		super();
	}

	/**
	 * Konstruktor der EuException
	 * 
	 * @param text
	 */
	public EuException(String text) {
		super(text);
	}
	
}