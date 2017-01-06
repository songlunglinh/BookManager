package com.pvthuan.book.config;

import java.util.List;


import com.pvthuan.book.utils.ConstanModel;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;


import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = ConstanModel.PACKAGE_SCAN + ".controller")
public class WebConfig {
	
	public MappingJackson2HttpMessageConverter jacksonMessageConverter() {
		MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();

		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new Hibernate4Module());

		messageConverter.setObjectMapper(mapper);
		return messageConverter;

	}

	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(jacksonMessageConverter());
		configureMessageConverters(converters);
	}
	

//    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//        configurer.ignoreAcceptHeader(true).defaultContentType(
//                MediaType.TEXT_HTML);
//    }
//    
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/public_html/**").addResourceLocations("/public_html/");
	}
}