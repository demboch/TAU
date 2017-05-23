package pl.edu.pjwstk.lab10.service;

import pl.edu.pjwstk.lab10.domain.Country;

import java.sql.Connection;
import java.util.List;

public interface ICountryManager {

    Connection getConnection();
    int addCountry(Country country);
    int deleteCountry(Country id);
    int updateCountry(Country country);
    List<Country> getAllCountries();
    Country getCountry(Country id);
    void clearCountries();
    int countRow();
}
