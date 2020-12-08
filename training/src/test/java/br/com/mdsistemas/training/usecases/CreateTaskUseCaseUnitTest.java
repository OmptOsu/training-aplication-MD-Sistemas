package br.com.mdsistemas.training.usecases;

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
import br.com.mdsistemas.training.gateways.database.task.TaskDatabaseGateway;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

public class CreateTaskUseCaseUnitTest {
	
	@InjectMocks
	private CreateTaskUseCase createTaskUseCase;
	
	@Mock
	private TaskDatabaseGateway taskDatabaseGateway;
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@BeforeClass
	public static void init() {
		FixtureFactoryLoader.loadTemplates(ClassUtils.getPackageName(DomainsTemplateLoader.class));
	}
	
	@Test
	public void createWithSucess() {
		final Task taskToCreated = Fixture.from(Task.class).gimme(TaskTemplate.TEMPLATE_TO_CREATE);
		final Task taskCreated = Fixture.from(Task.class).gimme(TaskTemplate.TEMPLATE_CREATED);
		
		when(this.taskDatabaseGateway.create(any(Task.class))).thenReturn(taskCreated);
		
		final Task taskResponse = this.createTaskUseCase.create(taskToCreated);
		
		final ArgumentCaptor<Task> taskCaptor = ArgumentCaptor.forClass(Task.class);
		
		verify(this.taskDatabaseGateway, VerificationModeFactory.times(1)).create(taskCaptor.capture());
		
		final Task taskCaptured = taskCaptor.getValue();
		
		assertEquals(taskToCreated.getDate(), taskCaptured.getDate());
		assertEquals(taskToCreated.getDescription(), taskCaptured.getDescription());
		assertEquals(null, taskCaptured.getId());
		assertEquals(false, taskCaptured.getIsDone());
		
		assertEquals(taskCreated.getDate(), taskResponse.getDate());
		assertEquals(taskCreated.getDescription(), taskResponse.getDescription());
		assertEquals(taskCreated.getId(), taskResponse.getId());
		assertEquals(taskCreated.getIsDone(), taskResponse.getIsDone());
	}
}