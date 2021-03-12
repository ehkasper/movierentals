package com.movierental.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Arrays.asList;

@RestController
public class UsersController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<User> save(@RequestBody UserRequest userRequest) {
        User newUser = new User();
        newUser.setUsername(userRequest.getUsername());
        newUser.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        newUser.setEnabled(true);
        User user = userRepository.save(newUser);
        return ResponseEntity.accepted().body(user);
    }

    static class UserRequest {
        private String username;
        private String password;
        public UserRequest() {}

        public UserRequest(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
