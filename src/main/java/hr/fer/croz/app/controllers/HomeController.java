package hr.fer.croz.app.controllers;

import hr.fer.croz.app.dao.DAO;
import hr.fer.croz.app.model.Address;
import hr.fer.croz.app.model.City;
import hr.fer.croz.app.model.Contact;
import hr.fer.croz.app.model.Country;
import hr.fer.croz.app.model.Sex;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	private Contact contact;
	private Address address;
	private City city;
	private Country country;
	private Sex sex;

	@Autowired
	private DAO contactDAO;

	@RequestMapping(value = "/")
	public ModelAndView listContact(ModelAndView model) throws IOException {
		List<Contact> contacts = contactDAO.getContacts();
		List<City> cities = contactDAO.getCities();
		List<Country> countries = contactDAO.getCountries();
		model.addObject("contacts", contacts);
		model.addObject("cities", cities);
		model.addObject("countries", countries);
		model.setViewName("home");
		resetVariables();
		return model;
	}

	@RequestMapping(value = "/newContact", method = RequestMethod.GET)
	public ModelAndView newContact(ModelAndView model) {
		Contact newContact = new Contact();
		model.addObject("contact", newContact);
		model.setViewName("ContactForm");
		return model;
	}

	@RequestMapping(value = "/saveContact", method = RequestMethod.POST)
	public ModelAndView saveContact(@Valid @ModelAttribute Contact contact,
			BindingResult result) {
		ModelAndView model;
		if (result.hasErrors()) {
			model = new ModelAndView("ContactForm", result.getModel());
		} else {
			this.contact = contact;
			Sex sex = Sex.getInstance();
			model = new ModelAndView("GenderForm", "sex", sex);
		}
		return model;
	}

	@RequestMapping(value = "/saveSex", method = RequestMethod.POST)
	public ModelAndView saveSex(@Valid @ModelAttribute Sex sex,
			BindingResult result) {
		ModelAndView model;
		if (result.hasErrors()) {
			model = new ModelAndView("GenderForm", result.getModel());
		} else {
			this.sex = sex;
			Address address = new Address();
			model = new ModelAndView("AddressForm", "address", address);
		}
		return model;
	}

	@RequestMapping(value = "/saveAddress", method = RequestMethod.POST)
	public ModelAndView saveAddress(@Valid @ModelAttribute Address address,
			BindingResult result) {
		ModelAndView model;
		if (result.hasErrors()) {
			model = new ModelAndView("AddressForm", result.getModel());
		} else {
			this.address = address;
			City city = new City();
			model = new ModelAndView("CityForm", "city", city);
		}
		return model;
	}

	@RequestMapping(value = "/saveCity", method = RequestMethod.POST)
	public ModelAndView saveCity(@Valid @ModelAttribute City city,
			BindingResult result) {
		ModelAndView model;
		if (result.hasErrors()) {
			model = new ModelAndView("CityForm", result.getModel());
		} else {
			this.city = city;
			Country country = new Country();
			model = new ModelAndView("CountryForm", "country", country);
		}
		return model;
	}

	@RequestMapping(value = "/saveCountry", method = RequestMethod.POST)
	public ModelAndView saveCountry(@Valid @ModelAttribute Country country,
			BindingResult result) {
		ModelAndView model;
		if (result.hasErrors()) {
			model = new ModelAndView("CountryForm", result.getModel());
		} else {
			this.country = country;
			// save in db everything
			saveToDatabase();
			model = new ModelAndView("redirect:/");
		}
		return model;
	}

	@RequestMapping(value = "/deleteContact", method = RequestMethod.GET)
	public ModelAndView deleteContact(HttpServletRequest request) {
		long contactId = Long.parseLong(request.getParameter("id"));
		contactDAO.deleteContact(contactId);
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/editContact", method = RequestMethod.GET)
	public ModelAndView editContact(HttpServletRequest request) {
		long contactId = Long.parseLong(request.getParameter("id"));
		Contact contact = contactDAO.getContact(contactId);
		ModelAndView model = new ModelAndView("ContactForm");
		model.addObject("contact", contact);

		return model;
	}

	@RequestMapping(value = "/editCity", method = RequestMethod.GET)
	public ModelAndView editCity(HttpServletRequest request) {
		long cityId = Long.parseLong(request.getParameter("id"));
		City city = contactDAO.getCity(cityId);
		ModelAndView model = new ModelAndView("CityForm");
		model.addObject("city", city);

		return model;
	}

	@RequestMapping(value = "/editCountry", method = RequestMethod.GET)
	public ModelAndView editCountry(HttpServletRequest request) {
		long countryId = Long.parseLong(request.getParameter("id"));
		Country country = contactDAO.getCountry(countryId);
		ModelAndView model = new ModelAndView("CountryForm");
		model.addObject("country", country);

		return model;
	}

	@RequestMapping(value = "/newCity", method = RequestMethod.GET)
	public ModelAndView newCity(ModelAndView model) {
		City newCity = new City();
		model.addObject("city", newCity);
		model.setViewName("CityForm");
		return model;
	}

	@RequestMapping(value = "/newCountry", method = RequestMethod.GET)
	public ModelAndView newCountry(ModelAndView model) {
		Country newCountry = new Country();
		model.addObject("country", newCountry);
		model.setViewName("CountryForm");
		return model;
	}

	@RequestMapping(value = "/deleteCity", method = RequestMethod.GET)
	public ModelAndView deleteCity(HttpServletRequest request, ModelMap map) {
		long citytId = Long.parseLong(request.getParameter("id"));
		int status = contactDAO.deleteCity(citytId);
		if (status == 1) {
			map.addAttribute(
					"errMsg",
					"Cannot delete city. There exists at least one address that depends on the city");
		}
		return new ModelAndView("redirect:/");

	}

	@RequestMapping(value = "/deleteCountry", method = RequestMethod.GET)
	public ModelAndView deleteCountry(HttpServletRequest request, ModelMap map) {
		long countryId = Long.parseLong(request.getParameter("id"));
		int status = contactDAO.deleteCity(countryId);
		if (status == 1) {
			map.addAttribute(
					"errMsg",
					"Cannot delete Country. There exists at least one city that depends on the city");
		}
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView invalidURL(HttpServletRequest request) {
		return new ModelAndView("redirect:/");
	}

	private void saveToDatabase() {

		if (country != null) {
			contactDAO.createTuple(country);
			city.setCountry_id(country.getId());
			city.setCountry(country);
		}
		if (city != null) {
			contactDAO.createTuple(city);
			address.setCity(city);
			address.setCity_id(city.getId());
		}
		if (address != null) {
			contactDAO.createTuple(address);
			contact.setAddress_id(address.getId());
			contact.setAddress(address);
		}
		if (contact != null) {
			String name = sex.getName();
			contact.setSex(name);
			contact.setSex_id(sex.getGenderID(name));
		}

	}

	private void resetVariables() {
		this.contact = null;
		this.address = null;
		this.city = null;
		this.country = null;
		this.sex = null;
	}
}
