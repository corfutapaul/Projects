package PT2020.demo.Assignment2;

import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException; 
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Manager implements Runnable {
    public int numberOfClients;
    public int numberOfQueues;
    public int tMaxSim;
    public int minArrivalTime;
    public int maxArrivalTime;
    public int minWaitingTime;
    public int maxWaitingTime;
    private float averageWaitingTime=0;
    private FileWriter fout;
	public Manager(int numberOfClients, int numberOfQueues, int tMaxSim, int minArrivalTime, int maxArrivalTime,
		int minWaitingTime, int maxWaitingTime,FileWriter fout) {
		super();
		this.numberOfClients = numberOfClients;
		this.numberOfQueues = numberOfQueues;
		this.tMaxSim = tMaxSim;
		this.minArrivalTime = minArrivalTime;
		this.maxArrivalTime = maxArrivalTime;
		this.minWaitingTime = minWaitingTime;
		this.maxWaitingTime = maxWaitingTime;
		this.fout=fout;
		scheduler = new Scheduler(numberOfQueues,numberOfClients);
		generateNRandomClients();	
	}
	public int getNumberOfClients() {
		return numberOfClients;
	}

	public void setNumberOfClients(int numberOfClients) {
		this.numberOfClients = numberOfClients;
	}

	public int getNumberOfQueues() {
		return numberOfQueues;
	}

	public void setNumberOfQueues(int numberOfQueues) {
		this.numberOfQueues = numberOfQueues;
	}

	public int gettMaxSim() {
		return tMaxSim;
	}

	public void settMaxSim(int tMaxSim) {
		this.tMaxSim = tMaxSim;
	}

	public int getMinArrivalTime() {
		return minArrivalTime;
	}

	public void setMinArrivalTime(int minArrivalTime) {
		this.minArrivalTime = minArrivalTime;
	}

	public int getMaxArrivalTime() {
		return maxArrivalTime;
	}

	public void setMaxArrivalTime(int maxArrivalTime) {
		this.maxArrivalTime = maxArrivalTime;
	}

	public int getMinWaitingTime() {
		return minWaitingTime;
	}

	public void setMinWaitingTime(int minWaitingTime) {
		this.minWaitingTime = minWaitingTime;
	}

	public int getMaxWaitingTime() {
		return maxWaitingTime;
	}

	public void setMaxWaitingTime(int maxWaitingTime) {
		this.maxWaitingTime = maxWaitingTime;
	}


	public ArrayList<Client> getGeneratedClients() {
		return generatedClients;
	}

	public void setGeneratedClients(ArrayList<Client> generatedClients) {
		this.generatedClients = generatedClients;
	}
    public int closed() {
    	int ok1=1,ok2=0;
    	if(generatedClients.size()==0)
    		ok1=0;
    	for(int i=0;i<numberOfQueues;i++)
    	{
    		if(scheduler.getQueues().get(i).getClients().size()!=0)
    			ok2=1;
    	}
    	if(ok1==0 && ok2==0)
    		return 0;
    return 1;
    }
	private ArrayList<Client> generatedClients=new ArrayList<Client>();
	private Scheduler scheduler;
    private void generateNRandomClients()//generez numberOClients clienti random
    {
    	for (int i=1;i<=numberOfClients;i++)
    	{    
    		Random random = new Random();
    		Random random1 = new Random();
    		int randomArrival = random.nextInt(maxArrivalTime - minArrivalTime) + minArrivalTime; 		
    		int randomProcessing = random1.nextInt(maxWaitingTime - minWaitingTime) + minWaitingTime;
    		generatedClients.add(new Client(i,randomArrival,randomProcessing));
    	}
    	averageWaitingTime=averageWaiting();
    	Collections.sort(generatedClients, new SortByArrivalTime());//ordonez lista de clienti aflati in asteptare
    	printClients();
    }
    public String printClients()//metoda de afisare pentru lista de clienti in asteptare
    {
    	String s="Waiting clients:";
    	for(int i=0;i<generatedClients.size();i++)
    	{   		
    		   s+=generatedClients.get(i)+";";
    	}
    	if(generatedClients.size()==0)
    		  s+="None";
    	s+="\n";
    	return s;
    }
   public String printQueues()//metoda de afisare pentru cozi
    {
	   String s="";
    	for(int i=0;i<scheduler.getQueues().size();i++)
    	{
    		if(scheduler.getQueues().get(i).getClients().isEmpty()==false)
    		{
    			Client c=scheduler.getQueues().get(i).getClients().element();
    			if(c.getT_service()!=0)
    			   s+="Queue"+ (i+1)+": "+c+"\n";
    			else
    			   s+="Queue"+ (i+1)+": "+"closed"+"\n";
    		}
    		else 
    			s+="Queue"+ (i+1)+": "+"closed"+"\n";
    	}
    	s+="\n";
    	return s;
    }
    public float averageWaiting()
    {
    	int a = 0;
    	float ma;
    	for(int i=0;i<generatedClients.size();i++)
    		a+=generatedClients.get(i).getT_service();
    	ma=(float)a/generatedClients.size();
    	return ma;
    }
    public String printInFile()
    {
    	String s="";
    	s+=printClients()+printQueues();
    	return s;
    }
  
    public void run() {
    	
		int currentTime=0;
		while(closed()!=0 && currentTime<tMaxSim)//cat timp exista clienti in asteptare sau cozi in care avem clienti si timpul nu depaseste limita maxima executa
		{	
			String s="";	
		  while(generatedClients.isEmpty()==false && currentTime==generatedClients.get(0).getT_arrival())//daca mai exista clienti in lista de asteptare si primul client(care are arrival time cel mai mic) este egal cu timpul curent
		  {  
			      int i;
			      if(scheduler.getQueues().isEmpty()==true)//daca toate cozile de asteptare sunt goale,atunci clientul o sa fie distribuit la prima coada
			          i=0;
			      else//in caz contrar se va alege indexul cozii a carei timp de procesare este minim
				      i=scheduler.getStrategy().addClient(scheduler.getQueues(), generatedClients.get(0));
				  if(scheduler.getQueues().get(i).getClients().isEmpty()==true)//daca coada este goala atunci pornesc un thread pe coada respectiva
				  {		
					  Thread t=new Thread(scheduler.getQueues().get(i));
					  scheduler.dispatchClient(generatedClients.get(0));
					  t.start();
				  }
				  else //daca nu este goala pun urmatorul client in coada cu timpul de asteptare minim
				  {
					  scheduler.dispatchClient(generatedClients.get(0));  
				  }
				  generatedClients.remove(generatedClients.get(0));//sterg primul client din lista de clienti in asteptare deoarece a fost pus in una dintre cozi
		  }
		  try
			{
				Thread.sleep(1000);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			} 
		  s=printInFile();
		  try {
			fout.write("Time "+currentTime+"\n"+s);
		} catch (IOException e) {
			e.printStackTrace();
		}
		  currentTime++;  
		 }
		try {
			fout.write("Average Time:"+Float.toString( averageWaitingTime));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			fout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 System.out.println("S-a terminat de scris in fisier!"); //aici afisez rezultatele dupa fiecare perioada de timp
		}
}
