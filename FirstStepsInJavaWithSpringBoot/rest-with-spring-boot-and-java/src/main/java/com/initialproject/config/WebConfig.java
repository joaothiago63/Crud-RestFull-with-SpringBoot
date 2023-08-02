package com.initialproject.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.initialproject.serialization.converter.YamlJackson2HttpMessageConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	private static final MediaType MEDIA_TYPE_APPLICATION_YAML = MediaType.valueOf("application/x-yaml");
	
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
	
		/*configurer.favorParameter(true);
		configurer.parameterName("mediatype");
		configurer.useRegisteredExtensionsOnly(false);
		configurer.defaultContentType(MediaType.APPLICATION_JSON);
		configurer.mediaType("json", MediaType.APPLICATION_JSON);
		configurer.mediaType("xml", MediaType.APPLICATION_XML);
		configurer.mediaType("x-yaml", MEDIA_TYPE_APPLICATION_YAML);

*/
		
		configurer.favorParameter(false);
		configurer.useRegisteredExtensionsOnly(false);
		configurer.defaultContentType(MediaType.APPLICATION_JSON);
		configurer.mediaType("json", MediaType.APPLICATION_JSON);
		configurer.mediaType("xml", MediaType.APPLICATION_XML);
		configurer.mediaType("x-yaml", MEDIA_TYPE_APPLICATION_YAML);
		
	}

	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		
       converters.add(new YamlJackson2HttpMessageConverter());
       
	}

}
