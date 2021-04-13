package data_layer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import business_layer.MenuItem;
import business_layer.Order;
import business_layer.Restaurant;
import presentation_layer.AdministratorGraphicalUserInterface;

public class RestaurantSerializator implements Serializable {

	private static final long serialVersionUID = 1L;
	public static String address = "C:\\PT2020\\pt_302210_corfuta_paul_assignment4\\Assignment4\\res.ser";

	public RestaurantSerializator() {
		address = AdministratorGraphicalUserInterface.address;
	}

	public void serializationMenu(ArrayList<MenuItem> menus) {
		try {
			FileOutputStream file = new FileOutputStream("C:\\PT2020\\pt_302210_corfuta_paul_assignment4\\Assignment4\\res.ser");
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(menus);
			out.flush();
			out.close();
			file.close();
			System.out.println("Object has been serialized");
		}

		catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	public ArrayList<MenuItem> deserializationMenu() {
		ArrayList<MenuItem> menuDeserilized = new ArrayList<MenuItem>();
		try {
			FileInputStream file = new FileInputStream(
					"C:\\PT2020\\pt_302210_corfuta_paul_assignment4\\Assignment4\\res.ser");
			ObjectInputStream in = new ObjectInputStream(file);
			ArrayList<MenuItem> readObject = ((ArrayList<MenuItem>) in.readObject());
			menuDeserilized = readObject;
			in.close();
			file.close();
			return menuDeserilized;
		}

		catch (IOException ex) {
			ex.printStackTrace();
		}

		catch (ClassNotFoundException ex) {
			System.out.println("ClassNotFoundException is caught");
		}
		return menuDeserilized;
	}

	public void serializationOrder(ArrayList<Order> o) {
		try {
			FileOutputStream file = new FileOutputStream("orders.ser");
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(o);
			out.flush();
			out.close();
			file.close();

			System.out.println("Object has been serialized");

		}

		catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	public ArrayList<Order> deserializationOrder() {
		ArrayList<Order> aux = new ArrayList<Order>();

		try {

			FileInputStream file = new FileInputStream("orders.ser");
			ObjectInputStream in = new ObjectInputStream(file);

			aux = (ArrayList<Order>) in.readObject();

			in.close();
			file.close();

			System.out.println("Object has been deserialized ");
			return aux;
		}

		catch (IOException ex) {
			ex.printStackTrace();
		}

		catch (ClassNotFoundException ex) {
			System.out.println("ClassNotFoundException is caught");
		}
		return aux;
	}

	public void serializationHM(HashMap<Order, ArrayList<MenuItem>> o) {
		try {

			FileOutputStream file = new FileOutputStream("HM.ser");
			ObjectOutputStream out = new ObjectOutputStream(file);

			out.writeObject(o);
			out.flush();
			out.close();
			file.close();

			System.out.println("Object has been serialized");

		}

		catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	public HashMap<Order, ArrayList<MenuItem>> deserializationHM() {
		HashMap<Order, ArrayList<MenuItem>> aux = new HashMap<Order, ArrayList<MenuItem>>();

		try {

			FileInputStream file = new FileInputStream("HM.ser");
			ObjectInputStream in = new ObjectInputStream(file);

			aux = (HashMap<Order, ArrayList<MenuItem>>) in.readObject();

			in.close();
			file.close();

			System.out.println("Object has been deserialized ");
			return aux;
		}

		catch (IOException ex) {
			System.out.println("IOException is caught");
		}

		catch (ClassNotFoundException ex) {
			System.out.println("ClassNotFoundException is caught");
		}
		return aux;
	}

	public void serializationRestaurant(Restaurant res) {
		try {

			FileOutputStream file = new FileOutputStream("restaurant.ser");
			ObjectOutputStream out = new ObjectOutputStream(file);

			out.writeObject(res);
			out.flush();
			out.close();
			file.close();

			System.out.println("Object has been serialized");

		}

		catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	public Restaurant deserializationRestaurant() {
		Restaurant a = new Restaurant(null, null, null, null);

		try {

			FileInputStream file = new FileInputStream("restaurant.ser");
			ObjectInputStream in = new ObjectInputStream(file);

			a = (Restaurant) in.readObject();

			in.close();
			file.close();

			System.out.println("Object has been deserialized ");
			return a;
		}

		catch (IOException ex) {
			System.out.println("IOException is caught");
		}

		catch (ClassNotFoundException ex) {
			System.out.println("ClassNotFoundException is caught");
		}
		return a;
	}
}
