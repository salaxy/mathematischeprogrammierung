package kryptologie;

import java.util.Observable;

/**
  * Klasse zur Ver- und Entschl�sselung eines Textes mit der
  * CAESAR-Verschl�sselung
  * @version 28.10.2010
  * @author Andy Klay, Christoph Ott, Stephan Leddin
  */
public class CaesarModel extends  Observable
{
    private String klartext;
    private String geheimtext;
    private int schluessel;


    /**
     *  Konstruktor der Klasse CaesarModel
     */
    public CaesarModel()
    {
      klartext = new String("");
      geheimtext = new String("");
      schluessel = 0;
    }

    /**
     * setzen des Klartextes
     * @param String - klartext
     */
    public void setKlartext(String txt)
    {
       klartext = txt;
    }
    
    /**
     * setzen des Geheimtextes
     * @param String - geheimtext
     */
    public void setGeheimtext(String txt)
    {
        geheimtext = txt;
    }
    
    /**
     * setzen des Schluessels
     * als kleinbuchstabe oder Gro�buchstabe
     * @param char - schl�ssel
     */
    public void setSchluessel(char c)throws KeyException{

        if(!(((c>=65)&&(c<=90))||((c>=97)&&(c<=122)))){
        	throw new KeyException("Nicht erlaubter Schl�ssel");
        }else{
	        if((c>=65)&&(c<=90)){
	        	schluessel = c-65; 
	        }
	        
	        if((c>=97)&&(c<=122)){
	        	schluessel = c-97; 
	        }    	
        }
        
    }
    
    /**
     * setzen des Schluessels
     * als integer
     * @param int - schl�ssel
     */
    private void setSchluessel(int s){
        	schluessel = s;
    }
    
    /**
     * abfragen des geheimtextes
     * @return Geheimtext
     */
    public String getGeheimtext()
    {
      return geheimtext;
    }
    
    /**
     * abrufen des klartextes
     * @return Klartext
     */
    public String getKlartext()
    {
        return klartext;
    }
    
    /**
     * verschluesseln mit caesar
     * und dem vorher festgelgten
     * Klartext und Schluessel
     */
    public void verschluesseln()
    {
        //erzeugen eines Stringbuffers zum Anh�ngen der einzelnen Zeichen
        StringBuffer ergebnis =new StringBuffer();
        
        //l�schen des Geheimtextes
        this.delGeheimtext();
        
           //solange noch zeichen des klartextes zu verarbeiten sind
           for (int n=0;n< klartext.length();n++)
           {
                   //wandel zeichen in Ascii-code um
                   int w=klartext.charAt(n);
                   
                   //�berpr�fen ob zeichen ein Gro�buchstabe ist
                   if((w>=65)&&(w<=90))
                   {
                     //ver�ndern des Zeichens um schl�sselzahl
                     w= w + schluessel;
                     if(w>90)
                     {
                       w=w-26;
                     }
                   }
                   
                   //�berpr�fen ob zeichen ein Kleinbuchstabe ist
                   if((w>=97)&&(w<=122))
                   {
                     //ver�ndern des Zeichens um schl�sselzahl
                     w= w + schluessel;
                     if(w>122)
                     {
                       w=w-26;
                     }
                   }
                   
                   //h�nge zeichen am StringBuffer ergebnis an
                   ergebnis.append((char)w);
           }
           //Umwandeln des stringbuffers in einen String f�r das Attribut des geheimtextes
           geheimtext=ergebnis.toString();
           
           //Observer benachrichtigen
    		setChanged();
    		notifyObservers();
    }
    
    /**
     * entschluesseln mit caesar
     * und dem vorher festgelgten
     * Geheimtext und Schluessel
     */
    public void entschluesseln()
    {  
        //erzeugen eines Stringbuffers zum Anh�ngen der einzelnen Zeichen
        StringBuffer ergebnis =new StringBuffer();
        
        //l�schen des klartextes
        this.delKlartext();
        
           //solange noch zeichen des geheimtextes zu verarbeiten sind
           for (int n=0;n< geheimtext.length();n++)
           {
                   //wandel zeichen in Ascii-code um
                   int w=geheimtext.charAt(n);
                   
                   //�berpr�fen ob zeichen ein Gro�buchstabe ist
                   if((w>=65)&&(w<=90))
                   {
                     //ver�ndern des Zeichens um schl�sselzahl
                     w= w - schluessel;
                     if(w<65)
                     {
                       w=w+26;
                     }
                   }
                   
                   //�berpr�fen ob zeichen ein Kleinbuchstabe ist
                   if((w>=97)&&(w<=122))
                   {
                     //ver�ndern des Zeichens um schl�sselzahl
                     w= w - schluessel;
                     if(w<97)
                     {
                       w=w+26;
                     }
                   }
                   
                   //h�nge zeichen am StringBuffer ergebnis an
                   ergebnis.append((char)w);
           }
           //Umwandeln des stringbuffers in einen String f�r das Attribut des klartextes
           klartext=ergebnis.toString();
           
           //Observer benachrichtigen
    		setChanged();
    		notifyObservers();

    }
    
    /**
     * l�schen des Klartextes
     */
    public void delKlartext()
    {
        klartext = "";
    }
    
    /**
     * l�schen des Geheimtextes
     */
    public void delGeheimtext()
    {
        geheimtext = "";
    }
    
    /**
     * automatisches entschluesseln 
     * vom vorher festgelgten Geheimtext
     * mit caesar durch h�ufigkeitenanalyse
     */
    public void autoEntschluesseln(){
    	
    	delKlartext();
    	//Textanalyse der H�ufigkeiten
        //erstelle neue Objekte zu Buchstabenh�ufigkeitsanalyse
        Haeufigkeit[] alphabet = new Haeufigkeit[26]; 
         
        //haeufigkeiten vergleichen
         
        int amBesten=0;
        double besteDiff=0.0;
        double aktDiff=0.0;
         
        //durchl�uft alle 25 brauchbaren schl�ssel
        //vergleicht die Haufigkeit des Ergebnisses
        //mit der deutschen H�ufigkeit
        //die geringste Differenz gewinnt das rennen 
        for(int i=1;i<=25;i++){
        	alphabet[i-1] =new Haeufigkeit();
            this.setSchluessel(i);
            this.entschluesseln();
        	alphabet[i-1].analyse(klartext);
        	
        	if(amBesten!=0){
        		//schleife
        		aktDiff=alphabet[i-1].verteilungsdifferenz();
        		if(aktDiff<besteDiff){
            		amBesten=i;
            		besteDiff=aktDiff;
        		}
        	}else{
        		//erste schleife
        		amBesten=1;
        		besteDiff=alphabet[i-1].verteilungsdifferenz();
        	}
        	
        }
         
         //gefundenen schl�ssel setzen
         schluessel=amBesten;

         //Entschl�sselung mit dem besten Schluessel
         this.entschluesseln();
	
         //Observer benachrichtigen
         setChanged();
         notifyObservers();
    }
    
}
