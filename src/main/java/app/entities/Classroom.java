package app.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // persistence
@Getter // Lombok getters
@Setter // Lombok setters
@NoArgsConstructor // Lombok constructor
@Inheritance(strategy=InheritanceType.JOINED) // Herencia - con uso de estrategia Joined-subclass
@Table(name="classroom") // database
public class Classroom implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int idClassroom;	
	
	// En el futuro va a ser un objeto de tipo Building
	@Column(name="building", columnDefinition = "varchar(45)")  // columnDefinition = Define la longitud de las columnas
	protected String building;
	
	@Column(name="number")
	protected int number;	
}