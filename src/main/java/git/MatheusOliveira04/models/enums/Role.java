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

    public static Role parse(String value) {
        if (value == null) {
            return null;
        }
        return Stream.of(Role.values())
                .filter(role -> role.getValue().equals(value))
                .findFirst()
                .orElseThrow(IllegalAccessError::new);
    }
}
