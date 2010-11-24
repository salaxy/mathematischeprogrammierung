package zahlendarst;

/**
 * ZahlNichtInBasisException-Klasse
 */
public class ZahlNichtInBasisException extends Exception{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Konstruktor der ZahlNichtInBasisException
	 */
	public ZahlNichtInBasisException(){
		super();
	}
	
	/**
	 * Konstruktor der ZahlNichtInBasisException
	 * @param text
	 */
	public ZahlNichtInBasisException(String text){
		super(text);
	}
}
