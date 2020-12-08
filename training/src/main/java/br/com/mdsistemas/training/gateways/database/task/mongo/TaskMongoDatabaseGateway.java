package br.com.mdsistemas.training.gateways.database.task.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mdsistemas.training.domains.Task;
import br.com.mdsistemas.training.gateways.database.task.TaskDatabaseGateway;
import br.com.mdsistemas.training.gateways.database.task.mongo.repository.TaskRepository;
import br.com.mdsistemas.training.gateways.exceptions.ErrorToAccessDatabaseGatewayException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TaskMongoDatabaseGateway implements TaskDatabaseGateway {
	
	@Autowired
	private TaskRepository taskRepository;
	
	public Task create(final Task task) {
		
		try {
			log.trace("task: {}", task);
			
			final Task taskCreated = this.taskRepository.save(task);
			
			return taskCreated;
		} catch (Exception error) {
			log.error("Error: {}", error);
			throw new ErrorToAccessDatabaseGatewayException();
		}
	
	
	}

	
	
}
