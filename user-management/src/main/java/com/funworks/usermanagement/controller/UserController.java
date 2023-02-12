package com.funworks.usermanagement.controller;

import com.funworks.usermanagement.model.AuthenticationRequest;
import com.funworks.usermanagement.model.AuthenticationResponse;
import com.funworks.usermanagement.model.User;
import com.funworks.usermanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @PostMapping("/auth/signup")
    public User signupUser(@RequestBody User user){
        LOGGER.info("Sign up request received: "+user.toString());
        return userService.signupUser(user);
    }

    @PostMapping("/auth/login")
    public AuthenticationResponse userLogin(@RequestBody AuthenticationRequest request) throws ExecutionException, InterruptedException {
        return userService.loginUser(request);
    }

    @PostMapping("/search")
    public List<User> searchUser(String firstName, String lastName) throws ExecutionException, InterruptedException {
        return userService.searchUser(firstName, lastName);
    }

    @GetMapping("/all")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/change_pass")
    public User changeUserPassword(String oldPassword, String newPassword){
        return userService.changeUserPassword(oldPassword, newPassword);
    }

    @PostMapping("/update")
    public User updateUser(User user){
        return userService.updateUser(user);
    }

    @PostMapping("/forget_pass")
    public String userForgetPassword(String email){
        return userService.userForgetPassword(email);
    }

}
