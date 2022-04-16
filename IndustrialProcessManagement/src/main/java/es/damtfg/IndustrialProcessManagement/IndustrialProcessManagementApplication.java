package es.damtfg.IndustrialProcessManagement;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import es.damtfg.IndustrialProcessManagement.data.Demo;
import es.damtfg.IndustrialProcessManagement.data.Initial;

@SpringBootApplication
public class IndustrialProcessManagementApplication {

	@PostConstruct
	void init() {

		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}
	
	public static void main(String[] args) {
		
		SpringApplication.run(IndustrialProcessManagementApplication.class, args);
	}

	@Bean
	public Initial getInitialData() {
		
		return new Initial();
	}
	
	@Bean
	public Demo getDemoData() {
		
		return new Demo();
	}

}
