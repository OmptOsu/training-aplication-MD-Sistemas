package br.com.mdsistemas.training.domains;

import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "task")

public class Task {
	
	@Id
	private String id;
	private String description;
	private LocalDate date;
	private Boolean isDone = false;
	
	public Task(final String description, final LocalDate date) {
		this.date = date;
		this.description = description;
	}
	
}
