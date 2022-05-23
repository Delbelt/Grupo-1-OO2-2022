package app.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.entities.Classroom;
import app.repositories.IClassroomRepository;
import app.services.IClassroomService;

@Service
public class ClassroomService implements IClassroomService {
	
	@Autowired() // injection
	private IClassroomRepository repository;
	
	@Override
	@Transactional(readOnly = true) // no modifica la bd, solamente lectura
	public Classroom findById(int idClassroom)
	{
		return repository.findById(idClassroom);
	}

	@Override
	@Transactional(readOnly = true) // no modifica la bd, solamente lectura
	public List<Classroom> getAll() {
		return repository.findAll();
	}	

	@Override
	@Transactional(readOnly = true) // no modifica la bd, solamente lectura
	public List<Classroom> findByBuilding_idBuilding(int idBuilding) {
		return repository.findByBuilding_idBuilding(idBuilding);
	}

	@Override
	@Transactional // modifica la BD: commit / rollback
	public boolean insertOrUpdate(Classroom classroom) {
		return repository.save(classroom) != null ? true : false;
	}

	@Override
	@Transactional // modifica la BD: commit / rollback
	public boolean remove(int idClassroom) {
		
		try
		{
			repository.deleteById(idClassroom); return true;			
		} 
		
		catch (Exception e)
		{
			return false;
		}
	}
}
