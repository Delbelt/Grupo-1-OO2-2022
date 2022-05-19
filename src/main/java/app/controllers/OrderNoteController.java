 
package app.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import app.entities.OrderNote; 
import app.services.implementation.OrderNoteService; 
import lombok.var;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/orderNote") // OrderNote route
@Slf4j
public class OrderNoteController {
	@Autowired(required=true)
	private OrderNoteService orderNoteService;

	@GetMapping("/orderNotes")
	public String OrderNotes(Model model)
	{
		log.info("CONTROLLER [OrderNote]"); 	// info console: Para no perder la pista del controlador (opcional)
		log.debug("METHOD [OrderNotes]");	// details console: Para saber que metodo se esta ejecutando (opcional)
		
		var listOrderNote = orderNoteService.getAll(); // var = Lombok		
		
		// inyeccion Thymeleaf
		model.addAttribute("listOrderNote", listOrderNote);
		
		return "orderNote/listOrderNote";
	}
	
	@GetMapping("/addOrderNote")
	public String addOrderNote(OrderNote orderNote, Model model)
	{		
		log.info("CONTROLLER [OrderNote]"); // info console
		log.debug("METHOD [addOrderNote]"); // details console
 
		return "orderNote/insert"; // go to: pagina de insertar o modificar (OrderNote)
	}

	@PostMapping("/addOrderNote")
	public String saveOrderNote(@Valid OrderNote orderNote, Errors error) // Inyecta automaticamente al ser metodo <post> busca en: th:action="@{/addOrderNote}" method="post"
	{		
		log.info("CONTROLLER [OrderNote]"); 	// info console
		log.debug("METHOD [saveOrderNote]");	// details console
		
		if(error.hasErrors()) // En caso de un error en las validaciones
		{
			return "orderNote/insert"; // Se queda en la pagina y muestra los errores
		}
		 
		orderNoteService.insertOrUpdate(orderNote); // En caso de que funcione agrega el rol
		
		return "redirect:/orderNote/orderNotes"; // go to: home page
	}
	
	// Type: Path variable
	@GetMapping("/edit/{idOrderNote}") // Al pasarle el parametro {idOrderNote} lo relaciona con el parametro de OrderNote
	public String editOrderNote(OrderNote orderNote, Model model)
	{		
		log.info("CONTROLLER [OrderNote]");	// info console
		log.debug("METHOD [editOrderNote]");	// details console
		
		model.addAttribute("orderNote", orderNoteService.findById(orderNote.getIdOrderNote())); // Necesario "instanciar" el objeto para ser mostrado en Thymeleaf

		return "orderNote/modify"; // go to: pagina de insertar o modificar (role)
	}
	
	@PostMapping("/editOrderNote")
	public String editOrderNote(@Valid OrderNote orderNote, Errors error) // Inyecta automaticamente al ser metodo <post> busca en: th:action="@{/addOrderNote}" method="post"
	{		
		log.info("CONTROLLER [OrderNote]"); 	// info console
		log.debug("METHOD [editOrderNote]");	// details console
		
		if(error.hasErrors()) // En caso de un error en las validaciones
		{
			return "orderNote/modify"; // Se queda en la pagina y muestra los errores
		}
		 
		orderNoteService.insertOrUpdate(orderNote); // En caso de que funcione agrega el rol
		
		return "redirect:/orderNote/orderNotes"; // go to: home page
	}
	
	// Type: Query Parameter
	@GetMapping("/delete") // Relaciona el IdRole en el HTML con el controlador para "apuntar" al correcto
	public String deleteOrderNote(OrderNote orderNote)
	{		
		log.info("CONTROLLER [OrderNote]");		// info console
		log.debug("METHOD [deleteOrderNote]");	// details console
		
		orderNoteService.remove(orderNote.getIdOrderNote()); // remueve el rol
		
		return "redirect:/orderNote/orderNotes"; // go to: home page
	}
}
