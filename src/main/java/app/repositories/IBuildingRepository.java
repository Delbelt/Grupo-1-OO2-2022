package app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.entities.Building;

public interface IBuildingRepository extends JpaRepository<Building, Integer> {
	
	@Query("FROM Building b inner join fetch b.classrooms where b.idBuilding=(:idBuilding)")
	public Building findByIdAndClassrooms(int idBuilding); // Para traer el edificio con sus respectivas aulas
}
