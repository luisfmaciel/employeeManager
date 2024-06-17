package br.edu.infnet.employeemanager.controller;

import br.edu.infnet.employeemanager.model.Employee;
import br.edu.infnet.employeemanager.model.User;
import br.edu.infnet.employeemanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User registeredEmployee = userService.registerUser(user);
        return ResponseEntity.ok(registeredEmployee);
    }
}
