package kryptologie;

/**
 * Klasse zur Bestimmung der relativen Häufigkeit von Buchstaben 
 * in einem Text
 * @author Andy Klay, Christoph Ott, Stephan Leddin
 * @version 28.10.2010
 */

public class Haeufigkeit
{
	/**
	 * Sttribut fuer dei summen der buchstaben
	 */
     private int[] absolut=new int[26];
     /**
      * Attribut für alle mitgezählten Buchstaben
      */
     private int anzahl=0;

     /**
      * Konstruktor der Klasse
      */
     public Haeufigkeit(){
           //Feld mit 0 werten belegen
           for (int n = 0; n < 26; n++) {
               absolut[n] = 0;
           }
     }


     /**
      * zaehlt wie oft ein buchstabe vorkommt
      * @param String - irgent ein Text
      */
     public void analyse(String inhalt){
           //umwandeln in Großbuchstaben
           inhalt=inhalt.toUpperCase();
           //durchläuft den String
           for(int n=0;n<inhalt.length();n++){

                   int zeichen=inhalt.charAt(n)-65;
                   if((zeichen>=0)&&(zeichen<=25)){
                       absolut[zeichen]++;
                       anzahl++;
                   }
            }
    }
         
    /**
     * gibt als double wieder 
     * wie oft ein Buchstabe prozentual vorkommt
     * @param int - zahl desBuchstaben im alphabet, begin bei 0
     * @return double - prozentuales vorkommen
     */
    private double relativ(int c){
        return absolut[c]*100/(double)anzahl;
    }
    
    /**
     * Ermittelt die Alphabet-Verteilungsdifferenz
     * des alalysierten Textes zur deutschen Sprache 
     * @return double - summe der differenzquadrate
     */
    public double verteilungsdifferenz(){
    	//Quelle für die häufigkeiten
    	//http://de.wikipedia.org/wiki/Buchstabenh%C3%A4ufigkeit
    	
    	double[] deutsch=new double[26];
    	//A
    	deutsch[0]=6.51;
    	//B
    	deutsch[1]=1.89;
    	//C
    	deutsch[2]=3.06;
    	//D
    	deutsch[3]=5.06;
    	//E
    	deutsch[4]=17.40;
    	//F
    	deutsch[5]=1.66;
    	//G
    	deutsch[6]=3.01;
    	//H
    	deutsch[7]=4.76;
    	//I
    	deutsch[8]=7.55;
    	//J
    	deutsch[9]=0.27;
    	//K
    	deutsch[10]=1.21;
    	//L
    	deutsch[11]=3.44;
    	//M
    	deutsch[12]=2.53;
    	//N
    	deutsch[13]=9.78;
    	//O
    	deutsch[14]=2.51;
    	//P
    	deutsch[15]=0.79;
    	//Q
    	deutsch[16]=0.02;
    	//R
    	deutsch[17]=7.00;
    	//S
    	deutsch[18]=7.27;
    	//T
    	deutsch[19]=6.15;
    	//U
    	deutsch[20]=4.35;
    	//V
    	deutsch[21]=0.67;
    	//W
    	deutsch[22]=1.89;
    	//X
    	deutsch[23]=0.03;
    	//Y
    	deutsch[24]=0.04;
    	//Z
    	deutsch[25]=1.13;
    	
    	
    	double diff=0.0;
    	double sumDiff=0.0;
    	
    	//differrenzen der einzelenen Buchstaben ausrrechnen
    	//und summieren
    	for(int i=0;i<26;i++){
    		
    		diff=relativ(i)-deutsch[i];
    		diff=diff*diff;
    		sumDiff+=diff;	
    	}
    	
    	return sumDiff;
    }
    
}
