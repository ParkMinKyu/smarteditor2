package kr.iiac.bugs.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Configuration
public class WebConfig extends WebMvcAutoConfigurationAdapter {
    
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer(){
	return new EmbeddedServletContainerCustomizer() {
	    
	    @Override
	    public void customize(ConfigurableEmbeddedServletContainer container) {
		// TODO Auto-generated method stub
	    		ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/");
	            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/");
	            ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/");
	            ErrorPage error505Page = new ErrorPage(HttpStatus.HTTP_VERSION_NOT_SUPPORTED, "/");
	            ErrorPage error506Page = new ErrorPage(HttpStatus.METHOD_NOT_ALLOWED, "/");
	            container.addErrorPages(error401Page, error404Page,error500Page, error505Page, error506Page);
	    }
	};
    }

    @Override
    public void configureMessageConverters(
	    List<HttpMessageConverter<?>> converters) {
	// TODO Auto-generated method stub
	MappingJackson2HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter();
	List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();
	supportedMediaTypes.add(MediaType.TEXT_PLAIN);
	supportedMediaTypes.add(MediaType.APPLICATION_JSON);
	jacksonConverter.setPrettyPrint(true);
	jacksonConverter.setSupportedMediaTypes(supportedMediaTypes);
	converters.add(jacksonConverter);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	// TODO Auto-generated method stub
	registry.addResourceHandler("/resources/**").addResourceLocations(
		"/resources/");
    }
}
