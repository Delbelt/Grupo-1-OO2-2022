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

import app.entities.Classroom;
import app.entities.Day;
import app.entities.Matter;
import app.entities.OrderNote;
import app.entities.Quarter;
import app.entities.Teacher;
import app.services.implementation.ClassroomService;
import app.services.implementation.MatterService;
import app.services.implementation.OrderNoteService;
import app.services.implementation.SpaceService;
import app.services.implementation.TeacherService;
import lombok.var;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/orderNote") // OrderNote route
@Slf4j
public class OrderNoteController {
	
	@Autowired
	private OrderNoteService orderNoteService;

	@Autowired
	private ClassroomService classroomService;

	@Autowired
	private MatterService matterService;

	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private SpaceService spaceService;
	
	@ModelAttribute("listMatter")
	public List<Matter> getMatter()
	{		
		return matterService.getAll();
	}
	
	@ModelAttribute("listTeacher")
	public List<Teacher> getTeacher()
	{		
		return teacherService.getAll();
	}
	
	@ModelAttribute("listClassroom")
	public List<Classroom> getClassroom()
	{
		return classroomService.getAll();
	}
	
	@GetMapping("/orderNotes")
	public String OrderNotes(Model model)
	{
		log.info("CONTROLLER [OrderNote]"); // info console: Para no perder la pista del controlador (opcional)
		log.debug("METHOD [OrderNotes]");	// details console: Para saber que metodo se esta ejecutando (opcional)
		
		var listOrderNote = orderNoteService.getAll(); // var = Lombok		
		
		// inyeccion Thymeleaf
		model.addAttribute("listOrderNote", listOrderNote);
		
		return "ordernote/listOrderNote";
	}
	
	// Type: Path variable
	@GetMapping("/edit/{idOrderNote}") // Al pasarle el parametro {idOrderNote} lo relaciona con el parametro de OrderNote
	public String editOrderNote(OrderNote orderNote, Model model)
	{		
		log.info("CONTROLLER [OrderNote]");	// info console
		log.debug("METHOD [editOrderNote]");	// details console
		
		var verification = orderNoteService.findById(orderNote.getIdOrderNote()); // Para verificar la instancia
				
		if(verification instanceof Day)
		{			
			model.addAttribute("day", (Day) orderNoteService.findById(orderNote.getIdOrderNote())); // Necesario "instanciar" el objeto para ser mostrado en Thymeleaf

			return "ordernote/modifyDayOrder"; // go to: pagina de insertar o modificar)
		}
		
		model.addAttribute("quarter", (Quarter) orderNoteService.findById(orderNote.getIdOrderNote())); // Necesario "instanciar" el objeto para ser mostrado en Thymeleaf

		return "ordernote/modifyQuarterOrder"; // go to: pagina de insertar o modificar (role)
	}
		
	// Type: Query Parameter
	@GetMapping("/delete") // Relaciona el IdRole en el HTML con el controlador para "apuntar" al correcto
	public String deleteOrderNote(OrderNote orderNote)
	{		
		log.info("CONTROLLER [OrderNote]");		// info console
		log.debug("METHOD [deleteOrderNote]");	// details console
		
		var verification = orderNoteService.findById(orderNote.getIdOrderNote()); // Para verificar la instancia
		
		// Para volver a "liberar" esos espacios
		if(verification instanceof Day)
		{			
			try
			{
				spaceService.changeSpace(((Day) verification).getDate(), verification.getShift(), verification.getClassroom(), true);
			}
			
			catch (Exception e)
			{
				System.out.println(e.getMessage());
			}
		}
		
		else if(verification instanceof Quarter)
		{
			try
			{
				spaceService.changeSpaceQuarter((Quarter) verification, true);
			}
			catch (Exception e)
			{
				System.out.println(e.getMessage());
			}
		}		
		
		orderNoteService.remove(orderNote.getIdOrderNote()); // remueve el rol
		
		return "redirect:/orderNote/orderNotes"; // go to: home page
	}
	
	// Trae a la clase por Id y devuelve dependiendo la instancia que sea
	@GetMapping("/orderNote/{idOrderNote}")
	public String bringInstance(OrderNote orderNote, Model model) {// Relaciona el Id con el parametro classroom 
		
		log.info("CONTROLLER [ORDERNOTE]");		// info console
		log.debug("METHOD [bringInstance]");	// details console
			
		var verification = orderNoteService.findById(orderNote.getIdOrderNote()); // Para verificar la instancia
		
		if(verification instanceof Day)
		{
			model.addAttribute("dayOrder", (Day) verification);			
			return "ordernote/day";
		}

		model.addAttribute("quarterOrder", (Quarter) verification);
		return "ordernote/quarter";
	}
	
