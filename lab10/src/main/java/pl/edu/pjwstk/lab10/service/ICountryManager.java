package pl.edu.pjwstk.lab10.service;

import pl.edu.pjwstk.lab10.domain.Country;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ICountryManager {

    Connection getConnection();
    int addCountry(Country country) throws SQLException;
    int deleteCountry(Country id) throws SQLException;
    int updateCountry(Country country) throws SQLException;
    List<Country> getAllCountries() throws SQLException;
    Country getCountryFromDB(Country id) throws SQLException;
    void clearCountries() throws SQLException;
    //int countRow() throws SQLException;
}
