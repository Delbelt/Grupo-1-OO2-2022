package app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.entities.OrderNote;

public interface IOrderNoteRepository extends JpaRepository<OrderNote, Integer> {

	@Query("FROM OrderNote o WHERE o.idOrderNote = (:idOrderNote)") // El metodo del JpaRepository NO trae las hijas
	public OrderNote findById(int idOrderNote);
}
