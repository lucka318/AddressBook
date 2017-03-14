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

/**
 * Class HomeController is a controller that manages mappings from .jsp files to
 * database.
 * 
 * @author Lucija Megla
 *
 */
@Controller
public class HomeController {

	private Contact contact;
	private Address address;
	private City city;
	private Country country;
	private Sex sex;

	@Autowired
	private DAO contactDAO;

	/**
	 * Get list of Contact, Cities and Countries from database and sends it to
	 * home.jsp file.
	 * 
	 * @param model
	 * @return {@link ModelAndView}
	 * @throws IOException
	 */
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

	/**
	 * Maps to ContactForm.jsp to insert new Contact
	 * 
	 * @param model
	 * @return {@link ModelAndView}
	 */
	@RequestMapping(value = "/newContact", method = RequestMethod.GET)
	public ModelAndView newContact(ModelAndView model) {
		Contact newContact = new Contact();
		model.addObject("contact", newContact);
		model.setViewName("ContactForm");
		return model;
	}

	/**
	 * Checks if ContactForm was filled properly. Saves contact in a variable so
	 * it could later be inserted in database.
	 * 
	 * @param contact
	 * @param result
	 * @return {@link ModelAndView}
	 */
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

	/**
	 * Checks if Gender Form was filled properly. Saves sex in a variable so it
	 * could later be inserted in database.
	 * 
	 * @param sex
	 * @param result
	 * @return {@link ModelAndView}
	 */
	@RequestMapping(value = "/saveSex", method = RequestMethod.POST)
	public ModelAndView saveSex(@Valid @ModelAttribute Sex sex,
			BindingResult result) {
		ModelAndView model;
		if (result.hasErrors()) {
			model = new ModelAndView("GenderForm", result.getModel());
		} else {
			this.sex = sex;
			if (contact.getAddress() == null) { // new address
				Address address = new Address();
				model = new ModelAndView("AddressForm", "address", address);
			} else { // edit address
				this.address = contact.getAddress();
				model = new ModelAndView("AddressForm", "address", this.address);
			}
		}
		return model;
	}

	/**
	 * Checks if AddressForm was filled properly. Saves Address in a variable so
	 * it could later be inserted in database.
	 * 
	 * @param address
	 * @param result
	 * @return {@link ModelAndView}
	 */
	@RequestMapping(value = "/saveAddress", method = RequestMethod.POST)
	public ModelAndView saveAddress(@Valid @ModelAttribute Address address,
			BindingResult result) {
		ModelAndView model;
		if (result.hasErrors()) {
			model = new ModelAndView("AddressForm", result.getModel());
		} else {
			if (this.address == null) { // new
				this.address = address;
				City city = new City();
				model = new ModelAndView("CityForm", "city", city);
			} else { // edit
				this.city = this.address.getCity();
				model = new ModelAndView("CityForm", "city", this.city);
			}
		}
		return model;
	}

	/**
	 * Checks if CityForm was filled properly. Saves City in a variable so it
	 * could later be inserted in database.
	 * 
	 * @param city
	 * @param result
	 * @return {@link ModelAndView}
	 */
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
			} else { // edit city
				this.country = this.city.getCountry();
				model = new ModelAndView("CountryForm", "country", this.country);
			}
		}
		return model;
	}

	/**
	 * Checks if CountryForm was filled properly. Saves Country in a variable so
	 * it could later be inserted in database.
	 * 
	 * @param country
	 * @param result
	 * @return {@link ModelAndView}
	 */
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

	/**
	 * Delete contact
	 * 
	 * @param request
	 * @return {@link ModelAndView}
	 */
	@RequestMapping(value = "/deleteContact", method = RequestMethod.GET)
	public ModelAndView deleteContact(HttpServletRequest request) {
		long contactId = Long.parseLong(request.getParameter("id"));
		contactDAO.deleteContact(contactId);
		return new ModelAndView("redirect:/");
	}

	/**
	 * Edit an existing contact.
	 * 
	 * @param request
	 * @return {@link ModelAndView}
	 */
	@RequestMapping(value = "/editContact", method = RequestMethod.GET)
	public ModelAndView editContact(HttpServletRequest request) {
		long contactId = Long.parseLong(request.getParameter("id"));
		Contact contact = contactDAO.getContact(contactId);
		this.contact = contact;
		ModelAndView model = new ModelAndView("ContactForm");
		model.addObject("contact", contact);

		return model;
	}

	/**
	 * Edit an existing city.
	 * 
	 * @param request
	 * @return {@link ModelAndView}
	 */
	@RequestMapping(value = "/editCity", method = RequestMethod.GET)
	public ModelAndView editCity(HttpServletRequest request) {
		long cityId = Long.parseLong(request.getParameter("id"));
		City city = contactDAO.getCity(cityId);
		this.city = city;
		ModelAndView model = new ModelAndView("CityForm");
		model.addObject("city", city);

		return model;
	}

	/**
	 * Edit an existing country.
	 * 
	 * @param request
	 * @return {@link ModelAndView}
	 */
	@RequestMapping(value = "/editCountry", method = RequestMethod.GET)
	public ModelAndView editCountry(HttpServletRequest request) {
		long countryId = Long.parseLong(request.getParameter("id"));
		Country country = contactDAO.getCountry(countryId);
		this.country = country;
		ModelAndView model = new ModelAndView("CountryForm");
		model.addObject("country", country);

		return model;
	}

	/**
	 * Insert new city.
	 * 
	 * @param request
	 * @return {@link ModelAndView}
	 */
	@RequestMapping(value = "/newCity", method = RequestMethod.GET)
	public ModelAndView newCity(ModelAndView model) {
		City newCity = new City();
		model.addObject("city", newCity);
		model.setViewName("CityForm");
		return model;
	}

	/**
	 * Insert new country.
	 * 
	 * @param request
	 * @return {@link ModelAndView}
	 */
	@RequestMapping(value = "/newCountry", method = RequestMethod.GET)
	public ModelAndView newCountry(ModelAndView model) {
		Country newCountry = new Country();
		model.addObject("country", newCountry);
		model.setViewName("CountryForm");
		return model;
	}

	/**
	 * Delete a city. City won't be deleted if there exists an address in
	 * database that is connected to the city.
	 * 
	 * @param request
	 * @param map
	 * @return
	 */
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

	/**
	 * Delete a country. Country won't be deleted if there exists a city in
	 * database that is connected to the country.
	 * 
	 * @param request
	 * @return {@link ModelAndView}
	 */
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

	/**
	 * If user enters invalid URL, redirects it to the main page.
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView invalidURL(HttpServletRequest request) {
		return new ModelAndView("redirect:/");
	}

	/**
	 * Save entites to database.
	 */
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

	/**
	 * Reset variables after they have been put to database.
	 */
	private void resetVariables() {
		this.contact = null;
		this.address = null;
		this.city = null;
		this.country = null;
		this.sex = null;
	}
}