	// DAUGHTER CLASS: DAY
	
	@GetMapping("/addDayOrder")
	public String addDayOrder(Day day, Model model)
	{		
		log.info("CONTROLLER [ORDERNOTE]"); // info console
		log.debug("METHOD [addDayOrder]"); // details console
		 
		return "ordernote/insertDayOrder"; // go to: pagina de insertar o modificar (OrderNote)
	}

	@PostMapping("/addDayOrder")
	public String saveDayOrder(@Valid Day day, Errors error, Model model) // Inyecta automaticamente al ser metodo <post> busca en: th:action="@{/addOrderNote}" method="post"
	{		
		log.info("CONTROLLER [ORDERNOTE]"); 	// info console
		log.debug("METHOD [saveDayOrder]");	// details console
		
		if(error.hasErrors()) // En caso de un error en las validaciones
		{			
			return "ordernote/insertDayOrder"; // Se queda en la pagina y muestra los errores
		}
		
		try
		{			
			spaceService.changeSpace(day.getDate(), day.getShift(), day.getClassroom(), false);
			orderNoteService.insertOrUpdate(day);			
		}
		catch (Exception e)
		{
			model.addAttribute("Exception", e.getMessage());
			return "ordernote/insertDayOrder";
		}				
		
		return "redirect:/orderNote/orderNotes"; // go to: home page
	}
	
	@PostMapping("/editDayOrder")
	public String editDayOrder(@Valid Day day, Errors error, Model model) // Inyecta automaticamente al ser metodo <post> busca en: th:action="@{/addOrderNote}" method="post"
	{		
		log.info("CONTROLLER [OrderNote]"); 	// info console
		log.debug("METHOD [editDayOrder]");	// details console
		
		if(error.hasErrors()) // En caso de un error en las validaciones
		{			
			return "ordernote/modifyDayOrder"; // Se queda en la pagina y muestra los errores
		}
		 
		orderNoteService.insertOrUpdate(day); // En caso de que funcione agrega el rol
		
		return "redirect:/orderNote/orderNotes"; // go to: home page
	}
	
	// DAUGHTER CLASS: QUARTER
	
	@GetMapping("/addQuarterOrder")
	public String addQuarterOrder(Quarter quarter, Model model)
	{		
		log.info("CONTROLLER [ORDERNOTE]"); // info console
		log.debug("METHOD [addQuarterOrder]"); // details console
  
		return "ordernote/insertQuarterOrder"; // go to: pagina de insertar o modificar (OrderNote)
	}

	@PostMapping("/addQuarterOrder")
	public String saveQuarterOrder(@Valid Quarter quarter, Errors error, Model model)
	{		
		log.info("CONTROLLER [ORDERNOTE]"); 	// info console
		log.debug("METHOD [saveQuarterOrder]");		// details console
		
		if(error.hasErrors()) // En caso de un error en las validaciones
		{
			return "ordernote/insertQuarterOrder"; // Se queda en la pagina y muestra los errores
		}
		
		try
		{
			spaceService.changeSpaceQuarter(quarter, false);
			orderNoteService.insertOrUpdate(quarter);			
		}
		catch (Exception e)
		{
			model.addAttribute("Exception", e.getMessage());
			return "ordernote/insertQuarterOrder";
		}		
		
		
		return "redirect:/orderNote/orderNotes";
	}
	
	@PostMapping("/editQuarterOrder")
	public String editQuarterOrder(@Valid Quarter quarter, Errors error, Model model) // Inyecta automaticamente al ser metodo <post> busca en: th:action="@{/addOrderNote}" method="post"
	{		
		log.info("CONTROLLER [ORDERNOTE]"); 	// info console
		log.debug("METHOD [editQuarterOrder]");	// details console
		
		if(error.hasErrors()) // En caso de un error en las validaciones
		{
			return "ordernote/modifyQuarterOrder"; // Se queda en la pagina y muestra los errores
		}
		 
		orderNoteService.insertOrUpdate(quarter); // En caso de que funcione agrega el rol
		
		return "redirect:/orderNote/orderNotes"; // go to: home page
	}
}
