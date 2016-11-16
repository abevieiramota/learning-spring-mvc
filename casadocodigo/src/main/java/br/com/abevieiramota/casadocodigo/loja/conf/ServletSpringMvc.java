package br.com.abevieiramota.casadocodigo.loja.conf;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

// Web application initializer
public class ServletSpringMvc extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	// classes de configuração para root
	protected Class<?>[] getRootConfigClasses() {
		return null;
	}

	@Override
	// classes de configuração para o dispatcher
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { AppWebConfiguration.class, JpaConfiguration.class };
	}

	@Override
	// paths para o dispatcher
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	// filtro para tratar codificação
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8");

		return new Filter[] { encodingFilter };
	}

}
