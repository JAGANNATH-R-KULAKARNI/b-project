package ticketBooking;
import routes.Routes;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.util.Scanner;
import java.util.Random;
import javax.swing.JFrame;
import java.util.*;

class InvalidBusId extends Exception
{
	InvalidBusId(String message)
	{
		super(message);
	}
}

class AlreadyBooked extends Exception
{
	AlreadyBooked(String message)
	{
		super(message);
	}
}

class SeatOutOfRange extends Exception
{
	SeatOutOfRange(String message)
	{
		super(message);
	}
}

class MyCanvas extends Canvas{  
    
	private String imageURL="C:\\Users\\user\\eclipse-workspace\\Reservation_System\\src\\ticketBooking\\bus image.gif";
	
    public void paint(Graphics g) {  
  
        Toolkit t=Toolkit.getDefaultToolkit();  
        Image i=t.getImage(this.imageURL);  
        g.drawImage(i, 120,100,this);  
          
    }   
  
}   

public class TicketBooking extends Routes
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
	
	public static void printTicket(String name,String source,String destination,String Km,String price,String busId,int[] seats,int N,String time)
	{
		System.out.println("----------------------------------------------------------------");
		System.out.println("Name : "+name);
		System.out.println("Bus Id : "+busId);
		System.out.println("Source : "+source);
		System.out.println("Destination : "+destination);
		System.out.println("Distance : "+Km+" Kms");
		System.out.println("Time : "+time);
		System.out.print("Booked Seats are : ");
		for(int i=0;i<N;i++)
			if(i == N-1)
			System.out.print(seats[i]);
			else
			System.out.print(seats[i]+",");	
		System.out.println("\nTotal Price = ("+price+"(bus fare) + "+0.1 * Double.parseDouble(price)+"(GST)) * "+N+"(seats) = "+(1.1 * Double.parseDouble(price)) * N+" Rupees");
		System.out.println("NOTE : Fees will be collected by the conductor(be 10 minutes before departure time)");
		System.out.println("WISHING YOU A HAPPY AND SAFE JOURNEY :)");
		System.out.println("Visit "+source+" again :)");
		System.out.println("*****");
		System.out.println("----------------------------------------------------------------");
	}
	
	public static void selectSeats(String source,String destination,String Km,String price,String busId,String time)
	{
		 Scanner sc= new Scanner(System.in);
		 System.out.println("Enter your name");
		 String name=sc.nextLine();
		 
		Integer[] bookedSeats=new Integer[15];
		
		Thread t1=new Thread(new Runnable() {
			 public void run()
			 {	
				   MyCanvas m=new MyCanvas();  
			       JFrame f=new JFrame();  
			       f.add(m);  
			       f.setSize(700,400);  
			       f.setVisible(true);  
			 }
		 },"Bus Seats Availability");
		 
	   Thread t2=new Thread(new Runnable() {
			 public void run()
			 {
				 Random rand = new Random();
				 bookedSeats[0]= rand.nextInt(50);
				 bookedSeats[1]= rand.nextInt(50);
				 bookedSeats[2]= rand.nextInt(50);
				 bookedSeats[3]= rand.nextInt(50);
				 bookedSeats[4]= rand.nextInt(50);
				 bookedSeats[5]= rand.nextInt(50);
				 bookedSeats[6]= rand.nextInt(50);
				 bookedSeats[7]= rand.nextInt(50);
				 bookedSeats[8]= rand.nextInt(50);
				 bookedSeats[9]= rand.nextInt(50);
				 bookedSeats[10]= rand.nextInt(50);
				 bookedSeats[11]= rand.nextInt(50);
				 bookedSeats[12]= rand.nextInt(50);
				 bookedSeats[13]= rand.nextInt(50);
				 bookedSeats[14]= rand.nextInt(50);
	         }
		 },"Randome number generator");
	   	
			 t1.start();
			 t2.start();
			
			 try {
			  t1.join();
			  t2.join();
			 }
			 catch(Exception e)
			 {
				 System.out.println(e);
			 }
			 
			 Set<Integer> hash_Set = new HashSet<Integer>();
			 hash_Set.addAll(Arrays.asList(bookedSeats));   
			 
			 System.out.print("Enter how many seats you want to book : ");
			 int N=sc.nextInt();
			 int[] seats=new int[N];
			 int i=0;
			 
			 System.out.print("Booked seats are : ");
			 Iterator<Integer> itr = hash_Set.iterator();
		        while (itr.hasNext())
		            System.out.print(itr.next()+" ");
		        System.out.print("\n");
		        
			 while(i < N)
			 {
				 System.out.println("Enter seat for person no : "+(i+1));
			     int seatNo=sc.nextInt();
			     
			     try {
			    	  if(hash_Set.contains(seatNo))
					     throw new  AlreadyBooked("Seat No : "+(seatNo)+" is Already Booked ");
			    	  else if(seatNo <= 0 || seatNo >= 50)
			    	    throw new SeatOutOfRange("Seat "+seatNo+" is out of range");
			    	  
			    	  seats[i]=seatNo;
			    	  i++;
			     }
			     catch(Exception e)
			     {
			    	 System.out.println(e);
			     }
	
			 }
			 
			 printTicket(name,source,destination,Km,price,busId,seats,N,time);
	}
	
public static void ticketBooking(String busId)
{
	readFile();
	boolean result=false;
	
	for(int i=0;i<busDetails.length;i++)
	{
		String[] info=busInfo(busDetails[i]);
		
		for(int j=1;j<info.length;j++)
		{
			String[] bus=busInfoComma(info[j]);
			
			if(bus[3].equals(busId))
			{
				selectSeats(info[0],bus[0],bus[1],bus[2],bus[3],bus[4]);
				result=true;
				break;
			}
			
	   }
		
		System.out.println("\n");
	}
	
	try {
	  if(!result)
		  throw new InvalidBusId("Bus Id : "+busId+" is Invalid");
	}
	catch(Exception e)
	{
		System.out.println(e);
	}
}

}
