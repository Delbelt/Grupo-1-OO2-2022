package app.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import app.entities.Classroom;
import app.services.implementation.ClassroomService;
import lombok.var;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/classroom") // classroom route
@Slf4j
public class ClassroomController {
	
	@Autowired(required=true)
	private ClassroomService classroomService;
	
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
	

}
