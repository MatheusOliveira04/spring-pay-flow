package git.MatheusOliveira04.services;

import git.MatheusOliveira04.models.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findByEmail(String email);

}
