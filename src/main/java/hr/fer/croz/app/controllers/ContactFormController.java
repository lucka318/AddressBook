package hr.fer.croz.app.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import hr.fer.croz.app.manager.AddressBookManager;
import hr.fer.croz.app.model.ContactEntity;

@Controller
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
	public String saveContact(@Valid @ModelAttribute ContactEntity contactEntity, HttpServletRequest request,
			BindingResult result, Model model) {
		String view = "";

		if (result.hasErrors()) {
			model.addAllAttributes(result.getModel());
			view = "ContactForm";
		} else {
			long addressID = Long.parseLong(request.getParameter("addresses"));
			contactEntity.setAddressID(addressID); // radi li se ovo ovdje??
			long genderID = Long.parseLong(request.getParameter("genders"));
			contactEntity.setGender(genderID);
			addressBookManager.saveNewToDatabase(contactEntity);
			view = "redirect:/";
		}
		return view;
	}

	@RequestMapping(value = "/saveEditContact", method = RequestMethod.POST)
	public String saveEditContact(@Valid @ModelAttribute ContactEntity contactEntity, BindingResult result,
			Model model) {
		String view = "";

		if (result.hasErrors()) {
			model.addAllAttributes(result.getModel());
			view = "ContactForm";
		} else {
			addressBookManager.saveUpdateToDatabase(contactEntity);
			view = "redirect:/";
		}
		return view;
	}

}
