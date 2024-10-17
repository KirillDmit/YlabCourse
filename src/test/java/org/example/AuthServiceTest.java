package org.example;

import org.example.entity.User;
import org.example.repository.UserStorage;
import org.example.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class AuthServiceTest {

    private UserStorage userStorage;
    private AuthService authService;

    @BeforeEach
    public void setUp() {
        userStorage = Mockito.mock(UserStorage.class);
        authService = new AuthService(userStorage);
    }

    @Test
    public void registerUser_shouldAddUser_whenUserDoesNotExist() {
        // Arrange
        String email = "test@example.com";
        String password = "password";
        String name = "Test User";
        when(userStorage.userExists(email)).thenReturn(false);

        // Act
        authService.registerUser(email, password, name);

        // Assert
        verify(userStorage, times(1)).addUser(any(User.class));
    }

    @Test
    public void registerUser_shouldNotAddUser_whenUserExists() {
        // Arrange
        String email = "test@example.com";
        when(userStorage.userExists(email)).thenReturn(true);

        // Act
        authService.registerUser(email, "password", "Test User");

        // Assert
        verify(userStorage, never()).addUser(any(User.class));
    }

    @Test
    public void loginUser_shouldReturnUser_whenCredentialsAreValid() {
        // Arrange
        String email = "test@example.com";
        String password = "password";
        User user = new User(1L, email, password, "Test User");
        when(userStorage.getUserByEmail(email)).thenReturn(user);

        // Act
        User result = authService.loginUser(email, password);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo(email);
    }

    @Test
    public void loginUser_shouldReturnNull_whenPasswordIsIncorrect() {
        // Arrange
        String email = "test@example.com";
        User user = new User(1L, email, "password", "Test User");
        when(userStorage.getUserByEmail(email)).thenReturn(user);

        // Act
        User result = authService.loginUser(email, "wrong_password");

        // Assert
        assertThat(result).isNull();
    }
}
