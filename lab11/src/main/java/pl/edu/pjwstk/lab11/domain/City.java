package pl.edu.pjwstk.lab11.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@NamedQueries({
		@NamedQuery(name = "City.showAll", query = "Select ci from City ci"),
		//@NamedQuery(name = "City.update", query = "Update City ci set ci.city = :city, ci.postal_code = :postal_code where ci.id = :id"),
		//@NamedQuery(name = "City.select", query = "Select ci from City ci where ci.id between :min and :max"),
		@NamedQuery(name = "City.byPostal_code", query = "Select c from City c where c.postal_code = :postal_code")
})
public class City {

	private Long id;
	private String city;
	private String postal_code;

	public City() {}

	public City(String city, String postal_code) {
		super();
		this.city = city;
		this.postal_code = postal_code;
	}

	public City(Long id, String city, String postal_code) {
		super();
		this.id = id;
		this.city = city;
		this.postal_code = postal_code;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	@Column(unique = true, nullable = false)
	public String getPostal_code() {
		return postal_code;
	}
	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}
}