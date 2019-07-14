package sessions;

import javax.ejb.Remote;

@Remote
public interface UserManagerRemote {
	
	public String register(String username, String password);
	public String login(String username, String password);
	public String addCar(String plate, int category);
	public String addCustomer(String jmbg, int category);
	public String removeCar(String plate);
	public String removeCustomer(String jmbg);
	public String listCustomers();
	public String listCars();
	public String viewCar(String plate);
	public String viewCustomer(String jmbg);
	public String rentCar(String jmbg, String plate);
	public String returnCar(String jmbg, String plate);
}
