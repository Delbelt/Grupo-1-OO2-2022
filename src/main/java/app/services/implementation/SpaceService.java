package app.services.implementation;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.entities.Classroom;
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
	public void fillQuarter() throws Exception {
		int monthOfStarting = 3;
		int monthOfEnding = 6;
		List<Classroom> classrooms = classroomRepository.findAll();
		while(monthOfStarting <= monthOfEnding) {
			for(Classroom classroom: classrooms) {
				this.addSpaceMonth(monthOfStarting, 2023, 'M', classroom);
				this.addSpaceMonth(monthOfStarting, 2023, 'T', classroom);
				this.addSpaceMonth(monthOfStarting, 2023, 'N', classroom);
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
}
