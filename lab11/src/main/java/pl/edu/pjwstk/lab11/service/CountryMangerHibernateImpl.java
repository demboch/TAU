package pl.edu.pjwstk.lab11.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pjwstk.lab11.domain.City;
import pl.edu.pjwstk.lab11.domain.Country;

@Component
@Transactional
public class CountryMangerHibernateImpl implements CountryManager{

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addCountry(Country country) {
		country.setId(null);
		sessionFactory.getCurrentSession().persist(country);
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void deleteCountry(Country country) {
		country = (Country) sessionFactory.getCurrentSession().get(Country.class, country.getId());

		for (City city : country.getCities()) {
			sessionFactory.getCurrentSession().update(city);
		}
		sessionFactory.getCurrentSession().delete(country);
	}

	@Override
	public void updateCountry(Country country) {
//		sessionFactory.getCurrentSession().getNamedQuery("Country.update")
//				.setParameter("country", country.getCountry())
//				.setParameter("id", country.getId()).executeUpdate();
		sessionFactory.getCurrentSession().update(country);
	}

	@Override
	public Country findCountryById(int id) {
		return (Country) sessionFactory.getCurrentSession().getNamedQuery("Country.byId").setInteger("id", id).uniqueResult();
		//sessionFactory.getCurrentSession().get(City.class, id);

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Country> getAllCountries() {
		return sessionFactory.getCurrentSession().getNamedQuery("Country.getAll").list();
	}

	@Override
	public Long addCity(City city) {
		city.setId(null);
		return (Long) sessionFactory.getCurrentSession().save(city);
	}

	@Override
	public void deleteCity(City city) {
		sessionFactory.getCurrentSession().delete(city);
	}

	@Override
	public void updateCity(City city) {
		sessionFactory.getCurrentSession().update(city);
	}

	@Override
	public City findCityByPostal_code(String postal_code) {
		return (City) sessionFactory.getCurrentSession().getNamedQuery("City.byPostal_code").setString("postal_code", postal_code).uniqueResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<City> getAllCities() {
		return sessionFactory.getCurrentSession().getNamedQuery("City.showAll").list();
	}
//	@Override
//	@SuppressWarnings("unchecked")
//	public List<City> getAllCities(Country country) {
//		country = (Country) sessionFactory.getCurrentSession().get(Country.class, country.getId());
//
//		List<City> cities = new ArrayList<City>(country.getCities());
//		return cities;
//	}

//	@Override
//	public List<City> searchCity(Long min, Long max) {
//		return sessionFactory.getCurrentSession().getNamedQuery("City.select")
//				.setParameter("min", min)
//				.setParameter("max", max)
//				.list();
//	}
}
