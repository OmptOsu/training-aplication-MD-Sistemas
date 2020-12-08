package br.com.mdsistemas.training.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mdsistemas.training.domains.Task;
import br.com.mdsistemas.training.gateways.database.task.TaskDatabaseGateway;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CreateTaskUseCase {
	
	@Autowired
	private TaskDatabaseGateway taskDatabaseGateway;
	
	public Task create(final Task task) {
		
		log.trace("task: {}", task);
		
		return this.taskDatabaseGateway.create(task);
	}
}