package br.com.mdsistemas.training.gateways.database.task.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.mdsistemas.training.domains.Task;

public interface TaskRepository extends MongoRepository<Task, String> {

}
