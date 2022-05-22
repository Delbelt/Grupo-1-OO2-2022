package app.services;

import java.util.List;

import app.entities.Teacher;

public interface ITeacherService {

	public Teacher findById(int idTeacher); // trae un user por id

	public List<Teacher> getAll(); // trae la lista de todos los usuarios

	public boolean insertOrUpdate(Teacher teacher); // agrega o modifica un usuario

	public boolean remove(int idTeacher); //
}
