package euklied;
import java.awt.event.*;

import javax.swing.*;

/**
 * Diese Klasse erstellt das Anzeigefenster.
 * @author Andy Klay - 20090001 <br>
 * 		   Christoph Ott - 20090017 <br>
 * 		   Stephan Leddin - 20090057
 *
 */
public class EuFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Konstruktor der Klasse
	 */
	public EuFrame(){
		super("Euklid'scher Algorithmus");
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		EuModel model = new EuModel();
		EuView view = new EuView(model);
		getContentPane().add(view);
		setSize(500,200);
		pack();
	}
	
	/**
	 *  Hauptaufruf des Programms
	 * @param args
	 */
	public static void main(String[] args)  {
		EuFrame ef = new EuFrame();
		ef.setVisible(true);
		
	}


}