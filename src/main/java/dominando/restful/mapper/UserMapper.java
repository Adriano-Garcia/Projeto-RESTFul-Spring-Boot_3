package dominando.restful.mapper;

import org.mapstruct.Mapper;

import dominando.restful.model.User;
import dominando.restful.model.DTO.UserRequestDTO;
import dominando.restful.model.DTO.UserResponseDTO;

@Mapper(componentModel = "spring")
public interface UserMapper {
	
	UserResponseDTO toUserResponseDTO(User user);
	UserRequestDTO toUserResquestDto(User user);
	User toUserInResquest(UserRequestDTO userRequest);

}
