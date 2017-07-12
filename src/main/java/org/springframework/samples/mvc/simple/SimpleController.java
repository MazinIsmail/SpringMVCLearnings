package org.springframework.samples.mvc.simple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SimpleController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleController.class);

	@RequestMapping("/simple")
	public @ResponseBody String simple() {
		LOGGER.error("Testing error LOGGER");
		LOGGER.debug("Testing debug LOGGER");
		LOGGER.info("Testing info LOGGER");
		return "Hello world!";
	}

}
