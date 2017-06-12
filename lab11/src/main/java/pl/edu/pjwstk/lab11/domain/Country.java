package pl.edu.pjwstk.lab11.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
		@NamedQuery(name = "Country.getAll", query = "Select co from Country co"),
		//@NamedQuery(name = "Country.update", query = "Update Country co set co.country = :country where co.id = :id"),
		@NamedQuery(name = "Country.byId", query = "Select co from Country co where co.id = :id")
})
public class Country {

	private Long id;
	private String country;
	private List<City> cities; //= new ArrayList<City>();

//	public Country() {}
//
//	public Country(String country) {
//		this.country = country;
//	}
//
//	public Country(Long id, String country) {
//		this.id = id;
//		this.country = country;
//	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}
}