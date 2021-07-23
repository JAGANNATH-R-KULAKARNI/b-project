package booking;
import java.util.Scanner;
import routes.Routes;
import ticketBooking.TicketBooking;

public class Booking 
{
public static void availableRoutes()
{
	Scanner sc= new Scanner(System.in);
	String code;
	
	while(true)
	{
		System.out.println("Enter your pickup location :");
		String pickUp=sc.nextLine();
		System.out.println("Enter your Destination :");
		String drop=sc.nextLine();
		
		Routes.busRoues(pickUp,drop);
		
		System.out.println("Copy and paste Bus id to BOOK TICKETS or enter 'searchAgain' to search different routes");
		code=sc.nextLine();
		
		if(!code.equals("searchAgain"))
			{
			TicketBooking.ticketBooking(code);
			break;
			}
	}
}
}
