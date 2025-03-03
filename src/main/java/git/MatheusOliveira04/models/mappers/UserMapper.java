package git.MatheusOliveira04.models.mappers;

import git.MatheusOliveira04.models.User;
import git.MatheusOliveira04.models.dtos.reponse.UserResponse;
import git.MatheusOliveira04.models.dtos.request.UserRequest;

public interface UserMapper {

    public UserResponse toUserResponse(User user);
    public User toUser(UserRequest userRequest);
}
