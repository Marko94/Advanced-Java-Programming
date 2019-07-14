package sessions;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Car;
import entities.Customer;
import entities.Rent;

@LocalBean
@Stateless
public class CustomerManager implements CustomerManagerLocal{

	@Override
	public String add(String jmbg, int category) {
		Customer customer;
		
		if(category < 1 || category > 5) {
			return "Invalid category.";
		}
		
		List<Customer> customers = em.createQuery("select c from Customer c where c.jmbg=:jmbg", Customer.class)
				.setParameter("jmbg", jmbg)
				.getResultList();
		
		for(Customer c : customers)
			if(c.getJmbg().equals(jmbg)) {
				return "Customer " + jmbg + " already exists.";
			}
	
		customer = new Customer();
		customer.setJmbg(jmbg);
		customer.setCategory(category);
		em.persist(customer);
		
		return "Customer with jmbg " + jmbg + " and category " + category + " successfully added.";
	}

	@Override
	public String remove(String jmbg) {
		List<Customer> customers = em.createQuery("select c from Customer c where c.jmbg=:jmbg", Customer.class)
				.setParameter("jmbg", jmbg)
				.getResultList();
		
		for(Customer c : customers){
			if(c.getJmbg().equals(jmbg)) {
				em.remove(c);
				return "Customer " + jmbg + " successfully removed.";
			}
		}
		
		return "No such customer "+ jmbg + ".";
	}

	@Override
	public String list() {
		List<Customer> customers = em.createNamedQuery("Customer.findAll", Customer.class).getResultList();
		StringBuilder list = new StringBuilder();
		
		for(Customer c : customers) {
			list.append(c.getJmbg() + " " + c.getCategory() + "\n");
		}
		
		return list.toString();
	}

	@Override
	public String view(String jmbg) {
		List<Customer> customers = em.createNamedQuery("Customer.findAll", Customer.class).getResultList();
		
		List<Rent> rents = em.createQuery("select r from Rent r where r.jmbg=:jmbg", Rent.class)
				.setParameter("jmbg", jmbg)
				.getResultList();
		
		StringBuilder view = new StringBuilder();
		String returned;
		
		for(Rent r : rents) {
			returned = r.getReturned() != 0 ? "returned":"not returned";
			view.append(r.getPlate() + " " + returned + "\n");
		}
		
		return view.toString();
		
	}
	
	@PersistenceContext
	EntityManager em;
}
