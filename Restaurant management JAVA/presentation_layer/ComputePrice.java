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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import Assignment4.Assignment4.App;
import business_layer.CompositeProduct;
import business_layer.Order;
import data_layer.RestaurantSerializator;

public class ComputePrice {
	JButton b1 = new JButton("Compute Price");
	private RestaurantSerializator rs = new RestaurantSerializator();
	JTextField tf1;
	JTextField tf2;

	public ComputePrice() {
		JFrame frame = new JFrame("Welcome Page");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(900, 700);

		JPanel content0 = new JPanel();
		JLabel l0 = new JLabel("Compute order price");
		content0.add(l0);

		JPanel content1 = new JPanel();
		tf1 = new JTextField();
		tf2 = new JTextField();
		JLabel l1 = new JLabel("OrderID:");
		tf1.setPreferredSize(new Dimension(200, 35));
		tf2.setPreferredSize(new Dimension(200, 35));

		content1.add(tf1);
		content1.add(tf2);

		JPanel content5 = new JPanel();
		content5.add(b1);

		JPanel panel = new JPanel();
		panel.add(content0);
		panel.add(content1);
		panel.add(content5);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		frame.setContentPane(panel);

		frame.setVisible(true);
	}

	public void priceButton() {
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bill();

			}
		});
	}


	public void bill() {
		CompositeProduct p = new CompositeProduct(null);
		float result = 0;
		int id = Integer.parseInt(tf1.getText());
		for (Order i : App.restaurant.clientOrders)
			if (id == i.getOrderId())
				result = p.ComputePrice(i.getOrders());
		tf2.setText(String.valueOf(result));
	}
}
