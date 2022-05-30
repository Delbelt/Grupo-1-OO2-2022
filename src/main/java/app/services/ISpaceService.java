package app.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.query.Param;

import app.entities.Classroom;
import app.entities.Quarter;
import app.entities.Space;

public interface ISpaceService {

	public Space findById(int idSpace); // traer espacio por ID
	
	public List<Space> getAll(); // traer la lista de espacios
	
	public boolean insertOrUpdate(Space space); // agrega o modifica un espacio
	
	public boolean remove(int idSpace); // elimina un espacio
	
	public boolean addSpace(LocalDate date, char shift, Classroom classroom, boolean free) throws Exception;
	
	public void addSpaceMonth(int month, int year, char shift, Classroom classroom) throws Exception;
	
	public void fillQuarter() throws Exception;
	
	public void fillQuarterProcedure(int year, int month) throws Exception;
	
	public Space find(LocalDate date, char shift, @Param("classroom") Classroom classroom);
	
	public List<Space> findByFree(boolean free);
	
	public void changeSpace(LocalDate date, char shift, Classroom classroom) throws Exception;

	public void changeSpaceQuarter(Quarter quarter);
}
