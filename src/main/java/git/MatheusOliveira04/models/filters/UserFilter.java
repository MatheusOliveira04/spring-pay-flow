package git.MatheusOliveira04.models.filters;

import git.MatheusOliveira04.models.User;
import git.MatheusOliveira04.models.filters.specifications.UserSpec;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

@Data
public class UserFilter {

    private String username;

    private String email;

    public Specification<User> toSpecification() {
        return UserSpec.usernameContains(username)
                .and(UserSpec.emailContains(email));
    }

}
