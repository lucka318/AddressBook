package hr.fer.croz.app.controllers;

import hr.fer.croz.app.dao.DAO;
import hr.fer.croz.app.model.Address;
import hr.fer.croz.app.model.City;
import hr.fer.croz.app.model.Contact;
import hr.fer.croz.app.model.Country;
import hr.fer.croz.app.model.Sex;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
		model.addObject("contacts", contacts);
		model.setViewName("home");
		resetVariables();
		return model;
	}

	private void resetVariables() {
		this.contact = null;
		this.address = null;
		this.city = null;
		this.country = null;
		this.sex = null;
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

	private void saveToDatabase() {

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
}
