package controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandleController {
	@ExceptionHandler(value=Exception.class)
	public String exceptionHandler(Exception e, Model model) {
		String message = "";
		if(e instanceof NoLoginException) {
			message = "noLogin";
		}else {
			// δ֪�쳣
			message = "noError";
		}
		model.addAttribute("mymessage", message);
		return "error";
	}
}
