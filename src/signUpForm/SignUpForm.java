package signUpForm;
import java.util.Scanner;
import authentication.CustomerDetails;

public class SignUpForm extends CustomerDetails
{
public void signUp()
{
	Scanner sc= new Scanner(System.in);
	System.out.println("Enter your Email :");
	String email= sc.nextLine();
	System.out.println("Enter a strong password : ");
	String password= sc.nextLine();
	addCustomerDetails(email,password);
	
	System.out.println("Now Login again");
}
}
