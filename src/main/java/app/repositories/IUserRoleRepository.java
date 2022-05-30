package app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entities.UserRole;

public interface IUserRoleRepository extends JpaRepository<UserRole, Integer> {
}
