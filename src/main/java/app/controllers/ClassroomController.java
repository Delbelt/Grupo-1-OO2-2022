package app.controllers;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import app.entities.Classroom;
import app.entities.Laboratory;
import app.entities.Traditional;
import app.services.implementation.BuildingService;
import app.services.implementation.ClassroomService;
import lombok.var;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/classroom") // classroom route
@Slf4j
public class ClassroomController {
	
	@Autowired(required=true)
	private ClassroomService classroomService;

	@Autowired(required=true)
	private BuildingService buildingService;
	
	@GetMapping("/classrooms")
	public String classrooms(Model model)
	{		
		log.info("CONTROLLER [CLASSROOM]");	// info console: Para no perder la pista del controlador (opcional)
		log.debug("METHOD [classrooms]");	// details console: Para saber que metodo se esta ejecutando (opcional)
		
		var listClassroom = classroomService.getAll(); // Variable de Lombook
		
		model.addAttribute("listClassroom", listClassroom); // Agrega el atributo a la pantalla de inicio
		
		return "classroom/listClassroom";
	}
	
	
	// Type: Query Parameter
	@GetMapping("/delete") // Relaciona el IdRole en el HTML con el controlador para "apuntar" al correcto
	public String deleteClassroom(Classroom classroom)
	{		
		log.info("CONTROLLER [CLASSROOM]");		// info console
		log.debug("METHOD [deleteClassroom]");	// details console
		
		classroomService.remove(classroom.getIdClassroom()); // remueve el rol
		
		return "redirect:/classroom/classrooms"; // go to: home page
	}	
	
	// Type: Path variable
	@GetMapping("/edit/{idClassroom}") // Al pasarle el parametro {idClassroom} lo relaciona con el parametro de classroom
	public String editClassroom(Classroom classroom, Model model)
	{		
		log.info("CONTROLLER [CLASSROOM]");	// info console
		log.debug("METHOD [editClassroom]");	// details console
			
		var verification = classroomService.findById(classroom.getIdClassroom()); // Para verificar la instancia
	
		// Necesario "instanciar" el objeto para ser mostrado en Thymeleaf
		if(verification instanceof Laboratory) {
			model.addAttribute("laboratory", (Laboratory) verification);
			return "classroom/insertOrUpdateLaboratory";
		}
		
		model.addAttribute("traditional", (Traditional) verification); 
		return "classroom/insertOrUpdateTraditional"; 
	}
	
	// Trae a la clase por Id y devuelve dependiendo la instancia que sea
	@GetMapping("/classroom/{idClassroom}")
	public String bringInstance(Classroom classroom, Model model) {// Relaciona el Id con el parametro classroom 
		
		log.info("CONTROLLER [CLASSROOM]");	// info console
		log.debug("METHOD [bringInstance]");	// details console
			
		var verification = classroomService.findById(classroom.getIdClassroom()); // Para verificar la instancia
		
		if(verification instanceof Traditional) {
			model.addAttribute("classroom", (Traditional) verification);
			return "classroom/traditional";
		}

		model.addAttribute("classroom", (Laboratory) verification);
		return "classroom/laboratory";
	}
	
	// DAUGHTER CLASS: Laboratory
	
	@GetMapping("/addLaboratory")
	public String addLaboratory(Laboratory laboratory, Model model)
	{		
		log.info("CONTROLLER [CLASSROOM]"); // info console
		log.debug("METHOD [addLaboratory]"); // details console
		
		var listBuilding = buildingService.getAll();
		model.addAttribute("listBuilding", listBuilding);
		
		return "classroom/insertOrUpdateLaboratory"; // go to: pagina de insertar o modificar (user)
	}
	
	@PostMapping("/addLaboratory")
	public String saveLaboratory(@Valid Laboratory laboratory, Errors error) // Inyecta automaticamente al ser metodo <post> busca en: th:action="@{/addUser}" method="post"
	{		
		log.info("CONTROLLER [CLASSROOM]"); 	// info console
		log.debug("METHOD [saveLaboratory]");	// details console
		
		if(error.hasErrors()) // En caso de un error en las validaciones
		{
			return "classroom/insertOrUpdateLaboratory"; // Se queda en la pagina y muestra los errores
		}
		
		classroomService.insertOrUpdate(laboratory); // En caso de que funcione agrega el rol     
		
		return "redirect:/classroom/classrooms"; // go to: home page
	}
	
	// DAUGHTER CLASS: Traditional
	
	@GetMapping("/addTraditional")
	public String addTradional(Traditional traditional, Model model)
	{		
		log.info("CONTROLLER [CLASSROOM]"); // info console
		log.debug("METHOD [addTradional]"); // details console
		
		var listBuilding = buildingService.getAll();
		model.addAttribute("listBuilding", listBuilding);
		
		return "classroom/insertOrUpdateTraditional"; // go to: pagina de insertar o modificar (Laboratory)
	}
	
	@PostMapping("/addTraditional")
	public String addTradional(@Valid Traditional traditional, Errors error) // Inyecta automaticamente al ser metodo <post> busca en: th:action="@{/addUser}" method="post"
	{		
		log.info("CONTROLLER [CLASSROOM]"); 	// info console
		log.debug("METHOD [addTradional]");	// details console
		
		if(error.hasErrors()) // En caso de un error en las validaciones
		{
			return "classroom/insertOrUpdateTraditional"; // Se queda en la pagina y muestra los errores
		}
		classroomService.insertOrUpdate(traditional); // En caso de que funcione agrega el aula tradicional
		
		return "redirect:/classroom/classrooms"; // go to: home page
	}
	
}
