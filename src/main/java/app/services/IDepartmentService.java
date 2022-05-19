package app.services;

import java.util.List;
import app.entities.Department;

public interface IDepartmentService {

	public Department findById(int idDepartment); // traer departamento por ID
	
	public List<Department> getAll(); // traer la lista de todos los departamentos
	
	public boolean insertOrUpdate(Department department); // agrega o modifica un departamento
	
	public boolean remove(int idDepartment); // elimina un departamento
	
}
