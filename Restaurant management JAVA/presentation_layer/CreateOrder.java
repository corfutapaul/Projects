package presentation_layer;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
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
import business_layer.Order;
import business_layer.Restaurant;
import data_layer.RestaurantSerializator;

public class CreateOrder {
	private JButton b1 = new JButton("Create Order");
	private JButton b2 = new JButton("Add in List");
	JTextField tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8, tf9;
	JLabel l0, l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12;
	private RestaurantSerializator rs = new RestaurantSerializator();
	private Restaurant restaurant;
	ArrayList<MenuItem> clientList = new ArrayList<MenuItem>();
	ArrayList<MenuItem> alo = new ArrayList<MenuItem>();

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

	public CreateOrder() {
		restaurant = App.restaurant;
		JFrame frame = new JFrame("Welcome Page");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(900, 900);

		JPanel content0 = new JPanel();
		content0 = new JPanel();
		l0 = new JLabel("Add Product in Order");
		content0.add(l0);

		JPanel content1 = new JPanel();
		l1 = new JLabel("Product Name from Menu:");
		content1.add(l1);
		tf1 = new JTextField();
		tf1.setText(null);
		content1.add(tf1);
		tf1.setPreferredSize(new Dimension(200, 35));

		JPanel content4 = new JPanel();
		l7 = new JLabel("OrderID:");
		tf6 = new JTextField();
		tf6.setPreferredSize(new Dimension(200, 35));
		tf6.setText("0");
		content4.add(l7);
		content4.add(tf6);
		l8 = new JLabel("Table:");
		tf7 = new JTextField();
		tf7.setPreferredSize(new Dimension(200, 35));
		tf7.setText("0");
		content4.add(l8);
		content4.add(tf7);
		JPanel content5 = new JPanel();
		content5.add(b1);

		JPanel panel = new JPanel();
		panel.add(content0);
		panel.add(content1);
		panel.add(content4);
		panel.add(content5);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		frame.setContentPane(panel);

		frame.setVisible(true);

	}

	void createOrderButton() {
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				process();
				System.out.println("You created an order.");

			}
		});
	}

	public Order createOrder(ArrayList<MenuItem> list) {
		System.out.println(list);
		LocalDateTime d;
		int orderID = 0;
		String table = null;
		Order o = null;
		if (tf6.getText() != "0" && tf7.getText() != "0") {
			orderID = Integer.parseInt(tf6.getText());
			table = tf7.getText();
		}
		if (orderID != 0 && table != null) {
			o = new Order(orderID, LocalDateTime.now(), table, list);
		}
		tf6.setText("0");
		tf7.setText("0");
		System.out.println(o);
		return o;

	}

	public void process() {

		MenuItem good = null;
		String nume = tf1.getText();
		for (MenuItem m : App.restaurant.getOrders()) {
			if ((m instanceof BaseProduct) && (nume.equals(((BaseProduct) m).getName())))
				good = m;
			else if ((m instanceof CompositeProduct) && (nume.equals(((CompositeProduct) m).getName())))
				good = m;
		}

		Order aux = createOrder(good.getList());
		App.restaurant.createOrder(aux);

	}

}
