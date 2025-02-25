package git.MatheusOliveira04.services.impls;

import git.MatheusOliveira04.models.User;
import git.MatheusOliveira04.repositories.UserRepository;
import git.MatheusOliveira04.services.exception.ObjectNotFoundException;
import git.MatheusOliveira04.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        List<User> usersFound = userRepository.findAll();
        if (usersFound.isEmpty()) {
            throw new ObjectNotFoundException("No User found.");
        }
        return usersFound;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository
                .findByEmail(email)
                .orElseThrow(() -> new ObjectNotFoundException("User not found with username: " + email));
    }
}
