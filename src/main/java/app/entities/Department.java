package app.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Entity // persistence
@Data // Lombok = auto-boilerplate
@Table(name = "department") // database
public class Department implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idDepartment;
	
	@Column(name = "departmentName", columnDefinition = "varchar(45)")
	@NotEmpty()
	private String departmentName;
}
