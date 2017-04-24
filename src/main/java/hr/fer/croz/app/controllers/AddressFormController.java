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
import hr.fer.croz.app.model.AddressEntity;

@Controller
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
	public String saveAddress(@Valid @ModelAttribute AddressEntity addressEntity, HttpServletRequest request,
			BindingResult result, Model model) {
		String view = "";
		if (result.hasErrors()) {
			model.addAllAttributes(result.getModel());
			view = "AddressForm";
		} else {
			long cityID = Long.parseLong(request.getParameter("cities"));
			addressEntity.setCityID(cityID); // radi li se ovo ovdje??
			addressBookManager.saveNewToDatabase(addressEntity);
			view = "redirect:/";
		}
		return view;
	}

	// isprobaj razliciti redoslijed parametara
	@RequestMapping(value = "/saveEditAddress", method = RequestMethod.POST)
	public String saveEditAddress(@Valid @ModelAttribute AddressEntity addressEntity, HttpServletRequest request,
			BindingResult result, Model model) {
		String view = "";
		if (result.hasErrors()) {
			model.addAllAttributes(result.getModel());
			view = "AddressForm";
		} else {
			long cityID = Long.parseLong(request.getParameter("cities"));
			addressEntity.setCityID(cityID); // radi li se ovo ovdje??
			addressBookManager.saveUpdateToDatabase(addressEntity);
			view = "redirect:/";
		}
		return view;
	}
}
