package git.MatheusOliveira04.controllers;

import git.MatheusOliveira04.models.User;
import git.MatheusOliveira04.models.dtos.reponse.UserResponse;
import git.MatheusOliveira04.models.mappers.impls.UserMapper;
import git.MatheusOliveira04.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;


    @Secured({"ROLE_USER"})
    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll() {
        return ResponseEntity.ok(userService.findAll().stream().map(user -> userMapper.toUserResponse(user)).toList());
    }
}
