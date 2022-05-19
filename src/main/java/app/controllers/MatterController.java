package app.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import app.entities.Matter;
import app.services.implementation.CareerService;
import app.services.implementation.MatterService;
import lombok.var;
import lombok.extern.slf4j.Slf4j;

 


@Controller
@RequestMapping("/matter") // matter route
@Slf4j
public class MatterController {
	
	@Autowired(required=true)
	private MatterService matterService;
	
	@Autowired(required=true)
	private CareerService careerService;
	
	@GetMapping("/matters")
	public String matters(Model model)
	{
		log.info("CONTROLLER [MATTER]");	// info console: Para no perder la pista del controlador (opcional)
		log.debug("METHOD [matters]");	// details console: Para saber que metodo se esta ejecutando (opcional)
		
		var listMatter = matterService.getAll(); // var = Lombok
		
		// inyeccion Thymeleaf
		model.addAttribute("listMatter", listMatter);
		
		return "matter/listMatter";
	}
	
	@GetMapping("/addMatter")
	public String addMatter(Matter matter, Model model)
	{
		log.info("CONTROLLER [MATTER]");	// info console
		log.debug("METHOD [addMatter]");	// details console
		
		var listCareer = careerService.getAll();
		model.addAttribute("listCareer", listCareer);
		
		return "matter/insert"; // go to: pagina de insertar o modificar (matter)
	}
	
	@PostMapping("/addMatter")
	public String saveMatter(@Valid Matter matter, Errors error) // Inyecta automaticamente al ser metodo <post> busca en: th:action="@{/addMatter}" method="post"	
	{		
		log.info("CONTROLLER [MATTER]");	// info console
		log.debug("METHOD [saveMatter]");	// details console
		
		if(error.hasErrors()) // En caso de un error en las validaciones
		{
			return "matter/insert"; // Se queda en la pagina y muestra los errores
		}
		
		matterService.insertOrUpdate(matter); // En caso de que funcione agrega el espacio
		
		return "redirect:/matter/matters"; // go to: home page
	}
	
	// Type: Path variable
	@GetMapping("/edit/{idMatter}") // Al pasarle el parametro {idMatter} lo relaciona con el parametro de Matter
	public String editMatter(Matter matter, Model model)
	{		
		log.info("CONTROLLER [MATTER]");	// info console
		log.debug("METHOD [editMatter]");	// details console
		
		var listCareer = careerService.getAll();
		model.addAttribute("listCareer", listCareer);
		
		model.addAttribute("matter", matterService.findById(matter.getIdMatter())); // Necesario "instanciar" el objeto para ser mostrado en thymeleaf
		
		return "matter/modify"; // go to: pagina de insertar o modificar (matter)
	}
	
	@PostMapping("/editMatter")
	public String editMatter(@Valid Matter matter, Errors error, Model model) // Inyecta automaticamente al ser metodo <post> busca en: th:action="@{/addRole}" method="post"	
	{		
		log.info("CONTROLLER [MATTER]");	// info console
		log.debug("METHOD [editMatter]");	// details console
		
		if(error.hasErrors()) // En caso de un error en las validaciones
		{
			var listCareer = careerService.getAll();
			model.addAttribute("listCareer", listCareer);
			
			return "matter/modify"; // Se queda en la pagina y muestra los errores
		}
		
		matterService.insertOrUpdate(matter); // En caso de que funcione agrega el espacio
		
		return "redirect:/matter/matters"; // go to: home page
	}
	
	// Type: Query Parameter
	@GetMapping("/delete") // Relaciona el idMatter en el HTML con el controlador para "apuntar" al correcto
	public String deleteMatter(Matter matter)
	{		
		log.info("CONTROLLER [MATTER]");		// info console
		log.debug("METHOD [deleteMatter]");	// details console
		
		matterService.remove(matter.getIdMatter()); // remueve el espacio
		
		return "redirect:/matter/matters"; // go to: home page
	}
}