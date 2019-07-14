package controllers;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import org.njp.sessions.UserManagerRemote;

import beans.User;

@Stateless
@LocalBean
@Path("/rent")
public class ControllerRent {
	
	public ControllerRent() {
		
	}
	
	@GET
	public String test() {
		return userManager.login("admin", "admin");
	}
	
	@POST
	@Path("/register")
	public String register(User user){
		return userManager.register(user.getUsername(), user.getPassword());
	
	}
	
	@POST
	@Path("/login")
	public String login(User user){
		return userManager.login(user.getUsername(), user.getPassword());
	}
	
	@GET
	@Path("/car/add/")
	public String addCar(@QueryParam("plate") String plate, @QueryParam("category") int category) {
		return userManager.addCar(plate, category);
	}
	
	@GET
	@Path("/car/remove/")
	public String removeCar(@QueryParam("plate") String plate) {
		return userManager.removeCar(plate);
	}
	
	@GET
	@Path("/customer/add/")
	public String addCustomer(@QueryParam("jmbg") String jmbg, @QueryParam("category") int category) {
		return userManager.addCustomer(jmbg, category);
	}
	
	@GET
	@Path("/customer/remove/")
	public String removeCustomer(@QueryParam("jmbg") String jmbg) {
		return userManager.removeCustomer(jmbg);
	}
	
	@GET
	@Path("/car/list")
	public String listCar() {
		return userManager.listCars();
	}
	
	@GET
	@Path("/customer/list")
	public String listCustomer() {
		return userManager.listCustomers();
	}
	
	@GET
	@Path("/car/view/{plate}")
	public String viewCar(@PathParam("plate") String plate) {
		return userManager.viewCar(plate);
	}
	
	@GET
	@Path("/customer/view/{jmbg}")
	public String viewCustomer(@PathParam("jmbg") String jmbg) {
		return userManager.viewCustomer(jmbg);
	}
	
	@GET
	@Path("car/rent")
	public String rentCar(@QueryParam("plate") String plate, @QueryParam("jmbg") String jmbg) {
		return userManager.rentCar(jmbg, plate);
	}
	
	@GET
	@Path("car/return")
	public String returnCar(@QueryParam("plate") String plate, @QueryParam("jmbg") String jmbg) {
		return userManager.returnCar(jmbg, plate);
	}
	
	@EJB
	private UserManagerRemote userManager;
}
