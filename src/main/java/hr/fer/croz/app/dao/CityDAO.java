package hr.fer.croz.app.dao;

import java.util.List;

import hr.fer.croz.app.model.City;

public interface CityDAO {

	public City createTuple(City city);

	public City updateTuple(City city);

	public City getCity(long id);

	public City getCity(String name, String zipcode, Long country_id);

	public List<City> getCities();

	public void deleteCity(Long id);

	public boolean cityExists(City city);

}
