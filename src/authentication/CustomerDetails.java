package authentication;
import java.io.*;
import java.lang.*;
import java.util.*;

public class CustomerDetails implements Authentication
{
private static String[] users={};
private static String[] passwords={};
private static String userFileName="C:\\Users\\user\\eclipse-workspace\\Reservation_System\\src\\authentication\\users.txt";
private static String passwordFileName="C:\\Users\\user\\eclipse-workspace\\Reservation_System\\src\\authentication\\passwords.txt";

public void readFile()
{
	File fileU = new File(this.userFileName);
	File fileP = new File(this.passwordFileName);
	  
	try {
		Scanner sc = new Scanner(fileU);
		  
	    sc.useDelimiter("\\Z");
	    String temp=sc.next();
	    this.users = temp.split(" ");
	}
	catch(Exception e)
	{
		System.out.println(e);
	}
	
	try {
		Scanner sc = new Scanner(fileP);
		  
	    sc.useDelimiter("\\Z");
	    String temp=sc.next();
	    this.passwords = temp.split(" ");
	}
	catch(Exception e)
	{
		System.out.println(e);
	}
}


public void addCustomerDetails(String email,String password)
{
	
	try {
		  
        BufferedWriter out = new BufferedWriter(
               new FileWriter(this.userFileName, true));
        out.write(" "+email);
        out.close();
    }
    catch (IOException e) {
        System.out.println("exception occoured" + e);
        
    }
    
	try {
		  
        BufferedWriter out = new BufferedWriter(
               new FileWriter(this.passwordFileName, true));
        out.write(" "+password);
        out.close();
    }
    catch (IOException e) {
        System.out.println("exception occoured" + e);
        
    }
}

public boolean getCustomerDetails(String email,String password)
{
	readFile();
	for(int i=0;i<this.users.length;i++)
	{
	if(email.equals(users[i]) && password.equals(passwords[i]))	
		return true;
	}
	
	return false;
}
}
