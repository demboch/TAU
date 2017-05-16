package pl.edu.pjwstk.lab7.service;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Test;

import pl.edu.pjwstk.lab7.domain.Country;

public class CountryManagerTest {

	CountryManager countryManager = new CountryManager();
	
	private final static String COUNTRY_1 = "Poland";
	private final static String CITY_1 = "Gdansk";
	private final static String POSTAL_CODE_1 = "80-180";

	public CountryManagerTest() throws SQLException {}

	@Test
	public void checkConnection() {
	    assertNotNull(countryManager.getConnection());
	}
	
	@Test
	public void checkAddingCountry() throws SQLException{
		Country country = new Country(/* 1, */COUNTRY_1, CITY_1, POSTAL_CODE_1); // id opcjonalne

		//countryManager.clearCountry();
		assertEquals(1,countryManager.addCountry(country));
		assertNotNull(countryManager.getCountry((int)country.getId()));

		List<Country> countries = countryManager.getAllCountries();
		Country countryRetrieved = countries.get(0);

		assertEquals(COUNTRY_1, countryRetrieved.getCountry());
		assertEquals(CITY_1, countryRetrieved.getCity());
		assertEquals(POSTAL_CODE_1, countryRetrieved.getPostal_code());

	}

	@Test
	public void deleteCountryFromDB(){
		Country country = new Country(2,"Poland", "Sopot", "81-704");
		countryManager.addCountry(country);
		assertEquals(1, countryManager.deleteCountry((int) country.getId()));
	}

	@Test
	public void editRowInDB(){
		Country country = new Country(3,"Poland", "Gdynia", "81-045");
		countryManager.addCountry(country);
		country.setCity("Warszawa");
		country.setPostal_code("00-001");
		assertEquals(1,countryManager.updateCountry(country));
	}

	@Test
	public void selectCountryRow() throws SQLException {
		Country country = new Country(4, "Poland","Warszawa","00-001");
		countryManager.addCountry(country);
		assertEquals(country.getId(),countryManager.getCountry((int) country.getId()).getId());
	}

	@Test
	public void deleteAllData() throws SQLException {
		countryManager.clearCountries();
	}



//	@After
//	public void cleanup() throws SQLException {
//		countryManager.clearCountries();
//	}

}
