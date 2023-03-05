package com.funworks.usermanagement.service;

import com.funworks.usermanagement.model.AuthenticationRequest;
import com.funworks.usermanagement.model.AuthenticationResponse;
import com.funworks.usermanagement.model.User;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface UserService {

    User signupUser(User user);

    User getUserByEmail(String email);

    AuthenticationResponse loginUser(AuthenticationRequest request) throws ExecutionException, InterruptedException;

    User changeUserPassword(String oldPassword, String newPassword);

    User updateUser(User user);

    List<User> getAllUsers();
    List<User> searchUser(String firstName, String lastName) throws ExecutionException, InterruptedException;

    String userForgetPassword(String email);
}
