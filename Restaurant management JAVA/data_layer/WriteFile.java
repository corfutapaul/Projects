package data_layer;

import java.io.FileWriter;

public class WriteFile {
	public void print(String s)
	{
		try {
			FileWriter fw=new FileWriter("C:\\PT2020\\pt_302210_corfuta_paul_assignment4\\Assignment4\\bill.txt");
			fw.write(s);
			fw.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
