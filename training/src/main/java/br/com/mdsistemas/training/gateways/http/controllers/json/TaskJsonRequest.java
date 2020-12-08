package br.com.mdsistemas.training.gateways.http.controllers.json;

import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TaskJsonRequest {
	
	@NotBlank
	@Valid
	private String description;
	
	@NotNull
	@Valid
	private LocalDate date;
	
}
