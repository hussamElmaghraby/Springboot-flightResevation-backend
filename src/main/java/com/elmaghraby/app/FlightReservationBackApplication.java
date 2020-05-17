package com.elmaghraby.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;


@SpringBootApplication
public class FlightReservationBackApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(FlightReservationBackApplication.class, args);
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//The handler will be invoked for every incoming request that matches to one of the specified path patterns. 
		registry.addResourceHandler("/**").addResourceLocations("classPath/static/");
	}
	
	
	//Implementations are encouraged to support internationalization, i.e. localized view resolution.
	@Bean 
	public ViewResolver viewResolver() {
		UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
		viewResolver.setViewClass(InternalResourceView.class);
		
		return viewResolver;
	}
}
