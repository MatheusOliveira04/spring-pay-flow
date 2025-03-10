package git.MatheusOliveira04.models;

import git.MatheusOliveira04.models.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Validated
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "tb_user")
public class User {

    @Id
    @Setter
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    @NotBlank(message = "Field cannot be empty.")
    private String username;

    @Column(unique = true, nullable = false)
    @Email(message = "Field must be a well-formed email address.")
    @NotBlank(message = "Field cannot be empty.")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "Field cannot be empty.")
    private String password;

    @Column(nullable = false)
    @NotEmpty(message = "List cannot be empty.")
    private List<
            @NotNull(message = "Field cannot be null.")
            Role> roles = new ArrayList<>();
}