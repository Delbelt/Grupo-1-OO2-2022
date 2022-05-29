package app.services.implementation;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.entities.OrderNote;
import app.repositories.IOrderNoteRepository;
import app.services.IOrderNoteService;

@Service // declare the class as service
public class OrderNoteService implements IOrderNoteService {

	@Autowired() // injection
	private IOrderNoteRepository repository;

	@Override
	@Transactional(readOnly = true) // no modifica la bd, solamente lectura
	public OrderNote findById(int idOrderNote) {
		return repository.findById(idOrderNote);
	}

	@Override
	@Transactional(readOnly = true) // no modifica la bd, solamente lectura
	public List<OrderNote> getAll() {
		return repository.findAll();
	}

	@Override
	@Transactional // modifica la BD: commit / rollback
	public boolean insertOrUpdate(OrderNote orderNote) {
		
		orderNote.setDateOrderNote(LocalDate.now());
		
		return repository.save(orderNote) != null ? true : false;
	}

	@Override
	@Transactional // modifica la BD: commit / rollback
	public boolean remove(int idOrderNote) {

		try {
			repository.deleteById(idOrderNote);
			return true;
		}

		catch (Exception e) {
			return false;
		}
	}

}
