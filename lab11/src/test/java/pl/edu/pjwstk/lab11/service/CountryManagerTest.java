package pl.edu.pjwstk.lab11.service;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pjwstk.lab11.domain.City;
import pl.edu.pjwstk.lab11.domain.Country;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/beans.xml"})
@Rollback
@Transactional(transactionManager = "txManager")
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
public class CountryManagerTest {

    @Autowired
    CountryManager countryManager;

    // COUNTRY
    @Test // INSERT
    @DatabaseSetup("/fullData.xml")
    //@Ignore
    @ExpectedDatabase(value = "/addCountryData.xml", table = "COUNTRY",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void addCountryTest() {
        assertEquals(3, countryManager.getAllCountries().size());

        Country country = new Country();
        country.setCountry("Hiszpania");
        countryManager.addCountry(country);

        assertEquals(4, countryManager.getAllCountries().size());
        assertEquals("Hiszpania",countryManager.findCountryById(1).getCountry());
    }

    @Test //SELECT
    @DatabaseSetup("/fullData.xml")
    public void selectCountryTest(){
        assertEquals(3, countryManager.getAllCountries().size());

        assertEquals("Polska", countryManager.findCountryById(3).getCountry());
    }

    @Test // UPDATE
    @DatabaseSetup("/addCountryData.xml")
    @ExpectedDatabase(value = "/editCountryData.xml", table = "COUNTRY",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void editCountryTest(){
        assertEquals(4,countryManager.getAllCountries().size());
        assertEquals("Hiszpania", countryManager.findCountryById(1).getCountry());

        Country country = countryManager.findCountryById(1);
        //country.setId(2L);
        country.setCountry("USA");
        countryManager.updateCountry(country);

        assertEquals(4,countryManager.getAllCountries().size());
        assertEquals("USA",countryManager.findCountryById(1).getCountry());
    }

    @Test // DELETE
    @DatabaseSetup("/addCountryData.xml")
    @ExpectedDatabase(value = "/deleteCountryData.xml", table = "COUNTRY",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void deleteCountryTest() {
        assertEquals(4, countryManager.getAllCountries().size());
        assertEquals("Hiszpania", countryManager.findCountryById(1).getCountry());

        Country country = countryManager.findCountryById(1);
        countryManager.deleteCountry(country);

        assertEquals(3, countryManager.getAllCountries().size());
        assertEquals("Polska", countryManager.findCountryById(3).getCountry());
    }

    // CITY
    @Test
    @DatabaseSetup("/fullData.xml")
    @ExpectedDatabase(value = "/addCityData.xml", table = "CITY", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void addCityTest() {
        assertEquals(1, countryManager.getAllCities().size());

        City city = new City();
        city.setCity("Gdansk");
        city.setPostal_code("80-180");
        countryManager.addCity(city);

        assertEquals("80-180", countryManager.findCityByPostal_code("80-180").getPostal_code());
        assertEquals(2, countryManager.getAllCities().size());
    }

    @Test
    @DatabaseSetup("/fullData.xml")
    public void findCityTest(){
        assertEquals(1, countryManager.getAllCities().size());
        assertEquals(3, countryManager.getAllCountries().size());

        Country country1 = countryManager.findCountryById(3);
        Country country2 = countryManager.findCountryById(4);
        City city = countryManager.findCityById(1);

        Long co1 = country1.getId();
        Long co2 = country2.getId();
        Long ci  = city.getId();

        countryManager.findCityForCountry(co1,co2,ci);
        assertEquals(1, country2.getCities().size());
        assertEquals(0, country1.getCities().size());
    }
}