package git.MatheusOliveira04.services.impls;

import git.MatheusOliveira04.models.User;
import git.MatheusOliveira04.models.filters.UserFilter;
import git.MatheusOliveira04.repositories.UserRepository;
import git.MatheusOliveira04.services.UserService;
import git.MatheusOliveira04.services.exception.IntegrityViolationException;
import git.MatheusOliveira04.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
    public Page<User> findAll(int page, int size, UserFilter userFilter) {
        Page<User> usersFound = userRepository.findAll(userFilter.toSpecification(), PageRequest.of(page, size));
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
                .orElseThrow(() -> new ObjectNotFoundException("User not found with email: " + email));
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
