package git.MatheusOliveira04.controllers;

import git.MatheusOliveira04.models.User;
import git.MatheusOliveira04.models.dtos.reponse.UserResponse;
import git.MatheusOliveira04.models.dtos.request.UserRequest;
import git.MatheusOliveira04.models.mappers.UserMapper;
import git.MatheusOliveira04.services.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

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

    @Secured({"ROLE_USER"})
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable @NotNull UUID id) {
        return ResponseEntity.ok(userMapper.toUserResponse(userService.findById(id)));
    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping
    public ResponseEntity<UserResponse> insert(@RequestBody @Valid UserRequest userRequest, UriComponentsBuilder uriComponentsBuilder) {
        User user = userService.insert(userMapper.toUser(userRequest));
        return ResponseEntity
                .created(uriComponentsBuilder.path("/api/v1/user/{id}").buildAndExpand(user.getId()).toUri())
                .body(userMapper.toUserResponse(user));
    }

    @Secured({"ROLE_ADMIN"})
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@RequestBody @Valid UserRequest userRequest, @PathVariable @NotNull UUID id) {
        User user = userMapper.toUser(userRequest);
        user.setId(id);
        return ResponseEntity.ok(userMapper.toUserResponse(userService.update(user)));
    }

    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @NotNull UUID id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
