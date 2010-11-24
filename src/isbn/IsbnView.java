package isbn;

import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

/**
 * Diese Klasse stellt die View-Komponente des Programms dar.
 * 
 * @author Andy Klay - 20090001 <br>
 *         Christoph Ott - 20090017 <br>
 *         Stephan Leddin - 20090057
 */
public class IsbnView extends JPanel implements ActionListener, Observer {

	private static final long serialVersionUID = 1L;

	private IsbnModel model;

	private JButton compute = new JButton("Berechnen");

	private JTextField isbn = new JTextField("", 11);
	private JTextField prüfziff = new JTextField("", 10);
	private JTextField gült = new JTextField("", 6);

	/**
	 * Die Methode erstellt die Eingabe-/Ausgabefelder<br>
	 * sowie den Button.
	 * 
	 * @param model
	 */
	public IsbnView(IsbnModel model) {
		this.model = model;
		model.addObserver(this);
		setBackground(Color.lightGray);

		Box box = Box.createVerticalBox();
		box.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 50));
		JLabel label1 = new JLabel("  ISBN");
		label1.setAlignmentY(TOP_ALIGNMENT);
		box.add(label1);
		box.add(Box.createVerticalStrut(5));
		this.isbn.setAlignmentX(LEFT_ALIGNMENT);
		box.add(this.isbn);
		box.add(Box.createVerticalStrut(5));
		box.add(Box.createVerticalStrut(15));
		this.compute.addActionListener(this);
		this.compute.setAlignmentX(LEFT_ALIGNMENT);
		box.add(this.compute);
		add(box);

		Box box2 = Box.createVerticalBox();
		box2.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		box2.add(new JLabel("  Prüfziffer"));
		box2.add(Box.createVerticalStrut(5));
		this.prüfziff.setAlignmentX(LEFT_ALIGNMENT);
		this.prüfziff.setEditable(false);
		box2.add(this.prüfziff);
		box2.add(new JLabel("  Gültigkeit"));
		box2.add(Box.createVerticalStrut(5));
		this.gült.setAlignmentX(LEFT_ALIGNMENT);
		this.gült.setEditable(false);
		box2.add(this.gült);
		add(box2);
	}

	/**
	 * Die Methode dient zum einlesen der ISBN.
	 */
	private void readInput() {
		try {
			this.model.setIsbn(this.isbn.getText());
			this.model.test();
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(this, "Falsches Zahlenformat",
					"Eingabefehler", JOptionPane.ERROR_MESSAGE);
		} catch (IsbnException ee) {
			JOptionPane.showMessageDialog(this, ee.getMessage(),
					"Eingabefehler", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Methode actionPerformed
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.compute)
			readInput();
	}

	/**
	 * Methode update
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		this.prüfziff.setText(this.model.getAusgabe());
		this.gült.setText(this.model.getGuelt() + "");
	}
}