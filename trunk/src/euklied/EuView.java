package euklied;

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
public class EuView extends JPanel implements ActionListener, Observer {

	private static final long serialVersionUID = 1L;

	EuModel model;

	JButton compute = new JButton("Berechnen");

	JTextField a = new JTextField("", 10);
	JTextField b = new JTextField("", 10);
	JTextField g = new JTextField("", 6);
	JTextField x = new JTextField("", 6);
	JTextField y = new JTextField("", 6);

	/**
	 * Die Methode erstellt die Eingabe-/Ausgabefelder<br>
	 * sowie den Button.
	 * 
	 * @param model
	 */
	public EuView(EuModel model) {
		this.model = model;
		model.addObserver(this);
		setBackground(Color.lightGray);

		Box box = Box.createVerticalBox();
		box.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 50));
		JLabel label1 = new JLabel("  a");
		label1.setAlignmentY(TOP_ALIGNMENT);
		box.add(label1);
		box.add(Box.createVerticalStrut(5));
		a.setAlignmentX(LEFT_ALIGNMENT);
		box.add(a);
		box.add(Box.createVerticalStrut(20));
		box.add(new JLabel("  b"));
		box.add(Box.createVerticalStrut(5));
		b.setAlignmentX(LEFT_ALIGNMENT);
		box.add(b);
		box.add(Box.createVerticalStrut(15));
		compute.addActionListener(this);
		compute.setAlignmentX(LEFT_ALIGNMENT);
		box.add(compute);
		add(box);

		Box box2 = Box.createVerticalBox();
		box2.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		box2.add(new JLabel("  ggt"));
		box2.add(Box.createVerticalStrut(5));
		g.setAlignmentX(LEFT_ALIGNMENT);
		g.setEditable(false);
		box2.add(g);
		box2.add(new JLabel("  x"));
		box2.add(Box.createVerticalStrut(5));
		x.setAlignmentX(LEFT_ALIGNMENT);
		x.setEditable(false);
		box2.add(x);
		box2.add(new JLabel("  y"));
		box2.add(Box.createVerticalStrut(5));
		y.setAlignmentX(LEFT_ALIGNMENT);
		y.setEditable(false);
		box2.add(y);
		add(box2);
	}

	/**
	 * Die Methode dient zum einlesen der Werte<br>
	 * a und b und fängt falsche Eingaben ab.
	 */
	private void readInput() {
		try {
			model.setA(Integer.valueOf(a.getText()));
			model.setB(Integer.valueOf(b.getText()));
			// einfacher euklid
			// model.ggt();
			// Für erweiterten Euklid:
			model.erwGgt();
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(this, "Falsches Zahlenformat",
					"Eingabefehler", JOptionPane.ERROR_MESSAGE);
		} catch (EuException ee) {
			JOptionPane.showMessageDialog(this, ee.getMessage(),
					"Eingabefehler", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == compute)
			readInput();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		g.setText(model.getGgt() + "");
		x.setText(model.getX() + "");
		y.setText(model.getY() + "");
	}
}