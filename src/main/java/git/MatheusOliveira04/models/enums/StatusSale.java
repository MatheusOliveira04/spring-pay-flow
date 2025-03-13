package git.MatheusOliveira04.models.enums;

import lombok.Getter;
import org.springframework.util.ObjectUtils;

import java.util.Objects;
import java.util.stream.Stream;

@Getter
public enum StatusSale {
    DUE("Due"),
    RECEIVED("Received"),
    NOT_RECEIVED("Not received");

    private final String value;

    private StatusSale(String value) {
        this.value = value;
    }

    public StatusSale parse(String value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }

        return Stream.of(StatusSale.values())
                .filter(role -> role.getValue().equals(value))
                .findFirst()
                .orElseThrow(IllegalAccessError::new);
    }
}