package zahlendarst;

/**
 * ModException-Klasse
 */
public class ModException extends Exception{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Konstruktor der ModException
	 */
	public ModException(){
		super();
	}
	
	/**
	 * Konstruktor der ModException
	 * @param text
	 */
	public ModException(String text){
		super(text);
	}
}
