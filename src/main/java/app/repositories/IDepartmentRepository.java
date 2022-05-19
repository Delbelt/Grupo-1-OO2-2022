package app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import app.entities.Department;

public interface IDepartmentRepository extends JpaRepository<Department, Integer> {

}
