package mjzilver.frac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})

public class FracApplication {
	public static void main(String[] args) {
		SpringApplication.run(FracApplication.class, args);
	}
}
