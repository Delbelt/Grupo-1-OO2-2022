package app.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/") // main route
@Slf4j
public class MainController {
	
	@GetMapping("/")
	public String home(Model model, @AuthenticationPrincipal User user)
	{
		log.info("CONTROLLER [MAIN]"); 	// info console: Para no perder la pista del controlador (opcional)
		log.debug("METHOD [home]");	 	// details console: Para saber que metodo se esta ejecutando (opcional)		
		
		log.info("USER LOGIN: " + user.getUsername()); // Para saber que usuario logeo
		
		return "home";
	}
	
	// TODO: add helpers or utils folder with Routes
}
