package app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Range;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // persistence
@Getter @Setter @NoArgsConstructor // Lombok = auto-boilerplate
@PrimaryKeyJoinColumn(referencedColumnName="idClassroom", name = "idLaboratory") // Herencia - Referencia el Id del padre
@Table(name="laboratory") // database
public class Laboratory extends Classroom {

	private static final long serialVersionUID = 1L;
	
	// Al ser hijo NO es necesario que declare un Id, usara el que esta en referencedColumnName
	
	@Range(min = 1, max = 200)
	@Column(name="quantityChairs") 
	private int quantityChairs;
	
	@Range(min = 1, max = 100)
	@Column(name="quantityPC")
	private int quantityPC;  
}
