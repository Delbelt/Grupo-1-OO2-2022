package app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entities.User;

public interface IUserRepository extends JpaRepository<User, Integer> {
		
	User findByUserName(String userName); // Es necesario agregar el metodo, lo pide Spring-segurity
}
