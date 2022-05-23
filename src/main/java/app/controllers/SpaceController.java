package app.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import app.entities.Building;
import app.entities.Space;
import app.services.implementation.ClassroomService;
import app.services.implementation.SpaceService;
import lombok.var;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/space") // space route
@Slf4j
public class SpaceController {
	
	@Autowired(required=true)
	private SpaceService spaceService;
	
	@Autowired(required=true)
	private ClassroomService classroomService;
	
	@GetMapping("/spaces")
	public String spaces(Model model)
	{
		log.info("CONTROLLER [SPACE]");	// info console: Para no perder la pista del controlador (opcional)
		log.debug("METHOD [spaces]");	// details console: Para saber que metodo se esta ejecutando (opcional)
		
		var listSpace = spaceService.getAll(); // var = Lombok
		
		// inyeccion Thymeleaf
		model.addAttribute("listSpace", listSpace);
		
		return "space/listSpace";
	}
	
	@GetMapping("/addSpace/{buildingName}-{idBuilding}")
	public String addSpace(Space space, Model model, Building building)
	{
		log.info("CONTROLLER [SPACE]");	// info console
		log.debug("METHOD [addSpace]");	// details console
		
		model.addAttribute("listClassroom", classroomService.findByBuilding_idBuilding(building.getIdBuilding()));
		
		return "space/insert"; // go to: pagina de insertar o modificar (space)
	}
	
	@PostMapping("/addSpace/{buildingName}-{idBuilding}")
	public String saveSpace(@Valid Space space, Errors error, Model model, Building building) // Inyecta automaticamente al ser metodo <post> busca en: th:action="@{/addRole}" method="post"	
	{		
		log.info("CONTROLLER [SPACE]");	// info console
		log.debug("METHOD [saveSpace]");	// details console
		
		if(error.hasErrors()) // En caso de un error en las validaciones
		{
			model.addAttribute("listClassroom", classroomService.findByBuilding_idBuilding(building.getIdBuilding()));
			return "space/insert"; // Se queda en la pagina y muestra los errores
		}
		
		spaceService.insertOrUpdate(space); // En caso de que funcione agrega el espacio
		
		return "redirect:/space/spaces"; // go to: home page
	}
	
	// Type: Path variable
	@GetMapping("/edit/{idSpace}") // Al pasarle el parametro {idSpace} lo relaciona con el parametro de Space
	public String editSpace(Space space, Model model)
	{		
		log.info("CONTROLLER [SPACE]");	// info console
		log.debug("METHOD [editSpace]");	// details console
		
		var listClassroom = classroomService.getAll();
		model.addAttribute("listClassroom", listClassroom);
		
		model.addAttribute("space", spaceService.findById(space.getIdSpace())); // Necesario "instanciar" el objeto para ser mostrado en thymeleaf
		
		return "space/modify"; // go to: pagina de insertar o modificar (space)
	}
	
	@PostMapping("/editSpace")
	public String editSpace(@Valid Space space, Errors error, Model model) // Inyecta automaticamente al ser metodo <post> busca en: th:action="@{/addRole}" method="post"	
	{		
		log.info("CONTROLLER [SPACE]");	// info console
		log.debug("METHOD [editSpace]");	// details console
		
		if(error.hasErrors()) // En caso de un error en las validaciones
		{
			var listClassroom = classroomService.getAll();
			model.addAttribute("listClassroom", listClassroom);
			
			return "space/modify"; // Se queda en la pagina y muestra los errores
		}
		
		spaceService.insertOrUpdate(space); // En caso de que funcione agrega el espacio
		
		return "redirect:/space/spaces"; // go to: home page
	}
	
	// Type: Query Parameter
	@GetMapping("/delete") // Relaciona el idSpace en el HTML con el controlador para "apuntar" al correcto
	public String deleteSpace(Space space)
	{		
		log.info("CONTROLLER [SPACE]");		// info console
		log.debug("METHOD [deleteSpace]");	// details console
		
		spaceService.remove(space.getIdSpace()); // remueve el espacio
		
		return "redirect:/space/spaces"; // go to: home page
	}
}
