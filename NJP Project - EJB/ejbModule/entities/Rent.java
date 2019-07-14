package entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the rents database table.
 * 
 */
@Entity
@Table(name="rents")
@NamedQuery(name="Rent.findAll", query="SELECT r FROM Rent r")
public class Rent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String jmbg;

	private String plate;

	private byte returned;

	public Rent() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getJmbg() {
		return this.jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public String getPlate() {
		return this.plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public byte getReturned() {
		return this.returned;
	}

	public void setReturned(byte returned) {
		this.returned = returned;
	}

}