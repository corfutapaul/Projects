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
import javax.swing.JTable;

import Assignment4.Assignment4.App;
import business_layer.MenuItem;
import business_layer.Order;
import business_layer.Restaurant;
import data_layer.RestaurantSerializator;

public class WaiterGraphicalUserInterface {
	Restaurant restaurant;

	private JButton createNewOrder = new JButton("Create new order");
	private JButton computePrice = new JButton("Compute price");
	private JButton generateBill = new JButton("Generate bill");
	private JButton view = new JButton("View all");
	private JTable table = new JTable();
	public static String address;

	public WaiterGraphicalUserInterface() {

		JFrame frame = new JFrame("Waiter Window");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(400, 400);

		JPanel panel1 = new JPanel();

		JLabel label1 = new JLabel("Waiter commands:");
		label1.setPreferredSize(new Dimension(220, 100));
		panel1.add(label1);

		JPanel label2 = new JPanel();
		createNewOrder.setPreferredSize(new Dimension(200, 50));
		label2.add(createNewOrder);

		JPanel label3 = new JPanel();
		computePrice.setPreferredSize(new Dimension(200, 50));
		label3.add(computePrice);

		JPanel label4 = new JPanel();
		generateBill.setPreferredSize(new Dimension(200, 50));
		label4.add(generateBill);

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
		viewButton();
		createNewOrder();
		computePrice();
		generateBill();
	}

	public void createNewOrder() {
		createNewOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Create Order");
				afisareMeniu();
				CreateOrder co = new CreateOrder();
				co.createOrderButton();

			}
		});
	}

	public void computePrice() {
		computePrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Compute Price");

				ComputePrice c = new ComputePrice();
				c.priceButton();

			}
		});
	}

	public void generateBill() {
		generateBill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Generate bill");

				GenerateBill b = new GenerateBill();
				b.billButton();

			}
		});
	}

	public void viewButton() {
		view.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				afisare();
			}
		});
	}

	public void afisare() {

		View view = new View();

		String[][] s = view.getOrders(App.restaurant.getClientOrders());
		view.orderTable(s);

	}

	public void afisareMeniu() {
		View view = new View();

		String[][] s = view.getMenu(App.restaurant.getOrders());
		view.menuTable(s);

	}
}
