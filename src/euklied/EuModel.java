package euklied;
import java.util.Observable;

/**
 * 
 * Die Klasse dient zur Berechnung des ggT:<br>
 * einmal unter Zuhilfenahme des Euklidischen Algorithmus<br>
 * sowie unter Zuhilfenahme des erweiterten Euklidischen Algorithmus
 *
 */
public class EuModel extends Observable {

	int a, b, x, y, ggt;

	public void setA(int a) {
		this.a = a;
	}
	
	public void setB(int b) {
		this.b = b;
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	public int getGgt() {
		return ggt;
	}

	public EuModel(int a, int b) {
		super();
		this.a = a;
		this.b = b;
		this.x = 0;
		this.y = 0;
	}

	public EuModel() {
		super();
		a = 0;
		b = 0;
		x = 0;
		y = 0;
	}

	/**
	 * 
	 * Die Methode ruft die Methode zur Berechnung<br>
	 * des ggT auf und wirft bei falscher Eingabe eine<br>
	 * Exception.
	 * @throws EuException
	 */
	public void ggt() throws EuException{
		if ((a == 0 && b == 0) || (a < 0) || (b < 0)) {
			throw new EuException("Eingabe muss größer Null sein");
		} else{
			ggt = ggt(a, b);
		}
		setChanged();
		notifyObservers();
	}
	
	/**
	 * 
	 * Diese Methode berechnet den ggT mit Hilfe<br>
	 * des rekursiven Euklidischen Algorithmus.
	 * @param a
	 * @param b
	 * @return
	 */
	private int ggt(int a, int b) {
		if (b == 0) {
			return a;
		} else{
			return ggt(b, a % b);
		}
	}
		/**
		 * Diese Methode berechnet den ggT mit Hilfe<br>
		 * des erweiterten Euklidischen Algorithmus und<br>
		 * wirft bei falscher Eingabe eine Exception.
		 * @throws EuException
		 */
	public void erwGgt() throws EuException{
		if ((a == 0 && b == 0) || (a < 0) || (b < 0)) {
			throw new EuException("Eingabe muss größer Null sein");
		} else{
			int q, r, s, t;
	        x = t = 1;
	        y = s = 0;
	        while (b > 0)
	        {
	            q = a / b;
	            r = a - q * b;
	            a = b;
	            b = r;
	            r = x - q * s;
	            x = s;
	            s = r;
	            r = y - q * t;
	            y = t;
	            t = r;
	        }
	        ggt = a;
		}
		setChanged();
		notifyObservers();
	}
}