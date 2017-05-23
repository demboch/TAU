package pl.edu.pjwstk.lab7.service;

import pl.edu.pjwstk.lab7.domain.Country;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ICountryManager {

    Connection getConnection();
    int addCountry(Country country);
    int deleteCountry(int id);
    int updateCountry(Country country);
    List<Country> getAllCountries();
    Country getCountry(int id);
    void clearCountries();
    int countRow();
}
