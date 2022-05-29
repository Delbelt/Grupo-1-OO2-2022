package app.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // persistence
@Getter @Setter @NoArgsConstructor // Lombok = auto-boilerplate
@PrimaryKeyJoinColumn(referencedColumnName="idOrderNote", name = "idDay") // Herencia - Referencia el Id del padre
@Table(name="day") // database
public class Day extends OrderNote {

	private static final long serialVersionUID = 1L;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="date")
	@NotNull
	private LocalDate date;
	
	@Column(name = "type", columnDefinition = "varchar(30)")
	@NotEmpty
	private String type;
}
