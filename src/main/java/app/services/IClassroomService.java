package app.services;

import java.util.List;

import app.entities.Classroom;

public interface IClassroomService {
	
	// custom methods for testings
	
	public Classroom findById(int idClassroom); // traer aula por id
	
	public List<Classroom> getAll(); // traer la lista de todas las aulas
	
	public boolean insertOrUpdate(Classroom classroom); // agrega o modifica un aula
	
	public boolean remove(int idClassroom); // elimina un aula
	
	public List<Classroom> findByBuilding_idBuilding(int idBuilding); // Trae la lista de aulas a partir del edificio
}
