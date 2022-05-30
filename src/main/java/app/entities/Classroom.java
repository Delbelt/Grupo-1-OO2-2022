package app.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Range;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // persistence
@Getter // Lombok getters
@Setter // Lombok setters
@NoArgsConstructor // Lombok constructor
@Inheritance(strategy=InheritanceType.JOINED) // Herencia - con uso de estrategia Joined-subclass
@Table(name="classroom") // database
public class Classroom implements Serializable { //Clase Padre
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int idClassroom;	

	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="idBuilding")
	protected Building building;
	
	@Column(name="number")
	@Range(min = 1, max = 100)
	protected int number;
	
}
