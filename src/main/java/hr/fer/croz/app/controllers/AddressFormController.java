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

public class AddressFormController {

	@Autowired
	private AddressBookManager addressBookManager;
	
	public AddressFormController() {
	}
	
	/**
	 * Checks if ContactForm was filled properly. Saves contact in a variable so
	 * it could later be inserted in database.
	 * 
	 * @param contact
	 * @param result
	 * @return {@link ModelAndView}
	 */
	// isprobaj razliciti redoslijed parametara
	@RequestMapping(value = "/saveAddress", method = RequestMethod.POST)
	public String saveContact(@Valid @ModelAttribute AddressBookEntity addressBookEntity, BindingResult result,
			Model model) {
		String view = "";

		if (result.hasErrors()) {
			
		} else {
		
		}
		return view;
	}

	@RequestMapping(value = "/saveEditAddress", method = RequestMethod.POST)
	public String saveEditContact(@Valid @ModelAttribute AddressBookEntity addressBookEntity, BindingResult result,
			Model model) {
		String view = "";

		if (result.hasErrors()) {
			
		} else {
			
		}
		return view;
	}
}
