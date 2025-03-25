package git.MatheusOliveira04.services;

import git.MatheusOliveira04.PayFlowApplication;
import git.MatheusOliveira04.services.impls.UserServiceImpl;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;

@TestConfiguration
@SpringBootTest(classes = PayFlowApplication.class)
@ActiveProfiles("test")
class BaseTest {

	@Bean
	public UserService userService() {
		return new UserServiceImpl();
	}

}
