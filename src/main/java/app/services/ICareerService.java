package app.services;

import java.util.List;
import app.entities.Career;

public interface ICareerService {

	public Career findById(int idCareer); // traer carrera por ID
	
	public List<Career> getAll(); // traer la lista de carreras de la universidad
	
	public boolean insertOrUpdate(Career career); // agrega o modifica una carrera
	
	public boolean remove(int idCareer); // elimina una carrera
	
}
