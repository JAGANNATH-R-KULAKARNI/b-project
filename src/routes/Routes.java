package routes;
import java.io.File;
import java.util.Scanner;

class RouteNotPresent extends Exception
{
	RouteNotPresent(String message)
	{
		super(message);
	}
}

public class Routes 
{
	private static String busDetailsFile="C:\\Users\\user\\eclipse-workspace\\Reservation_System\\src\\routes\\BusDetails.txt";
	private static String[] busDetails= {};
	
	public static void readFile()
	{
		File fileB = new File(busDetailsFile);
		  
		try {
			Scanner sc = new Scanner(fileB);
			  
		    sc.useDelimiter("\\Z");
		    String temp=sc.next();
		    busDetails = temp.split(" ");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
	
	public static String[] busInfo(String str)
	{
		String[] arr=str.split("\\|");
		
		return arr;
	}
	
	public static String[] busInfoComma(String str)
	{
		String[] arr=str.split(",");
		return arr;
	}
	
	
public static void busRoues(String pickUp,String drop)
{
	readFile();
	boolean result1=false,result2=false;
	
	for(int i=0;i<busDetails.length;i++)
	{
		String[] info=busInfo(busDetails[i]);
		
		if(info[0].equalsIgnoreCase(pickUp))
			result1=true;
		else
			continue;
		
		for(int j=1;j<info.length;j++)
		{
			String[] bus=busInfoComma(info[j]);
			
			if(bus[0].equalsIgnoreCase(drop))
				result2=true;
			else
				continue;
			
			System.out.println("Source : "+info[0]+" ---> Destination : "+bus[0]+" ("+bus[1]+" Km, Price : "+bus[2]+" Rupees, BusID : "+bus[3]+", Time : "+bus[4]+")");
		}
		
		System.out.println("\n");
	}
	
	try {
		if(!result1 || !result2)
			throw new RouteNotPresent("Given Route is not present. Search Again :(");
	}
	catch(Exception e)
	{
		System.out.println(e);
	}
}

}











