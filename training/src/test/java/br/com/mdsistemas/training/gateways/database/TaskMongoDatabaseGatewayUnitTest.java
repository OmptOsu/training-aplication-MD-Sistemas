package br.com.mdsistemas.training.gateways.database;

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
import static org.mockito.Mockito.doThrow;

import br.com.mdsistemas.training.databuilders.domains.DomainsTemplateLoader;
import br.com.mdsistemas.training.databuilders.domains.TaskTemplate;
import br.com.mdsistemas.training.domains.Task;
import br.com.mdsistemas.training.gateways.database.task.mongo.TaskMongoDatabaseGateway;
import br.com.mdsistemas.training.gateways.database.task.mongo.repository.TaskRepository;
import br.com.mdsistemas.training.gateways.exceptions.ErrorToAccessDatabaseGatewayException;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;


public class TaskMongoDatabaseGatewayUnitTest {

	@InjectMocks
	private TaskMongoDatabaseGateway taskMongoDatabaseGateway;
	
	@Mock
	private TaskRepository taskRepository;
	
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
		
		when(this.taskRepository.save(any(Task.class))).thenReturn(taskCreated);
		
		final Task taskResponse = this.taskMongoDatabaseGateway.create(taskToCreated);
		
		final ArgumentCaptor<Task> taskCaptor = ArgumentCaptor.forClass(Task.class);
		
		verify(this.taskRepository, VerificationModeFactory.times(1)).save(taskCaptor.capture());
		
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
	
	@Test(expected = ErrorToAccessDatabaseGatewayException.class)
	public void createWithErrorToAccessDatabase() {
		final Task taskToCreated = Fixture.from(Task.class).gimme(TaskTemplate.TEMPLATE_TO_CREATE);
		
		doThrow(new RuntimeException()).when(this.taskRepository).save(any(Task.class));
		
		try {
			this.taskMongoDatabaseGateway.create(taskToCreated);
		} catch (ErrorToAccessDatabaseGatewayException e) {
			assertEquals("training.errorToAccessDatabase", e.id().code());
			assertEquals("Error to access database.", e.id().message());
			
			throw e;
		}
	}
}
