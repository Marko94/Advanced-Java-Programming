package sessions;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceUnit;

import entities.User;

@Stateful
@LocalBean
public class UserManager implements UserManagerRemote{
	
	@PersistenceContext (type=PersistenceContextType.EXTENDED)
	EntityManager em;
	@EJB
	CarManagerLocal carManager;
	@EJB
	CustomerManagerLocal customerManager;
	
	private boolean logged;
	
	public UserManager() {
		logged = false;
		
	}

	@Override
	public String register(String username, String password) {
		if(this.logged) {
			return "User already logged in.";
		}
		List<User> users = em.createNamedQuery("User.findAll", User.class).getResultList();
		User user;
		
		for(User u : users) {
			if(u.getUsername().equals(username)) {
				return "Username already in use.";
			}
		}
		
		user = new User();
		user.setUsername(username);
		user.setPassword(password);
		em.persist(user);
		
		this.logged = true;
		
		return "User " + username + " registered succcessfully.";
	}

	@Override
	public String login(String username, String password) {
		
		if(this.logged) {
			return "User already logged in.";
		}
		List<User> users = em.createNamedQuery("User.findAll", User.class).getResultList();
		
		for(User u : users) {
			if(u.getUsername().equals(username)) {
				if(u.getPassword().equals(password)) {
					this.logged = true;
					return "User " + username + " successfully logged in.";
				} else {
					return "Wrong password for user " + username + ".";
				}
			}
		}
		
		return "No such user " + username;
		
	}

	@Override
	public String addCar(String plate, int category) {
		if(logged) {
			return carManager.add(plate, category);
		}
		
		return "403 Forbidden!";
	}

	@Override
	public String addCustomer(String jmbg, int category) {
		if(logged) {
			return customerManager.add(jmbg, category);
		}
		
		return "403 Forbidden!";
	}

	@Override
	public String removeCar(String plate) {
		if(logged) {
			return carManager.remove(plate);
		}
		
		return "403 Forbidden!";
	}

	@Override
	public String removeCustomer(String jmbg) {
		if(logged) {
			return customerManager.remove(jmbg);
		}
		
		return "403 Forbidden!";
	}

	@Override
	public String listCustomers() {
		if(logged) {
			return customerManager.list();
		}
		
		return "403 Forbidden!";
	}

	@Override
	public String listCars() {
		if(logged) {
			return carManager.list();
		}
		
		return "403 Forbidden!";
	}

	@Override
	public String viewCar(String plate) {
		if(logged) {
			return carManager.view(plate);
		}
		
		return "403 Forbidden!";
	}

	@Override
	public String viewCustomer(String jmbg) {
		if(logged) {
			return customerManager.view(jmbg);
		}
		
		return "403 Forbidden!";
	}

	@Override
	public String rentCar(String jmbg, String plate) {
		if(logged) {
			return carManager.rent(jmbg, plate);
		}
		
		return "403 Forbidden!";
	}

	@Override
	public String returnCar(String jmbg, String plate) {
		if(logged) {
			return carManager.returnCar(jmbg, plate);
		}
		
		return "403 Forbidden!";
	}
}
