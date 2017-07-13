package org.springframework.samples.mvc.async;

import java.util.concurrent.Callable;

import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.async.CallableProcessingInterceptorAdapter;

/*
The interceptor is called just before and after running the callback.

When a servlet container thread detects that a async callable has timed-out,
it invokes handleTimeout() (in its own context).
Thats the reason you see the handleTimeout() getting executed.
It is executed by a servlet container thread and not by the thread that runs the Callable.
*/

public class TimeoutCallableProcessingInterceptor extends CallableProcessingInterceptorAdapter {

	/*
	 * @Override public <T> Object handleTimeout(NativeWebRequest request,
	 * Callable<T> task) throws Exception { throw new IllegalStateException("["
	 * + task.getClass().getName() + "] timed out"); }
	 */

	@Override
	public <T> void beforeConcurrentHandling(NativeWebRequest request, Callable<T> task) throws Exception {
		System.out.println("callableInterceptor#beforeConcurrentHandling called. " + "Thread: "
				+ Thread.currentThread().getName());
	}

	@Override
	public <T> void preProcess(NativeWebRequest request, Callable<T> task) throws Exception {
		System.out.println("callableInterceptor#preProcess called. " + " Thread: " + Thread.currentThread().getName());
	}

	@Override
	public <T> void postProcess(NativeWebRequest request, Callable<T> task, Object concurrentResult) throws Exception {
		System.out.println("callableInterceptor#postProcess called. " + " Thread: " + Thread.currentThread().getName());
	}

	@Override
	public <T> Object handleTimeout(NativeWebRequest request, Callable<T> task) throws Exception {
		System.out
				.println("callableInterceptor#handleTimeout called." + " Thread: " + Thread.currentThread().getName());
		return RESULT_NONE;
	}

	@Override
	public <T> void afterCompletion(NativeWebRequest request, Callable<T> task) throws Exception {
		System.out.println(
				"callableInterceptor#afterCompletion called." + " Thread: " + Thread.currentThread().getName());
	}

}
