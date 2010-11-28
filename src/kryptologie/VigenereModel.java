package kryptologie;

import java.util.Observable;

/**
  * Klasse zum Ver- und Entschl�sselung eines Textes mit der
  * VIGENERE-Verschl�sselung
  * @author Andy Klay, Christoph Ott, Stephan Leddin  
  * @version 28.10.2010
  */
public class VigenereModel extends  Observable
{
	
    private String klartext;
    private String geheimtext;
    private String schluesselwort;
    
    
    /**
     * Konstruktor der Klasse
     */
    public VigenereModel(){ 
      klartext="";
      geheimtext="";
      schluesselwort="A";
    }
 
    /**
     * setzen des Klartextes
     * @param String - klartext
     */
    public void setKlartext(String s){
        klartext=s;
    }
       
    /**
     * setzen des Schluesselwortes
     * als kleinbuchstaben oder Gro�buchstaben
     * (konvertiert autom zu Gro�buchstaben)
     * @param char - schl�ssel
     */
    public void setschluessel(String s)throws KeyException{
    	
    	//Normalisieren des Schl�ssels nur Buchstaben a bis z
    	
        StringBuffer buffer= new StringBuffer();
        int c=0;
        
        for(int i=0;i<s.length();i++){
        	
        	c=s.charAt(i);
             
             if((c>=65)&&(c<=90)){
            	 buffer.append((char)c); 
              }else{    	
            	  //konvertiert zu Gro�buchstaben
            	  if((c>=97)&&(c<=122)){
            		  buffer.append((char)(c-32));
            	  }else{
            		  throw new KeyException("Nicht erlaubter Schl�ssel!");
            	  }
            	  
              }

        } 
    	
        schluesselwort=buffer.toString();
    }

    /**
     * abfragen des geheimtextes
     * @return Geheimtext
     */
    public String getGeheimtext(){    
       return geheimtext;
    }
    
    /**
     * setzen des Geheimtextes
     * @param String - geheimtext
     */
    public void setGeheimtext(String s){    
       geheimtext=s;
    }
    
    /**
     * abrufen des klartextes
     * @return Klartext
     */
    public String getKlartext(){    
       return klartext;
    }
    
    /**
     * verschluesseln mit vigenere
     * und dem vorher festgelgten
     * Klartext und Schluesselwort
     */
    public void verschluesseln()throws KeyException{
        //schl�sselwort in Gro�Buchstaben
    	
    	if(schluesselwort.length()==0){
    		throw new KeyException("Leerer Schl�ssel!");
    	}
    	

        //Buffer f�r das Ergebnis erstellen
        StringBuffer ergebnis= new StringBuffer();
        //l�nge des Sch�sselwortes
        int lang=schluesselwort.length();
        //Stelle der Verschl�sselung
        int statuslang=0;
        //f�r die L�nge des Textes text verschl�sseln
        for(int n=0;n<klartext.length();n++){
            
            //wenn stelle der Verschl�sselung gr��er als l�nge des Schl�sselwortes
            if(statuslang==lang){
                    statuslang= statuslang - lang;
                }
                
            //errechne schl�ssel dieses Zeichens
            int schluessel=schluesselwort.charAt(statuslang)-65;
            
            
            //wandel zeichen um
            int  w = klartext.charAt(n);
            
            //Verschl�sseln der Gro�Buchstaben
            if((w>=65)&&(w<=90)){
                    w=w+schluessel;
                
                    if(w>90){
                        w=w-26;
                    }
                    statuslang++;
             }
            //Verschl�sseln der KleinBuchstaben
             if((w>=97)&&(w<=122))
             {
                     w= w + schluessel;
                     if(w>122)
                     {
                       w=w-26;
                     }
                     statuslang++;
                   }
             //hange zeichen am Stringbuffer an
             ergebnis.append((char)w); 
                
        }   

        //Wandel in String um
         geheimtext= ergebnis.toString();   
         //Observer benachrichtigen
 		setChanged();
		notifyObservers();
    }

    /**
     * entschluesseln mit vigenere
     * und dem vorher festgelgten
     * Geheimtext und Schluesselwort
     */
    public void entschluesseln()throws KeyException{
        //schl�sselwort in Gro�Buchstaben
        
    	if(schluesselwort.length()==0){
    		throw new KeyException("Leerer Schl�ssel!");
    	}

        //Buffer f�r das Ergebnis erstellen
        StringBuffer ergebnis= new StringBuffer();
        //l�nge des Sch�sselwortes
        int lang=schluesselwort.length();
        //Stelle der Verschl�sselung
        int statuslang=0;
        //f�r die L�nge des Textes text verschl�sseln
        for(int n=0;n<geheimtext.length();n++){
            
            //wenn stelle der Verschl�sselung gr��er als l�nge des Schl�sselwortes
            if(statuslang==lang){
                    statuslang= statuslang - lang;
                }
                
            //errechne schl�ssel dieses Zeichens
            int schluessel=schluesselwort.charAt(statuslang)-65;
            
            
            //wandel zeichen um
            int  w = geheimtext.charAt(n);
            
            //entschl�sseln der Gro�Buchstaben
            if((w>=65)&&(w<=90)){
                    w=w-schluessel;
                
                    if(w<65){
                        w=w+26;
                    }
                    statuslang++;
             }
            //entschl�sseln der KleinBuchstaben
             if((w>=97)&&(w<=122))
             {
                     w= w - schluessel;
                     if(w<97)
                     {
                       w=w+26;
                     }
                     statuslang++;
                   }
             //hange zeichen am Stringbuffer an
             ergebnis.append((char)w); 
                
        }   

        //Wandel in String um
         klartext= ergebnis.toString(); 
         //bescheid sagen: Bescheid!! XD
 		setChanged();
		notifyObservers();
    }

}   
