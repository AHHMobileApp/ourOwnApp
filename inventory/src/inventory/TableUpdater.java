package inventory;

import java.util.Iterator;
import java.util.Vector;

public class TableUpdater {
	private Vector observers = new Vector();

	public void attach(Observer o) {
		observers.add(o);
	}

	public void detach(Observer o) {
		observers.remove(o);
	}

	public void Notify(int row, int qty) {
		for (Iterator it = observers.iterator(); it.hasNext();) {
			Observer o = (Observer) it.next();
			o.update(this,row,qty);
		}
	}



	public void setState(int n){

	}

}
