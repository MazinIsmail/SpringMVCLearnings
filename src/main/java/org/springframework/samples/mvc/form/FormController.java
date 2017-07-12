package org.springframework.samples.mvc.form;

import javax.validation.Valid;

import org.springframework.mvc.extensions.ajax.AjaxUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/*Spring’s @SessionAttributes is used on a controller to designate which model attributes should be stored in the session.
 * Spring provides a means to remove Spring session attributes, 
 * and thereby also remove it from HttpSession (without having to kill the entire HttpSession).
 * Simply add a Spring SessionStatus object as a parameter to a controller handler method.
 * In this method, use the SessionStatus object to end the Spring session.
 * 
@RequestMapping("/endsession")
public String nextHandlingMethod2(SessionStatus status){
  status.setComplete();
  return "lastpage";
}*/

@Controller
@RequestMapping("/form")
@SessionAttributes("formBean")
public class FormController {

	// Invoked on every request

	@ModelAttribute
	public void ajaxAttribute(WebRequest request, Model model) {
		model.addAttribute("ajaxRequest", AjaxUtils.isAjaxRequest(request));
	}

	// Invoked initially to create the "form" attribute
	// Once created the "form" attribute comes from the HTTP session (see
	// @SessionAttributes)

	@ModelAttribute("formBean")
	public FormBean createFormBean() {
		return new FormBean();
	}

	@RequestMapping(method = RequestMethod.GET)
	public void form() {
	}

	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(@Valid FormBean formBean, BindingResult result,
			@ModelAttribute("ajaxRequest") boolean ajaxRequest, Model model, RedirectAttributes redirectAttrs) {
		if (result.hasErrors()) {
			return null;
		}
		// Typically you would save to a db and clear the "form" attribute from
		// the session
		// via SessionStatus.setCompleted(). For the demo we leave it in the
		// session.
		String message = "Form submitted successfully.  Bound " + formBean;
		// Success response handling
		if (ajaxRequest) {
			// prepare model for rendering success message in this request
			model.addAttribute("message", message);
			return null;
		} else {
			// store a success message for rendering on the next request after
			// redirect
			// redirect back to the form to render the success message along
			// with newly bound values
			redirectAttrs.addFlashAttribute("message", message);
			return "redirect:/form";
		}
	}

}
