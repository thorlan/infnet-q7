package infnetjavaq7.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class PropertyReaderConfig {

	//Bean para ler o arquivo de configuração rabbit.properties
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		PropertySourcesPlaceholderConfigurer properties = new PropertySourcesPlaceholderConfigurer();
		
		properties.setLocation(new FileSystemResource("config/rabbit.properties"));
		properties.setIgnoreResourceNotFound(false);
		return properties;
	}

}
