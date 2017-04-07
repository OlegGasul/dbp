package de.mexchange.packagingdb.configuration;

import de.mexchange.packagingdb.controller.interceptor.AdminRoleRequiredInterceptor;
import de.mexchange.packagingdb.controller.interceptor.layout.ThymeleafLayoutInterceptor;
import de.mexchange.packagingdb.domain.lcp.Language;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import javax.servlet.MultipartConfigElement;
import org.springframework.boot.context.embedded.MultipartConfigFactory;

/**
 * Created by Garik on 4/30/16.
 */
@Configuration
@EnableWebMvc
@ComponentScan
public class WebConfiguration extends WebMvcConfigurerAdapter {

	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize("128MB");
		factory.setMaxRequestSize("128MB");
		return factory.createMultipartConfig();
	}
	/**
	 * Provides own message source
	 */
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages");
		messageSource.setDefaultEncoding("UTF-8");
		// refresh cache once per hour
		messageSource.setCacheSeconds(3600);
		return messageSource;
	}

	/**
	 * Passes out message source to validators
	 */
	@Override
	public Validator getValidator() {
		LocalValidatorFactoryBean factory = new LocalValidatorFactoryBean();
		factory.setValidationMessageSource(messageSource());
		return factory;
	}

	/**
	 * Sets cookie as a locale resolver
	 */
	@Bean
	public LocaleResolver localeResolver() {
		CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
		cookieLocaleResolver.setDefaultLocale(Language.getDefault().getLocale());
		return cookieLocaleResolver;
	}

    @Override
    public void addFormatters(FormatterRegistry registry) {
       // registry.addConverter();
    }


	/**
	 * Adding custom interceptors
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// interceptor which manages switching locale
		LocaleChangeInterceptor localeInterceptor = new LocaleChangeInterceptor();
		localeInterceptor.setParamName("lang");
		registry.addInterceptor(localeInterceptor);

		// interceptor checking admin role on required resources
		registry.addInterceptor(new AdminRoleRequiredInterceptor());

		// interceptor for custom thymeleaf layout template
		registry.addInterceptor(new ThymeleafLayoutInterceptor());
	}

	/**
	 * Adds resource paths
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**")
			.addResourceLocations(
				"classpath:/META-INF/resources/",
				"classpath:/resources/",
				"classpath:/static/",
				"classpath:/public/",
				"classpath:/webjars/"
			);
	}

	/**
	 * Adds static view/controller
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
	}
}
