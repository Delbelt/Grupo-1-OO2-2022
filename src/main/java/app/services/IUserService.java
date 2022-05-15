package app.services;

import java.util.List;

import app.entities.User;

public interface IUserService {
	
	// custom methods for testings
	
	public User findById(int idUser); // trae un user por id
	
	public User findByUserName(String userName); // Trae a un usuario por su nombre (para traer los datos del perfil)
	
	public List<User> getAll(); // trae la lista de todos los usuarios
	
	public boolean insertOrUpdate(User user); // agrega o modifica un usuario
	
	public boolean remove(int idUser); // elimina un rol
	
	// TODO: add methods (if needed)
}
