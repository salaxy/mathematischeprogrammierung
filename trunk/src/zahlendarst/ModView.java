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
 * Diese klasse implementiert
 * einen Panel zur ein und 
 * Ausgabe der Klasse Modmodel
 * zur Berechnung der Modularen Potenz
 * 
 */
public class ModView extends JPanel implements ActionListener, Observer {

	private static final long serialVersionUID = 1L;
	private ModModel model;
	private JButton compute = new JButton("Berechnen");
	private JTextField fieldExp = new JTextField("", 10);
	private JTextField fieldBase = new JTextField("", 10);
	private JTextField fieldMod = new JTextField("", 10);
	private JTextField fieldErg = new JTextField("", 10);

	/**
	 * Konstruktor
	 * @param model
	 */
	public ModView(ModModel model) {
		this.model = model;
		model.addObserver(this);
		this.compute.addActionListener(this);

		Box box = Box.createVerticalBox();
		box.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 50));
		box.add(new JLabel("  Zahl"));
		box.add(Box.createVerticalStrut(5));
		this.fieldExp.setAlignmentX(LEFT_ALIGNMENT);
		box.add(this.fieldBase);
		box.add(Box.createVerticalStrut(20));
		box.add(new JLabel("  Potenz"));
		box.add(Box.createVerticalStrut(5));
		this.fieldBase.setAlignmentX(LEFT_ALIGNMENT);
		box.add(this.fieldExp);
		box.add(Box.createVerticalStrut(20));
		box.add(new JLabel("  Modulo"));
		box.add(Box.createVerticalStrut(5));
		this.fieldMod.setAlignmentX(LEFT_ALIGNMENT);
		box.add(this.fieldMod);
		add(box);

		Box box2 = Box.createVerticalBox();
		box2.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		box2.add(new JLabel("  Ergebnis"));
		box2.add(Box.createVerticalStrut(5));
		this.fieldErg.setAlignmentX(LEFT_ALIGNMENT);
		this.fieldErg.setEditable(false);
		box2.add(this.fieldErg);
		box2.add(Box.createVerticalStrut(20));
		box2.add(this.compute);
		add(box2);

	}

	/**
	 * Methode zum einlesen der Werte und<br>
	 * aufrufen der berechnen Methode
	 */
	private void readInput() {
		try {
			this.model.setBase(Integer.valueOf(this.fieldBase.getText()));
			this.model.setExp(Integer.valueOf(this.fieldExp.getText()));
			this.model.setMod(Integer.valueOf(this.fieldMod.getText()));		
			this.model.berechne();
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Falsches Zahlenformat \noder zu groﬂe zahl,\nmaximale Zahl ist 2147483647 \n (Wertebereich Integer)",
					"Eingabefehler", JOptionPane.ERROR_MESSAGE);
		}catch (ModException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(),
					"Eingabefehler", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * update Methode
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		this.fieldErg.setText(this.model.getErgebnis()+"");
	}

	/**
	 * actionPerformed Methode
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
			readInput();
	}

}
