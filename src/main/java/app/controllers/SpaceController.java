package app.controllers;

import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import app.entities.Building;
import app.entities.Space;
import app.services.implementation.ClassroomService;
import app.services.implementation.SpaceService;
import app.util.Routes;
import lombok.var;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(Routes.PRINCIPAL_SPACE) // space route
@Slf4j
public class SpaceController {
	
	@Autowired
	private SpaceService spaceService;
	
	@Autowired
	private ClassroomService classroomService;
	
	
	@GetMapping(Routes.ADD_SPACE_QUARTER)
	public String addSpaceQuarter(Space space, Model model)
	{
		log.info("CONTROLLER [SPACE]");	// info console
		log.debug("METHOD [addSpaceQuarter]");	// details console
		
		return "space/insertQuarter"; // go to: pagina de insertar o modificar (space)
	}
	
	@PostMapping(Routes.ADD_SPACE_QUARTER)
	public String addSpaceQuarter(@RequestParam(value="from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
								  @RequestParam(value="till") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTill, Error error)
	{		
		log.info("CONTROLLER [SPACE]");	// info console
		log.debug("METHOD [addSpaceQuarter]");	// details console	
		
		try
		{
			spaceService.fillQuarter(dateFrom.getMonthValue(), dateTill.getMonthValue(), dateFrom.getYear());
		}
		
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return "space/insertQuarter";
		}
		
		return "redirect:/space/spaces"; // go to: home page
	}
		
	@GetMapping(Routes.LIST_SPACE)
	public String spaces(Model model)
	{
		log.info("CONTROLLER [SPACE]");	// info console: Para no perder la pista del controlador (opcional)
		log.debug("METHOD [spaces]");	// details console: Para saber que metodo se esta ejecutando (opcional)
		
		var listSpace = spaceService.findByFree(true);

		// inyeccion Thymeleaf
		model.addAttribute("listSpace", listSpace);

		return "space/listSpace";
	}
	
	@GetMapping(value = Routes.BRING_SPACE_SEARCH)
	public String brigSpaceFree(@RequestParam(value = "free", required = true) boolean free, Model model)
	{		
		var listSpace = spaceService.findByFree(free); // var = Lombok
		
		// inyeccion Thymeleaf
		model.addAttribute("listSpace", listSpace);
		
		return "space/listSpace";
	}

	
	@GetMapping(Routes.ADD_SPACE)
	public String addSpace(Space space, Model model, Building building)
	{
		log.info("CONTROLLER [SPACE]");	// info console
		log.debug("METHOD [addSpace]");	// details console
		
		model.addAttribute("listClassroom", classroomService.findByBuilding_idBuilding(building.getIdBuilding()));
		
		return "space/insert"; // go to: pagina de insertar o modificar (space)
	}
	
	@PostMapping(Routes.ADD_SPACE)
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
	@GetMapping(Routes.EDIT_SPACE) // Al pasarle el parametro {idSpace} lo relaciona con el parametro de Space
	public String editSpace(Space space, Model model)
	{		
		log.info("CONTROLLER [SPACE]");	// info console
		log.debug("METHOD [editSpace]");	// details console
		
		var listClassroom = classroomService.getAll();
		model.addAttribute("listClassroom", listClassroom);
		
		model.addAttribute("space", spaceService.findById(space.getIdSpace())); // Necesario "instanciar" el objeto para ser mostrado en thymeleaf
		
		return "space/modify"; // go to: pagina de insertar o modificar (space)
	}
	
	@PostMapping(Routes.EDIT_POST_SPACE)
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
	@GetMapping(Routes.DELETE) // Relaciona el idSpace en el HTML con el controlador para "apuntar" al correcto
	public String deleteSpace(Space space)
	{		
		log.info("CONTROLLER [SPACE]");		// info console
		log.debug("METHOD [deleteSpace]");	// details console
		
		spaceService.remove(space.getIdSpace()); // remueve el espacio
		
		return "redirect:/space/spaces"; // go to: home page
	}
	
}
