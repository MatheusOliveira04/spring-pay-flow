package git.MatheusOliveira04.services.impls;

import git.MatheusOliveira04.models.User;
import git.MatheusOliveira04.repositories.UserRepository;
import git.MatheusOliveira04.services.exception.IntegrityViolationException;
import git.MatheusOliveira04.services.exception.ObjectNotFoundException;
import git.MatheusOliveira04.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private void validateEmailIsUnique(User user) {
        Optional<User> userFound = userRepository.findByEmail(user.getEmail());
        if (userFound.isPresent() && user.getId() != userFound.get().getId()) {
            throw new IntegrityViolationException("Email already exists");
        }
    }

    @Override
    public List<User> findAll() {
        List<User> usersFound = userRepository.findAll();
        if (usersFound.isEmpty()) {
            throw new ObjectNotFoundException("No User found.");
        }
        return usersFound;
    }

    @Override
    public User findById(UUID id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("User not found with id: " + id));
    }

    @Override
    public User findByEmail(String email) {
        return userRepository
                .findByEmail(email)
                .orElseThrow(() -> new ObjectNotFoundException("User not found with username: " + email));
    }

    @Override
    public User insert(User user) {
        validateEmailIsUnique(user);
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        findById(user.getId());
        validateEmailIsUnique(user);
        return userRepository.save(user);
    }

    @Override
    public void delete(UUID id) {
        userRepository.delete(findById(id));
    }
}
