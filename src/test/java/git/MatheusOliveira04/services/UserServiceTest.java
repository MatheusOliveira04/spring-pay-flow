package git.MatheusOliveira04.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import git.MatheusOliveira04.models.User;
import git.MatheusOliveira04.models.enums.Role;
import git.MatheusOliveira04.models.filters.UserFilter;
import git.MatheusOliveira04.services.exception.IntegrityViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class UserServiceTest extends BaseTest {

    @Autowired
    UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User(null, "inserting test", "test@example.com", "12345", List.of(Role.USER, Role.ADMIN));
    }

    @Test
    @DisplayName("Find all users with success")
    @Sql({"classpath:/sqls/create_users.sql"})
    void findAllTestSuccess() {
        var usersFound = userService.findAll(0, 100, new UserFilter());
        assertNotNull(usersFound);
        assertEquals(3, usersFound.toList().size());
    }

    @Test
    @DisplayName("insert user success")
    @Sql({"classpath:/sqls/create_users.sql"})
    void insertTestSuccess() {
        var userInsert = userService.insert(user);
        assertNotNull(userInsert);
        assertNotNull(userInsert.getId());
        assertEquals("inserting test", userInsert.getUsername());
        assertEquals("test@example.com", userInsert.getEmail());
        assertEquals("12345", userInsert.getPassword());
        assertTrue(userInsert.getRoles().containsAll(List.of(Role.USER, Role.ADMIN)));
        assertEquals(2, userInsert.getRoles().size());
    }

    @Test
    @DisplayName("insert user error using two emails equals")
    @Sql({"classpath:/sqls/create_users.sql"})
    void insertWithTwoEmailsEqualsTestError() {
        var user2 = new User(null, "inserting test", "test@example.com", "12345", List.of(Role.USER, Role.ADMIN));
        var userInsert1 = userService.insert(user);
        assertNotNull(userInsert1);
        var exception = assertThrows(IntegrityViolationException.class, () -> userService.insert(user2));
        assertEquals("Email already exists", exception.getMessage());
    }

}
