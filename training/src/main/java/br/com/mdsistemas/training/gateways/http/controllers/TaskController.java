package br.com.mdsistemas.training.gateways.http.controllers;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.mdsistemas.training.domains.Task;
import br.com.mdsistemas.training.gateways.http.controllers.json.TaskJsonRequest;
import br.com.mdsistemas.training.gateways.http.controllers.json.TaskJsonResponse;
import br.com.mdsistemas.training.usecases.CreateTaskUseCase;
import lombok.extern.slf4j.Slf4j;

@Validated
@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("${baseurl.v1}/tasks")
//@RequestMapping("/training/v1/tasks")
public class TaskController {
	
	@Autowired
	private CreateTaskUseCase createTaskUseCase;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public TaskJsonResponse create(
			final @RequestBody(required = true) @NotNull @Valid TaskJsonRequest taskJsonRequest) {
		
		log.trace("taskJsonRequest: {}", taskJsonRequest);
	
		final Task taskToBeCreate = new Task(taskJsonRequest.getDescription(), taskJsonRequest.getDate());
	
		final Task taskCreated = this.createTaskUseCase.create(taskToBeCreate);
	
		final TaskJsonResponse response = new TaskJsonResponse(taskCreated.getId(), taskCreated.getDescription(), taskCreated.getDate(), taskCreated.getIsDone());
		
		return response;

	}
	
}
