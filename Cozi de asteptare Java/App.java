package PT2020.demo.Assignment2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException; 

public class App 
{
    public static void main( String[] args ) throws IOException
    {
        File file = new File(args[0]); 
        FileWriter file2 = new FileWriter(args[1]);
        Scanner sc = new Scanner(file);
        Manager m=new Manager(sc.nextInt(),sc.nextInt(),sc.nextInt(),sc.nextInt(),sc.nextInt(),sc.nextInt(),sc.nextInt(),file2);
        Thread t= new Thread(m);
        t.start();       	    
    }
    
}
