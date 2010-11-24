package kalendat;

import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

/**
 * Diese Klasse stellt die View-Komponente des Programms dar.
 * @author Andy Klay - 20090001 <br>
 *         Christoph Ott - 20090017 <br>
 *         Stephan Leddin - 20090057
 */
public class KaView extends JPanel implements ActionListener, Observer {

	private static final long serialVersionUID = 1L;

	private KaModel model;

	JButton compute = new JButton("Berechnen");

	JTextField tfDate = new JTextField("", 6);
	JTextField tfAusgabe = new JTextField("", 6);

	/**
	 * Die Methode erstellt die Eingabe-/Ausgabefelder<br>
	 * sowie den Button.
	 * @param model
	 */
	public KaView(KaModel model) {
		this.model = model;
		model.addObserver(this);
		setBackground(Color.lightGray);

		Box box = Box.createVerticalBox();
		box.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 50));
		
		JLabel label1 = new JLabel("  Datum");
		label1.setAlignmentY(TOP_ALIGNMENT);
		box.add(label1);
		box.add(Box.createVerticalStrut(5));
		tfDate.setAlignmentX(LEFT_ALIGNMENT);
		box.add(tfDate);

		box.add(Box.createVerticalStrut(20));
		box.add(new JLabel("  Wochentag"));
		box.add(Box.createVerticalStrut(5));
		tfAusgabe.setAlignmentX(LEFT_ALIGNMENT);
		tfAusgabe.setEditable(false);
		box.add(tfAusgabe);
		
		compute.addActionListener(this);
		compute.setAlignmentX(LEFT_ALIGNMENT);
		box.add(Box.createVerticalStrut(20));
		box.add(compute);
		add(box);
	}

	/**
	 * Die Methode dient zum einlesen der Werte<br>
	 * a und b und fängt falsche Eingaben ab.
	 */
	private void readInput() {
		try {
			model.setAll(tfDate.getText());
			model.berechnung();
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(this, "Falsches Zahlenformat",
					"Eingabefehler", JOptionPane.ERROR_MESSAGE);
		} catch (KaException ee) {
			JOptionPane.showMessageDialog(this, ee.getMessage(),
					"Eingabefehler", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * actionPerformed Methode
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == compute)
			readInput();
	}

	/**
	 * update Methode
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		tfAusgabe.setText(model.getAusgabe() + "");
	}
}