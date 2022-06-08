package app.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import app.entities.Building;
import app.entities.Classroom;
import app.entities.Laboratory;
import app.entities.Traditional;
import app.services.implementation.BuildingService;
import app.services.implementation.ClassroomService;
import app.util.Routes;
import lombok.var;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(Routes.PRINCIPAL_CLASSROOM) // classroom route
@Slf4j
public class ClassroomController {
	
	@Autowired
	private ClassroomService classroomService;

	@Autowired
	private BuildingService buildingService;
	
	@ModelAttribute(Routes.LIST_CLASSROOM)
	public List<Building> getMatter()
	{		
		return buildingService.getAll();
	}
		
	// Type: Query Parameter
	@GetMapping(Routes.DELETE) // Relaciona el IdRole en el HTML con el controlador para "apuntar" al correcto
	public String deleteClassroom(Classroom classroom)
	{		
		log.info("CONTROLLER [CLASSROOM]");		// info console
		log.debug("METHOD [deleteClassroom]");	// details console
		
		// Para redireccionar a la view correspondiente, caso contrario sale como null y no se puede acceder
		int idBuilding = classroomService.findById(classroom.getIdClassroom()).getBuilding().getIdBuilding();			
		
		classroomService.remove(classroom.getIdClassroom()); // remueve el aula
		
		return "redirect:/building/" + idBuilding + "/classrooms"; // go to: home page
	}
	
	// Type: Path variable
	@GetMapping(Routes.EDIT_CLASSROOM) // Al pasarle el parametro {idClassroom} lo relaciona con el parametro de classroom
	public String editClassroom(Classroom classroom, Model model)
	{		
		log.info("CONTROLLER [CLASSROOM]");	// info console
		log.debug("METHOD [editClassroom]");	// details console
			
		var verification = classroomService.findById(classroom.getIdClassroom()); // Para verificar la instancia
	
		// Necesario "instanciar" el objeto para ser mostrado en Thymeleaf
		if(verification instanceof Laboratory)
		{
			model.addAttribute("laboratory", (Laboratory) verification);
			
			return "classroom/modifyLaboratory";
		}
		
		model.addAttribute("traditional", (Traditional) verification);
		
		return "classroom/modifyTraditional"; 
	}
	
	// Trae a la clase por Id y devuelve dependiendo la instancia que sea
	@GetMapping(Routes.BRING_CLASSROOM)
	public String bringInstance(Classroom classroom, Model model) {// Relaciona el Id con el parametro classroom 
		
		log.info("CONTROLLER [CLASSROOM]");	// info console
		log.debug("METHOD [bringInstance]");	// details console
			
		var verification = classroomService.findById(classroom.getIdClassroom()); // Para verificar la instancia
		
		if(verification instanceof Traditional)
		{
			model.addAttribute("classroom", (Traditional) verification);			
			return "classroom/traditional";
		}

		model.addAttribute("classroom", (Laboratory) verification);
		return "classroom/laboratory";
	}
	
	// DAUGHTER CLASS: Laboratory
	
	@GetMapping(Routes.ADD_LABORATORY)
	public String addLaboratory(Laboratory laboratory, Model model, Building building)
	{		
		log.info("CONTROLLER [CLASSROOM]"); // info console
		log.debug("METHOD [addLaboratory]"); // details console
		
		return "classroom/insertLaboratory"; // go to: pagina de insertar o modificar (user)
	}
	
	@PostMapping(Routes.ADD_LABORATORY)
	public String saveLaboratory(@Valid Laboratory laboratory, Errors error, Building building) // Inyecta automaticamente al ser metodo <post> busca en: th:action="@{/addUser}" method="post"
	{		
		log.info("CONTROLLER [CLASSROOM]"); 	// info console
		log.debug("METHOD [saveLaboratory]");	// details console
		
		if(error.hasErrors()) // En caso de un error en las validaciones
		{
			return "classroom/insertLaboratory"; // Se queda en la pagina y muestra los errores
		}
		
		laboratory.setBuilding(buildingService.findById(building.getIdBuilding()));
		
		classroomService.insertOrUpdate(laboratory); // En caso de que funcione agrega el rol     
		
		return "redirect:/building/{idBuilding}/classrooms"; // go to: home page
	}
	
	@PostMapping(Routes.EDIT_LABORATORY)
	public String editLaboratory(@Valid Laboratory laboratory, Errors error, Model model, Building building) // Inyecta automaticamente al ser metodo <post> busca en: th:action="@{/addUser}" method="post"
	{		
		log.info("CONTROLLER [CLASSROOM]"); 	// info console
		log.debug("METHOD [editLaboratory]");	// details console
		
		if(error.hasErrors()) // En caso de un error en las validaciones
		{
			return "classroom/modifyLaboratory"; // Se queda en la pagina y muestra los errores
		}
		
		classroomService.insertOrUpdate(laboratory); // En caso de que funcione agrega el rol     
		
		return "redirect:/building/{idBuilding}/classrooms"; // go to: home page
	}
	
	// DAUGHTER CLASS: Traditional
	
	@GetMapping(Routes.ADD_TRADITIONAL)
	public String addTradional(Traditional traditional, Model model, Building building)
	{		
		log.info("CONTROLLER [CLASSROOM]"); // info console
		log.debug("METHOD [addTradional]"); // details console
		
		return "classroom/insertTraditional"; // go to: pagina de insertar o modificar (Laboratory)
	}
	
	@PostMapping(Routes.ADD_TRADITIONAL)
	public String addTradional(@Valid Traditional traditional, Errors error, Building building) // Inyecta automaticamente al ser metodo <post> busca en: th:action="@{/addUser}" method="post"
	{		
		log.info("CONTROLLER [CLASSROOM]"); 	// info console
		log.debug("METHOD [addTradional]");	// details console
		
		if(error.hasErrors()) // En caso de un error en las validaciones
		{
			return "classroom/insertTraditional"; // Se queda en la pagina y muestra los errores
		}
		
		traditional.setBuilding(buildingService.findById(building.getIdBuilding()));
		
		classroomService.insertOrUpdate(traditional); // En caso de que funcione agrega el aula tradicional
		
		return "redirect:/building/{idBuilding}/classrooms"; // go to: home page
	}
	
	@PostMapping(Routes.EDIT_TRADITIONAL)
	public String editTradional(@Valid Traditional traditional, Errors error, Model model, Building building) // Inyecta automaticamente al ser metodo <post> busca en: th:action="@{/addUser}" method="post"
	{		
		log.info("CONTROLLER [CLASSROOM]"); 	// info console
		log.debug("METHOD [editTradional]");	// details console
		
		if(error.hasErrors()) // En caso de un error en las validaciones
		{
			return "classroom/modifyTraditional"; // Se queda en la pagina y muestra los errores
		}
		
		classroomService.insertOrUpdate(traditional); // En caso de que funcione agrega el aula tradicional
		
		return "redirect:/building/{idBuilding}/classrooms"; // go to: home page
	}	
}
