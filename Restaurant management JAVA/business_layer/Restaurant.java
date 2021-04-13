package business_layer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Observer;
import java.io.Serializable;
import data_layer.RestaurantSerializator;

import java.io.Serializable;

public class Restaurant implements Serializable {
	private static final long serialVersionUID = 1L;
	HashMap<Order, ArrayList<MenuItem>> comanda = new HashMap<Order, ArrayList<MenuItem>>();
	public static ArrayList<MenuItem> menus;
	public ArrayList<MenuItem> orders;
	public ArrayList<Order> clientOrders = new ArrayList<Order>();
	private RestaurantSerializator rs = new RestaurantSerializator();
	ArrayList<Observer> observer = new ArrayList<Observer>();
	Order order;

	public Restaurant(HashMap<Order, ArrayList<MenuItem>> comanda, ArrayList<MenuItem> orders,
			ArrayList<Order> clientOrders, RestaurantSerializator restaurantSerializator) {
		super();
		this.comanda = comanda;
		this.orders = orders;
		this.clientOrders = clientOrders;
		this.rs = restaurantSerializator;
		observer = new ArrayList<Observer>();
	}

	public HashMap<Order, ArrayList<MenuItem>> getComanda() {
		return comanda;
	}

	public void setComanda(HashMap<Order, ArrayList<MenuItem>> comanda) {
		this.comanda = comanda;
	}

	public ArrayList<MenuItem> getOrders() {
		return orders;
	}

	public void setOrders(ArrayList<MenuItem> orders) {
		this.orders = orders;
	}

	public ArrayList<Order> getClientOrders() {
		return clientOrders;
	}

	public void setClientOrders(ArrayList<Order> clientOrders) {
		this.clientOrders = clientOrders;
	}

	public static ArrayList<MenuItem> getMenus() {
		return menus;
	}

	public void comanda(Order order, ArrayList<MenuItem> meniu) {
		comanda.put(order, menus);
	}

	public void afisareComanda(Order order) {
		if (comanda.containsKey(order)) {
			System.out.println("User found in the collection");
		}
	}

	public void afisareTot() {
		comanda.entrySet().forEach(entry -> {
			System.out.println(entry.getKey() + " " + entry.getValue());
		});
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public RestaurantSerializator getSerializator() {
		return rs;
	}

	public void setSerializator(RestaurantSerializator serializator) {
		this.rs = serializator;
	}

	public HashMap<Order, ArrayList<MenuItem>> getOrderInfo() {
		return comanda;
	}

	public boolean wellFormed() {
		if (this.menus == null && this.comanda == null)
			return false;
		return true;
	}

	public void createMenuItem(MenuItem m) {
		assert wellFormed();
		assert m != null;
		try {
			orders.add(m);
			System.out.println(m);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * sterge din meniu
	 * 
	 * @param remove elementul care urmeaza sa fie sters
	 */
	public void deleteMenuItem(ArrayList<MenuItem> remove) {
		assert wellFormed();
		assert remove != null;
		try {
			orders.removeAll(remove);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param menus
	 * @param modified
	 * @param contor
	 */
	public void modifyInMenu(ArrayList<MenuItem> menus, MenuItem modified, int contor) {
		assert wellFormed();

		try {
			if (modified instanceof BaseProduct) {
				orders.set(contor, modified);

			}
			// rs.serializationMenu(menus);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * creeaza o comanda noua
	 * 
	 * @param o comanda ce urmeaza a fi creata
	 */
	public void createOrder(Order o) {
		assert wellFormed();
		assert o != null;
		try {
			clientOrders.add(o);
			comanda.put(o, orders);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * compute price
	 * 
	 * @param o comanda pe care se va calcula pretul
	 */
	public void computeOrderPrice(Order o) {
		assert wellFormed();
		assert o != null;
		try {
			CompositeProduct a = new CompositeProduct(null);
			a.ComputePrice(o.getOrders());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
