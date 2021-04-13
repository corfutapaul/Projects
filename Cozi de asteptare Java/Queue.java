package PT2020.demo.Assignment2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Queue implements Runnable {
    private BlockingQueue<Client> clients; 
	private AtomicInteger waitingPeriod;
	private int number;
	private int numberOfClients;
    public Queue(int number,int numberOfClients) {
    	this.number=number;
    	this.numberOfClients=numberOfClients;
    	this.waitingPeriod=new AtomicInteger(0);
    	this.clients = new ArrayBlockingQueue<Client> (numberOfClients);
    }
 
    public BlockingQueue<Client> getClients() {
		return clients;
	}

	public void setClients(BlockingQueue<Client> clients) {
		this.clients = clients;
	}

	public AtomicInteger getWaitingPeriod() {
		return waitingPeriod;
	}

	public void setWaitingPeriod(AtomicInteger waitingPeriod) {
		this.waitingPeriod = waitingPeriod;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public void addClient(Client newClient)
    {
		if(clients.remainingCapacity()==0)//daca coada este plina iese din ea
			return;
    	clients.add(newClient);
    	waitingPeriod.getAndAdd(newClient.getT_service());//adauga service time la waiting period, astfel incat sa tinem cont de timpul de procesare pentru fiecare coada
    }
    public void run() {
    	 int ok,ok1=0;
    	 Client client = null;
  		 while(true)
  		 {   ok=0;
  			 if(clients.peek()!=null)//daca in capul cozii avem client atunci il salvam intr-o variabila de tip Client
  			 {
  				client= clients.peek();
  			    ok=1;
  			 }
  			 while(ok==1)//cat timp clientul "client" este inca in coada executa
  			 {
  				try
				{
					Thread.sleep(1000);//thread-ul este pus in asteptare pentru 1 secunda astfel incat sa se sincronizeze cu cel din clasa manager(alta metoda nu a mers)
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
  				if(ok1==1)//la prima iteratie nu vrem ca service time sa scada
  				{
  				this.waitingPeriod.decrementAndGet();//decrementez timpul de asteptare al cozii
  				client.setT_service(client.getT_service()-1);//decrementez service time-ul clientului
  				}
  				else ok1=1;
  			if(client.getT_service() == 0)//Daca clientul si-a terminat treaba
			{
				this.clients.poll();//este scos din coada
				ok=0;
				if(this.waitingPeriod.intValue() == 0)//verific daca mai avem clienti in coada
				{
					return;//daca coada este goala atunci se opreste thread-ul
				}
			  }
  			 }
  		 }
  		}
    

    
    	

}
