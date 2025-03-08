package git.MatheusOliveira04.models.dtos.reponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserPageResponse {
    private List<UserResponse> users;
    private Long totalItems;
    private Integer totalPages;
}
