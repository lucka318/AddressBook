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
			if (contact == null) { // new contact save
				this.contact = contact;
			}
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
			if (contact.getAddress() == null) {
				Address address = new Address();
				model = new ModelAndView("AddressForm", "address", address);
			} else {
				this.address = contact.getAddress();
				model = new ModelAndView("AddressForm", "address", this.address);
			}
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
			if (this.address == null) {
				this.address = address;
				City city = new City();
				model = new ModelAndView("CityForm", "city", city);
			} else {
				this.city = this.address.getCity();
				model = new ModelAndView("CityForm", "city", this.city);
			}
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
			if (this.city == null) { // new city
				this.city = city;
				Country country = new Country();
				model = new ModelAndView("CountryForm", "country", country);
			} else {
				this.country = this.city.getCountry();
				model = new ModelAndView("CountryForm", "country", this.country);
			}
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
		this.contact = contact;
		ModelAndView model = new ModelAndView("ContactForm");
		model.addObject("contact", contact);

		return model;
	}

	@RequestMapping(value = "/editCity", method = RequestMethod.GET)
	public ModelAndView editCity(HttpServletRequest request) {
		long cityId = Long.parseLong(request.getParameter("id"));
		City city = contactDAO.getCity(cityId);
		this.city = city;
		ModelAndView model = new ModelAndView("CityForm");
		model.addObject("city", city);

		return model;
	}

	@RequestMapping(value = "/editCountry", method = RequestMethod.GET)
	public ModelAndView editCountry(HttpServletRequest request) {
		long countryId = Long.parseLong(request.getParameter("id"));
		Country country = contactDAO.getCountry(countryId);
		this.country = country;
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
		long cityId = Long.parseLong(request.getParameter("id"));
		int status = contactDAO.deleteCity(cityId);
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
		int status = contactDAO.deleteCountry(countryId);
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
		}
		if (city != null) {
			city.setCountry_id(country.getId());
			city.setCountry(country);
			contactDAO.createTuple(city);
		}
		if (address != null) {
			address.setCity(city);
			address.setCity_id(city.getId());
			contactDAO.createTuple(address);
		}
		if (contact != null) {
			String name = sex.getName();
			contact.setSex(name);
			contact.setSex_id(sex.getGenderID(name));

			contact.setAddress_id(address.getId());
			contact.setAddress(address);

			contactDAO.createTuple(contact);
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
