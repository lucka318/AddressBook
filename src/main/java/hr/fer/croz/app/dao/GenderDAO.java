package hr.fer.croz.app.dao;

import java.util.List;

import hr.fer.croz.app.model.Sex;

public interface GenderDAO {

	public Sex getSex(Long id);

	public List<Sex> getSexes();

}
