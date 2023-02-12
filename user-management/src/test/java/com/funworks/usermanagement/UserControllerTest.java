package com.funworks.usermanagement;

import com.funworks.usermanagement.model.Gender;
import com.funworks.usermanagement.model.Role;
import com.funworks.usermanagement.model.User;
import com.funworks.usermanagement.repository.UserRepository;
import com.funworks.usermanagement.service.UserService;
import com.funworks.usermanagement.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    private User user1, user2;
    private List<User> userList;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        user1 = new User(1l, "Abdullah", "Mamun", "17-NOV-1992",
                "auvyauvy@gmail.com", "01751015599", Gender.MALE, "Gopibag, Motijheel", "password",
                Role.USER,
                null);
        user2 = new User(1l, "Tanzima", "Azad", "01-JAN-1988",
                "tanzima@gmail.com", "01751015588", Gender.FEMALE, "Sydne, Australia", "password",
                Role.USER,
                null);

        userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
    }

    @Test
    void userLogin() {
    }

    @Test
    void searchUser() {
    }

    @Test
    void getAllUsers() {
        when(userRepository.findAll()).thenReturn(userList);
        assertEquals(2, userService.getAllUsers().size());
    }

    @Test
    void signupUser() {
        when(userRepository.save(any())).thenReturn(user1);

        User user = userService.signupUser(user1);
        assertNotNull(user);
    }

    @Test
    void changeUserPassword() {
    }

    @Test
    void updateUser() {
        when(userRepository.save(any())).thenReturn(user2);

        User user = userService.signupUser(user2);
        assertNotNull(user);
        user.setMobile("01751012288");

        User updatedUser = userService.updateUser(user);
        assertEquals("01751012288", updatedUser.getMobile());
    }

    @Test
    void userForgetPassword() {
    }
}