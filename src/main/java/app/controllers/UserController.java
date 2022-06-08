package app.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import app.entities.User;
import app.services.implementation.UserRoleService;
import app.services.implementation.UserService;
import app.util.Routes;
import lombok.var;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(Routes.PRINCIPAL_USER) // user route
@Slf4j
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleService roleService; // Necesario para "elegir" los roles
	
	@GetMapping(Routes.LIST_USER)
	public String users(Model model)
	{
		log.info("CONTROLLER [USER]"); 	// info console: Para no perder la pista del controlador (opcional)
		log.debug("METHOD [users]");	// details console: Para saber que metodo se esta ejecutando (opcional)
		
		var listUser = userService.getAll(); // var = Lombok		
		
		// inyeccion Thymeleaf
		model.addAttribute("listUser", listUser);
		
		return "user/listUser";
	}
	
	@GetMapping(Routes.ADD_USER)
	public String addUser(User user, Model model)
	{		
		log.info("CONTROLLER [USER]"); // info console
		log.debug("METHOD [addUser]"); // details console
		
		var listRole = roleService.getAll(); // var = Lombok
		model.addAttribute("listRole", listRole); // Necesario para poder agregar la relacion al crear un user
		
		return "user/insert"; // go to: pagina de insertar o modificar (user)
	}

	@PostMapping(Routes.ADD_USER)
	public String saveUser(@Valid User user, Errors error, Model model) // Inyecta automaticamente al ser metodo <post> busca en: th:action="@{/addUser}" method="post"
	{		
		log.info("CONTROLLER [USER]"); 	// info console
		log.debug("METHOD [saveUser]");	// details console
		
		if(error.hasErrors()) // En caso de un error en las validaciones
		{			
			var listRole = roleService.getAll(); // var = Lombok
			model.addAttribute("listRole", listRole); // Necesario para poder agregar la relacion al crear un user
			return "user/insert"; // Se queda en la pagina y muestra los errores
		}			
		
		userService.insertOrUpdate(user); // En caso de que funcione agrega el rol
		
		return "redirect:/user/users"; // go to: home page
	}
	
	@GetMapping(Routes.BRING_USER_USERNAME) // Path Variable - trae el usuario por el username
	public String findUserRoleByName(User user, Model model) // Relaciona el username del path con el userName del parametro user
	{
		model.addAttribute("user", userService.findByUserName(user.getUserName())); // traigo al usuario
		
		return "user/user";		
	}
	
	// Type: Path variable
	@GetMapping(Routes.EDIT_USER) // Al pasarle el parametro {idUser} lo relaciona con el parametro de User
	public String editUser(User user, Model model)
	{		
		log.info("CONTROLLER [USER]");	// info console
		log.debug("METHOD [editUser]");	// details console
		
		model.addAttribute("user", userService.findById(user.getIdUser())); // Necesario "instanciar" el objeto para ser mostrado en Thymeleaf
		var listRole = roleService.getAll(); // var = Lombok
		model.addAttribute("listRole", listRole); // Necesario para poder agregar la relacion al crear un user
		
		return "user/modify"; // go to: pagina de insertar o modificar (role)
	}
	
	@PostMapping(Routes.EDIT_POST_USER)
	public String editUser(@Valid User user, Errors error, Model model) // Inyecta automaticamente al ser metodo <post> busca en: th:action="@{/editUser}" method="post"
	{		
		log.info("CONTROLLER [USER]"); 	// info console
		log.debug("METHOD [editUser]");	// details console
		
		if(error.hasErrors()) // En caso de un error en las validaciones
		{			
			var listRole = roleService.getAll(); // var = Lombok
			model.addAttribute("listRole", listRole); // Necesario para poder editar la relacion al crear un user
			return "user/modify"; // Se queda en la pagina y muestra los errores
		}		
		
		userService.insertOrUpdate(user); // En caso de que funcione agrega el rol
		
		return "redirect:/user/users"; // go to: users
	}
	
	// Type: Query Parameter
	@GetMapping(Routes.DELETE) // Relaciona el IdRole en el HTML con el controlador para "apuntar" al correcto
	public String deleteUser(User user)
	{		
		log.info("CONTROLLER [USER]");		// info console
		log.debug("METHOD [deleteUser]");	// details console
		
		userService.remove(user.getIdUser()); // remueve el user
		
		return "redirect:/user/users"; // go to: home page
	}
}
