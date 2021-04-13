package business_layer;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class Order implements Serializable {
	private static final long serialVersionUID = 1624096636290258059L;
     private long orderId;
     private LocalDateTime date;
     private String table;
     private ArrayList<MenuItem> orders=new ArrayList<MenuItem>();
	public Order(long orderId, LocalDateTime date, String table,ArrayList<MenuItem> orders) {
		super();
		this.orderId = orderId;
		this.date = date;
		this.table = table;
		this.orders=orders;
	}
	
	public ArrayList<MenuItem> getOrders() {
		return orders;
	}

	public void setOrders(ArrayList<MenuItem> orders) {
		this.orders = orders;
	}

	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
     @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + (int) (orderId ^ (orderId >>> 32));
		result = prime * result + ((table == null) ? 0 : table.hashCode());
		return result;
	}
     
     @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (orderId != other.orderId)
			return false;
		if (table == null) {
			if (other.table != null)
				return false;
		} else if (!table.equals(other.table))
			return false;
		return true;
	}
  
 
	public String toString()
     {
    	 String s="";
    	 s+="Order ID:"+getOrderId();
    	 return s;
     }

}
