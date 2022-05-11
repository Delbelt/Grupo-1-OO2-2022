package app.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Entity // persistence
@Data // Lombok = auto-boilerplate
@Table(name = "building") // database
public class Building implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "buildingName", columnDefinition = "varchar(45)")
	@NotEmpty()
	private String buildingName; // no ingresar nombres en los atributos que sean iguales al nombre de la misma clase


	@ManyToOne(cascade=CascadeType.PERSIST) // Relation with Role
	@JoinColumn(name="idClassroom")
	private Classroom classroom ;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idBuilding;

}
