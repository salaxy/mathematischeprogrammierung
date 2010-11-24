package zahlendarst;

/**
 * BasisException-Klasse
 */
public class BasisException extends Exception{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Konstruktor der BasisException
	 */
	public BasisException(){
		super();
	}
	
	/**
	 * Konstruktor der BasisException
	 * @param text
	 */
	public BasisException(String text){
		super(text);
	}
}
