package kalendat;

import java.util.Observable;

/**
 * Klasse zur Berechnung des Wochentages bei Eingabe eines Datums
 */
public class KaModel extends Observable {
	private int[] monatTag = {0,31,28,31,30,31,30,31,31,30,31,30,31};
	private int tag, monat, jahr, ergebnis;
	private String ausgabe;
	
	public String getAusgabe() {
		return this.ausgabe;
	}

	/**
	 * Methode liest das Datum aus und setzt Tag, Monat, Jahr
	 * @param date
	 * @throws KaException
	 */
	public void setAll(String date) throws KaException {
		int point = 0;
		String _tag = "";
		String _monat = "";
		String _jahr = "";

		if (date.length() < 8 || date.length() > 10)
			throw new KaException("Datum ist zu kurz oder zu lang!");

		// Datum auslesen
		for (int i = 0; i < date.length(); i++) {
			if (date.charAt(i) == '.')
				point++;
			else {
				if (point == 0)
					_tag = "" + _tag + date.charAt(i);
				if (point == 1)
					_monat = "" + _monat + date.charAt(i);
				if (point == 2)
					_jahr = "" + _jahr + date.charAt(i);
			}
		}

		if (point != 2)
			throw new KaException(
					"Im Datum sind zu wenig oder zu viele Punkte!");

		this.tag = Integer.valueOf(_tag);
		this.monat = Integer.valueOf(_monat);
		this.jahr = Integer.valueOf(_jahr);


		// Datum zwischen 1700 und 2100
		if (isValidDate())
			throw new KaException("Datum muss zwischen 1700 und 2100 sein!");

		// Monat Exceptions
		if (_monat.length() > 2)
			throw new KaException("Der Monat ist zu lang!");
		if (this.monat > 12 || this.monat < 1)
			throw new KaException("Der Monat ist nicht zulässig!");

		// Tag Exceptions
		if (_tag.length() > 2)
			throw new KaException("Der Tag ist zu lang!");
		if (this.tag <= 0)
			throw new KaException("Der Tag ist nicht zulässig!");


		// Jahr Exceptions
		if (_jahr.length() > 4 || _jahr.length() < 4)
			throw new KaException("Das Jahr ist zu lang oder zu kurz!");
		if (this.jahr <= 0)
			throw new KaException("Das Jahr ist nicht zulässig!");
	}

	/**
	 * Konstruktor
	 * @param tag
	 * @param monat
	 * @param jahr
	 */
	public KaModel(int tag, int monat, int jahr) {
		super();
		this.tag = tag;
		this.monat = monat;
		this.jahr = jahr;
	}

	/**
	 * Konstruktor
	 */
	public KaModel() {
		super();
		this.tag = 0;
		this.monat = 0;
		this.jahr = 0;
	}

	/**
	 * Die Methode berechnet den Wert des<br>
	 * Wochentags und wandelt ihn in einen<br>
	 * Wochentag um.
	 * @throws KaException
	 */
	public void berechnung() throws KaException {
		String[] wochentag = {"Sonntag","Montag","Dienstag","Mittwoch","Donnerstag","Freitag","Samstag"};
		
		this.ergebnis = (tagesziffer() + monatsziffer() + jahresziffer() + jahrhundertziffer() + schaltjahr()) % 7;
		this.ausgabe = wochentag[this.ergebnis];
		setChanged();
		notifyObservers();
	}

	/**
	 * überprüft ob das Datum im vorgegebenen Bereich liegt
	 * @return true/false
	 */
	private boolean isValidDate() {
		return (this.jahr < 1700 || this.jahr > 2100) ? true : false;
	}

	/**
	 * Berechnung der Tagesziffer
	 * @return tag
	 */
	private int tagesziffer() {
		return this.tag % 7;
	}

	/**
	 * Berechnung der Monatsziffer
	 * @return monat
	 */
	private int monatsziffer() throws KaException{
		int wert1 = 0, wert2 = 0;
		if(schaltjahr() == 6){
			this.monatTag[2] = 29;
		}else{
			this.monatTag[2] = 28;
		}
		if (this.tag > this.monatTag[this.monat]){
			throw new KaException("Der Tag ist nicht zulässig!");
		}
		for (int i = 1; i < this.monat; i++) {
			wert1 = this.monatTag[i] % 7;
			wert2 = (wert2 + wert1) % 7;
		}
		return wert2;
	}

	/**
	 * Berechnung der Jahresziffer
	 * @return jahresziff
	 */
	private int jahresziffer() {
		int zahl = 0, jahresziff = 0;
		zahl = this.jahr / 100 * 100;
		zahl = this.jahr - zahl;
		jahresziff = (zahl + (zahl / 4)) % 7;
		return jahresziff;
	}

	/**
	 * Berechnung der Jahrhundertziffer
	 * @return jahrhundert
	 */
	private int jahrhundertziffer() {
		int jahrhundert = 0;
		jahrhundert = this.jahr / 100;
		jahrhundert = (3 - (jahrhundert % 4)) * 2;
		return jahrhundert;
	}

	/**
	 * Überprüfung auf Schaltjahr
	 * @return 6/0
	 */
	private int schaltjahr() {
		if (this.jahr % 4 == 0 && this.jahr % 100 != 0 || this.jahr % 400 == 0)
			return 6;
		else
			return 0;		
	}
}