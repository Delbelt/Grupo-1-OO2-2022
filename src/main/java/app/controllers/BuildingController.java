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
import app.services.implementation.BuildingService;
import lombok.var;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/building") // Building route
@Slf4j
public class BuildingController {
	
	@Autowired
	private BuildingService buildingService;

	@GetMapping("/buildings")
	public String Buildings(Model model)
	{
		log.info("CONTROLLER [BUILDING]"); 	// info console: Para no perder la pista del controlador (opcional)
		log.debug("METHOD [Buildings]");	// details console: Para saber que metodo se esta ejecutando (opcional)
		
		var listBuilding = buildingService.getAll(); // var = Lombok		
		
		// inyeccion Thymeleaf
		model.addAttribute("listBuilding", listBuilding);
		
		return "building/listBuilding";
	}
	
	// Trae a la clase y sus aulas
	@GetMapping("/classrooms/{idBuilding}")
	public String bringBuilding(Building building, Model model) { // Relaciona el Id con el parametro
		
		log.info("CONTROLLER [BUILDING]");		// info console
		log.debug("METHOD [bringBuilding]");	// details console
				
		model.addAttribute("building", buildingService.findByIdAndClassrooms(building.getIdBuilding()));
		
		return "building/building";
	}
	
	@GetMapping("/addBuilding")
	public String addBuilding(Building building, Model model)
	{		
		log.info("CONTROLLER [BUILDING]"); // info console
		log.debug("METHOD [addBuilding]"); // details console
 
		return "building/insert"; // go to: pagina de insertar o modificar (Building)
	}

	@PostMapping("/addBuilding")
	public String saveBuilding(@Valid Building building, Errors error) // Inyecta automaticamente al ser metodo <post> busca en: th:action="@{/addBuilding}" method="post"
	{		
		log.info("CONTROLLER [Building]"); 	// info console
		log.debug("METHOD [saveBuilding]");	// details console
		
		if(error.hasErrors()) // En caso de un error en las validaciones
		{
			return "building/insert"; // Se queda en la pagina y muestra los errores
		}
		 
		buildingService.insertOrUpdate(building); // En caso de que funcione agrega el rol
		
		return "redirect:/building/buildings"; // go to: home page
	}
	
	// Type: Path variable
	@GetMapping("/edit/{idBuilding}") // Al pasarle el parametro {idBuilding} lo relaciona con el parametro de Building
	public String editBuilding(Building building, Model model)
	{		
		log.info("CONTROLLER [Building]");	// info console
		log.debug("METHOD [editBuilding]");	// details console
		
		model.addAttribute("building", buildingService.findById(building.getIdBuilding())); // Necesario "instanciar" el objeto para ser mostrado en Thymeleaf

		return "building/modify"; // go to: pagina de insertar o modificar (role)
	}
	
	@PostMapping("/editBuilding")
	public String editBuilding(@Valid Building building, Errors error) // Inyecta automaticamente al ser metodo <post> busca en: th:action="@{/addBuilding}" method="post"
	{		
		log.info("CONTROLLER [Building]"); 	// info console
		log.debug("METHOD [editBuilding]");	// details console
		
		if(error.hasErrors()) // En caso de un error en las validaciones
		{
			return "building/modify"; // Se queda en la pagina y muestra los errores
		}
		 
		buildingService.insertOrUpdate(building); // En caso de que funcione agrega el rol
		
		return "redirect:/building/buildings"; // go to: home page
	}
	
	// Type: Query Parameter
	@GetMapping("/delete") // Relaciona el IdRole en el HTML con el controlador para "apuntar" al correcto
	public String deleteBuilding(Building building)
	{		
		log.info("CONTROLLER [Building]");		// info console
		log.debug("METHOD [deleteBuilding]");	// details console
		
		buildingService.remove(building.getIdBuilding()); // remueve el rol
		
		return "redirect:/building/buildings"; // go to: home page
	}
}
