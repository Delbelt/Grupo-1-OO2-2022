package app.entities;

import java.io.Serializable;
import java.time.LocalDate;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
 
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity // persistence
@Data // Lombok = auto-boilerplate
@Table(name = "orderNote") // database
public class OrderNote implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idOrderNote;

	@Column(name = "classroom", columnDefinition = "varchar(45)")
	@NotEmpty()
	private String classroom; // no ingresar nombres en los atributos que sean iguales al nombre de la misma

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "date")
	private LocalDate date;

	@Column(name = "matter", columnDefinition = "varchar(45)")
	@NotEmpty()
	private String matter; // no ingresar nombres en los atributos que sean iguales al nombre de la misma

	@Column(name = "observation", columnDefinition = "varchar(255)")
	@NotEmpty()
	private String observation; // no ingresar nombres en los atributos que sean iguales al nombre de la misma

	@Column(name = "quantityStudent")
	private int quantityStudent;

	@Column(name = "shift")
	private char shift;

}