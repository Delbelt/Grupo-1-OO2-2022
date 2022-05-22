package app.entities;

import java.io.Serializable;
import java.time.LocalDate;

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
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // persistence
@Getter // Lombok getters
@Setter // Lombok setters
@NoArgsConstructor // Lombok constructor
@Inheritance(strategy=InheritanceType.JOINED) // Herencia - con uso de estrategia Joined-subclass
@Table(name = "orderNote") // database
public class OrderNote implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idOrderNote;

	@ManyToOne(cascade = CascadeType.PERSIST) // Relation with Classroom
	@JoinColumn(name = "idClassroom")
	private Classroom classroom; // no ingresar nombres en los atributos que sean iguales al nombre de la misma

	@Column(name = "codCourse")
	@NotEmpty()
	private String codCourse; // no ingresar nombres en los atributos que sean iguales al nombre de la misma

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "date")
	private LocalDate date;

	@ManyToOne(cascade = CascadeType.PERSIST) // Relation with Matter
	@JoinColumn(name = "idMatter")
	private Matter matter; // no ingresar nombres en los atributos que sean iguales al nombre de la misma

	@Column(name = "observation", columnDefinition = "varchar(255)")
	@NotEmpty()
	private String observation; // no ingresar nombres en los atributos que sean iguales al nombre de la misma

	@Column(name = "quantityStudent")
	private int quantityStudent;

	@Column(name = "shift")
	private char shift;

	@ManyToOne(cascade = CascadeType.PERSIST) // Relation with Teacher
	@JoinColumn(name = "idTeacher")
	private Teacher teacher; // no ingresar nombres en los atributos que sean iguales al nombre de la misma

}