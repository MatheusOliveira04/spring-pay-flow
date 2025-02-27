package git.MatheusOliveira04;

import git.MatheusOliveira04.models.User;
import git.MatheusOliveira04.repositories.UserRepository;
import git.MatheusOliveira04.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
public class PayFlowApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayFlowApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(UserService userService, UserRepository userRepository) {
		return args -> {
			userRepository.deleteAll();

			User user = new User(null, "Matheus", "U@gmail.com", "12345", List.of("USER", "ADMIN"));

			userRepository.save(user);
		};
	}

}
