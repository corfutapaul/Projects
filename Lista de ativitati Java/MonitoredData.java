package Assignment5.Assignment5;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MonitoredData {
    private String start_time;
    private String end_time;
    private String activity_label;
	public MonitoredData(String start_time, String end_time, String activity_label) {
		super();
		this.start_time = start_time;
		this.end_time = end_time;
		this.activity_label = activity_label;
	}

	static ArrayList<MonitoredData> data=new ArrayList<MonitoredData>();
	static ArrayList<MonitoredData> data1=new ArrayList<MonitoredData>();
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getActivity_label() {
		return activity_label;
	}
	public void setActivity_label(String activity_label) {
		this.activity_label = activity_label;
	}
	
	public static void Task1() throws IOException {
		FileOutputStream t1=new FileOutputStream("Task1.txt");
    	PrintWriter p1=new PrintWriter(t1);
    	Stream<String> rows=Files.lines(Paths.get(Link.getPath()));
    	rows.map(x->x.split("\\s{2,}")).forEach(x->data.add(new MonitoredData(x[0],x[1],x[2])));
    	data.stream().forEach(x->p1.println(x));
    	rows.close();
    	p1.close();
	}
	public static void Task2() throws FileNotFoundException {
	  FileOutputStream t1=new FileOutputStream("Task2.txt");
      PrintWriter p1=new PrintWriter(t1);
	  ArrayList<String> start=new ArrayList<String>();	
	  data.stream().map(x->x.getStart_time().split("\\s+")).forEach(x->start.add(x[0]));
	  p1.println("In fisier apar "+start.stream().distinct().count()+" zile distincte");
	  p1.close();
	}
	public static Map<String, Integer> Task3(String ceva) throws FileNotFoundException {
		 Map< String,Integer> map1 =  new HashMap< String,Integer>(); 
		  ArrayList<String> activities=new ArrayList<String>();
		  data.stream().map(x->x.getActivity_label().replaceAll("\\s+","")).distinct().forEach(x->activities.add(x));
		  if (ceva.equalsIgnoreCase("count"))
		  activities.stream().forEach(x->map1.put(x, (int) data.stream().filter(z->z.getActivity_label().replaceAll("\\s+","").equalsIgnoreCase(x)).count()));
		  else 
			  activities.stream().forEach(x->map1.put(x, (int) data.stream().filter(z->z.getActivity_label().replaceAll("\\s+","").equalsIgnoreCase(x)).filter(y->y.getStart_time().startsWith(ceva)).count()));
		  return map1;
	}
	public static Map<String,Map<String,Integer>> Task4() throws FileNotFoundException
	{
		Map<String,Map<String,Integer>> map2=new HashMap<String,Map<String,Integer>>();
		ArrayList<String> start=new ArrayList<String>();	
		data.stream().map(x->x.getStart_time().split("\\s+")).forEach(x->start.add(x[0]));
		start.stream().distinct().forEach(x->{
			try {
				map2.put(x,Task3(x));
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}
		});
		return map2;
	}
	public Duration Diferenta()
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		Duration duration = Duration.between(LocalDateTime.parse(start_time,formatter),LocalDateTime.parse(end_time,formatter));
		return duration;
	}
	public static Map<String, Duration> Task5()
	{ 
		
		Map<String,Duration> map5 = data.stream().collect(Collectors.toMap(MonitoredData::getActivity_label, MonitoredData::Diferenta, Duration::plus));
		return map5;
	}
	
	public static List<String> Task6()
	{		
		Predicate<MonitoredData> min5=(x)->(x.Diferenta().toMinutes()<5);
		List<MonitoredData> aux= data.stream().distinct().filter(min5).collect(Collectors.toList());
		List<String> activity=new ArrayList<String>();
		aux.stream().forEach(x->activity.add(x.getActivity_label()));
		List<String> list=activity.stream().distinct().collect(Collectors.toList());
		return list;
	}
    public String toString()
    {
    	String s="";
    	s+=this.getStart_time()+"  "+this.getEnd_time()+"  "+this.getActivity_label();
    	return s;
    }
}
