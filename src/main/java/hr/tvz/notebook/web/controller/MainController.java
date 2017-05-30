package hr.tvz.notebook.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({ "currentUser" })
public class MainController {

	@GetMapping("/")
	public String home() {
		return "redirect:/newNote";
	}

}
