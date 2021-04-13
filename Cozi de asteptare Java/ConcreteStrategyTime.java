package PT2020.demo.Assignment2;

import java.util.List;

public class ConcreteStrategyTime implements Strategy{
	public int addClient(List<Queue> queues, Client c) {
		int min=999;
		int number=-1;
		for(int i=0;i<queues.size();i++)
		{
			if(queues.get(i).getWaitingPeriod().intValue()<min)
			{
				min=queues.get(i).getWaitingPeriod().intValue();
				number=i;
			}
		}
		return number;//returnez numarul cozii cu cel mai mic timp de procesare(WaitingPeriod)
	}
	
}
