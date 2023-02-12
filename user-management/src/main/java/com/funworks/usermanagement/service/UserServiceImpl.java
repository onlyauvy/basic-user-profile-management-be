package com.funworks.usermanagement.service;

import com.funworks.usermanagement.config.JwtService;
import com.funworks.usermanagement.model.AuthenticationRequest;
import com.funworks.usermanagement.model.AuthenticationResponse;
import com.funworks.usermanagement.model.User;
import com.funworks.usermanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User signupUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public AuthenticationResponse loginUser(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
        //return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public User changeUserPassword(String oldPassword, String newPassword) {
        User user = new User();
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> searchUser(String firstName, String lastName) {
        return userRepository.findByFirstNameLikeIgnoreCaseOrLastNameLikeIgnoreCase(firstName, lastName).orElse(null);
    }

    @Override
    public String userForgetPassword(String email) {
        return null;
    }
}
