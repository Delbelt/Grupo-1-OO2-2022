package app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.entities.Classroom;

public interface IClassroomRepository extends JpaRepository<Classroom, Integer>  {
	
	@Query("FROM Classroom cl WHERE cl.idClassroom = (:idClassroom)") // El metodo del JpaRepository NO trae las hijas
	public Classroom findById(int idClassroom);
	
	public List<Classroom> findByBuilding_idBuilding(int idBuilding);
}
