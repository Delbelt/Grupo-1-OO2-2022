package app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entities.Space;

public interface ISpaceRepository extends JpaRepository<Space, Integer>{

	
	
}
