package app.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.entities.Space;
import app.repositories.ISpaceRepository;
import app.services.ISpaceService;

@Service
public class SpaceService implements ISpaceService {

	@Autowired() // injection
	private ISpaceRepository repository;
	
	@Override
	@Transactional(readOnly = true) // no modifica la bd, solamente lectura
	public Space findById(int idSpace) {
		return repository.findById(idSpace).orElse(null);
	}

	@Override
	@Transactional(readOnly = true) // no modifica la bd, solamente lectura
	public List<Space> getAll() {
		return repository.findAll();
	}

	@Override
	@Transactional // modifica la BD: commit / rollback
	public boolean insertOrUpdate(Space space) {
		return repository.save(space) != null ? true : false;
	}

	@Override
	@Transactional // modifica la BD: commit / rollback
	public boolean remove(int idSpace) {
		try {
			repository.deleteById(idSpace); 
			return true;			
		} catch(Exception e) {
			return false;
		}	
	}
}
