package app.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.entities.User;
import app.repositories.IUserRepository;
import app.services.IUserService;

@Service // declare the class as service
public class UserService implements IUserService {
	
	@Autowired() // injection
	private IUserRepository repository;
	
	@Override
	@Transactional(readOnly = true) // no modifica la bd, solamente lectura
	public User findById(int idUser) {
		return repository.findById(idUser).orElse(null);
	}

	@Override
	@Transactional(readOnly = true) // no modifica la bd, solamente lectura
	public List<User> getAll() {
		return repository.findAll();
	}

	@Override
	@Transactional // modifica la BD: commit / rollback
	public boolean insertOrUpdate(User user) {
		return repository.save(user) != null ? true : false;
	}

	@Override
	@Transactional // modifica la BD: commit / rollback
	public boolean remove(int idUser) {
		
		try
		{
			repository.deleteById(idUser); return true;			
		} 
		
		catch (Exception e)
		{
			return false;
		}		
	}
}
