package logInForm;
import java.util.Scanner;
import authentication.CustomerDetails;
import signUpForm.SignUpForm;

class InvalidCredentials extends Exception
{
	InvalidCredentials(String message)
	{
		super(message);
	}
}

public class LogInForm extends CustomerDetails
{
public boolean LogInFormDetails()
{
	Scanner sc= new Scanner(System.in);
	System.out.println("Enter your registered Email :");
	System.out.println("(Don't have an account? Enter 'SignUp')");
	String email= sc.nextLine();
	
	if(email.equals("SignUp"))
	{
	SignUpForm signup=new SignUpForm();
	signup.signUp();
	return false;	
	}
	
	System.out.println("Enter your password");
	String password= sc.nextLine();
	
	try {
		if(!getCustomerDetails(email,password))
		throw new InvalidCredentials("Invalid Credentials Try again :(");

	}
	catch(Exception e)
	{
		System.out.println(e);
		return false;
	}
	
	return true;
}
}
