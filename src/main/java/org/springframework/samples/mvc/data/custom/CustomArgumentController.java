package org.springframework.samples.mvc.data.custom;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/*
@ModelAttribute annotation can be used on methods or on method arguments.
An @ModelAttribute on a method indicates the purpose of that method is to add one or more model attributes.
Such methods support the same argument types as @RequestMapping methods but cannot be mapped directly to requests.
Instead @ModelAttribute methods in a controller are invoked before @RequestMapping methods, within the same controller. 

// Add one attribute
// The return value of the method is added to the model under the name "account"
// You can customize the name via @ModelAttribute("myAccount")

@ModelAttribute
public Account addAccount(@RequestParam String number) {
    return accountManager.findAccount(number);
}

// Add multiple attributes

@ModelAttribute
public void populateModel(@RequestParam String number, Model model) {
    model.addAttribute(accountManager.findAccount(number));
    // add more ...
}

@RequestAttribute is not a Spring annotation.
The RequestAttribute is nothing but the parameters which you have passed in the form submission.
<form action="...">
<input type=hidden name=param1 id=param1 value=test/>
</form>

@Controller
public class CustomArgumentController {

@ModelAttribute
void beforeInvokingHandlerMethod(HttpServletRequest request) {
    request.setAttribute("foo", "bar");
}

@RequestMapping(value="/data/custom", method=RequestMethod.GET)
public @ResponseBody String custom(@RequestAttribute("param1") String param1 ) {
    // Here, I will have value of param1 as test in String object which will be mapped my Spring itself.
}

Annotating beforeInvokingHandlerMethod() method by @ModelAttribute annotation is the same
thing as correctly passed a form with the "foo", "bar" values.
*/

@Controller
public class CustomArgumentController {

	@ModelAttribute
	void beforeInvokingHandlerMethod(HttpServletRequest request) {
		request.setAttribute("foo", "bar");
	}

	@RequestMapping(value = "/data/custom", method = RequestMethod.GET)
	public @ResponseBody String custom(@RequestAttribute("foo") String foo) {
		return "Got 'foo' request attribute value '" + foo + "'";
	}

}
