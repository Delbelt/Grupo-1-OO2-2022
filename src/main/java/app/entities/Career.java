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
@Table(name = "career") // database
public class Career implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCareer;
	
	@Column(name = "careerName", columnDefinition = "varchar(60)")
	@NotEmpty()
	private String careerName;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="idDepartment")
	private Department department;
}
