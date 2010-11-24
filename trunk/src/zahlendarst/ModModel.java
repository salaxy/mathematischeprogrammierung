package zahlendarst;

import java.util.Observable;

/**
 * Klasse zur Berechnung der<br>
 * modularen Potenz a^n mod m
 *
 */
public class ModModel extends Observable {
	
	private long ergebnis;
	private long base, exp, mod;

	/**
	 * Konstruktor
	 */
	public ModModel(){
		super();
		this.ergebnis = this.mod = this.exp = this.base = 0;
	}
	
	/**
	 * Ergebnis abfragen
	 * @return ergebnis
	 */
	public int getErgebnis() {
		return (int)this.ergebnis;
	}

	/**
	 * Basis abfragen
	 * @return
	 */
	public int getBase() {
		return (int)this.base;
	}

	/**
	 * Basis setzen
	 * @param base
	 */
	public void setBase(int base) {
		this.base = base;
	}

	/**
	 * Exponenten abfragen
	 * @return
	 */
	public int getExp() {
		return (int)this.exp;
	}

	/**
	 * Exponenten setzen
	 * @param exp
	 */
	public void setExp(int exp) {
		this.exp = exp;
	}

	/**
	 * Modulo abfragen
	 * @return
	 */
	public int getMod() {
		return (int)this.mod;
	}

	/**
	 * Model setzen
	 * @param mod
	 */
	public void setMod(int mod) {
		this.mod = mod;
	}
	
	/**
	 * Methode zur Berechnung
	 * des Ergebnisses
	 * @throws ModException
	 */
	public void berechne()throws ModException{		
		//abfrage nach verbotenenen Werten
		if(this.mod <= 0)throw new ModException("Modulo darf nicht kleiner als 1 sein!");
		if(this.exp < 0)throw new ModException("Exponent darf nicht negativ sein!");
		
	  	long z = 1;
	  	while (this.exp != 0){
	  	    	while (this.exp % 2== 0){
	  	    	    this.exp = this.exp / 2;

	  	    	    if(base>=0){
	  	    	    	this.base = this.base * this.base % this.mod;
	  	    	    }else{
	  	    	    	//falls base negativ dann muss anders
	  	    	    	//modulo genutzt werden
	  	    	    	this.base = this.base * modulo(this.base, this.mod);
	  	    	    }

	  	    	}
	  	    	
	  	    	this.exp = this.exp - 1;
	  	    	
	  	    	
  	    	    if(base>=0){
  		  	    	z = (z * this.base) % this.mod;
  	    	    }else{
  	    	    	//falls base negativ
  	    	    	z =  modulo((z * this.base), this.mod);
  	    	    }
	  	}
		
	  	this.ergebnis = z;
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Eigene Modulo berchung die auch
	 * im negativen mathematisch korrekt ist
	 * @param zahl
	 * @param mod
	 * @return zahl%mod
	 */
	private long modulo(long zahl, long mod){
		
		return (int)(zahl - Math.floor(((double)zahl)/mod)*mod);

	}
}
