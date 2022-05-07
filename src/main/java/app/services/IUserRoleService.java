package app.services;

import java.util.List;

import app.entities.UserRole;

public interface IUserRoleService {
	
	// custom methods for testings
	
	public UserRole findById(int idRole); // traer rol por id
	
	public List<UserRole> getAll(); // traer la lista de todos los roles
	
	public boolean insertOrUpdate(UserRole role); // agrega o modifica un rol
	
	public boolean remove(int idRole); // elimina un rol
	
	// TODO: add methods (if needed)
}
