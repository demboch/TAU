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
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/beans.xml"})
@Rollback
//@Commit
@Transactional(transactionManager = "txManager")
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
public class CountryManagerTest {

    @Autowired
    CountryManager countryManager;


    @Test
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
        assertEquals("Hiszpania",countryManager.findCountryById(3).getCountry());
    }

    @Test
    @DatabaseSetup("/addCountryData.xml")
    @ExpectedDatabase(value = "/deleteCountryData.xml", table = "COUNTRY",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void deleteCountryTest() {
        assertEquals(4, countryManager.getAllCountries().size());

        Country country = new Country();
        country.setCountry("Hiszpania");
        countryManager.addCountry(country);

        assertEquals(5, countryManager.getAllCountries().size());
        assertEquals("Hiszpania", countryManager.findCountryById(3).getCountry());

        countryManager.deleteCountry(country);

        assertEquals(4, countryManager.getAllCountries().size());
    }

    @Test
    @DatabaseSetup("/addCountryData.xml")
    @ExpectedDatabase(value = "/editCountryData.xml", table = "COUNTRY",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void editCountryTest(){
        assertEquals(4,countryManager.getAllCountries().size());

        Country country = new Country();
        country.setCountry("Hiszpania");

        countryManager.addCountry(country);

        assertEquals("Hiszpania", countryManager.findCountryById(3).getCountry());

        //country.setId(5L);
        country.setCountry("USA");
        countryManager.updateCountry(country);

        assertEquals("USA",countryManager.findCountryById(3).getCountry());

        assertEquals(4,countryManager.getAllCountries().size());
    }
//
//    @Test
//    @DatabaseSetup("/fullData.xml")
//    //   @ExpectedDatabase(value = "/dbSetup.xml")
//    public void selectCountryShouldBeSuccessful(){
//        assertEquals(2,countryManager.getAllCountries().size());
//
//        assertEquals(1,countryManager.findCountryById(1));
//
//    }

    // City
    @Test
    @DatabaseSetup("/fullData.xml")
    @ExpectedDatabase(value = "/addCityData.xml", table = "CITY", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void addCityTest() {
        assertEquals(1, countryManager.getAllCities().size());

        City city = new City();
        city.setCity("Gdansk");
        city.setPostal_code("80-180");
        countryManager.addCity(city);

        assertEquals("Gdansk", countryManager.findCityByPostal_code("80-180").getPostal_code());
        assertEquals(2, countryManager.getAllCities().size());
    }

//    @Test
//    @DatabaseSetup("/fullData2.xml")
//    public void searchCityTest() {
//        assertEquals(5, countryManager.searchCity(3L, 7L).size());
//
//        List<City> dbCities = countryManager.searchCity(3L, 7L);
//        List<City> assertCities = new ArrayList<City>(Arrays.asList(
//                new City("Jokohama","33-333"),
//                new City("Nara","44-444"),
//                new City("Pekin","55-555"),
//                new City("Szanghaj","66-666"),
//                new City("Tiencin","77-777")));
//
//        for (int i = 0; i < dbCities.size(); i++){
//            assertEquals(dbCities.get(i).getCity(),  assertCities.get(i).getCity());
//        }
//    }
}