package hr.fer.croz.app.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
import hr.fer.croz.app.model.City;

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
	public String saveAddress(@Valid @ModelAttribute AddressEntity addressEntity, BindingResult result, Model model,
			HttpServletRequest request) {
		String view = "";
		if (result.hasErrors()) {
			model.addAllAttributes(result.getModel());
			List<City> cities = addressBookManager.fetchCities();
			model.addAttribute("cities", cities);
			view = "AddressForm";
		} else {
			long cityID = Long.parseLong(request.getParameter("cities"));
			addressEntity.setCityID(cityID); // radi li se ovo ovdje??
			String error = addressBookManager.saveNewToDatabase(addressEntity);
			request.getSession().setAttribute("error", error);
			view = "redirect:/";
		}
		return view;
	}

	// isprobaj razliciti redoslijed parametara
	@RequestMapping(value = "/saveEditAddress", method = RequestMethod.POST)
	public String saveEditAddress(@Valid @ModelAttribute AddressEntity addressEntity, BindingResult result, Model model,
			HttpServletRequest request) {
		String view = "";
		if (result.hasErrors()) {
			model.addAllAttributes(result.getModel());
			List<City> cities = addressBookManager.fetchCities();
			model.addAttribute("cities", cities);
			view = "AddressEditForm";
		} else {
			HttpSession session = request.getSession();
			long addressId = (Long) session.getAttribute("addressId");
			addressEntity.setId(addressId);
			session.removeAttribute("addressId");
			long cityID = Long.parseLong(request.getParameter("cities"));
			addressEntity.setCityID(cityID); // radi li se ovo ovdje??
			String error = addressBookManager.saveUpdateToDatabase(addressEntity);
			request.getSession().setAttribute("error", error);
			view = "redirect:/";
		}
		return view;
	}
}
