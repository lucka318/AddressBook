package hr.fer.croz.app.dao;

import java.util.List;

import hr.fer.croz.app.model.Country;

public interface CountryDAO {

	public Country createTuple(Country country);

	public Country updateTuple(Country country);

	public Country getCountry(long id);

	public Country getCountry(String name);

	public List<Country> getCountries();

	public void deleteCountry(Long id);

	public boolean countryExists(Country country);

}
