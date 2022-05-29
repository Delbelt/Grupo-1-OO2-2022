package app.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import app.entities.Classroom;
import app.entities.Space;

public interface ISpaceRepository extends JpaRepository<Space, Integer> {
	
	// public boolean addSpace(LocalDate date, char shift, Classroom classroom, boolean free);
	
	// public void addSpaceMonth(int month, int year, char shift, Classroom classroom);
	
	// PRESTAR ATENCIÓN AL 'c.idClassroom = :#{#classroom.idClassroom}'. CUANDO EN LOS REPOSITORIOS
	// QUIERO TRAER UN OBJETO PASANDO COMO ARGUMENTO OTRO OBJETO, DEBO RESPETAR LA SINTAXIS DE LA QUERY
	// Y COLOCAR EL @Param("objetoArgumento") EN EL MÉTODO
	@Query("from Space s inner join fetch s.classroom c where s.date = (:date) and s.shift = (:shift) and "
			+ "c.idClassroom = :#{#classroom.idClassroom}")
	public Space find(LocalDate date, char shift, @Param("classroom") Classroom classroom);
	
	/*
	 * Este store procedure carga todos los epsacioes libres para todas las clases por todos los turnos para todos los dias desde el mes pasado como parametro +4 meses 
	 * */
	@Query(value = "{call fillQuarter (:year, :month)} ", nativeQuery = true)
	public void callFillQuarter( @Param("year") int year, @Param("month")int month);
	
	public List<Space> findByFree(boolean free);
	
}
