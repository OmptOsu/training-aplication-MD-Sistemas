package br.com.mdsistemas.training.gateways.database.task;

import br.com.mdsistemas.training.domains.Task;

public interface TaskDatabaseGateway {
	
	Task create(final Task task);
}
