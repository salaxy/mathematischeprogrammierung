package zahlendarst;

/**
 * ZahlException-Klasse
 */
public class ZahlException extends Exception{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Konstruktor der ZahlException
	 */
	public ZahlException() {
		super();
	}

	/**
	 * Konstruktor der ZahlException
	 * @param text
	 */
	public ZahlException(String text) {
		super(text);
	}

}
