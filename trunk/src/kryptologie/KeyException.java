package kryptologie;

/**
 * 
 * Key Exception,
 * wird dazu benötigt einen
 * invaliden schluessel zu signalisieren
 * @author Andy Klay, Christoph Ott, Stephan Leddin
 *
 */
public class KeyException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Konstruktor der EuException
	 */
	public KeyException() {
		super();
	}

	/**
	 * Konstruktor der EuException
	 * 
	 * @param text - Message
	 */
	public KeyException(String text) {
		super(text);
	}
	
}