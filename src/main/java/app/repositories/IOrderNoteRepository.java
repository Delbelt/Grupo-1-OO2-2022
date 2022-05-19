package app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import app.entities.OrderNote;

public interface IOrderNoteRepository extends JpaRepository<OrderNote, Integer> {

}
