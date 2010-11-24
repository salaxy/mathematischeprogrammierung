package isbn;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.*;

import javax.swing.*;

/**
 * Diese Klasse erstellt das Anzeigefenster.
 * @author Andy Klay - 20090001 <br>
 * 		   Christoph Ott - 20090017 <br>
 * 		   Stephan Leddin - 20090057
 *
 */
public class IsbnFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Konstruktor der Klasse
	 */
	public IsbnFrame(){
		super("Prüfziffern - \u00A9 Andy Klay & Christoph Ott & Stephan Leddin 2010");
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		IsbnModel model = new IsbnModel();
		IsbnView view = new IsbnView(model);
		getContentPane().add(view);
		setSize(500,200);
		
		//Fenster wird in der Mitte des Bildschirms platziert
		Point p = calculateCenter( new Rectangle( new Point( 0, 0 ), Toolkit.getDefaultToolkit().getScreenSize() ), getSize() );
		setLocation( p );
		
		//Fenster Icon hinzu
		this.setIconImage( new ImageIcon( getClass().getResource( "/isbn/isbn.gif" ) ).getImage() );
		
		pack();
	}
	
	/**
	 * Hauptaufruf des Programms
	 * @param args
	 */
	public static void main(String[] args)  {
		IsbnFrame ef = new IsbnFrame();
		ef.setVisible(true);
		
	}
	

	/**
	 * Berechnet die Mitte des Bildschirms
	 * 
	 * @param screen
	 * @param windowSize
	 * @return p
	 */
	public static Point calculateCenter(Rectangle screen, Dimension windowSize) {
		Point p = screen.getLocation();
		Dimension m = screen.getSize();

		p.translate( (int) m.width / 2, (int) m.height / 2 );
		p.translate( windowSize.width / -2, windowSize.height / -2 );

		return p;
	}

}