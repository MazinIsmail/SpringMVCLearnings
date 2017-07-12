package org.springframework.samples.mvc.data.custom;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/*
Using type converters is a good choice if we want to create “simple” value objects and
inject these objects into our controller methods.

However Custom HandlerMethodArgumentResolver can be used
If we want to inject “complex” objects into our controller methods,
and these objects aren’t form objects or read from the request body,
we should create a custom method argument resolver.

We can create a new method argument resolver by implementing the HandlerMethodArgumentResolver interface.

Check servlet-context.xml
Before we can use a custom HandlerMethodArgumentResolver, we have to register it as a method argument resolver.

https://www.petrikainulainen.net/programming/spring-framework/spring-from-the-trenches-creating-a-custom-handlermethodargumentresolver/
*/

public class CustomArgumentResolver implements HandlerMethodArgumentResolver {

	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.getParameterAnnotation(RequestAttribute.class) != null;
	}

	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		RequestAttribute attr = parameter.getParameterAnnotation(RequestAttribute.class);
		return webRequest.getAttribute(attr.value(), WebRequest.SCOPE_REQUEST);
	}

}
