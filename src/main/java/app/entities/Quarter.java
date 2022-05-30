package app.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // persistence
@Getter @Setter @NoArgsConstructor // Lombok = auto-boilerplate
@PrimaryKeyJoinColumn(referencedColumnName="idOrderNote", name = "idQuarter") // Herencia - Referencia el Id del padre
@Table(name="quarter") // database
public class Quarter extends OrderNote { // No va el implements Serializable, ya que lo hereda del padre

	private static final long serialVersionUID = 1L;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="dateFrom") 
	@NotNull
	private LocalDate dateFrom;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="dateTill")
	@NotNull
	private LocalDate dateTill; 
	
	@Column(name = "courseType", columnDefinition = "varchar(25)")
	private String courseType;
	
	@Column(name = "dayOfWeek")
	private int dayOfWeek;

	
}
