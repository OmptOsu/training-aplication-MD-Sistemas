package br.com.mdsistemas.training.databuilders.domains;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import br.com.mdsistemas.training.domains.Task;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;

public class TaskTemplate {
	
	protected static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d/MM/yyyy");
	
	public static final String TEMPLATE_TO_CREATE = "TEMPLATE_TO_CREATE";
	public static final String TEMPLATE_CREATED = "TEMPLATE_CREATED";
	
	protected static void load() {
		Fixture.of(Task.class).addTemplate(TEMPLATE_TO_CREATE, new Rule() {
			{
				add("description", random("anyemail@01", "anyemail@01"));
				add("date", LocalDate.parse("10/11/2019", FORMATTER));
			}
		});
		
		Fixture.of(Task.class).addTemplate(getTemplateCreated(), new Rule() {
			{
				add("id", random("id_01", "id_02"));
				add("description", random("description_01", "description_02"));
				add("date", LocalDate.parse("10/11/2019", FORMATTER));
				add("isDone", random(true, false));
			}
		});
	}

	public static String getTemplateCreated() {
		return TEMPLATE_CREATED;
	}

	
}
