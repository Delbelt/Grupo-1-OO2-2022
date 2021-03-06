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
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity // persistence
@Data // Lombok = auto-boilerplate
@Table(name = "matter") // database
public class Matter implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idMatter; // no ingresar nombres en los atributos que sean iguales al nombre de la misma clase
	
	@ManyToOne(cascade = CascadeType.PERSIST) // si se borra el edificio se borran las aulas automaticamente.
	@JoinColumn(name = "idCareer")
	private Career career;
	
	@Column(name = "codeMatter")
	@NotNull()
	private int codeMatter; // no ingresar nombres en los atributos que sean iguales al nombre de la misma clase
							
	@Column(name = "matterName", columnDefinition = "varchar(60)")
	@NotEmpty()
	private String matterName; // no ingresar nombres en los atributos que sean iguales al nombre de la misma clase
}
