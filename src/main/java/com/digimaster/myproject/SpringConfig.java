package com.digimaster.myproject;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringConfig implements WebMvcConfigurer {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		registry.addResourceHandler("/images/**").addResourceLocations("file:F:\\Belajar Spring tool\\images\\");
//		registry.addResourceHandler("/images/**").addResourceLocations("file:F:\\Belajar Spring tool\\images\\myprojectimages\\");
		
	}
	

}
