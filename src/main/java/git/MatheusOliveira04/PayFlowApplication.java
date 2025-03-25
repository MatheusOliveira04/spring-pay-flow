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
}
