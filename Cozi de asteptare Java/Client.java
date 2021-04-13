package PT2020.demo.Assignment2;

public class Client {

	private int id;
	private int t_arrival;
	private int t_service;
	public Client(int id, int t_arrival, int t_service) {
		super();
		this.id = id;
		this.t_arrival = t_arrival;
		this.t_service = t_service;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getT_arrival() {
		return t_arrival;
	}
	public void setT_arrival(int t_arrival) {
		this.t_arrival = t_arrival;
	}
	public int getT_service() {
		return t_service;
	}
	public void setT_service(int t_service) {
		this.t_service = t_service;
	}
	public String toString()
	{
		String s="";
		s+="("+id+","+t_arrival+","+t_service+")";
		return s;
		
	}
}
