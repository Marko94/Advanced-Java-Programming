package sessions;

import javax.ejb.Local;

@Local
public interface CustomerManagerLocal {
	public String add(String jmbg, int category);
	public String remove(String jmbg);
	public String list ();
	public String view (String jmbg);
}
