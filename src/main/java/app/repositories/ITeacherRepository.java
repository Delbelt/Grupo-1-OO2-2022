package app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entities.Teacher;

public interface ITeacherRepository extends JpaRepository<Teacher, Integer> {
}