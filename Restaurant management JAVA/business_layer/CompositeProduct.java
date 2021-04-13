package business_layer;

import java.io.Serializable;
import java.util.*;

public class CompositeProduct extends MenuItem implements Serializable {

	private static final long serialVersionUID = -7824175346776148298L;
	ArrayList<MenuItem> subMenus = new ArrayList<MenuItem>();
	int menuPrice = 0;
	private String name;

	public CompositeProduct(String name) {

		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return menuPrice;
	}

	public void setPrice(float f) {
		menuPrice += f;
	}

	public ArrayList<MenuItem> getList() {
		return subMenus;
	}

	@Override
	public void add(MenuItem menuItem) {
		this.subMenus.add(menuItem);
	}

	@Override
	public void displayMenu() {
		System.out.println(getName());
		this.subMenus.forEach(MenuItem::displayMenu);
		System.out.println();
	}

	@Override
	public float ComputePrice(ArrayList<MenuItem> lista) {
		menuPrice = 0;

		if (lista.size() == 1) {
			if (lista.get(0) instanceof BaseProduct)
				return ((BaseProduct) lista.get(0)).getPrice();
		}
		for (int i = 0; i < lista.size(); i++)
			if (lista.get(i) instanceof BaseProduct)
				setPrice(((BaseProduct) lista.get(i)).getPrice());
			else {
				comp(((CompositeProduct) lista.get(i)).getList());
			}

		return menuPrice;
	}

	public float comp(ArrayList<MenuItem> lista) {
		for (int i = 0; i < lista.size(); i++)
			if (lista.get(i) instanceof BaseProduct)
				setPrice(((BaseProduct) lista.get(i)).getPrice());
			else {
				comp(((CompositeProduct) lista.get(i)).getList());
			}
		return menuPrice;
	}

	public String toString() {
		String s = "";
		s += getName();
		return s;
	}

	public void setMenus(ArrayList<MenuItem> list) {
		this.subMenus = list;

	}
}