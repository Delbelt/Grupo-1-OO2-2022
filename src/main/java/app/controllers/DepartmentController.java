package app.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import app.entities.Department;
import app.services.implementation.DepartmentService;
import lombok.var;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/department") // classroom route
@Slf4j
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;
	
	@GetMapping("/departments")
	public String departments(Model model)
	{		
		log.info("CONTROLLER [DEPARTMENT]");	// info console: Para no perder la pista del controlador (opcional)
		log.debug("METHOD [departments]");	// details console: Para saber que metodo se esta ejecutando (opcional)
		
		var listDepartment = departmentService.getAll(); // Variable de Lombook
		
		model.addAttribute("listDepartment", listDepartment); // Agrega el atributo a la pantalla de inicio
		
		return "department/listDepartment";
	}
	
	@GetMapping("/addDepartment")
	public String addDepartment(Department department, Model model)
	{
		log.info("CONTROLLER [DEPARTMENT]");	// info console
		log.debug("METHOD [addDepartment]");	// details console
		
		return "department/insert"; // go to: pagina de insertar o modificar (space)
	}
	
	@PostMapping("/addDepartment")
	public String saveDepartment(@Valid Department department, Errors error, Model model) // Inyecta automaticamente al ser metodo <post> busca en: th:action="@{/addRole}" method="post"	
	{		
		log.info("CONTROLLER [DEPARTMENT]");	// info console
		log.debug("METHOD [saveDepartment]");	// details console
		
		if(error.hasErrors()) // En caso de un error en las validaciones
		{
			return "career/insert"; // Se queda en la pagina y muestra los errores
		}
		
		departmentService.insertOrUpdate(department); // En caso de que funcione agrega el espacio
		
		return "redirect:/department/departments"; // go to: home page
	}
	
	// Type: Query Parameter
	@GetMapping("/delete") // Relaciona el IdRole en el HTML con el controlador para "apuntar" al correcto
	public String deleteDepartment(Department department) {		
		log.info("CONTROLLER [DEPARTMENT]");		// info console
		log.debug("METHOD [deleteDepartment]");	// details console
				
		departmentService.remove(department.getIdDepartment()); // remueve el rol
				
		return "redirect:/department/departments"; // go to: home page
	}	
	
	// Type: Path variable
	@GetMapping("/edit/{idDepartment}") // Al pasarle el parametro {idDepartment} lo relaciona con el parametro de Department
	public String editDepartment(Department department, Model model) {		
		log.info("CONTROLLER [DEPARTMENT]");	// info console
		log.debug("METHOD [editDepartment]");	// details console
			
		model.addAttribute("department", departmentService.findById(department.getIdDepartment())); // Necesario "instanciar" el objeto para ser mostrado en thymeleaf
				
		return "department/modify"; // go to: pagina de insertar o modificar (space)
	}
	
	@PostMapping("/editDepartment")
	public String editDepartment(@Valid Department department, Errors error, Model model) // Inyecta automaticamente al ser metodo <post> busca en: th:action="@{/addRole}" method="post"	
	{		
		log.info("CONTROLLER [DEPARTMENT]");	// info console
		log.debug("METHOD [editDepartment]");	// details console
		
		if(error.hasErrors()) { // En caso de error en validaciones
			return "department/modify"; // Se queda en la pagina y muestra los errores
		}
		
		departmentService.insertOrUpdate(department); // En caso de que funcione agrega el espacio
		
		return "redirect:/department/departments"; // go to: home page
	}
	
}
