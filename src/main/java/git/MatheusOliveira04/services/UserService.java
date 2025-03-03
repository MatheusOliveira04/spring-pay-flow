package git.MatheusOliveira04.services;

import git.MatheusOliveira04.models.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface UserService {

    List<User> findAll();

    User findByEmail(@NotBlank String email);

    User insert(@Valid User user);

}
