package git.MatheusOliveira04.models.dtos.request;

import jakarta.validation.constraints.NotEmpty;

public record LoginRequest(
        @NotEmpty(message = "The field cannot be empty")
        String email,
        @NotEmpty(message = "The field cannot be empty")
        String password
) {
}