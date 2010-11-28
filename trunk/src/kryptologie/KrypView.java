package kryptologie;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Diese Klasse stellt die View-Komponente des Programms dar.
 * 
 * @author Andy Klay - 20090001 <br>
 *         Christoph Ott - 20090017 <br>
 *         Stephan Leddin - 20090057
 */
public class KrypView extends JPanel implements ActionListener, Observer {

	private static final long serialVersionUID = 1L;
	
	private CaesarModel caesar;
	private VigenereModel vigenere;
	
	private JTextArea innArea=new JTextArea("");
	private JTextArea outArea=new JTextArea("");
	private JTextField keyField = new JTextField("", 10);
	private JScrollPane innPane=new JScrollPane();
	private JScrollPane outPane=new JScrollPane();
	
	private JLabel eingabeText=new JLabel("Text-Input");
	private JLabel ausgabeText=new JLabel("Text-Output");
	private JLabel keyText=new JLabel("Key Word or Char");
	
	private JButton chifButton =new JButton("encipher");
	private JButton loadButton =new JButton("load from . . .");
	private JButton saveButton =new JButton("save to . . .");
	private JButton dechifButton =new JButton("decipher");
	private JButton inDelButton =new JButton("delete input");
	private JButton outDelButton =new JButton("delete output");
	private JButton automaticButton =new JButton("autodecode");
	
	private Checkbox caesarBox,vigenereBox;
	private CheckboxGroup boxgroup=new CheckboxGroup();

	/**
	 * dieser wahrheitswert sagt aus ob verschlüsselt wird oder entschlüsselt
	 * (dient zur Vermeidung von Redundanz)
	 */
	private boolean cipherIsOn=true;
	
	
	/**
	 * Konstruktor der Klasse
	 * initialisiert die Klasse
	 * @param caesar - CaesarModel
	 * @param vigenere - VigenereModel
	 */
	public KrypView(CaesarModel caesar,VigenereModel vigenere) {
		
		setLayout(null);
		this.vigenere=vigenere;
		this.caesar = caesar;
		//Observer hinzu
		vigenere.addObserver(this);
		caesar.addObserver(this);	
		
		//checkboxen initialisieren
		caesarBox= new Checkbox("Caesar",boxgroup, true);
		vigenereBox= new Checkbox("Vigenere",boxgroup, false);
		
		innArea.setLineWrap(true);
		outArea.setLineWrap(true);

		//gui-elemente anordnen
		eingabeText.setBounds(5,1,120,20);
		ausgabeText.setBounds(310, 1,120,20);
		
		innPane.setBounds(5, 20, 300, 400);
		outPane.setBounds(310,20,300,400);
		innPane.setViewportView(innArea);
		outPane.setViewportView(outArea);
		
		chifButton.setBounds(620,20, 120, 20);
		dechifButton.setBounds(620, 50, 120, 20);
		automaticButton.setBounds(620, 80, 120, 20);
		
		inDelButton.setBounds(620, 110, 120, 20);
		outDelButton.setBounds(620, 140, 120, 20);
		loadButton.setBounds(620,170,120,20);
		saveButton.setBounds(620,200,120,20);
		keyText.setBounds(620,225,120,20);
		keyField.setBounds(620, 250,120, 20);
		caesarBox.setBounds(630, 280,100,15);
		vigenereBox.setBounds(630, 310,100,15);
		
		
		//Tooltips eintragen
		chifButton.setToolTipText("Verschlüsseln");
		dechifButton.setToolTipText("Entschlüsseln");
		automaticButton.setToolTipText("Automatisch entschlüsseln mit Caesar");
		inDelButton.setToolTipText("Eingabefeld löschen");
		outDelButton.setToolTipText("Ausgabefeld löschen");
		loadButton.setToolTipText("Input-Text aus Datei laden");
		saveButton.setToolTipText("Output-Text in Datei speichern");
		keyField.setToolTipText("Hier schlüssel eintragen");
		
		
		//hinzufügen zum layout
		add(chifButton);
		add(dechifButton);
		add(inDelButton);
		add(outDelButton);
		add(loadButton);
		add(saveButton);
		add(automaticButton);
		
		add(eingabeText);
		add(ausgabeText);
		add(keyText);
		add(keyField);
		add(caesarBox);
		add(vigenereBox);
		
		add(innPane);
		add(outPane);
		
		//action listener
		chifButton.addActionListener(this);
		dechifButton.addActionListener(this);
		inDelButton.addActionListener(this);
		outDelButton.addActionListener(this);
		loadButton.addActionListener(this);
		saveButton.addActionListener(this);
		automaticButton.addActionListener(this);
	}

