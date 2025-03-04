package git.MatheusOliveira04.models.dtos.request;

import git.MatheusOliveira04.models.enums.Role;
import git.MatheusOliveira04.models.enums.validations.annotations.ValueOfEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    @NotBlank
    private String username;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
    @NotEmpty
    private List<@NotBlank @NotNull @ValueOfEnum(enumClass = Role.class) String> roles;
}
