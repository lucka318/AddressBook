package hr.fer.croz.app.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import hr.fer.croz.app.manager.AddressBookManager;
import hr.fer.croz.app.model.AddressBookEntity;

public class ContactFormController {

	@Autowired
	private AddressBookManager addressBookManager;

	/**
	 * Checks if ContactForm was filled properly. Saves contact in a variable so
	 * it could later be inserted in database.
	 * 
	 * @param contact
	 * @param result
	 * @return {@link ModelAndView}
	 */
	// isprobaj razliciti redoslijed parametara
	@RequestMapping(value = "/saveContact", method = RequestMethod.POST)
	public String saveContact(@Valid @ModelAttribute AddressBookEntity addressBookEntity, BindingResult result,
			Model model) {
		String view = "";

		if (result.hasErrors()) {
			model.addAllAttributes(result.getModel());
			view = "ContactForm";
		} else {
			addressBookManager.saveNewToDatabase(addressBookEntity);
			view = "redirect:/";
		}
		return view;
	}

	@RequestMapping(value = "/saveEditContact", method = RequestMethod.POST)
	public String saveEditContact(@Valid @ModelAttribute AddressBookEntity addressBookEntity, BindingResult result,
			Model model) {
		String view = "";

		if (result.hasErrors()) {
			model.addAllAttributes(result.getModel());
			view = "ContactForm";
		} else {
			addressBookManager.saveUpdateToDatabase(addressBookEntity);
			view = "redirect:/";
		}
		return view;
	}

}
