package git.MatheusOliveira04.models.enums.converters;

import git.MatheusOliveira04.models.enums.Role;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.NoArgsConstructor;

import java.util.stream.Stream;

@NoArgsConstructor
@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<Role, String> {

    @Override
    public String convertToDatabaseColumn(Role role) {
        if (role == null) {
            return null;
        }
        return role.getValue();
    }

    @Override
    public Role convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        return Stream.of(Role.values())
                .filter(role -> role.getValue().equals(value))
                .findFirst()
                .orElseThrow(IllegalAccessError::new);
    }
}
