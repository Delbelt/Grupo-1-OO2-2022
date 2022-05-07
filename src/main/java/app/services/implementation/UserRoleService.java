package app.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.entities.UserRole;
import app.repositories.IUserRoleRepository;
import app.services.IUserRoleService;

@Service // declare the class as service
public class UserRoleService implements IUserRoleService {
	
	@Autowired() // injection
	private IUserRoleRepository repository;

	@Override
	@Transactional(readOnly = true) // no modifica la bd, solamente lectura
	public UserRole findById(int idRole) {
		return repository.findById(idRole).orElse(null);
	}

	@Override
	@Transactional(readOnly = true) // NO modifica la BD: read only
	public List<UserRole> getAll() {
		return repository.findAll();
	}

	@Override
	@Transactional // modifica la BD: commit / rollback
	public boolean insertOrUpdate(UserRole role) {
		return repository.save(role) != null ? true : false;
	}

	@Override
	@Transactional // modifica la BD: commit / rollback
	public boolean remove(int idRole) {		
		
		try
		{
			repository.deleteById(idRole); return true;			
		} 
		
		catch (Exception e)
		{
			return false;
		}	
	}
}
