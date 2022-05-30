package app.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import app.entities.UserRole;
import app.services.implementation.UserRoleService;
import lombok.var;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/role") // role route
@Slf4j
public class UserRoleController {
	
	@Autowired
	private UserRoleService roleService;
	
	@GetMapping("/roles")
	public String roles(Model model)
	{
		log.info("CONTROLLER [ROLE]");	// info console: Para no perder la pista del controlador (opcional)
		log.debug("METHOD [roles]");	// details console: Para saber que metodo se esta ejecutando (opcional)
		
		var listRole = roleService.getAll(); // var = Lombook
		
		// inyeccion Thymeleaf
		model.addAttribute("listRole", listRole);
		
		return "userRole/listRole";
	}
	
	@GetMapping("/addRole")
	public String addRole(UserRole role) // th:object userRole aunque el parametro sea role
	{
		log.info("CONTROLLER [ROLE]");	// info console
		log.debug("METHOD [addRole]");	// details console
		
		return "userRole/insert"; // go to: pagina de insertar o modificar (role)
	}
	
	@PostMapping("/addRole")
	public String saveRole(@Valid UserRole role, Errors error) // Inyecta automaticamente al ser metodo <post> busca en: th:action="@{/addRole}" method="post"	
	{		
		log.info("CONTROLLER [ROLE]");	// info console
		log.debug("METHOD [saveRole]");	// details console
		
		if(error.hasErrors()) // En caso de un error en las validaciones
		{
			return "userRole/insert"; // Se queda en la pagina y muestra los errores
		}
		
		roleService.insertOrUpdate(role); // En caso de que funcione agrega el rol
		
		return "redirect:/role/roles"; // go to: home page
	}
	
	@GetMapping("/role/{idRole}") //Path Variable
	public String findUserRoleById(UserRole role, Model model) // Relaciona el ID del path con el parametro Role
	{
		model.addAttribute("role", roleService.findById(role.getIdRole()));
		
		return "userRole/role";		
	}
	
	// Type: Path variable
	@GetMapping("/edit/{idRole}") // Al pasarle el parametro {idRole} lo relaciona con el parametro de Role
	public String editRole(UserRole userRole, Model model)
	{		
		log.info("CONTROLLER [ROLE]");	// info console
		log.debug("METHOD [editRole]");	// details console
		
		model.addAttribute("userRole", roleService.findById(userRole.getIdRole())); // Necesario "instanciar" el objeto para ser mostrado en thymeleaf
		
		return "userRole/modify"; // go to: pagina de insertar o modificar (role)
	}
	
	@PostMapping("/editRole")
	public String editRole(@Valid UserRole role, Errors error, Model model) // Inyecta automaticamente al ser metodo <post> busca en: th:action="@{/addRole}" method="post"	
	{		
		log.info("CONTROLLER [ROLE]");	// info console
		log.debug("METHOD [editRole]");	// details console
		
		if(error.hasErrors()) // En caso de un error en las validaciones
		{
			return "userRole/modify"; // Se queda en la pagina y muestra los errores
		}
		
		roleService.insertOrUpdate(role); // En caso de que funcione agrega el rol
		
		return "redirect:/role/roles"; // go to: home page
	}
	
	// Type: Query Parameter
	@GetMapping("/delete") // Relaciona el IdRole en el HTML con el controlador para "apuntar" al correcto
	public String deleteRole(UserRole role)
	{		
		log.info("CONTROLLER [ROLE]");		// info console
		log.debug("METHOD [deleteRole]");	// details console
		
		roleService.remove(role.getIdRole()); // remueve el rol
		
		return "redirect:/role/roles"; // go to: home page
	}
}
