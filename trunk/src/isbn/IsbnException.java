package isbn;

/**
 * 
 * Exception-Klasse
 *
 */
public class IsbnException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Konstruktor der IsbnException
	 */
	public IsbnException() {
		super();
	}

	/**
	 * Konstruktor der IsbnException
	 * 
	 * @param text
	 */
	public IsbnException(String text) {
		super(text);
	}
	
}