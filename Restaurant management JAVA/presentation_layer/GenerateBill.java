package presentation_layer;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
import business_layer.MenuItem;
import business_layer.Order;
import data_layer.RestaurantSerializator;
import data_layer.WriteFile;

public class GenerateBill {

	JButton b1 = new JButton("Compute Price");
	JTextField tf1;

	public GenerateBill() {
		JFrame frame = new JFrame("Welcome Page");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(900, 700);
		JPanel content0 = new JPanel();
		JLabel l0 = new JLabel("Compute order price");
		content0.add(l0);

		JPanel content1 = new JPanel();
		tf1 = new JTextField(); // nume
		JLabel l1 = new JLabel("OrderID:");
		tf1.setPreferredSize(new Dimension(200, 35));
		content1.add(l1);
		content1.add(tf1);

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

	public void billButton() {
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bill();

			}
		});
	}

	public void printOrderDetails(Order o, String price) {
		WriteFile f = new WriteFile();
		String s = o.toString();
		s += "\nTotal Price: " + price;
		f.print(s);
	}

	public void bill() {
		int order = 0;
		if (tf1.getText() != null) {
			order = Integer.parseInt(tf1.getText());
		}

		ArrayList<Order> aux = new ArrayList<Order>();

		CompositeProduct p = new CompositeProduct(null);
		for (Order o : App.restaurant.getClientOrders()) {
			if (o.getOrderId() == order) {
				String s = String.valueOf(p.ComputePrice(o.getOrders()));
				printOrderDetails(o, s);
			}
		}

	}

}
