package org.springframework.samples.mvc.data;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/data")
public class RequestDataController {

	@RequestMapping(value = "param", method = RequestMethod.GET)
	public @ResponseBody String withParam(@RequestParam String foo) {
		return "Obtained 'foo' query parameter value '" + foo + "'";
	}

	@RequestMapping(value = "group", method = RequestMethod.GET)
	public @ResponseBody String withParamGroup(JavaBean bean) {
		return "Obtained parameter group " + bean;
	}

	@RequestMapping(value = "path/{var}", method = RequestMethod.GET)
	public @ResponseBody String withPathVariable(@PathVariable String var) {
		return "Obtained 'var' path variable value '" + var + "'";
	}

	@RequestMapping(value = "{path}/simple", method = RequestMethod.GET)
	public @ResponseBody String withMatrixVariable(@PathVariable String path, @MatrixVariable String foo) {
		return "Obtained matrix variable 'foo=" + foo + "' from path segment '" + path + "'";
	}

	@RequestMapping(value = "{path1}/{path2}", method = RequestMethod.GET)
	public @ResponseBody String withMatrixVariablesMultiple(@PathVariable String path1,
			@MatrixVariable(value = "foo", pathVar = "path1") String foo1, @PathVariable String path2,
			@MatrixVariable(value = "foo", pathVar = "path2") String foo2) {
		return "Obtained matrix variable foo=" + foo1 + " from path segment '" + path1 + "' and variable 'foo=" + foo2
				+ " from path segment '" + path2 + "'";
	}

	/*A more powerful example, check the request URL.*/
	@RequestMapping(value = "matrixvars/{stocks}/{account}", method = RequestMethod.GET)
	public @ResponseBody String withMatrixVariablesMultiple2(
			@MatrixVariable(pathVar = "stocks") Map<String, List<String>> stocks,
			@MatrixVariable(pathVar = "account") Map<String, List<String>> accounts, Model model) {
		Map<String, List<String>> stocksMap = stocks;
		Map<String, List<String>> accountsMap = accounts;
		return "Stocks: " + stocksMap + " Accounts: " + accountsMap;
	}

	@RequestMapping(value = "header", method = RequestMethod.GET)
	public @ResponseBody String withHeader(@RequestHeader String Accept) {
		return "Obtained 'Accept' header '" + Accept + "'";
	}

	@RequestMapping(value = "cookie", method = RequestMethod.GET)
	public @ResponseBody String withCookie(@CookieValue String openid_provider) {
		return "Obtained 'openid_provider' cookie '" + openid_provider + "'";
	}

	@RequestMapping(value = "body", method = RequestMethod.POST)
	public @ResponseBody String withBody(@RequestBody String body) {
		return "Posted request body '" + body + "'";
	}

	@RequestMapping(value = "entity", method = RequestMethod.POST)
	public @ResponseBody String withEntity(HttpEntity<String> entity) {
		return "Posted request body '" + entity.getBody() + "'; headers = " + entity.getHeaders();
	}

}
