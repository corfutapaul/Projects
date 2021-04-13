package presentation_layer;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Assignment4.Assignment4.App;
import business_layer.BaseProduct;
import business_layer.CompositeProduct;
import business_layer.MenuItem;
import business_layer.Restaurant;
import data_layer.RestaurantSerializator;

public class Delete {
	JButton b1 = new JButton("Delete composite product");
	JButton b2 = new JButton("Delete base product");
	JTextField tf1, tf4 = new JTextField();
	private RestaurantSerializator rs = new RestaurantSerializator();
	private Restaurant restaurant;
	private ArrayList<MenuItem> menus = new ArrayList<MenuItem>();
	public static String path;

	public RestaurantSerializator getRs() {
		return rs;
	}

	public void setRs(RestaurantSerializator rs) {
		this.rs = rs;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public ArrayList<MenuItem> getMenus() {
		return menus;
	}

	public void setMenus(ArrayList<MenuItem> menus) {
		this.menus = menus;
	}

	public Delete() {
		restaurant = App.restaurant;

		JFrame frame = new JFrame("Delete Product");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(600, 400);

		JPanel content0 = new JPanel();
		JLabel l0 = new JLabel("Delete from Menu");
		content0.add(l0);

		JPanel content1 = new JPanel();
		JLabel l1 = new JLabel("Delete Base Product");
		content1.add(l1);

		JPanel content2 = new JPanel();
		tf1 = new JTextField(); // nume
		JLabel l2 = new JLabel("Name:");
		tf1.setPreferredSize(new Dimension(200, 35));
		tf1.setText(null);
		content2.add(l2);
		content2.add(tf1);

		JPanel c = new JPanel();
		c.add(b2);

		JPanel content3 = new JPanel();
		JLabel l3 = new JLabel("Delete Composite Product");
		content3.add(l3);

		JPanel content4 = new JPanel();
		tf4 = new JTextField(); // nume
		JLabel l4 = new JLabel("Name:");
		tf4.setPreferredSize(new Dimension(200, 35));
		tf4.setText(null);
		content4.add(l4);
		content4.add(tf4);

		JPanel content5 = new JPanel();
		content5.add(b1);

		JPanel panel = new JPanel();
		panel.add(content0);
		panel.add(content1);
		panel.add(content2);
		panel.add(c);
		panel.add(content3);
		panel.add(content4);
		panel.add(content5);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		frame.setContentPane(panel);

		frame.setVisible(true);

	}

	public void deleteButton() {
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				deleteCompositeProduct();
				

			}
		});
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deleteBaseProduct();
				

			}
		});
	}

	/**
	 * metoda pentru stergere base product
	 */
	public void deleteBaseProduct() {

		ArrayList<MenuItem> list = new ArrayList<MenuItem>();
		list = App.restaurant.getOrders();
		ArrayList<MenuItem> deleteProduct = new ArrayList<MenuItem>();

		String bp = "";
		if (tf1.getText() != null) {
			bp = tf1.getText();
		}
		int ok = 0;
		try {
			if (bp != "") {
				for (MenuItem m : App.restaurant.orders) {
					if (m instanceof BaseProduct) {
						if (bp.equals(((BaseProduct) m).getName())) {
							ok = 1;
							deleteProduct.add((BaseProduct) m);
							tf1.setText(null);
							break;
						}

					}
				}
				if (ok == 1) {
					System.out.println("Deleted");
					restaurant.deleteMenuItem(deleteProduct);
				} else
					System.out.println("Nu exista produs cu acest nume");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * metoda pentru stergerea unui composite product
	 */
	public void deleteCompositeProduct() {

		ArrayList<MenuItem> list = new ArrayList<MenuItem>();
		list = App.restaurant.getOrders();
		ArrayList<MenuItem> deleteProduct = new ArrayList<MenuItem>();
		int ok = 0;
		String bp = "";
		if (tf4.getText() != null) {
			bp = tf4.getText();
		}
		try {
			if (bp != "") {
				for (MenuItem m : list) {
					if (m instanceof CompositeProduct) {
						if (bp.equals(((CompositeProduct) m).getName())) {
							ok = 1;
							System.out.println("Am gasit");
							System.out.println(m);
							deleteProduct.add((CompositeProduct) m);
							tf4.setText(null);
							break;
						}

					}
				}
				if (ok == 1) {
					System.out.println("Deleted");
					restaurant.deleteMenuItem(deleteProduct);
				} else
					System.out.println("Nu exista produs cu acest nume");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
