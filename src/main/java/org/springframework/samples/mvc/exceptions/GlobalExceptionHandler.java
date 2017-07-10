package org.springframework.samples.mvc.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/*A controller advice allows you to use exactly the same exception handling techniques 
 * but apply them across the whole application, not just to an individual controller.
 * You can think of them as an annotation driven interceptor.

Any class annotated with @ControllerAdvice becomes a controller-advice and three types of method are supported:

Exception handling methods annotated with @ExceptionHandler.
Model enhancement methods (for adding additional data to the model) annotated with
@ModelAttribute. Note that these attributes are not available to the exception handling views.
Binder initialization methods (used for configuring form-handling) annotated with
@InitBinder.

@ExceptionHandler only apply to a single controller, 
to apply it globally (all controllers), annotate a class with @ControllerAdvice*/

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler
	public @ResponseBody String handleBusinessException(BusinessException ex) {
		return "Handled BusinessException";
	}

}
