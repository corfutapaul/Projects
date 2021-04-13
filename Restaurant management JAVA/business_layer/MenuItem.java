package business_layer;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class MenuItem implements Serializable {

	/**
	 * Clasa abstracta de tip MenuItem
	 */
	private static final long serialVersionUID = 4721952269398576966L;

	public MenuItem() {
		super();

	}

	public void add(MenuItem item) {
		throw new UnsupportedOperationException();
	};

	public float ComputePrice(ArrayList<MenuItem> a) {
		throw new UnsupportedOperationException();
	};

	public abstract void displayMenu();

	public abstract ArrayList<MenuItem> getList();

}
