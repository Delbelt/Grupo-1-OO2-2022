
package app.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.entities.Teacher;
import app.repositories.ITeacherRepository; 
import app.services.ITeacherService;

@Service // declare the class as service
public class TeacherService implements ITeacherService {

	@Autowired() // injection
	private ITeacherRepository repository;

	@Override
	@Transactional(readOnly = true) // no modifica la bd, solamente lectura
	public Teacher findById(int idTeacher) {
		return repository.findById(idTeacher).orElse(null);
	}

	@Override
	@Transactional(readOnly = true) // no modifica la bd, solamente lectura
	public List<Teacher> getAll() {
		return repository.findAll();
	}

	@Override
	@Transactional // modifica la BD: commit / rollback
	public boolean insertOrUpdate(Teacher teacher) {

		return repository.save(teacher) != null ? true : false;
	}

	@Override
	@Transactional // modifica la BD: commit / rollback
	public boolean remove(int idTeacher) {

		try {
			repository.deleteById(idTeacher);
			return true;
		}

		catch (Exception e) {
			return false;
		}
	}
}
