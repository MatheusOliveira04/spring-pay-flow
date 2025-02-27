package git.MatheusOliveira04.models.dtos.request;

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
    private String username;
    private String email;
    private String password;
    private List<String> roles;
}
