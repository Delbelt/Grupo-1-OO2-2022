
package app.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.entities.Matter;
import app.repositories.IMatterRepository;
import app.services.IMatterService;

@Service // declare the class as service
public class MatterService implements IMatterService {

	@Autowired() // injection
	private IMatterRepository repository;

	@Override
	@Transactional(readOnly = true) // no modifica la bd, solamente lectura
	public Matter findById(int idMatter) {
		return repository.findById(idMatter).orElse(null);
	}

	@Override
	@Transactional(readOnly = true) // no modifica la bd, solamente lectura
	public List<Matter> getAll() {
		return repository.findAll();
	}

	@Override
	@Transactional // modifica la BD: commit / rollback
	public boolean insertOrUpdate(Matter matter) {
		return repository.save(matter) != null ? true : false;
	}

	@Override
	@Transactional // modifica la BD: commit / rollback
	public boolean remove(int idMatter) {

		try {
			repository.deleteById(idMatter);
			return true;
		}

		catch (Exception e) {
			return false;
		}
	}

}
