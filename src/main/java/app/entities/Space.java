package app.entities;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

@Entity // persistence
@Data // Lombok = auto-boilerplate
@Table(name="space") // database
public class Space implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idSpace;
	
	@Column(name="classroom", columnDefinition = "varchar(45)")
	private String classroom;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="date")
	private LocalDate date;
	
	@Column(name="free")
	private boolean free;
	
	@Column(name="shift")
	private char shift;
}