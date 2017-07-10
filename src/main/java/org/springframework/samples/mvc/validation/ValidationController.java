package org.springframework.samples.mvc.validation;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ValidationController {

	/*
	 * The BindingResult must come right after the model object that is
	 * validated or else Spring will fail to validate the object and throw an
	 * exception.
	 */
	@RequestMapping("/validate")
	public @ResponseBody String validate(@Valid JavaBean bean, BindingResult result) {
		if (result.hasErrors()) {
			return "Object has validation errors";
		} else {
			return "No errors";
		}
	}

}
