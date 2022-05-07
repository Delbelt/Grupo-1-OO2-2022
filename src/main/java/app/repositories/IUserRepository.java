package app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entities.User;

public interface IUserRepository extends JpaRepository<User, Integer> { // JpaRepository / CrudRepository<Entitie, class ID (Integer, Long)>
		
	User findByUserName(String userName); // Es necesario agregar el metodo, lo pide Spring-segurity
	
	// TODO: add query methods (if needed)
}
