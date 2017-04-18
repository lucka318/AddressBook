package hr.fer.croz.app.dao;

import java.util.List;

import hr.fer.croz.app.model.Address;

public interface AddressDAO {

	public Address createTuple(Address address);

	public Address updateTuple(Address address);

	public Address getAddress(Long id);

	public Address getAddress(String name, String no, Long city_id);

	public List<Address> getAddresses();

	public void deleteAddress(Long id);

	public boolean addressExists(Address address);

}
