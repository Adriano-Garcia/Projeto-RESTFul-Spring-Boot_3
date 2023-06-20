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
import dominando.restful.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Users", description = "Endpoints for Managing Users")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@GetMapping(
			produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(summary = "Finds all User", description = "Finds all User",
	tags = {"Users"},
	responses = {
		@ApiResponse(description = "Success", responseCode = "200",
			content = {
				@Content(
					mediaType = "application/json",
					array = @ArraySchema(schema = @Schema(implementation = UserResponseDTO.class))
				)
			}),
		@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
		@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
		@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
		@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
	}
)
	public ResponseEntity<List<UserResponseDTO>> findAll(){
		var entity = service.findAll();
		return ResponseEntity.ok().body(entity);	
	}
	
	@GetMapping(value = "/{id}", 
			produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(summary = "Finds a User", description = "Finds a User",
	tags = {"Users"},
	responses = {
		@ApiResponse(description = "Success", responseCode = "200",
			content = @Content(schema = @Schema(implementation = UserResponseDTO.class))
		),
		@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
		@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
		@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
		@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
		@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
	}
)
	public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id){
		var entity = service.findById(id);
		return ResponseEntity.ok().body(entity);	
	}
	
	@PostMapping(
			consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML  },
			produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML  })
		@Operation(summary = "Adds a new User",
			description = "Adds a new User by passing in a JSON, XML or YML representation of the user!",
			tags = {"Users"},
			responses = {
				@ApiResponse(description = "Success", responseCode = "201",
					content = @Content(schema = @Schema(implementation = UserResponseDTO.class))
				),
				@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
				@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
				@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
			}
		)
	public ResponseEntity<UserResponseDTO> create(@RequestBody UserRequestDTO userRequest){
		var entity = service.create(userRequest);
		return new ResponseEntity<>(entity, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{id}",
					consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML  },
					produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML  })
				@Operation(summary = "Updates a User",
					description = "Updates a User by passing in a JSON, XML or YML representation of the user!",
					tags = {"Users"},
					responses = {
						@ApiResponse(description = "Updated", responseCode = "200",
							content = @Content(schema = @Schema(implementation = UserResponseDTO.class))
						),
						@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
						@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
						@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
						@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
					}
				)
	public ResponseEntity<UserResponseDTO> update(@PathVariable Long id, @RequestBody UserRequestDTO userRequest){
		var entity = service.update(id, userRequest);
		return ResponseEntity.ok().body(entity);
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Deletes a User",
	description = "Deletes a User by passing in a JSON, XML or YML representation of the user!",
	tags = {"Users"},
	responses = {
		@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
		@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
		@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
		@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
		@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
	}
)
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
