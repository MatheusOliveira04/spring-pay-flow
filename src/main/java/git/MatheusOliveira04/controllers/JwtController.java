package git.MatheusOliveira04.controllers;

import git.MatheusOliveira04.configurations.jwts.JwtUtil;
import git.MatheusOliveira04.models.dtos.request.LoginRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class JwtController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager auth;

    @PostMapping("/token")
    public String authenticateAndGetToken(@RequestBody @Valid LoginRequest login) {
        Authentication authentication = auth.authenticate(
                new UsernamePasswordAuthenticationToken(login.email(), login.password()));
        if (authentication.isAuthenticated()) {
            return jwtUtil.generateToken(login.email());
        } else {
            throw new UsernameNotFoundException("Invalid user");
        }

    }
}
