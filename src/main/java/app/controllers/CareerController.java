package app.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import app.entities.Career;
import app.services.implementation.CareerService;
import app.services.implementation.DepartmentService;
import lombok.var;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/career") // classroom route
@Slf4j
public class CareerController {

	@Autowired(required=true)
	private CareerService careerService;

	@Autowired(required=true)
	private DepartmentService departmentService;
	
	@GetMapping("/careers")
	public String careers(Model model)
	{		
		log.info("CONTROLLER [CAREER]");	// info console: Para no perder la pista del controlador (opcional)
		log.debug("METHOD [careers]");	// details console: Para saber que metodo se esta ejecutando (opcional)
		
		var listCareer = careerService.getAll(); // Variable de Lombook
		
		model.addAttribute("listCareer", listCareer); // Agrega el atributo a la pantalla de inicio
		
		return "career/listCareer";
	}
	
	@GetMapping("/addCareer")
	public String addCareer(Career career, Model model)
	{
		log.info("CONTROLLER [CAREER]");	// info console
		log.debug("METHOD [addCareer]");	// details console
		
		var listDepartment = departmentService.getAll();
		model.addAttribute("listDepartment", listDepartment);
		
		return "career/insert"; // go to: pagina de insertar o modificar (space)
	}
	
	@PostMapping("/addCareer")
	public String saveCareer(@Valid Career career, Errors error, Model model) // Inyecta automaticamente al ser metodo <post> busca en: th:action="@{/addRole}" method="post"	
	{		
		log.info("CONTROLLER [CAREER]");	// info console
		log.debug("METHOD [saveCareer]");	// details console
		
		if(error.hasErrors()) // En caso de un error en las validaciones
		{
			var listDepartment = departmentService.getAll();
			model.addAttribute("listDepartment", listDepartment); 
			return "career/insert"; // Se queda en la pagina y muestra los errores
		}
		
		careerService.insertOrUpdate(career); // En caso de que funcione agrega el espacio
		
		return "redirect:/career/careers"; // go to: home page
	}
	
	// Type: Query Parameter
	@GetMapping("/delete") // Relaciona el IdRole en el HTML con el controlador para "apuntar" al correcto
	public String deleteCareer(Career career)
	{		
		log.info("CONTROLLER [CAREER]");		// info console
		log.debug("METHOD [deleteCareer]");	// details console
			
		careerService.remove(career.getIdCareer()); // remueve el rol
			
		return "redirect:/career/careers"; // go to: home page
	}	
	

	// Type: Path variable
	@GetMapping("/edit/{idCareer}") // Al pasarle el parametro {idSpace} lo relaciona con el parametro de Space
	public String editCareer(Career career, Model model) {		
		log.info("CONTROLLER [CAREER]");	// info console
		log.debug("METHOD [editCareer]");	// details console
			
		var listDepartment = departmentService.getAll();
		model.addAttribute("listDepartment", listDepartment);
			
		model.addAttribute("career", careerService.findById(career.getIdCareer())); // Necesario "instanciar" el objeto para ser mostrado en thymeleaf
			
		return "career/modify"; // go to: pagina de insertar o modificar (space)
	}
	
	@PostMapping("/editCareer")
	public String editCareer(@Valid Career career, Errors error, Model model) // Inyecta automaticamente al ser metodo <post> busca en: th:action="@{/addRole}" method="post"	
	{		
		log.info("CONTROLLER [CAREER]");	// info console
		log.debug("METHOD [editCareer]");	// details console
		
		if(error.hasErrors()) // En caso de un error en las validaciones
		{
			var listDepartment = departmentService.getAll();
			model.addAttribute("listDepartment", listDepartment);
			
			return "career/modify"; // Se queda en la pagina y muestra los errores
		}
		
		careerService.insertOrUpdate(career); // En caso de que funcione agrega el espacio
		
		return "redirect:/career/careers"; // go to: home page
	}
}
