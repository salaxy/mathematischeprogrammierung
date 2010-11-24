package isbn;
import java.util.Observable;

/**
 * 
 * Die Klasse dient zur Berechnung der Prüfziffer<br>
 * einer ISBN und zum überprüfen ob die eingegebene<br>
 * ISBN gültig ist sowie das berechnen einer fehlenden Ziffer.
 *
 */
public class IsbnModel extends Observable {

	private String isbn, guelt, zahl;

	/**
	 * getAusgabe
	 * 
	 * @return String
	 */
	public String getAusgabe() {
		return this.zahl;
	}
	
	/**
	 * setzen und überprüfen der ISBN
	 * 
	 * @param isbn
	 * @throws IsbnException
	 */
	public void setIsbn(String isbn) throws IsbnException{
		if(isbn.length() != 10)
			throw new IsbnException("Die Eingabe muss min. 9 Zahlen und 1 Stern haben.");
		this.isbn = isbn;
	}
	
	/**
	 * getGült
	 * 
	 * @return String
	 */
	public String getGuelt() {
		return this.guelt;
	}

	/**
	 * IsbnModel Konstruktor
	 * 
	 * @param isbn
	 * @param zahl
	 */
	public IsbnModel(String isbn, String zahl) {
		super();
		this.isbn = isbn;
		this.zahl = zahl;
	}

	/**
	 * IsbnModel Konstruktor
	 */
	public IsbnModel() {
		super();
		this.isbn = null;
		this.zahl = null;
	}
	
	/**
	 * Diese methode berechnet die Prüfziffer<br>
	 * einer eingegebenen ISBN. Ebenfalls wird<br>
	 * bei Eingabe einer 10-stelligen ISBN, die<br>
	 * einen '*' beinhaltet die fehlende Ziffer<br>
	 * berechnet.
	 * 
	 * @throws IsbnException
	 */
	public void test() throws IsbnException{
		int i, j, stelle = 0, hilf = 0, erg = 0, zahl = 0, stars=0;
		
		for (i = 1, j = 0; i <= 10 ; i++, j++) {
			if (this.isbn.charAt(j) == '*') {
				stelle = i;
				stars++;
			}
			else {
				if(j == 9 && this.isbn.charAt(j) == 'X') {
					erg += 100;
				}
				else
					erg += Integer.parseInt("" + this.isbn.charAt(j)) * i;
			}
		}
		if(stars > 1)
			throw new IsbnException("Zu viele Sterne! Bitte nur einen eingeben.");
		
		this.guelt = (isCorrect(erg)) ? "gültig" : "nicht gültig";
			
		if(stelle != 0) {
			zahl = erg % 11;
			if(zahl != 0) {
				hilf = 11 - zahl;
				erg = hilf + erg;
				while((hilf % stelle != 0) || (erg % 11 != 0)) {
					hilf = hilf + 11;
				}
			}

			this.guelt = ("");
			this.zahl = ("" + hilf / stelle);
			if(this.zahl.equals("10"))
				this.zahl = "X";
		}
		else
			this.zahl = ("");
		setChanged();
		notifyObservers();
	}

	/**
	 * überprüft die ISBN auf Gültigkeit
	 * 
	 * @param zahl
	 * @return
	 */
	private boolean isCorrect(int zahl){
		return (zahl % 11 == 0) ? true : false;
	}
}