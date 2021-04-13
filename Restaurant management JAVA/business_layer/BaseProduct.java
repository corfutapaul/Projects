package business_layer;

import java.io.Serializable;
import java.util.ArrayList;

public class BaseProduct extends MenuItem implements Serializable {
	private float price;
	private String name;

	public BaseProduct(String name, float pret) {
		this.name = name;
		this.price = pret;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Override

	public void displayMenu() {
		System.out.println(getName() + ": " + getPrice());
	}

	public String toString() {
		String s = "";
		s += getName();
		return s;
	}

	@Override
	public ArrayList<MenuItem> getList() {
		ArrayList<MenuItem> a = new ArrayList<MenuItem>();
		a.add(new BaseProduct(getName(), getPrice()));
		return a;
	}

}
