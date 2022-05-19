package app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import app.entities.Career;

public interface ICareerRepository extends JpaRepository<Career, Integer> {

}
