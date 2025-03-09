package git.MatheusOliveira04.models.mappers;

import git.MatheusOliveira04.models.User;
import git.MatheusOliveira04.models.dtos.reponse.UserResponse;
import git.MatheusOliveira04.models.dtos.request.UserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    User toUser(UserRequest userRequest);

    UserResponse toUserResponse(User  user);

    List<UserResponse> toUserResponse(List<User> users);
}
