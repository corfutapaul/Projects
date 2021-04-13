package presentation_layer;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import business_layer.BaseProduct;
import business_layer.CompositeProduct;
import business_layer.MenuItem;
import business_layer.Order;
public class View extends JFrame{
	
	JTable menuItems;
	JFrame items;	
	JTable orders;
	JFrame od;
	
	public String[][] getMenu(ArrayList<MenuItem> menuItems)
	{
		String[][] data=new String[100][100];
		int i=0;
		for(MenuItem m: menuItems)
		{
			if(m instanceof BaseProduct)
			{
					BaseProduct product=((BaseProduct)m);
					data[i][0]=m.getClass().getSimpleName();
					data[i][1]=product.getName();
					data[i][2]=String.valueOf(product.getPrice());
					data[i][3]="-";
					i++;	
			}
			else if(m instanceof CompositeProduct)
			{
					CompositeProduct product=(CompositeProduct)m;
					data[i][0]=m.getClass().getSimpleName();
					data[i][1]=product.getName();
					data[i][2]=String.valueOf(product.ComputePrice(m.getList()));
					data[i][3]=String.valueOf(product.getList());
					i++;
			}
		}
		
		
		return data;
	}

	public String[][] getOrders(ArrayList<Order> orders)
	{
		String[][] data=new String[100][100];
		int i=0;
		for(Order order: orders)
		{
			try
			{
				Order ord= (Order)order;
				data[i][0]=String.valueOf(ord.getOrderId());
				data[i][1]=String.valueOf(ord.getDate());
				data[i][2]=String.valueOf(ord.getTable());
				data[i][3]=String.valueOf(ord.getOrders());

				i++;
			}
			catch (IllegalArgumentException e)
			{
				System.out.println(e.getMessage());
			}
		}
		return data;
		
	}
	
	public void menuTable(String[][] menu)
	{
		items= new JFrame();
		items.setTitle("Tabel Poduct");

		String[] columnNames= {"Menu item type","Name product","Price product","Menu contains"};

		menuItems= new JTable(menu,columnNames);
		menuItems.setBounds(30, 40, 300, 300);
		
		JScrollPane scrollPane= new JScrollPane(menuItems);
		items.add(scrollPane);
		items.setSize(800, 500);
		items.setVisible(true);
	}
	
	public void orderTable(String[][] order)
	{
		od=new JFrame();
		od.setTitle("Teble orders");

		String[] columnNames = {"Order ID", "Date", "Table", "Client Order"};
		
		orders = new JTable(order, columnNames);
		orders.setBounds(30, 40, 300, 300);

		JScrollPane scrollPane = new JScrollPane(orders);
		od.add(scrollPane);
		od.setSize(500, 200);
		od.setVisible(true);
		}

	}

	
	
