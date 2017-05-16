package pl.edu.pjwstk.lab7.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pl.edu.pjwstk.lab7.domain.Country;
import pl.edu.pjwstk.lab7.service.CountryManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class CountryApi {
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    CountryManager countryManager;

    @RequestMapping("/")
    public String index() {
        return "I check if it works";
    }

    @RequestMapping(
            value = "/country",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public List<Country> getAllCountries(@RequestParam(value = "name", defaultValue = "country") String name) throws SQLException {
        List<Country> countriesList = new ArrayList();
        countriesList = countryManager.getAllCountries();
        return countriesList;
    }

//    @ResponseBody
//    public List<Country> showAllAddresses(){
//        return countryManager.getAllCountries();
//    }

    @RequestMapping(
            value = "/country/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public ResponseEntity<Country> getCountry(@PathVariable("id") int id) throws SQLException {
        Country country = countryManager.getCountry(id);
        if(country == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(country, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/country/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<Country> deleteCountry(@PathVariable("id") int id) throws SQLException {
        Country country = countryManager.getCountry(id);
        if(country == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        countryManager.deleteCountry(id);
        return new ResponseEntity<>(country, HttpStatus.NO_CONTENT);
    }

    @RequestMapping(
            value = "country/{id}",
            method = RequestMethod.PUT
    )
    @ResponseBody
    public ResponseEntity<Country> editCountry(@PathVariable("id") int id, @RequestBody Country country) throws SQLException {
        Country editedCountry = countryManager.getCountry(id);
        if(editedCountry == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        editedCountry.setId(id);
        editedCountry.setCountry(country.getCountry());
        editedCountry.setCity(country.getCity());
        editedCountry.setPostal_code(country.getPostal_code());
        countryManager.updateCountry(editedCountry);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(
            value = "country/",
            method = RequestMethod.POST
    )
    @ResponseBody
    public ResponseEntity<Void> addCountry(@RequestBody Country country, UriComponentsBuilder uriComponentsBuilder) throws SQLException {
        countryManager.addCountry(country);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uriComponentsBuilder.path("/user/{id}").buildAndExpand(country.getId()).toUri());
        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }

}
