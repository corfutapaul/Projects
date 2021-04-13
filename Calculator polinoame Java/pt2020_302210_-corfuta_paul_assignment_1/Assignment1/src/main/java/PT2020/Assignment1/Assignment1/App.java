package PT2020.Assignment1.Assignment1;

import controller.Controller;
import model.Polynomial;
import view.View;

public class App 
{
    public static void main( String[] args )
    {  	
    	Polynomial p1=new Polynomial(null);
    	Polynomial p2=new Polynomial(null);
    	View v = new View("View");
    	Controller c = new Controller(p1,p2, v);	
    }
}
