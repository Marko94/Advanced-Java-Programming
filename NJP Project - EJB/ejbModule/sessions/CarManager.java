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
public class CarManager implements CarManagerLocal{

	@Override
	public String add(String plate, int category) {
		System.out.println(category);
		
		if(category < 1 || category > 5) {
			return "Invalid category.";
		}
		
		List<Car> cars = em.createQuery("select c from Car c where c.plate=:plate", Car.class)
				.setParameter("plate", plate)
				.getResultList();
		
		for(Car c : cars)
			if(c.getPlate().equals(plate)) {
				return "Plate " + plate + " already exists.";
			}
		
		Car car;
		car = new Car();
		car.setPlate(plate);
		car.setCategory(category);
		em.persist(car);
		
		return "Car with plate " + plate + " and category " + category + " successfully added.";
	}

	@Override
	public String remove(String plate) {
		List<Car> cars = em.createQuery("select c from Car c where c.plate=:plate", Car.class)
				.setParameter("plate", plate)
				.getResultList();
		
		for(Car c : cars){
			if(c.getPlate().equals(plate)) {
				em.remove(c);
				return "Car " + plate + " successfully removed.";
			}
		}
		
		return "No such car "+ plate + ".";
	}

	@Override
	public String list() {
		List<Car> cars = em.createNamedQuery("Car.findAll",Car.class).getResultList();
		StringBuilder list = new StringBuilder();
		
		for(Car car : cars) {
			list.append(car.getPlate() + " " + car.getCategory() + "\n");
		}
		
		return list.toString();
	}

	@Override
	public String view(String plate) {
		StringBuilder line = new StringBuilder();
		String returned;
		
		List<Rent> rents = em.createQuery("select r from Rent r where r.plate=:plate", Rent.class)
				.setParameter("plate", plate)
				.getResultList();
		
		for(Rent r : rents) {
			returned = r.getReturned() != 0 ? "returned" : "not returned";
			line.append(r.getJmbg() + " " + returned + "\n");
		}
		
		return line.toString();
	}
	
	@PersistenceContext
	EntityManager em;

	@Override
	public String rent(String jmbg, String plate) {
		List<Car> cars;
		Car car = null;
		Customer customer = null;
		boolean p = false;
		cars = em.createQuery("select c from Car c where c.plate=:plate", Car.class)
				.setParameter("plate", plate)
				.getResultList();
		
		for(Car c : cars){
			if(c.getPlate().equals(plate)) {
				car = c;
				p = true;
				break;
				
			}
		}
		
		if(p == false) {
			return "No cush car " + plate + ".";
		}
		
		p = false;
		
		List<Customer> customers;
		customers = em.createQuery("select c from Customer c where c.jmbg=:jmbg", Customer.class)
				.setParameter("jmbg", jmbg)
				.getResultList();
		
		for(Customer c : customers){
			if(c.getJmbg().equals(jmbg)) {
				customer = c;
				p = true;
				break;
				
			}
		}
		
		if(p == false) {
			return "No cush customer " + jmbg + ".";
		}
		
		if(car.getCategory() > customer.getCategory()) {
			return "Car " + car.getPlate() + " has greater category than customer " + customer.getJmbg();
		}
		
		List<Rent> rents = em.createNamedQuery("Rent.findAll").getResultList();
		
		for(Rent r : rents) {
			if(r.getPlate().equals(plate)) {
				if(r.getReturned() == 0) {
					return "Car " + plate + "already rented";
				}
			}
		}
		
		Rent rent = new Rent();
		rent.setJmbg(jmbg);
		rent.setPlate(plate);
		em.persist(rent);
		
		return "Customer " + jmbg + " has successfully rented car " + plate;
	}

	@Override
	public String returnCar(String jmbg, String plate) {
		List<Car> cars;
		boolean p = false;
		cars = em.createQuery("select c from Car c where c.plate=:plate", Car.class)
				.setParameter("plate", plate)
				.getResultList();
		
		for(Car c : cars){
			if(c.getPlate().equals(plate)) {
				p = true;
				break;
				
			}
		}
		
		if(p == false) {
			return "No cush car " + plate + ".";
		}
		
		p = false;
		
		List<Customer> customers;
		customers = em.createQuery("select c from Customer c where c.jmbg=:jmbg", Customer.class)
				.setParameter("jmbg", jmbg)
				.getResultList();
		
		for(Customer c : customers){
			if(c.getJmbg().equals(jmbg)) {
				p = true;
				break;
				
			}
		}
		
		if(p == false) {
			return "No cush customer " + jmbg + ".";
		}
		
		byte returned = 0;
		
		List<Rent> rents;
		Rent rent = null;
		rents = em.createQuery("select r from Rent r where r.jmbg=:jmbg and r.plate=:plate and r.returned=:returned", Rent.class)
				.setParameter("jmbg", jmbg).setParameter("plate", plate).setParameter("returned", returned)
				.getResultList();
		
		for(Rent r : rents){
			rent = r;
		}
		
		if(rent != null) {
			rent.setReturned((byte) 1);
			em.persist(rent);
			return "Rent with customer " + jmbg + " and car "+ plate +" is returned";
		}
		
		return "Rent with customer " + jmbg + " and car "+ plate +" doesn't exist or has been returned";
	}
	
}