	/**
	 * Methode die das ver und entschluesseln
	 * auslagert um Redundanz zu vermeiden und
	 * uebersichtlichkeit zu behalten
	 * (wertet u.a. die Checkboxen aus)
	 */
	private void verarbeite() {
		try {
			if(caesarBox.getState()){
				
				if(keyField.getText().length()>1){
					throw new KeyException("Schlüssel ist zu lang!!");
				}
				caesar.setSchluessel(keyField.getText().charAt(0));
				if(cipherIsOn){
					caesar.setKlartext(DateiArbeit.filterText(innArea.getText()));
					caesar.verschluesseln();
				}else{
					caesar.setGeheimtext(DateiArbeit.filterText(innArea.getText()));
					caesar.entschluesseln();
				}
			}
			
			if(vigenereBox.getState()){
				
				vigenere.setschluessel(keyField.getText());
				
				if(cipherIsOn){
					vigenere.setKlartext(DateiArbeit.filterText(innArea.getText()));
					vigenere.verschluesseln();
				}else{
					vigenere.setGeheimtext(DateiArbeit.filterText(innArea.getText()));
					vigenere.entschluesseln();
				}
			}	
			
		} catch (StringIndexOutOfBoundsException se) {
			JOptionPane.showMessageDialog(this, "Sie haben kein Schlüssel eingegeben!!","Eingabefehler", JOptionPane.ERROR_MESSAGE);
		}catch (KeyException ke) {
			JOptionPane.showMessageDialog(this, ke.getMessage(),"Eingabefehler", JOptionPane.ERROR_MESSAGE);
		}	
	}
	


	@Override
	public void actionPerformed(ActionEvent e) {
		
		//Verschlüsseln
		if (e.getSource() == chifButton){
			cipherIsOn=true;
			verarbeite();
		}
		
		//Entschlüsseln
		if (e.getSource() == dechifButton){
			cipherIsOn=false;
			verarbeite();
		}
		
		//löscht input TextArea
		if (e.getSource() == inDelButton){
			innArea.setText("");
		}
		
		//löscht output TextArea
		if (e.getSource() == outDelButton){
			outArea.setText("");
		}
		
		//auto entschluesseln mit caesar
		if (e.getSource() == automaticButton){
			cipherIsOn=false;
			caesar.setGeheimtext(DateiArbeit.filterText(innArea.getText()));
			caesar.autoEntschluesseln();
		}
		
		
		
		//laden des Eingabefeldes von Datei
		if (e.getSource() == loadButton){
			JFileChooser chooser=new JFileChooser();
			FileNameExtensionFilter filter=new FileNameExtensionFilter("Text-files","txt");
			chooser.setFileFilter(filter);
			chooser.setAcceptAllFileFilterUsed(false);
			chooser.setDialogTitle("Load file to input");
			chooser.showOpenDialog(this);
			//nur wenn Datei auch auswegwählt wurde
			//falls dialog abbgebrochen wird
			if(chooser.getSelectedFile()!=null){
				String datname=chooser.getSelectedFile().getPath();
				String in;
				
				try {
					in = DateiArbeit.laden(datname);				
					innArea.setText(in);
				} catch (IOException e1) {   
		        	   JOptionPane.showMessageDialog(this, "Fehler beim Laden der Datei","Fehler", JOptionPane.ERROR_MESSAGE);
		        }	
			}
		}	
		
		//laden des Eingabefeldes von Datei
		if (e.getSource() == saveButton){
			JFileChooser chooser=new JFileChooser();
			FileNameExtensionFilter filter=new FileNameExtensionFilter("Text-files","txt");
			chooser.setFileFilter(filter);
			chooser.setAcceptAllFileFilterUsed(false);
			chooser.setDialogTitle("Save output to file");
			chooser.showSaveDialog(this);
			//nur wenn Datei auch auswegwählt wurde
			//falls dialog abbgebrochen wird
			if(chooser.getSelectedFile()!=null){
				String datname=chooser.getSelectedFile().getPath();
				
				try {
					DateiArbeit.speichern(outArea.getText(), datname);
				} catch (IOException e1) {   
		        	   JOptionPane.showMessageDialog(this, "Fehler beim Speichern der Datei","Fehler", JOptionPane.ERROR_MESSAGE);
		        }	

				JOptionPane.showMessageDialog(this, "Text-Output erfolgreich gespeichert!","Information", JOptionPane.INFORMATION_MESSAGE);
			}
		}	
		
		
		
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		
		if(arg0 instanceof CaesarModel){
			if(cipherIsOn){
				outArea.setText(caesar.getGeheimtext().toUpperCase());
			}else{
				outArea.setText(caesar.getKlartext());
			}
		}

		if(arg0 instanceof VigenereModel){
			if(cipherIsOn){
				outArea.setText(vigenere.getGeheimtext().toUpperCase());
			}else{
				outArea.setText(vigenere.getKlartext());
			}
		}
	}
	

}