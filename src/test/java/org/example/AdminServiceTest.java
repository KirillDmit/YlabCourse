package org.example;

import org.example.entity.User;
import org.example.repository.UserStorage;
import org.example.service.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

public class AdminServiceTest {

    private UserStorage userStorage;
    private AdminService adminService;

    @BeforeEach
    public void setUp() {
        userStorage = Mockito.mock(UserStorage.class);
        adminService = new AdminService(userStorage);
    }

    @Test
    public void blockUser_shouldBlockUser_whenUserExists() {
        // Arrange
        User user = new User(1L, "test@example.com", "password", "Test User");
        when(userStorage.findUserByEmail("test@example.com")).thenReturn(user);

        // Act
        adminService.blockUser("test@example.com");

        // Assert
        verify(userStorage, times(1)).findUserByEmail("test@example.com");
        assertThat(user.isBlocked()).isTrue();
    }

    @Test
    public void deleteUser_shouldRemoveUser_whenUserExists() {
        // Arrange
        when(userStorage.removeUserByEmail("test@example.com")).thenReturn(true);

        // Act
        adminService.deleteUser("test@example.com");

        // Assert
        verify(userStorage, times(1)).removeUserByEmail("test@example.com");
    }
}
