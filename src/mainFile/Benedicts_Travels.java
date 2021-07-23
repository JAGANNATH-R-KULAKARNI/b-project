package mainFile;
import companyInfo.CompanyInfo;
import logInForm.LogInForm;

public class Benedicts_Travels
{
public static void main(String[] args)
{
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
}
}
