package sessions;

import javax.ejb.Local;

@Local
public interface CarManagerLocal {
	
	public String add (String plate, int category);
	public String remove (String plate);
	public String list ();
	public String view (String plate);
	public String rent(String jmbg, String plate);
	public String returnCar(String jmbg, String plate);
	
}
