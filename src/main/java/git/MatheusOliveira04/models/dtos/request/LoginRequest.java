package git.MatheusOliveira04.models.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "The field cannot be empty")
        String email,
        @NotBlank(message = "The field cannot be empty")
        String password
) {
}