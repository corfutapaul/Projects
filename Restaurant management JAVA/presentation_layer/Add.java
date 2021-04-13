package presentation_layer;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import Assignment4.Assignment4.App;
import business_layer.BaseProduct;
import business_layer.CompositeProduct;
import business_layer.MenuItem;
import business_layer.Order;
import business_layer.Restaurant;

import data_layer.RestaurantSerializator;

public class Add implements Serializable {

	private JButton b1 = new JButton("Add CompositeProduct");
	private JButton b2 = new JButton("Add BaseProduct");
	private RestaurantSerializator rs = new RestaurantSerializator();
	private Restaurant restaurant = new Restaurant(null, null, null, null);
	public static ArrayList<MenuItem> menus;
	public static String path;

	JTextField tf0;
	JTextField tf1;
	JTextField tf2;
	JTextField tf3;
	JTextField tf4;
	JTextField tf5;
	JTextField tf6;
	JTextField tf7;
	JTextField tf8;
	JTextField tf9;
	JTextField tf10 = new JTextField();

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

	/**
	 * fereastra unde se adauga composite product sau base product
	 */
	public Add() {

		restaurant = App.restaurant;
		JFrame frame = new JFrame("Welcome Page");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(1000, 800);

		JPanel content0 = new JPanel();
		JLabel l0 = new JLabel("Add in Menu");
		content0.add(l0);

		JLabel l4 = new JLabel("Name:");
		// JLabel l5= new JLabel("Quantity:");
		JLabel l6 = new JLabel("Price:");

		JLabel l7 = new JLabel("Base Product 1:");
		JLabel l8 = new JLabel("Base Product 2:");
		JLabel l9 = new JLabel("Composite Product:");

		// JLabel l11= new JLabel("Quantity:");
		JLabel l12 = new JLabel("Price product1:");
		JLabel l13 = new JLabel("Price product2:");

		JPanel content1 = new JPanel();
		JLabel l1 = new JLabel("Add Base Product");
		content1.add(l1);

		JPanel content2 = new JPanel();
		tf1 = new JTextField(); // nume
		tf1.setPreferredSize(new Dimension(200, 35));
		tf1.setText(null);
		tf2 = new JTextField(); // cantitate
		tf2.setPreferredSize(new Dimension(200, 35));

		tf3 = new JTextField(); // pret
		tf3.setPreferredSize(new Dimension(200, 35));

		content2.add(l4);
		content2.add(tf1);
		content2.add(l6);
		content2.add(tf3);

		JPanel content3 = new JPanel();
		JLabel l3 = new JLabel("Add Composite Product");
		content3.add(l3);

		JPanel content4 = new JPanel();
		tf4 = new JTextField(); 
		tf4.setPreferredSize(new Dimension(200, 35));
		tf4.setText(null);
		tf5 = new JTextField(); 
		tf5.setPreferredSize(new Dimension(200, 35));
		tf5.setText(null);
		tf6 = new JTextField(); 
		tf6.setPreferredSize(new Dimension(200, 35));
		tf6.setText(null);
		content4.add(l7);
		content4.add(tf4);
		content4.add(l8);
		content4.add(tf5);
		content4.add(l9);
		content4.add(tf6);
		JPanel c12 = new JPanel();
		c12.add(b2);
		JPanel content5 = new JPanel();

		tf9 = new JTextField(); 
		tf9.setPreferredSize(new Dimension(200, 35));
		content5.add(l12);
		content5.add(tf9);
		content5.add(l13);
		content5.add(tf2);
		JPanel content6 = new JPanel();
		content6.add(b1);
		JPanel panel = new JPanel();
		panel.add(content0);
		panel.add(content1);
		panel.add(content2);
		panel.add(c12);
		panel.add(content3);
		panel.add(content4);
		panel.add(content5);
		panel.add(content6);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		frame.setContentPane(panel);
		frame.setVisible(true);

	}

	public void addButton() {
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				addInMenuCompositeProduct();
				System.out.println("You added.");	

			}
		});
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addInMenuBaseProduct();
				System.out.println("You added.");
			

			}
		});

	}


	public void addInMenuBaseProduct() {
		String nume = "";

		float pret = 0;
		try {

			if (tf1.getText() != null) {
				nume = tf1.getText();

				pret = Float.parseFloat(tf3.getText());
			}

			if (nume != "" && pret != 0) {
				BaseProduct bp = new BaseProduct(nume, pret);
				System.out.println(bp);
				MenuItem aux = (MenuItem) bp;
				App.restaurant.createMenuItem(aux);
				tf1.setText(null);
				tf2.setText("0");
				tf3.setText("0");
				System.out.println(App.restaurant.getOrders());

			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

	}
	public void addInMenuCompositeProduct() {

		String bp1 = "";
		String bp2 = "";
		String nume = "";

		float pret1 = 0;
		float pret2 = 0;
		if (tf4.getText() != null && tf5.getText() != null && tf6.getText() != null && tf9.getText() != null
				&& tf2.getText() != null) {
			bp1 = tf4.getText();
			bp2 = tf5.getText();
			nume = tf6.getText();
			pret1 = Float.parseFloat(tf9.getText());
			pret2 = Float.parseFloat(tf2.getText());
		}

		try {

			if (bp1 != "" && bp2 != "" && nume != "" && pret1 != 0 && pret2 != 0) {
				BaseProduct base1 = new BaseProduct(bp1, pret1);
				BaseProduct base2 = new BaseProduct(bp2, pret2);
				CompositeProduct cp = new CompositeProduct(nume);
				cp.add(base2);
				cp.add(base1);
				cp.setName(nume);
				App.restaurant.createMenuItem(cp);
				tf4.setText(null);
				tf5.setText(null);
				tf6.setText("0");
				tf9.setText("0");

			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

	}

}
