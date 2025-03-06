package git.MatheusOliveira04.models.filters.specifications;

import git.MatheusOliveira04.models.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

public class UserSpec {

    public static Specification<User> usernameContains(String username) {
        return (root, query, builder) -> {
          if (ObjectUtils.isEmpty(username)) {
              return null;
          }
          return builder.like(root.get("username"), "%" + username + "%");
        };
    }

    public static Specification<User> emailContains(String email) {
        return (root, query, builder) -> {
            if (ObjectUtils.isEmpty(email)) {
                return null;
            }
            return builder.like(root.get("email"), "%" + email + "%");
        };

    }

}
