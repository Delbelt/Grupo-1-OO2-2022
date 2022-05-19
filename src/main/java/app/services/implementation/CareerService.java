package app.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.entities.Career;
import app.repositories.ICareerRepository;
import app.services.ICareerService;

@Service
public class CareerService implements ICareerService {

	@Autowired() // injection
	private ICareerRepository repository;

	@Override
	@Transactional(readOnly = true) // no modifica la bd, solamente lectura
	public Career findById(int idCareer) {
		return repository.findById(idCareer).orElse(null);
	}

	@Override
	@Transactional(readOnly = true) // no modifica la bd, solamente lectura
	public List<Career> getAll() {
		return repository.findAll();
	}

	@Override
	@Transactional() // no modifica la bd, solamente lectura
	public boolean insertOrUpdate(Career career) {
		return repository.save(career) != null ? true : false;
	}

	@Override
	@Transactional() // no modifica la bd, solamente lectura
	public boolean remove(int idCareer) {
		try {
			repository.deleteById(idCareer); 
			return true;			
		} catch(Exception e) {
			return false;
		}	
	}
}
