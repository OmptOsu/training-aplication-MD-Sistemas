package br.com.mdsistemas.training;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.mdsistemas.training.domains.Task;
import br.com.mdsistemas.training.gateways.database.task.mongo.repository.TaskRepository;

@SpringBootApplication
public class TrainingApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrainingApplication.class, args);
	}
	
	@Bean
	public String listAll(TaskRepository taskRepository) {
		System.out.println("Task Criadas:");
		for(Task task: taskRepository.findAll()) {
			System.out.println(task.toString());
		}
		
		return "";
	}
	
	@Bean
	public String deleteTask(TaskRepository taskRepository, String id) {
		taskRepository.deleteById(id);
		return "";
	}
	
	@Bean
	public String updateTask(TaskRepository taskRepository, String id) {
		taskRepository.findById(id).get().setIsDone(true);
		return "";
	}
	
}
