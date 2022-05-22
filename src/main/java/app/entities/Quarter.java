package app.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

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
	
	@Column(name="completeQuarter")
	private boolean completeQuarter;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="dateFrom") 
	private LocalDate dateFrom;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="dateTill")
	private LocalDate dateTill; 

	@Column(name="pairWeeks")
	private boolean pairWeeks;
}
