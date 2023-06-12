package dominando.restful.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dominando.restful.model.DTO.UserRequestDTO;
import dominando.restful.model.DTO.UserResponseDTO;
import dominando.restful.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@GetMapping
	public ResponseEntity<List<UserResponseDTO>> findAll(){
		var entity = service.findAll();
		return ResponseEntity.ok().body(entity);	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id){
		var entity = service.findById(id);
		return ResponseEntity.ok().body(entity);	
	}
	
	@PostMapping
	public ResponseEntity<UserResponseDTO> create(@RequestBody UserRequestDTO userRequest){
		var entity = service.create(userRequest);
		return new ResponseEntity<>(entity, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserResponseDTO> update(@PathVariable Long id, @RequestBody UserRequestDTO userRequest){
		var entity = service.update(id, userRequest);
		return ResponseEntity.ok().body(entity);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
