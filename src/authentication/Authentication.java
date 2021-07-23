package authentication;

public interface Authentication 
{
	public void addCustomerDetails(String email,String password);
	public boolean getCustomerDetails(String email,String password);
};
