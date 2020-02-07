package com.online.store.config;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.SpringConstraintValidatorFactory;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
public class BeanConfig {

	@Bean
	public CommonsMultipartResolver getCommonsMultipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(20971520);
		multipartResolver.setMaxInMemorySize(1048576);
		return multipartResolver;
	}

	
	@Bean
	public Validator validator(final AutowireCapableBeanFactory autowireCapableBeanFactory) {

		ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class).configure()
				.constraintValidatorFactory(new SpringConstraintValidatorFactory(autowireCapableBeanFactory))
				.buildValidatorFactory();
		Validator validator = validatorFactory.getValidator();

		return validator;
	}

}
