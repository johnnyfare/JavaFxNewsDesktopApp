package application;

public class JournalistFactory extends BaseCustomerClass{

	@Override
	public ICustomer CreateCustomer() {
		 JournalistCutomer objCust = new JournalistCutomer();
		 return objCust;  
	}

}
