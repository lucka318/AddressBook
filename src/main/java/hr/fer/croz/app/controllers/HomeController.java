package hr.fer.croz.app.controllers;

import hr.fer.croz.app.manager.AddressBookManager;
import hr.fer.croz.app.model.AddressBookEntity;
import hr.fer.croz.app.model.City;
import hr.fer.croz.app.model.Contact;
import hr.fer.croz.app.model.Country;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

	@Autowired
	private AddressBookManager addressBookManager;

	/**
	 * Get list of Contact, Cities and Countries from database and sends it to
	 * home.jsp file.
	 * 
	 * @param model
	 * @return {@link ModelAndView}
	 * @throws IOException
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String listContact(Model model) throws IOException {
		List<Contact> contacts = addressBookManager.fetchContacts();
		List<City> cities = addressBookManager.fetchCities();
		List<Country> countries = addressBookManager.fetchCountries();
		model.addAttribute("contacts", contacts);
		model.addAttribute("cities", cities);
		model.addAttribute("countries", countries);
		return "home";
	}

	/**
	 * Maps to ContactForm.jsp to insert new Contact
	 * 
	 * @param model
	 * @return {@link ModelAndView}
	 */
	@RequestMapping(value = "/newContact", method = RequestMethod.GET)
	public String newContact(Model model) {
		AddressBookEntity addressBookEntity = new AddressBookEntity();
		model.addAttribute("addressBookEntity", addressBookEntity);
		return "ContactForm";
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
		// salji greske kao povratnu vrijednost
		addressBookManager.deleteContactFromDatabase(contactId);
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

		AddressBookEntity addressBookEntity = addressBookManager.prepareAddressBookEntity(contactId);
		ModelAndView model = new ModelAndView("InputFormEdit");
		model.addObject("addressBookEntity", addressBookEntity);

		return model;
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


}
