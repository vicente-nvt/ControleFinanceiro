package application;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main (String[] args){		
		SpringApplication.run(Application.class, args);		
	}
}
