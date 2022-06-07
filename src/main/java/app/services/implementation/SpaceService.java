package app.services.implementation;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.entities.Classroom;
import app.entities.Quarter;
import app.entities.Space;
import app.repositories.IClassroomRepository;
import app.repositories.ISpaceRepository;
import app.services.ISpaceService;

@Service
public class SpaceService implements ISpaceService {

	@Autowired() // injection
	private ISpaceRepository repository;
	
	@Autowired()
	private IClassroomRepository classroomRepository;
	
	@Override
	@Transactional(readOnly = true) // no modifica la bd, solamente lectura
	public Space findById(int idSpace) {
		return repository.findById(idSpace).orElse(null);
	}

	@Override
	@Transactional(readOnly = true) // no modifica la bd, solamente lectura
	public List<Space> getAll() {
		return repository.findAll();
	}

	@Override
	@Transactional // modifica la BD: commit / rollback
	public boolean insertOrUpdate(Space space) {
		return repository.save(space) != null ? true : false;
	}

	@Override
	@Transactional // modifica la BD: commit / rollback
	public boolean remove(int idSpace) {
		try {
			repository.deleteById(idSpace); 
			return true;			
		} catch(Exception e) {
			return false;
		}	
	}

	@Override
	public boolean addSpace(LocalDate date, char shift, Classroom classroom, boolean free) throws Exception {
		if(this.find(date, shift, classroom)!=null) {
			throw new Exception("El espacio ya esta registrado en la base de datos.");
		}
		return repository.save(new Space(date, shift, classroom, free)) != null ? true : false;
	}

	@Override
	public void addSpaceMonth(int month, int year, char shift, Classroom classroom) throws Exception {
		LocalDate firstDay = LocalDate.of(year, month, 1);
		while(firstDay.getMonthValue() == month) {
			this.addSpace(firstDay, shift, classroom, true);
			firstDay = firstDay.plusDays(1);
		}
	}
	
	@Override
	public void fillQuarter(int monthOfStarting, int monthOfEnding, int year) throws Exception {

		List<Classroom> classrooms = classroomRepository.findAll();
		
		while(monthOfStarting <= monthOfEnding)
		{
			for(Classroom classroom: classrooms)
			{
				this.addSpaceMonth(monthOfStarting, year, 'M', classroom);
				this.addSpaceMonth(monthOfStarting, year, 'T', classroom);
				this.addSpaceMonth(monthOfStarting, year, 'N', classroom);
			}
			
				monthOfStarting += 1;
		}
	}
	
	@Override
	public void fillQuarterProcedure(int year, int month)   {
		repository.callFillQuarter(year, month);
	}
	
	@Override
	public Space find(LocalDate date, char shift, Classroom classroom) {
		return repository.find(date, shift, classroom);
	}

	@Override
	public List<Space> findByFree(boolean free) {
		return repository.findByFree(free);
	}

	// Método auxiliar
	private int numberOfWeek(LocalDate date) {
		return date.get(ChronoField.ALIGNED_WEEK_OF_YEAR);
	}
	
	@Override
	public void changeSpace(LocalDate date, char shift, Classroom classroom, boolean free) throws Exception {
		
		Space space = repository.find(date, shift, classroom);
		
		if(space == null) throw new Exception("El espacio no existe o no se encuentra disponible");
		
		space.setFree(free);
		this.insertOrUpdate(space);
	}
	
	@Override
	public void changeSpaceQuarter(Quarter quarter, boolean free) throws Exception
	{		
		LocalDate date = quarter.getDateFrom();
		
		if(repository.find(date, quarter.getShift(), quarter.getClassroom()) == null) throw new Exception("Los espacios no existen o no se encuentran disponibles");
		
		while(date.isBefore(quarter.getDateTill().plusDays(1)))
		{	
			try
			{
				if(date.getDayOfWeek().getValue() == quarter.getDateFrom().getDayOfWeek().getValue())
				{
					if(quarter.getCourseType().equalsIgnoreCase("Cuatrimestre"))
					{
						this.changeSpace(date, quarter.getShift(), quarter.getClassroom(), free);
					}
					
					else
					{
						// Si es semana par y el número de semana es par
						if(quarter.getCourseType().equalsIgnoreCase("Semana Par") && this.numberOfWeek(date)%2==0)
						{
							this.changeSpace(date, quarter.getShift(), quarter.getClassroom(), free);
							
						}
						// Si es semana impar y el número de semana es impar
						else if(quarter.getCourseType().equalsIgnoreCase("Semana Impar") && this.numberOfWeek(date)%2==1)
						{
							this.changeSpace(date, quarter.getShift(), quarter.getClassroom(), free);
						}
					}
				}
			}
			
			catch (Exception e)
			{
				System.out.println(e.getMessage());
			}
			
			date = date.plusDays(1);
		}
	}
}
