package app.services;

import java.util.List;

import app.entities.User;

public interface IUserService {
	
	// custom methods for testings
	
	public User findById(int idUser); // trae un user por id
	
	public List<User> getAll(); // trae la lista de todos los usuarios
	
	public boolean insertOrUpdate(User user); // agrega o modifica un usuario
	
	public boolean remove(int idUser); // elimina un rol
	
	// TODO: add methods (if needed)
}
