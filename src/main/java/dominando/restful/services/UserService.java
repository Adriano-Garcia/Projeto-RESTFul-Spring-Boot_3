package dominando.restful.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dominando.restful.exceptions.ResourceNotFoundException;
import dominando.restful.mapper.UserMapper;
import dominando.restful.model.DTO.UserResponseDTO;
import dominando.restful.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private UserMapper mapper;

	public List<UserResponseDTO> findAll() {
		var listUser = repository.findAll();
		List<UserResponseDTO> listResponse = listUser.stream().map(p -> mapper.toUserResponseDTO(p))
				.collect(Collectors.toList());
		return listResponse;
	}
	
	public UserResponseDTO findById(Long id) {
		var user = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para este id!"));
		UserResponseDTO userResponse = mapper.toUserResponseDTO(user);
		return userResponse;
	}
}
