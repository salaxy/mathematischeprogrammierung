package kalendat;

/**
 * 
 * Exception-Klasse
 *
 */
public class KaException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Konstruktor der KaException
	 */
	public KaException() {
		super();
	}

	/**
	 * Konstruktor der KaException
	 * 
	 * @param text
	 */
	public KaException(String text) {
		super(text);
	}
	
}