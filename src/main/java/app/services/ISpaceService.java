package app.services;

import java.util.List;

import app.entities.Space;

public interface ISpaceService {

	public Space findById(int idSpace); // traer espacio por ID
	
	public List<Space> getAll(); // traer la lista de espacios
	
	public boolean insertOrUpdate(Space space); // agrega o modifica un espacio
	
	public boolean remove(int idSpace); // elimina un espacio
}
