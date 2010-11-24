package zahlendarst;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * Diese Klasse erstellt das Anzeigefenster.
 * @author Andy Klay - 20090001 <br>
 * 		   Christoph Ott - 20090017 <br>
 * 		   Stephan Leddin - 20090057
 *
 */
public class MainFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;

	/**
	 * Konstruktor der klasse
	 */
	public MainFrame(){
		super("Zahlendarstellungen");
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		ZahlModel zahlModel = new ZahlModel();
		ModModel modModel=new ModModel();
		MainView view = new MainView(zahlModel,modModel);
		getContentPane().add(view);
		setSize(500,200);
		
		//Fenster wird in der Mitte des Bildschirms platziert
		Point p = calculateCenter( new Rectangle( new Point( 0, 0 ), Toolkit.getDefaultToolkit().getScreenSize() ), getSize() );
		setLocation( p );
		
		//Fenster Icon hinzu
		this.setIconImage( new ImageIcon( getClass().getResource( "/zahlendarst/zahlen.gif" ) ).getImage() );
		
		pack();
	}
	
	/**
	 * programmstart methode
	 * @param args
	 */
	public static void main(String[] args)  {
		MainFrame zf = new MainFrame();
		zf.setVisible(true);
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
		//SwingUtilities.convertPointToScreen( p, parent );

		p.translate( (int) m.width / 2, (int) m.height / 2 );
		p.translate( windowSize.width / -2, windowSize.height / -2 );

		return p;
	}
}
