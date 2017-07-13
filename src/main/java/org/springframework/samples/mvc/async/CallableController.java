package org.springframework.samples.mvc.async;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.WebAsyncTask;

/*
You can make any existing controller method asynchronous by changing it to return a Callable.
Returning Callable implies that Spring MVC will invoke the task defined in the Callable in a different thread.
Spring will manage this thread.

Sequence of events for async request processing with a Callable:
Controller returns a Callable.
Spring MVC starts asynchronous processing and submits the Callable to a TaskExecutor for processing in a separate thread.
The DispatcherServlet and all Filter’s exit the Servlet container thread but the response remains open.
The Callable produces a result and Spring MVC dispatches the request back to the Servlet container to resume processing.
The DispatcherServlet is invoked again and processing resumes with the asynchronously produced result from the Callable.
*/

@Controller
@RequestMapping("/async/callable")
public class CallableController {

	@RequestMapping("/response-body")
	public @ResponseBody Callable<String> callable() {
		return new Callable<String>() {
			@Override
			public String call() throws Exception {
				Thread.sleep(2000);
				return "Callable result";
			}
		};
	}

	@RequestMapping("/view")
	public Callable<String> callableWithView(final Model model) {
		return new Callable<String>() {
			@Override
			public String call() throws Exception {
				Thread.sleep(2000);
				model.addAttribute("foo", "bar");
				model.addAttribute("fruit", "apple");
				return "views/html";
			}
		};
	}

	@RequestMapping("/exception")
	public @ResponseBody Callable<String> callableWithException(
			final @RequestParam(required = false, defaultValue = "true") boolean handled) {
		return new Callable<String>() {
			@Override
			public String call() throws Exception {
				Thread.sleep(2000);
				if (handled) {
					// see handleException method further below
					throw new IllegalStateException("Callable error");
				} else {
					throw new IllegalArgumentException("Callable error");
				}
			}
		};
	}

	/*
	 * WebAsyncTask can be used to wrap a callable to customize asynchronous
	 * processing. WebAsyncTask allows to set timeout and a custom task executor
	 * per callable basis.
	 */
	@RequestMapping("/custom-timeout-handling")
	public @ResponseBody WebAsyncTask<String> callableWithCustomTimeoutHandling() {
		Callable<String> callable = new Callable<String>() {
			@Override
			public String call() throws Exception {
				Thread.sleep(2000);
				return "Callable result";
			}
		};
		ConcurrentTaskExecutor t = new ConcurrentTaskExecutor(Executors.newFixedThreadPool(1));
		return new WebAsyncTask<String>(1000L, t, callable);
	}

	@ExceptionHandler
	@ResponseBody
	public String handleException(IllegalStateException ex) {
		return "Handled exception: " + ex.getMessage();
	}

}
