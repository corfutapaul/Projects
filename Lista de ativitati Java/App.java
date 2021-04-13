package Assignment5.Assignment5;

import java.util.stream.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.*;
import java.util.function.Supplier;

public class App 
{
    public static <C> void main( String[] args ) throws IOException
    {
    	FileOutputStream t1=new FileOutputStream("Task3.txt");
        PrintWriter p1=new PrintWriter(t1);
        FileOutputStream t2=new FileOutputStream("Task4.txt");
        PrintWriter p2=new PrintWriter(t2);
    	FileOutputStream t3=new FileOutputStream("Task5.txt");
        PrintWriter p3=new PrintWriter(t3);
        FileOutputStream t4=new FileOutputStream("Task6.txt");
        PrintWriter p4=new PrintWriter(t4);
        Link link=new Link(args[0]);
        
        Map<String,Integer> map=new HashMap<String,Integer>();
        Map<String,Map<String,Integer>> map2=new HashMap<String,Map<String,Integer>>();
    	MonitoredData.Task1();
    	MonitoredData.Task2();
    	map=MonitoredData.Task3("count");
    	map.forEach((key, value) -> p1.println(key + ":" + value));
    	p1.close();
    	map2=MonitoredData.Task4();
    	map2.forEach((key, value) -> p2.println(key + ":" + value));
    	p2.close();
        Map<String,Duration> duration=MonitoredData.Task5();	
		duration.forEach((K,V)-> p3.println(K+ ": " + V.toHours()+" hours "+ V.toMinutes()%60+" minutes "+V.toSeconds()%60+" seconds "));
        p3.close();   
        List<String> min5= MonitoredData.Task6();
		min5.stream().forEach(x->p4.println(x));
		p4.close();
    }
}
