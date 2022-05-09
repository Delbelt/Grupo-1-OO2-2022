package app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // persistence
@Getter @Setter @NoArgsConstructor // Lombok = auto-boilerplate
@PrimaryKeyJoinColumn(referencedColumnName="idClassroom", name= "idTraditional") // Herencia - Referencia el Id del padre
@Table(name="traditional") // database
public class Traditional extends Classroom {
	
	private static final long serialVersionUID = 1L;
	
	// Al ser hijo NO es necesario que declare un Id, usara el que esta en referencedColumnName
		
	@Column(name="chalkboard", columnDefinition = "varchar(45)")
	private String chalkboard;
	
	@Column(name="hasProjector")
	private boolean hasProjector; 
	
	@Column(name="quantityBenchs")
	private int quantityBenchs; 
}
