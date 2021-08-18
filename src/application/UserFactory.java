package application;

public class UserFactory extends BaseCustomerClass{

	@Override
	public ICustomer CreateCustomer() {
		 UserCustomer objCust = new UserCustomer();
		return objCust;
	}

}
