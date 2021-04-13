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

public class Edit {

	JButton b1 = new JButton("Modify");
	JTextField tf1, tf2, tf3, tf4;
	JTextField tf6 = new JTextField();

	private Restaurant restaurant;
	public static String path;

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public Edit() {
		restaurant = App.restaurant;
		JFrame frame = new JFrame("Welcome Page");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(800, 600);

		JPanel content0 = new JPanel();
		JLabel l0 = new JLabel("Modify in Menu");
		content0.add(l0);

		JLabel l4 = new JLabel("Original Name:");

		JLabel l6 = new JLabel("Price:");
		JLabel l7 = new JLabel("New Name:");

		JPanel content2 = new JPanel();
		tf1 = new JTextField();
		tf1.setPreferredSize(new Dimension(200, 35));
		tf1.setText(null);
		tf2 = new JTextField();
		tf2.setPreferredSize(new Dimension(200, 35));
		tf2.setText(null);

		JPanel content3 = new JPanel();

		tf4 = new JTextField();
		tf4.setPreferredSize(new Dimension(100, 35));
		tf4.setText("0");

		JPanel content4 = new JPanel();
		content4.add(b1);

		content2.add(l4);
		content2.add(tf1);
		content2.add(l7);
		content2.add(tf2);

		content3.add(l6);
		content3.add(tf4);

		JPanel content5 = new JPanel();
		JLabel l8 = new JLabel("OPTIONAL - Name Composite Product:");
		tf6 = new JTextField();
		tf6.setPreferredSize(new Dimension(200, 35));

		content5.add(l8);
		content5.add(tf6);

		JPanel panel = new JPanel();
		panel.add(content0);
		panel.add(content5);
		panel.add(content2);
		panel.add(content3);
		panel.add(content4);

		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		frame.setContentPane(panel);

		frame.setVisible(true);

	}

	public void modifyProduct() {

		ArrayList<MenuItem> list = new ArrayList<MenuItem>();
		list = restaurant.getOrders();

		MenuItem modified = null;
		MenuItem modifiedComposite = null;
		ArrayList<MenuItem> baseList = new ArrayList<MenuItem>();
		String nume = "";
		String numeNou = "";

		float pret = 0;

		if (tf1.getText() != null && tf2.getText() != null && tf4.getText() != "0") {
			nume = tf1.getText();
			numeNou = tf2.getText();

			pret = Float.parseFloat(tf4.getText());

			modified = new BaseProduct(numeNou, pret);

			if (tf6.getText().equals("null")) {
				modified = new BaseProduct(numeNou, pret);

			} else {
				modified = new BaseProduct(numeNou, pret);
				modifiedComposite = new CompositeProduct(tf6.getText());
				((CompositeProduct) modifiedComposite).setName(tf6.getText());
				((CompositeProduct) modifiedComposite).setMenus(baseList);
			}

		}

		int contor = 0;
		for (MenuItem m : list) {
			if (m instanceof BaseProduct) {
				if (((BaseProduct) m).getName().equals(nume)) {
					list.set(contor, modified);
					restaurant.modifyInMenu(list, modified, contor);

				}
			}

			if (m instanceof CompositeProduct) {
				ArrayList<MenuItem> l = new ArrayList<MenuItem>();
				if (((CompositeProduct) m).getName().equals(tf6.getText())) {
					int c = 0;
					l = ((CompositeProduct) m).getList();
					for (MenuItem x : l) {
						if (((BaseProduct) x).getName().equals(nume)) {
							l.set(c, modified);
						}
						c++;
					}

					baseList.addAll(l);
				}

				System.out.println(modifiedComposite);
				restaurant.modifyInMenu(list, modifiedComposite, contor);
			}
			contor++;
		}

		tf1.setText(null);
		tf2.setText(null);
		tf3.setText("0");
		tf4.setText("0");
		tf6.setText("null");

	}

	public void modifyButton() {
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modifyProduct();
				System.out.println("You modified.");
			}
		});
	}
}
