package br.com.mdsistemas.training.gateways.http.controllers;

import java.time.LocalDate;

import org.apache.commons.lang3.ClassUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.VerificationModeFactory;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;

import br.com.mdsistemas.training.databuilders.domains.DomainsTemplateLoader;
import br.com.mdsistemas.training.databuilders.domains.TaskTemplate;
import br.com.mdsistemas.training.domains.Task;
import br.com.mdsistemas.training.gateways.http.controllers.json.TaskJsonRequest;
import br.com.mdsistemas.training.gateways.http.controllers.json.TaskJsonResponse;
import br.com.mdsistemas.training.usecases.CreateTaskUseCase;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

public class TaskControllerUnitTest {
	
	@InjectMocks
	private TaskController taskController;
	
	@Mock
	private CreateTaskUseCase createTaskUseCase;
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@BeforeClass
	public static void init() {
		FixtureFactoryLoader.loadTemplates(ClassUtils.getPackageName(DomainsTemplateLoader.class));
	}
	
	@Test
	public void createWithSuccess() {
		
		final TaskJsonRequest taskJsonRequest = new TaskJsonRequest("anyDescription", LocalDate.parse("2020-02-02"));
		final Task taskCreated = Fixture.from(Task.class).gimme(TaskTemplate.getTemplateCreated());
		
		when(this.createTaskUseCase.create(any(Task.class))).thenReturn(taskCreated);
		
		final TaskJsonResponse taskResponse = this.taskController.create(taskJsonRequest);
		
		final ArgumentCaptor<Task> taskCaptor = ArgumentCaptor.forClass(Task.class);
		
		verify(this.createTaskUseCase, VerificationModeFactory.times(1)).create(taskCaptor.capture());
		
		final Task taskCaptured = taskCaptor.getValue();
		
		assertEquals(taskJsonRequest.getDate(), taskCaptured.getDate());
		assertEquals(taskJsonRequest.getDescription(), taskCaptured.getDescription());
		assertEquals(null, taskCaptured.getId());
		assertEquals(false, taskCaptured.getIsDone());
		
		assertEquals(taskCreated.getDate(), taskResponse.getDate());
		assertEquals(taskCreated.getDescription(), taskResponse.getDescription());
		assertEquals(taskCreated.getDate(), taskResponse.getDate());
		assertEquals(taskCreated.getIsDone(), taskResponse.getIsDone());
	}

}
