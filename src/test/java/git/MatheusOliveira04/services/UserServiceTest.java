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
import java.util.UUID;

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
    @DisplayName("Find by id successfully")
    @Sql({"classpath:/sqls/create_users.sql"})
    void findUserByIdSuccessTest() {
        var userFound = userService.findById(UUID.fromString("53a1e0d8-887b-4c2c-973d-50d03803bd35"));
        assertNotNull(userFound);
        assertEquals(UUID.fromString("53a1e0d8-887b-4c2c-973d-50d03803bd35"), userFound.getId());
        assertEquals("test 1", userFound.getUsername());
        assertEquals("test1@example.com", userFound.getEmail());
        assertEquals("123", userFound.getPassword());
        assertTrue(user.getRoles().containsAll(List.of(Role.USER, Role.ADMIN)));
    }

    @Test
    @DisplayName("Error when find user by id with no user found")
    void findUserByIdWithNoUserFoundThrowsExceptionTest() {
        var exception = assertThrows(ObjectNotFoundException.class, () -> userService.findById(UUID.fromString("53a1e0d8-887b-4c2c-973d-50d03803bd35")));
        assertEquals("User not found with id: 53a1e0d8-887b-4c2c-973d-50d03803bd35", exception.getMessage());
    }

    @Test
    @DisplayName("Find by email succefully")
    @Sql({"classpath:/sqls/create_users.sql"})
    public void findUserByEmailSuccessTest() {
        var userFound = userService.findByEmail("test1@example.com");
        assertNotNull(userFound);
        assertEquals(UUID.fromString("53a1e0d8-887b-4c2c-973d-50d03803bd35"), userFound.getId());
        assertEquals("test 1", userFound.getUsername());
        assertEquals("test1@example.com", userFound.getEmail());
        assertEquals("123", userFound.getPassword());
        assertTrue(user.getRoles().containsAll(List.of(Role.USER, Role.ADMIN)));
    }

    @Test
    @DisplayName("Error when find user by id with no user found")
    void findUserByEmailWithNoUserFoundThrowsExceptionTest() {
        var exception = assertThrows(ObjectNotFoundException.class, () -> userService.findByEmail("test1@example.com"));
        assertEquals("User not found with email: test1@example.com", exception.getMessage());
    }

    @Test
    @DisplayName("Insert user successfully")
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

    @Test
    @DisplayName("Update user successfully")
    @Sql({"classpath:/sqls/create_users.sql"})
    void updateUserSuccessTest() {
        var user = new User(UUID.fromString("53a1e0d8-887b-4c2c-973d-50d03803bd35"), "test update", "update@test.com", "update12", List.of(Role.USER));
        var userUpdate = userService.update(user);
        assertEquals(UUID.fromString("53a1e0d8-887b-4c2c-973d-50d03803bd35"), userUpdate.getId());
        assertEquals("test update", userUpdate.getUsername());
        assertEquals("update@test.com", userUpdate.getEmail());
        assertEquals("update12", userUpdate.getPassword());
        assertTrue(userUpdate.getRoles().contains(Role.USER));
        assertEquals(1, userUpdate.getRoles().size());
    }

    @Test
    @DisplayName("Update user using the same email")
    @Sql({"classpath:/sqls/create_users.sql"})
    void updateUserUsingTheSameEmail() {
        var user = new User(
                UUID.fromString("53a1e0d8-887b-4c2c-973d-50d03803bd35"), "test update", "test1@example.com", "update12", List.of(Role.USER)
        );
        var userUpdate = userService.update(user);
        assertEquals(UUID.fromString("53a1e0d8-887b-4c2c-973d-50d03803bd35"), userUpdate.getId());
        assertEquals("test update", userUpdate.getUsername());
        assertEquals("test1@example.com", userUpdate.getEmail());
        assertEquals("update12", userUpdate.getPassword());
        assertTrue(userUpdate.getRoles().contains(Role.USER));
        assertEquals(1, userUpdate.getRoles().size());
    }

    @Test
    @DisplayName("Error when updating a user with id not found")
    void updateUserWithIdNotFoundThrowsExceptionTest() {
        var user = new User(
                UUID.fromString("11a1e0d8-887b-4c2c-973d-50d03803bd11"), "test update", "testUpdate@example.com", "update12", List.of(Role.USER)
        );
        var exception = assertThrows(ObjectNotFoundException.class, () -> userService.update(user));
        assertEquals("User not found with id: 11a1e0d8-887b-4c2c-973d-50d03803bd11", exception.getMessage());
    }

    @Test
    @DisplayName("Error when updating a user with duplicate emails")
    @Sql({"classpath:/sqls/create_users.sql"})
    void updateUserWithDuplicateEmailsThrowsExceptionTest() {
        var user = new User(
                UUID.fromString("53a1e0d8-887b-4c2c-973d-50d03803bd35"), "test update", "test2@example.com", "update12", List.of(Role.USER)
        );
        var exception = assertThrows(IntegrityViolationException.class, () -> userService.update(user));
        assertEquals("Email already exists", exception.getMessage());
    }

    @Test
    @DisplayName("Delete user successfully")
    @Sql({"classpath:/sqls/create_users.sql"})
    void deleteUserSuccess() {
        userService.delete(UUID.fromString("53a1e0d8-887b-4c2c-973d-50d03803bd35"));
        var usersFound = userService.findAll(0, 10, new UserFilter());
        assertEquals(2, usersFound.toList().size());
    }

    @Test
    @DisplayName("Delete user with id not found")
    void deleteUserWithIdNotFoundThrowsExceptionTest() {
        var exception = assertThrows(ObjectNotFoundException.class,
                () -> userService.delete(UUID.fromString("53a1e0d8-887b-4c2c-973d-50d03803bd35")));
        assertEquals("User not found with id: 53a1e0d8-887b-4c2c-973d-50d03803bd35", exception.getMessage());
    }
}
