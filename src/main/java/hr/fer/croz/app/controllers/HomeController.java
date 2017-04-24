package hr.fer.croz.app.controllers;

import hr.fer.croz.app.manager.AddressBookManager;
import hr.fer.croz.app.model.Address;
import hr.fer.croz.app.model.AddressEntity;
import hr.fer.croz.app.model.City;
import hr.fer.croz.app.model.Contact;
import hr.fer.croz.app.model.ContactEntity;
import hr.fer.croz.app.model.Sex;

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
		List<Address> addresses = addressBookManager.fetchAddresses();
		model.addAttribute("contacts", contacts);
		model.addAttribute("addresses", addresses);
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
		ContactEntity contactEntity = new ContactEntity();
		List<Address> addresses = addressBookManager.fetchAddresses();
		List<Sex> genders = addressBookManager.fetchGenders();
		model.addAttribute("addresses", addresses);
		model.addAttribute("genders", genders);
		model.addAttribute("contactEntity", contactEntity);
		return "ContactForm";
	}

	@RequestMapping(value = "/newAddress", method = RequestMethod.GET)
	public String newAddress(Model model) {
		AddressEntity addressEntity = new AddressEntity();
		List<City> cities = addressBookManager.fetchCities();
		model.addAttribute("cities", cities);
		model.addAttribute("addressEntity", addressEntity);
		return "AddressForm";
	}

	/**
	 * Delete contact
	 * 
	 * @param request
	 * @return {@link ModelAndView}
	 */

	@RequestMapping(value = "/deleteContact", method = RequestMethod.GET)
	public String deleteContact(HttpServletRequest request) {
		long contactId = Long.parseLong(request.getParameter("id"));
		// salji greske kao povratnu vrijednost
		addressBookManager.deleteContactFromDatabase(contactId);
		return "redirect:/";
	}

	@RequestMapping(value = "/deleteAddress", method = RequestMethod.GET)
	public String deleteAddress(HttpServletRequest request) {
		long addressId = Long.parseLong(request.getParameter("id"));
		// salji greske kao povratnu vrijednost
		String error = addressBookManager.deleteAddressFromDatabase(addressId);
		return "redirect:/";
	}

	/**
	 * Edit an existing contact.
	 * 
	 * @param request
	 * @return {@link ModelAndView}
	 */
	@RequestMapping(value = "/editContact", method = RequestMethod.GET)
	public String editContact(HttpServletRequest request, Model model) {
		long contactId = Long.parseLong(request.getParameter("id"));

		ContactEntity addressBookEntity = addressBookManager.prepareContactEntity(contactId);
		model.addAttribute("addressBookEntity", addressBookEntity);

		return "ContactForm";
	}

	/**
	 * If user enters invalid URL, redirects it to the main page.
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String invalidURL(HttpServletRequest request) {
		return "redirect:/";
	}

}
