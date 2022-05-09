package app.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.entities.Building;
import app.repositories.IBuildingRepository;
import app.services.IBuildingService;
 
@Service // declare the class as service
public class BuildingService  implements IBuildingService {

	@Autowired() // injection
	private IBuildingRepository repository;
	
	@Override
	@Transactional(readOnly = true) // no modifica la bd, solamente lectura
	public Building findById(int idBuilding) {
		return repository.findById(idBuilding).orElse(null);
	}

	@Override
	@Transactional(readOnly = true) // no modifica la bd, solamente lectura
	public List<Building> getAll() {
		return repository.findAll();
	}

	@Override
	@Transactional // modifica la BD: commit / rollback
	public boolean insertOrUpdate(Building building) {
		return repository.save(building) != null ? true : false;
	}

	@Override
	@Transactional // modifica la BD: commit / rollback
	public boolean remove(int idBuilding) {
		
		try
		{
			repository.deleteById(idBuilding); return true;			
		} 
		
		catch (Exception e)
		{
			return false;
		}		
	}

}
