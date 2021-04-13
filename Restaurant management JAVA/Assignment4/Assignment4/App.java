package Assignment4.Assignment4;


import business_layer.Restaurant;
import presentation_layer.AdministratorGraphicalUserInterface;
import presentation_layer.WaiterGraphicalUserInterface;
import data_layer.RestaurantSerializator;

/**
 * Functia main
 *
 */
public class App {
	public static Restaurant restaurant = new Restaurant(null, null, null, null);

	public static void main(String[] args) {
		RestaurantSerializator rs = new RestaurantSerializator();
		restaurant = rs.deserializationRestaurant();
		AdministratorGraphicalUserInterface a = new AdministratorGraphicalUserInterface();
		WaiterGraphicalUserInterface w = new WaiterGraphicalUserInterface();

	}

}
