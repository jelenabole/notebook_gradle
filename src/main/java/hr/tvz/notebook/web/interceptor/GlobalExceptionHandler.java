package hr.tvz.notebook.web.interceptor;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.NoHandlerFoundException;

//TODO - lovi i @Valid errors
@ControllerAdvice
@SessionAttributes({ "currentUser" })
public class GlobalExceptionHandler {

	private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(NoHandlerFoundException.class)
	public String handle(Exception ex) {
		logger.info("sfsldkfjs");
		return "redirect:/404";
	}

	@ExceptionHandler(value = AccessDeniedException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String accessDeniedException(Exception ex, Model model) {
		logger.info("An error occured processing a rest request", ex);
		model.addAttribute("errorMessage", "403");
		return "error";
	}

	@ExceptionHandler(value = NullPointerException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String nullPointer(Exception ex) {
		logger.info("An error occured processing a rest request", ex);
		return "error";
	}
	
	// @ExceptionHandler(NoHandlerFoundException.class)
	// public String handleError404(HttpServletRequest request, Exception e,
	// Model model) {
	// // model.addAttribute("exception", e);
	// // mav.addObject("errorcode", "404");
	// logger.info("no handler found exception");
	// model.addAttribute("errorMessage", e.getMessage());
	// return "error/error";
	// }

	// @ExceptionHandler(Throwable.class)
	// @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	// public String exceptionInternal(final Throwable throwable, final Model
	// model) {
	// logger.error("Exception during execution of SpringSecurity application",
	// throwable);
	// String errorMessage = (throwable != null ? throwable.getMessage() :
	// "Unknown error");
	// model.addAttribute("errorMessage", errorMessage);
	// return "error";
	// }

	// TODO - hibernateException = ukoliko ne�to nije uredu sa hibernate-om
	// @ResponseStatus(HttpStatus.FORBIDDEN)
	// @ExceptionHandler(value = HibernateException.class)
	// public String hibernateErrorHandler(HttpServletRequest req, Exception e,
	// Model model) throws Exception {
	// logger.info("error - hibernate exception");
	//// if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class)
	// != null)
	//// throw e;
	//
	// // Otherwise setup and send the user to a default error-view.
	//// model.addAttribute("exception", e);
	//// model.addAttribute("url", req.getRequestURL());
	// model.addAttribute("500", "Internal server error - Hibernate!");
	// return "error";
	// }

	// TODO - throwable lovi sve gre�ke:
	// @ResponseStatus(HttpStatus.FORBIDDEN)
	// @ExceptionHandler(value = Throwable.class)
	// public String forbiddenErrorHandler(HttpServletRequest req, Exception e,
	// Model model) throws Exception {
	// logger.info("error - forbidden");
	// if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) !=
	// null)
	// throw e;
	//
	// // Otherwise setup and send the user to a default error-view.
	//// model.addAttribute("exception", e);
	//// model.addAttribute("url", req.getRequestURL());
	// model.addAttribute("errorMessage", "Access is denied!");
	// return "error";
	// }

	// @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	// @ExceptionHandler(value = Throwable.class)
	// public String serverErrorHandler(HttpServletRequest req, Exception e,
	// Model model) throws Exception {
	// logger.info("error - internal server error");
	// if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) !=
	// null)
	// throw e;
	//
	// // Otherwise setup and send the user to a default error-view.
	// model.addAttribute("exception", e);
	// model.addAttribute("url", req.getRequestURL());
	// model.addAttribute("errorMessage", "Internal server error!");
	// return "error";
	// }

	@ResponseStatus(HttpStatus.NOT_FOUND)
	// @ExceptionHandler(value = Throwable.class)
	public String notFoundErrorHandler(HttpServletRequest req, Exception e, Model model)
			throws Exception {
		logger.info("error - not found");
		if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null)
			throw e;

		// Otherwise setup and send the user to a default error-view.
		// model.addAttribute("exception", e);
		// model.addAttribute("url", req.getRequestURL());
		model.addAttribute("errorMessage", e.getMessage());
		// model.addAttribute("errorMessage", "Page not found!");
		// return "error/error";
		return "redirect:/error";
	}

}