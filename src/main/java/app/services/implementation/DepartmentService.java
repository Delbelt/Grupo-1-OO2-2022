package app.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.entities.Department;
import app.services.IDepartmentService;
import app.repositories.IDepartmentRepository;

@Service
public class DepartmentService implements IDepartmentService {

	@Autowired() // injection
	private IDepartmentRepository repository;
	
	@Override
	@Transactional(readOnly = true) // no modifica la bd, solamente lectura
	public Department findById(int idDepartment) {
		return repository.findById(idDepartment).orElse(null);
	}

	@Override
	@Transactional(readOnly = true) // no modifica la bd, solamente lectura
	public List<Department> getAll() {
		return repository.findAll();
	}

	@Override
	@Transactional // modifica la BD: commit / rollback
	public boolean insertOrUpdate(Department department) {
		return repository.save(department) != null ? true : false;
	}

	@Override
	@Transactional // modifica la BD: commit / rollback
	public boolean remove(int idDepartment) {
		try {
			repository.deleteById(idDepartment); 
			return true;			
		} catch(Exception e) {
			return false;
		}	
	}
}
