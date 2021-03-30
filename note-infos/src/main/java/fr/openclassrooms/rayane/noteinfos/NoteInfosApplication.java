package fr.openclassrooms.rayane.noteinfos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class NoteInfosApplication {

	public static void main(String[] args) {
		SpringApplication.run(NoteInfosApplication.class, args);
	}

}
