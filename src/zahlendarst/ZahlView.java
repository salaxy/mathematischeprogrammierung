package zahlendarst;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * Klasse für das Aussehen der Umwandlung
 */
public class ZahlView extends JPanel implements ActionListener, Observer {

	private static final long serialVersionUID = 1L;
	private ZahlModel zahlModel;
	private JButton compute = new JButton("Berechnen");
	private JTextField tfBasis1 = new JTextField("", 10);
	private JTextField tfBasis2 = new JTextField("", 10);
	private JTextField tfZahl = new JTextField("", 10);
	private JTextField tfErg = new JTextField("", 12);

	/**
	 * Konstruktor für die Klasse
	 * @param zahlModel
	 */
	public ZahlView(ZahlModel zahlModel) {
		this.zahlModel = zahlModel;
		zahlModel.addObserver(this);

		Box box = Box.createVerticalBox();
		box.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 50));
		box.add(new JLabel("  Basis 1"));
		box.add(Box.createVerticalStrut(5));
		this.tfBasis1.setAlignmentX(LEFT_ALIGNMENT);
		box.add(this.tfBasis1);
		box.add(Box.createVerticalStrut(20));
		box.add(new JLabel("  Basis 2"));
		box.add(Box.createVerticalStrut(5));
		this.tfBasis2.setAlignmentX(LEFT_ALIGNMENT);
		box.add(this.tfBasis2);
		add(box);

		Box box2 = Box.createVerticalBox();
		box2.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		box2.add(new JLabel("  Zahl"));
		box2.add(Box.createVerticalStrut(5));
		this.tfZahl.setAlignmentX(LEFT_ALIGNMENT);
		box2.add(this.tfZahl);
		box2.add(Box.createVerticalStrut(20));
		box2.add(new JLabel("  Ergebnis"));
		box2.add(Box.createVerticalStrut(5));
		this.tfErg.setAlignmentX(LEFT_ALIGNMENT);
		this.tfErg.setEditable(false);
		box2.add(this.tfErg);
		add(box2);

		Box box3 = Box.createVerticalBox();
		box3.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		this.compute.addActionListener(this);
		box3.add(this.compute);
		add(box3);
	}

	/**
	 * Methode zum Einlesen der Werte und<br>
	 * aufrufen der umwandlungB1B2 Methode
	 */
	private void readInput() {
		try {
			this.zahlModel.setBasis1(Integer.valueOf(this.tfBasis1.getText()));
			this.zahlModel.setBasis2(Integer.valueOf(this.tfBasis2.getText()));
			this.zahlModel.setEingabe(this.tfZahl.getText());
			this.zahlModel.umwandlungB1B2();
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(this, "Falsches Zahlenformat",
					"Eingabefehler", JOptionPane.ERROR_MESSAGE);
		} catch (ZahlNichtInBasisException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(),
					"Eingabefehler", JOptionPane.ERROR_MESSAGE);
		} catch (BasisException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(),
					"Eingabefehler", JOptionPane.ERROR_MESSAGE);
		} catch (ZahlException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(),
					"Eingabefehler", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * update Methode
	 */
	@Override
	public void update(Observable o, Object arg) {
		this.tfErg.setText(this.zahlModel.getAusgabe() + "");
	}

	/**
	 * actionPerformed Methode
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.compute)
			readInput();
	}
}
