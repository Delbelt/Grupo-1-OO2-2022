package app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import app.entities.Matter;

public interface IMatterRepository extends JpaRepository<Matter, Integer> {

}