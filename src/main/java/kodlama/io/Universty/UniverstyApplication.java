package kodlama.io.Universty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class UniverstyApplication {

	public static void main(String[] args) {
		SpringApplication.run(UniverstyApplication.class, args);
	}


}
