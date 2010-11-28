package kryptologie;

import java.io.*;

/** 
 * Klasse zum Speichern und Laden eines Textes
 * in bzw. aus einer Datei und zum Filtern von Texten
 * @author Andy Klay, Christoph Ott, Stephan Leddin
 * @version 28.10.2010
 */
public class DateiArbeit{


       /**
        * Speichern eines Textes
        * @param newone - Text der gespeichert werden soll
        * @param datname - Dateiname bzw. Dateipfad
        * @throws IOException
        */
       public static void speichern(String newone,String datname)throws IOException{

               //erstellen der Datei in der gespeichert wird
               File datei=new File(datname);
               // öffnen der Datei
               FileWriter dateischreiber= new FileWriter(datei);
               //schreiben der Daten
               PrintWriter ausgabe=new PrintWriter(dateischreiber);
               ausgabe.println(newone);
               
               //schließen der Datei
               ausgabe.close();
       }
       
       /** 
        * Laden eines Textes aus einer Datei
        * @param Dateiname bzw. Dateipfad
        * @return geladener Text
        * @throws  IOException    
        */
       public static String laden(String datname)throws IOException{
              String inhalt="";

               //öffnen der Datei
               FileReader eingabestream=new FileReader(datname);
               // lesen der Datei
               StringBuffer text= new StringBuffer();
               int gelesen =0;
               while(gelesen!=-1){
                 gelesen=eingabestream.read();
                 if(gelesen!=-1){
                 text.append((char)gelesen);
                 }
               }

               //schließen der Datei
               eingabestream.close();
               inhalt=text.toString();
              
              return inhalt;
       }
       
       
   	/**
   	 * Normalisiert Strings fuer die Verarbeitung
   	 * alles klein und keine sonderzeichen, punkte usw.
   	 * @param String - text der gefiltert werden soll
   	 * @return String - gefilterter Text
   	 */    
   	public static String filterText(String in){
   		
   		int zeichen=0;
   		StringBuilder out=new StringBuilder();
   		
   		for(int n=0;n<in.length();n++){
   		
              //wandel zeichen um in integer
              zeichen= in.charAt(n);
   			
   	          //GroßBuchstaben zu kleinen machen
   	         if((zeichen>=65)&&(zeichen<=90)){
   	        	 out.append((char)(zeichen+32));
   	         }
   	        //KleinBuchstaben durchlassen
   	         if((zeichen>=97)&&(zeichen<=122)){
   	        	 out.append((char)(zeichen));
   	         }
   	         
   	         if(zeichen==228||zeichen==196){
   	        	 out.append("ae");  
   	         }
   	         if(zeichen==252||zeichen==220){
   	        	 out.append("ue");  
   	         }
   	         if(zeichen==246||zeichen==214){
   	        	 out.append("oe");  
   	         }
   	         
   	         if(zeichen==223){
   	        	 out.append("ss");
   	        	 
   	         }
   	         
   		}
   		
   		return out.toString();
   	}
    
   	
}
