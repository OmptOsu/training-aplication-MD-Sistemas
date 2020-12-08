package br.com.mdsistemas.training.gateways.http.controllers.json;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TaskJsonResponse {
	
	private String id;
	private String description;
	private LocalDate date;
	private Boolean isDone = false;
}
