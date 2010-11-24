package zahlendarst;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * Die Klasse beinhaltet die Methode zum<br>
 * hinzufügen der Panels sowie die update Methode<br>
 * und die actionPerformed Methode.
 */
public class MainView extends JPanel implements ActionListener, Observer {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Methode zum hinzufügen der Panels
	 * @param zahlModel
	 * @param modModel
	 */
	public MainView(ZahlModel zahlModel,ModModel modModel){
		// Panels hinzufügen
		JTabbedPane tp = new JTabbedPane(JTabbedPane.LEFT); 
		
		tp.addTab("Umwandlung", new ZahlView(zahlModel));
		tp.addTab("ModPot", new ModView(modModel));
		add(tp);
	}
	
	/**
	 * update Methode
	 */
	@Override
	public void update(Observable arg0, Object arg1) {}

	/**
	 * actionPerformed Methode
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {}
}
