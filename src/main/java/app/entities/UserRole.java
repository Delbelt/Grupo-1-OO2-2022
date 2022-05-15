package app.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity // persistence
@Data // // Lombok = auto-boilerplate
@Table(name="user_role") // database
public class UserRole implements Serializable { // Perfiles

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idRole;
	
	@Size(min=2, max=20)
	@Column(name="name")
	private String name; // ROLE_ADMIN / ROLE_AUDIT	
}
