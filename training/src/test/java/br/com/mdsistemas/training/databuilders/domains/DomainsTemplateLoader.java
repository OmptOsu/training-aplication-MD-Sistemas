package br.com.mdsistemas.training.databuilders.domains;

import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class DomainsTemplateLoader implements TemplateLoader {
	
	@Override
	public void load() {
		TaskTemplate.load();
	}

}
