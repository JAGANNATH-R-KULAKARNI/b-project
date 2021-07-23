package mainFile;
import companyInfo.CompanyInfo;
import logInForm.LogInForm;

import java.util.Scanner;

import booking.Booking;

public class Benedicts_Travels
{
public static void main(String[] args)
{
	Scanner sc= new Scanner(System.in);
	
	try {
	CompanyInfo.message();
	}
	catch(Exception e)
	{
		System.out.println(e);
	}
	
	LogInForm logIn=new LogInForm();
	
	while(!logIn.LogInFormDetails());
	
	System.out.println("You are successfully logged in \nBOOK YOUR TICKETS NOW");
	
	while(true)
	{
		System.out.println("Enter 'bookTickets' to book tickets and 'logOut' to log out");
		String code=sc.nextLine();
		if(code.equals("logOut"))
			break;
		
		Booking.availableRoutes();
	}
	
	System.out.println("Visit again :)");
}
}
