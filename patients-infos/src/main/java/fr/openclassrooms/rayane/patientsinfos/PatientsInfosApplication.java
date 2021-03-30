package fr.openclassrooms.rayane.patientsinfos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PatientsInfosApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientsInfosApplication.class, args);
	}

}
