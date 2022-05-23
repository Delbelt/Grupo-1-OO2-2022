package app.services;

import java.util.List;

import app.entities.Building;

public interface IBuildingService {

	public Building findById(int idBuilding);

	public List<Building> getAll();

	public boolean insertOrUpdate(Building building);

	public boolean remove(int idBuilding);
	
	public Building findByIdAndClassrooms(int idBuilding); // Trae el edificio y sus aulas
}
