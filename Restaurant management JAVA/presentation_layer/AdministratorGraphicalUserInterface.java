package presentation_layer;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import Assignment4.Assignment4.App;
import business_layer.MenuItem;
import business_layer.Restaurant;
import data_layer.RestaurantSerializator;

public class AdministratorGraphicalUserInterface {

	RestaurantSerializator serializator = new RestaurantSerializator();
	Restaurant restaurant;
	private JButton createNewMenuItem = new JButton("Create new Menu Item");
	private JButton deleteMenuItem = new JButton("Delete menu item");
	private JButton editMenuItem = new JButton("Edit menu item");
	private JButton view = new JButton("View all");
	private JTable table = new JTable();
	public static String address;

	public RestaurantSerializator getSerializator() {
		return serializator;
	}

	public void setSerializator(RestaurantSerializator serializator) {
		this.serializator = serializator;
	}

	public AdministratorGraphicalUserInterface() {

		JFrame frame = new JFrame("Administrator Window");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(400, 400);

		JPanel panel1 = new JPanel();

		JLabel label1 = new JLabel("Administrator commands:");
		label1.setPreferredSize(new Dimension(220, 100));
		panel1.add(label1);

		JPanel label2 = new JPanel();
		createNewMenuItem.setPreferredSize(new Dimension(200, 50));
		label2.add(createNewMenuItem);

		JPanel label3 = new JPanel();
		deleteMenuItem.setPreferredSize(new Dimension(200, 50));
		label3.add(deleteMenuItem);

		JPanel label4 = new JPanel();
		editMenuItem.setPreferredSize(new Dimension(200, 50));
		label4.add(editMenuItem);

		JPanel label5 = new JPanel();
		view.setPreferredSize(new Dimension(200, 50));
		label5.add(view);

		JPanel panel = new JPanel();
		panel.add(label1);
		panel.add(label2);
		panel.add(label3);
		panel.add(label4);
		panel.add(label5);

		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		frame.setContentPane(panel);

		frame.setVisible(true);
		restaurant = App.restaurant;
		System.out.println(App.restaurant.orders);
		newMenuItem();
		viewButton();
		deleteMenuItem();
		editMenuItem();
	}

	public void afisare() {
		View table = new View();
		String[][] s = table.getMenu(App.restaurant.getOrders());
		table.menuTable(s);

	}

	public void newMenuItem() {
		createNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Add in menu");

				Add add = new Add();
				add.addButton();
			}
		});
	}

	public void deleteMenuItem() {
		deleteMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Delete from menu");

				Delete d = new Delete();
				d.deleteButton();
			}
		});
	}

	public void editMenuItem() {
		editMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Modify in menu");

				Edit m = new Edit();
				m.modifyButton();

			}
		});
	}

	public void viewButton() {
		view.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Afisare");
				afisare();

			}
		});
	}
}
