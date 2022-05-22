
package app.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import lombok.Data;

@Entity // persistence
@Data // Lombok = auto-boilerplate
@Table(name = "teacher") // database
public class Teacher implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTeacher;

	// Variables in ascending order

	@Range(min = 10000000, max = 99999999)
	@NotNull
	@Column(name = "document")
	private long document; // Example: 11.111.111

	@Email
	@NotEmpty
	@Column(name = "email")
	private String email; // Example: AAAAA@provider.domain

	@Size(min = 2, max = 25)
	@Column(name = "name")
	private String name; // Example: AAAAAA

	@Size(min = 2, max = 30)
	@Column(name = "surname")
	private String surname; // Example: BBBBBBB

	@Column(name = "type")
	private String type; // Example: L.E/DNI - CARNET EXT.

}
