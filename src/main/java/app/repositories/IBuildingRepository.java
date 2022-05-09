package app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import app.entities.Building;

public interface IBuildingRepository extends JpaRepository<Building, Integer> {
 
}
