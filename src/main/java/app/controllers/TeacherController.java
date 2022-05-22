 
package app.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import app.entities.Teacher; 
import app.services.implementation.TeacherService; 
import lombok.var;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/teacher") // Teacher route
@Slf4j
public class TeacherController {
	@Autowired(required=true)
	private TeacherService teacherService;

	@GetMapping("/teachers")
	public String Teachers(Model model)
	{
		log.info("CONTROLLER [Teacher]"); 	// info console: Para no perder la pista del controlador (opcional)
		log.debug("METHOD [Teachers]");	// details console: Para saber que metodo se esta ejecutando (opcional)
		
		var listTeacher = teacherService.getAll(); // var = Lombok		
		
		// inyeccion Thymeleaf
		model.addAttribute("listTeacher", listTeacher);
		
		return "teacher/listTeacher";
	}
	
	@GetMapping("/addTeacher")
	public String addTeacher(Teacher teacher, Model model)
	{		
		log.info("CONTROLLER [Teacher]"); // info console
		log.debug("METHOD [addTeacher]"); // details console
 
		return "teacher/insert"; // go to: pagina de insertar o modificar (Teacher)
	}

	@PostMapping("/addTeacher")
	public String saveTeacher(@Valid Teacher teacher, Errors error) // Inyecta automaticamente al ser metodo <post> busca en: th:action="@{/addTeacher}" method="post"
	{		
		log.info("CONTROLLER [Teacher]"); 	// info console
		log.debug("METHOD [saveTeacher]");	// details console
		
		if(error.hasErrors()) // En caso de un error en las validaciones
		{
			return "teacher/insert"; // Se queda en la pagina y muestra los errores
		}
		 
		teacherService.insertOrUpdate(teacher); // En caso de que funcione agrega el rol
		
		return "redirect:/teacher/teachers"; // go to: home page
	}
	
	// Type: Path variable
	@GetMapping("/edit/{idTeacher}") // Al pasarle el parametro {idTeacher} lo relaciona con el parametro de Teacher
	public String editTeacher(Teacher teacher, Model model)
	{		
		log.info("CONTROLLER [Teacher]");	// info console
		log.debug("METHOD [editTeacher]");	// details console
		
		model.addAttribute("teacher", teacherService.findById(teacher.getIdTeacher())); // Necesario "instanciar" el objeto para ser mostrado en Thymeleaf

		return "teacher/modify"; // go to: pagina de insertar o modificar (role)
	}
	
	@PostMapping("/editTeacher")
	public String editTeacher(@Valid Teacher teacher, Errors error) // Inyecta automaticamente al ser metodo <post> busca en: th:action="@{/addTeacher}" method="post"
	{		
		log.info("CONTROLLER [Teacher]"); 	// info console
		log.debug("METHOD [editTeacher]");	// details console
		
		if(error.hasErrors()) // En caso de un error en las validaciones
		{
			return "teacher/modify"; // Se queda en la pagina y muestra los errores
		}
		 
		teacherService.insertOrUpdate(teacher); // En caso de que funcione agrega el rol
		
		return "redirect:/teacher/teachers"; // go to: home page
	}
	
	// Type: Query Parameter
	@GetMapping("/delete") // Relaciona el IdRole en el HTML con el controlador para "apuntar" al correcto
	public String deleteTeacher(Teacher teacher)
	{		
		log.info("CONTROLLER [Teacher]");		// info console
		log.debug("METHOD [deleteTeacher]");	// details console
		
		teacherService.remove(teacher.getIdTeacher()); // remueve el rol
		
		return "redirect:/teacher/teachers"; // go to: home page
	}
}
