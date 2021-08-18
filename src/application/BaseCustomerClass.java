package application;
public abstract class BaseCustomerClass {
	public ICustomer getCustomer() {
		ICustomer myCust = this.CreateCustomer();
		return myCust;
	}
	
	public abstract ICustomer CreateCustomer();
}
