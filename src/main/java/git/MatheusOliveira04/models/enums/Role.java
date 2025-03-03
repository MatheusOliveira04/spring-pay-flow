package git.MatheusOliveira04.models.enums;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum Role {
    USER("USER"),
    ADMIN("ADMIN");

    private final String value;

    private Role(String value) {
        this.value = value;
    }
}
