package zahlendarst;

import java.util.Observable;

/**
 * Die Klasse führt die Berechnungen durch<br>
 * und überprüft auf gültige eingaben.
 */
public class ZahlModel extends Observable {

	private int basis1, basis2, zahl;
	private String eingabe, ausgabe;
	
	/**
	 * setBasis1
	 * 
	 * @param basis1
	 */
	public void setBasis1(int basis1) {
		this.basis1 = basis1;
	}

	/**
	 * setBasis2
	 * 
	 * @param basis2
	 */
	public void setBasis2(int basis2) {
		this.basis2 = basis2;
	}

	/**
	 * setEingabe
	 * 
	 * @param eingabe
	 */
	public void setEingabe(String eingabe) {
		this.eingabe = eingabe;
	}

	/**
	 * getAusgabe
	 * 
	 * @return
	 */
	public String getAusgabe() {
		return this.ausgabe;
	}

	/**
	 * ZahlModel Konstruktor
	 */
	public ZahlModel() {
		this.basis1 = 0;
		this.basis2 = 0;
		this.eingabe = null;
	}

	/**
	 * ZahlModel Konstruktor
	 * 
	 * @param basis1
	 * @param basis2
	 * @param eingabe
	 */
	public ZahlModel(int basis1, int basis2, String eingabe) {
		super();
		this.basis1 = basis1;
		this.basis2 = basis2;
		this.eingabe = eingabe;
	}

	/**
	 * aufruf des HornerSchemas und Umwandlung<br>
	 * in Zahl mit der Basis2
	 * 
	 * @throws ZahlNichtInBasisException
	 * @throws BasisException
	 * @throws ZahlException
	 */
	public void umwandlungB1B2() throws ZahlNichtInBasisException, BasisException, ZahlException {
		this.ausgabe = "";
		int ganz = 0, rest = 0;
		boolean beding = true;

		if (this.eingabe.length() == 0)
			throw new ZahlException("Sie müssen eine Zahl eingeben!");

		if (this.basis1 < 2 || this.basis1 > 16 || this.basis2 < 2 || this.basis2 > 16)
			throw new BasisException("Basen müssen zwischen 2 und 16 sein!");

		if (!this.isInBasis(eingabe))
			throw new ZahlNichtInBasisException("Bitte Zahlen < " + this.basis1 + " und >= 0 eingeben.");
		if (this.eingabe.length()>10)
			throw new ZahlException("Bitte nur 10 Zahlen eingeben!");
		
		this.zahl = hornerSchema();
		while (beding) {
			ganz = this.zahl / this.basis2;
			rest = this.zahl % this.basis2;
			this.zahl = ganz;
			if (this.zahl == 0)
				beding = false;
			this.ausgabe = Integer.toHexString(rest) + this.ausgabe;
		}
		setChanged();
		notifyObservers();
	}

	/**
	 * Berechnung mittels HornerSchema
	 * 
	 * @return
	 */
	private int hornerSchema() {
		this.zahl = charToInt(this.eingabe.charAt(0));
		// eingabe 256898888 darf nicht funktionieren von hex in dez
		for (int i = 1; i < this.eingabe.length(); i++) {
			this.zahl = (this.zahl * this.basis1) + charToInt(this.eingabe.charAt(i));
			int zahl2 = (this.zahl * this.basis1) + charToInt(this.eingabe.charAt(i));
			int zahl3 = Integer.MAX_VALUE / zahl2;
			if(zahl3 <= 0)
				System.out.println("test");
		}
		return this.zahl;
	}

	/**
	 * Umwandlung der Hexzahlen a-f in Dezimalzahlen
	 * 
	 * @param c
	 * @return Integer.valueOf(""+c)
	 */
	private int charToInt(char c) {
		switch (c) {
		case 'a':
			return 10;
		case 'b':
			return 11;
		case 'c':
			return 12;
		case 'd':
			return 13;
		case 'e':
			return 14;
		case 'f':
			return 15;
		case 'A':
			return 10;
		case 'B':
			return 11;
		case 'C':
			return 12;
		case 'D':
			return 13;
		case 'E':
			return 14;
		case 'F':
			return 15;
		default:
			return Integer.valueOf("" + c);
		}
	}

	/**
	 * Abfrage ob eingegebene Zahl gültig ist
	 * 
	 * @param eingabe
	 * @return false/true
	 */
	private boolean isInBasis(String eingabe) {
		for (int i = 0; i < eingabe.length(); i++) {
			if (this.charToInt(this.eingabe.charAt(i)) >= this.basis1) {
				return false;
			}
		}
		return true;
	}
}
