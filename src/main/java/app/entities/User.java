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

import lombok.Data;

@Entity // persistence
@Data // Lombok = auto-boilerplate
@Table(name="user") // database
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idUser;
	
	// Variables in ascending order
	
	@Column(name="document")
	private long document; // Example: 11.111.111
	
	@Column(name="enabled")
	private boolean enabled; // active or not
	
	@Column(name="email")
	private String email; // Example: AAAAA@provider.domain
	
	@Column(name="name")
	private String name; // Example: AAAAAA
	
	@Column(name="password")
	private String password; // Example: abcde11_UNLA
	
	@ManyToOne(cascade=CascadeType.PERSIST) // Relation with Role
	@JoinColumn(name="idRole")
	private UserRole role;
	
	@Column(name="surname")
	private String surname; // Example: BBBBBBB
	
	@Column(name="type")
	private String type; // Example: L.E/DNI - CARNET EXT.
	
	@Column(name="userName")
	private String userName; // Example: unlaUser
	
	/* TODO add validations and exceptions:
	 * document: length 8
	 * email: mail format
	 * name: length between 2 to 45 characters
	 * password: Minimum 8 characters,at least one uppercase letter, one lowercase letter, one number and one special character
	 * surname: length between 2 to 45 characters
	 * type: length between 2 to 60 characters
	 * userName: length between 6 to 20 characters
	 * */
}
