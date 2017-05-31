package hr.tvz.notebook.web.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import hr.tvz.notebook.server.service.MessageService;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@Autowired
	MessageService messageService;

	@ExceptionHandler(value = AccessDeniedException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public String accessDenied(Exception ex, Model model) {
		logger.info("ERROR: ", ex);
		model.addAttribute("errorCode", "403");
		model.addAttribute("errorMessage", messageService.getMessage("exception.accessDenied"));
		return "error/error";
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handle(Exception ex, Model model) {
		logger.info("ERROR: ", ex);
		model.addAttribute("errorCode", "404");
		model.addAttribute("errorMessage", messageService.getMessage("exception.notFound"));
		return "error/error";
	}

	@ExceptionHandler(value = NullPointerException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String nullPointer(Exception ex, Model model) {
		logger.info("ERROR: ", ex);
		model.addAttribute("errorCode", "500");
		model.addAttribute("errorMessage",
				messageService.getMessage("exception.internalServerError"));
		return "error/error";
	}

	// TemplateInputException, TemplateProcessingException, HibernateException
	@ExceptionHandler(Throwable.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String exceptionInternal(Throwable throwable, Model model) {
		logger.info("ERROR: ", throwable);
		String errorMessage = (throwable != null ? throwable.getMessage() : "Unknown error");
		model.addAttribute("errorMessage", errorMessage);
		return "error/error";
	}

}