package pl.edu.pjwstk.lab10.service;

import static org.junit.Assert.assertEquals;

import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.IDatabaseTester;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import pl.edu.pjwstk.lab10.domain.Country;

import java.net.URL;
import java.sql.SQLException;

@RunWith(JUnit4.class)
public class CountryManagerTest extends DBTestCase {
	ICountryManager countryManager;

	public CountryManagerTest() throws Exception {
		super("CountryManager test");
		countryManager = new CountryManager(this.getConnection().getConnection());
	}

	protected DatabaseOperation getSetUpOperation() throws Exception {
		return DatabaseOperation.INSERT;
	}

	protected DatabaseOperation getTearDownOperation() throws Exception {
		return DatabaseOperation.TRUNCATE_TABLE;
	}

	/**
	 * Gets the default dataset. This dataset will be the initial state of database
	 * @return the default dataset
	 * @throws Exception when there are errors getting resources
	 */
	@Override
	protected IDataSet getDataSet() throws Exception {
		return this.getDataSet("dataset-pm.xml");
	}

	/**
	 * Returns dataset for selected resource
	 * @param datasetName filename in resources
	 * @return flat xml data set
	 * @throws Exception when there is a problem with opening dataset
	 */
	protected IDataSet getDataSet(String datasetName) throws Exception {
		URL url = getClass().getClassLoader().getResource(datasetName);
		FlatXmlDataSet ret = new FlatXmlDataSetBuilder().build(url.openStream());
		return ret;
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();
		countryManager = new CountryManager(this.getConnection().getConnection());
	}

	@Test
	public void checkAdding() throws Exception {
		Country country = new Country("Poland", "Warszawa", "00-001");

		assertEquals(1, countryManager.addCountry(country));
		// Data verification
		IDataSet dbDataSet = this.getConnection().createDataSet();
		ITable actualTable = dbDataSet.getTable("COUNTRY");
		ITable filteredTable = DefaultColumnFilter.excludedColumnsTable
				(actualTable, new String[]{"ID"});
		IDataSet expectedDataSet = getDataSet("dataset-pm-add.xml");
		ITable expectedTable = expectedDataSet.getTable("COUNTRY");

		Assertion.assertEquals(expectedTable, filteredTable);
	}
	@Test
	public void checkGetCountry() throws Exception {
		Country country = new Country("Poland", "Gdynia", "82-180");
		//countryManager.clearCountries();

		assertEquals(1,countryManager.addCountry(country));

		assertEquals("Poland", countryManager.getCountryFromDB(country).getCountry());
		assertEquals("Gdynia", countryManager.getCountryFromDB(country).getCity());

		IDataSet expectedDataSet = getDataSet("dataset-pm-get.xml");
		ITable expectedTable = expectedDataSet.getTable("COUNTRY");

		assertEquals(expectedTable.getValue(2, "country"), countryManager.getCountryFromDB(country).getCountry());
	}

	@Test
	public void checkUpdate() throws Exception{
		Country updateCountry = new Country("Poland", "Sopot","66-666");
		assertEquals(1,countryManager.updateCountry(updateCountry));

		IDataSet dbDataSet = this.getConnection().createDataSet();
		ITable actualTable = dbDataSet.getTable("COUNTRY");
		ITable filteredTable = DefaultColumnFilter.excludedColumnsTable
				(actualTable, new String[]{"ID"});
		IDataSet expectedDataSet = getDataSet("dataset-pm-update.xml");
		ITable expectedTable = expectedDataSet.getTable("COUNTRY");

		Assertion.assertEquals(expectedTable, filteredTable);

	}
	@Test
	public void checkDelete() throws Exception{
		Country country = new Country("Polska", "Gdansk", "80-180");
		assertEquals(1,countryManager.deleteCountry(country));

		IDataSet dbDataSet = this.getConnection().createDataSet();
		ITable actualTable = dbDataSet.getTable("COUNTRY");
		ITable filteredTable = DefaultColumnFilter.excludedColumnsTable
				(actualTable, new String[]{"ID"});
		IDataSet expectedDataSet = getDataSet("dataset-pm-delete.xml");
		ITable expectedTable = expectedDataSet.getTable("COUNTRY");

		Assertion.assertEquals(expectedTable, filteredTable);
	}
}
