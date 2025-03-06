package git.MatheusOliveira04.services;

import git.MatheusOliveira04.models.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

@Validated
public interface UserService {

    List<User> findAll();

    User findById(@NotNull UUID id);

    User findByEmail(@NotBlank String email);

    User insert(@Valid User user);

    User update (@Valid User user);

    void delete(@NotNull UUID id);
}
