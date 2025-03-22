package git.MatheusOliveira04;

import git.MatheusOliveira04.models.User;
import git.MatheusOliveira04.models.enums.Role;
import git.MatheusOliveira04.repositories.UserRepository;
import git.MatheusOliveira04.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;

@EnableCaching
@EnableScheduling
@EnableAsync
@SpringBootApplication
public class PayFlowApplication {

    public static void main(String[] args) {
        SpringApplication.run(PayFlowApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(UserService userService, UserRepository userRepository) {
        return args -> {
            userRepository.deleteAll();
            User userAdmin = new User(null, "Matheus de oliveira sá", "U@gmail.com", "12345", List.of(Role.USER, Role.ADMIN));
            userRepository.save(userAdmin);

            for (int i = 0; i < 20; i++) {
                userRepository.save(new User(null, "user" + i, i + "@gmail.com", "12345", List.of(Role.USER, Role.ADMIN)));
            }
        };
    }

}
