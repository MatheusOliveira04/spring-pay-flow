package git.MatheusOliveira04.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import git.MatheusOliveira04.models.User;
import git.MatheusOliveira04.models.enums.Role;
import git.MatheusOliveira04.models.filters.UserFilter;
import git.MatheusOliveira04.services.exception.IntegrityViolationException;
import git.MatheusOliveira04.services.exception.ObjectNotFoundException;
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
    @DisplayName("Find all users successfully")
    @Sql({"classpath:/sqls/create_users.sql"})
    void findAllUsersSuccessTest() {
        var usersFound = userService.findAll(0, 100, new UserFilter());
        assertNotNull(usersFound);
        assertEquals(3, usersFound.toList().size());
    }

    @Test
    @DisplayName("Find all users using pagination with a page and size parameter getting others pages successfully")
    @Sql({"classpath:/sqls/create_users.sql"})
    void findAllUsersUsingPaginationWithPageAndSizeParameterGettingOthersPagesSuccessTest() {
        var usersFound = userService.findAll(1, 2, new UserFilter());
        assertNotNull(usersFound);
        assertEquals(1, usersFound.toList().size());
    }

    @Test
    @DisplayName("Find all users using username filter successfully")
    @Sql({"classpath:/sqls/create_users.sql"})
    void findAllUsersUsingUsernameFilterSuccessTest() {
        var userFilter = new UserFilter();
        userFilter.setUsername("test 1");
        var userFound = userService.findAll(0, 3, userFilter);
        assertEquals(1, userFound.toList().size());
        userFilter.setUsername("test");
        userFound = userService.findAll(0, 3, userFilter);
        assertEquals(3, userFound.toList().size());
    }

    @Test
    @DisplayName("Find all users using email filter successfully")
    @Sql({"classpath:/sqls/create_users.sql"})
    void findAllUsersUsingEmailFilterSuccessTest() {
        var userFilter = new UserFilter();
        userFilter.setEmail("test2@example.com");
        var userFound = userService.findAll(0, 3, userFilter);
        assertEquals(1, userFound.toList().size());
    }

    @Test
    @DisplayName("Error when finding all users with no users")
    void findAllUsersWithPaginationWithSizeParameterAndNoUsersThrowsExceptionWhenNoUsersFoundTest() {
        var exception = assertThrows(ObjectNotFoundException.class, () -> userService.findAll(0, 100, new UserFilter()));
        assertEquals("No User found.", exception.getMessage());
    }

    @Test
    @DisplayName("Error when finding all users using pagination with a page parameter and no users")
    @Sql({"classpath:/sqls/create_users.sql"})
    void findAllUsersWithPaginationWithPageParameterAndNoUsersThrowsExceptionWhenNoUsersFoundTest() {
        var exception = assertThrows(ObjectNotFoundException.class, () -> userService.findAll(1, 100, new UserFilter()));
        assertEquals("No User found.", exception.getMessage());
    }

    @Test
    @DisplayName("insert user successfully")
    @Sql({"classpath:/sqls/create_users.sql"})
    void insertUserSuccessTest() {
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
    @DisplayName("Error when inserting a user with duplicate emails")
    @Sql({"classpath:/sqls/create_users.sql"})
    void insertUserWithDuplicateEmailsThrowsExceptionTest() {
        var user2 = new User(null, "inserting test", "test@example.com", "12345", List.of(Role.USER, Role.ADMIN));
        var userInsert1 = userService.insert(user);
        assertNotNull(userInsert1);
        var exception = assertThrows(IntegrityViolationException.class, () -> userService.insert(user2));
        assertEquals("Email already exists", exception.getMessage());
    }

}
