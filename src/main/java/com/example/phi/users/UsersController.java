package com.example.phi.users;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Arrays.asList;

@RestController
public class UsersController {
    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<User>> users() {
        return ResponseEntity.ok(asList(new User(1, "user1", "password")));
    }

    @PostMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<String> save(@RequestBody UserRequest userRequest) {
        return ResponseEntity.accepted().body("user created");
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
