package git.MatheusOliveira04.services;

import git.MatheusOliveira04.models.User;
import git.MatheusOliveira04.models.filters.UserFilter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Validated
public interface UserService {

    Page<User> findAll(@PositiveOrZero int page, @Positive @Max(100) int size, UserFilter userFilter);

    User findById(@NotNull UUID id);

    User findByEmail(@NotBlank String email);

    User insert(@Valid User user);

    User update (@Valid User user);

    void delete(@NotNull UUID id);
}
