package git.MatheusOliveira04;

import git.MatheusOliveira04.models.User;
import git.MatheusOliveira04.models.enums.Role;
import git.MatheusOliveira04.repositories.UserRepository;
import git.MatheusOliveira04.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class PayFlowApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayFlowApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(UserService userService, UserRepository userRepository) {
		return args -> {
			userRepository.deleteAll();

			User user = new User(null, "Matheus de oliveira sá", "U@gmail.com", "12345", List.of(Role.USER, Role.ADMIN));

			userRepository.save(user);
		};
	}

}
