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

import dominando.restful.model.DTO.BookRequestDTO;
import dominando.restful.model.DTO.BookResponseDTO;
import dominando.restful.services.BookService;
import dominando.restful.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/books")
@Tag(name = "Books", description = "Endpoints for Managing Books")
public class BookController {
	
	@Autowired
	private BookService service;
	
	@GetMapping(
			produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(summary = "Finds all Book", description = "Finds all Book",
	tags = {"Books"},
	responses = {
		@ApiResponse(description = "Success", responseCode = "200",
			content = {
				@Content(
					mediaType = "application/json",
					array = @ArraySchema(schema = @Schema(implementation = BookResponseDTO.class))
				)
			}),
		@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
		@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
		@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
		@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
	}
)
	public ResponseEntity<List<BookResponseDTO>> findAll(){
		var entity = service.findAll();
		return ResponseEntity.ok().body(entity);	
	}
	
	@GetMapping(value = "/{id}", 
			produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(summary = "Finds a Book", description = "Finds a Book",
	tags = {"Books"},
	responses = {
		@ApiResponse(description = "Success", responseCode = "200",
			content = @Content(schema = @Schema(implementation = BookResponseDTO.class))
		),
		@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
		@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
		@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
		@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
		@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
	}
)
	public ResponseEntity<BookResponseDTO> findById(@PathVariable Long id){
		var entity = service.findById(id);
		return ResponseEntity.ok().body(entity);	
	}
	
	@PostMapping(
			consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML  },
			produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML  })
		@Operation(summary = "Adds a new Book",
			description = "Adds a new Book by passing in a JSON, XML or YML representation of the book!",
			tags = {"Books"},
			responses = {
				@ApiResponse(description = "Success", responseCode = "201",
					content = @Content(schema = @Schema(implementation = BookResponseDTO.class))
				),
				@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
				@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
				@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
			}
		)
	public ResponseEntity<BookResponseDTO> create(@RequestBody BookRequestDTO bookRequest){
		var entity = service.create(bookRequest);
		return new ResponseEntity<>(entity, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{id}",
					consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML  },
					produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML  })
				@Operation(summary = "Updates a Book",
					description = "Updates a Book by passing in a JSON, XML or YML representation of the book!",
					tags = {"Books"},
					responses = {
						@ApiResponse(description = "Updated", responseCode = "200",
							content = @Content(schema = @Schema(implementation = BookResponseDTO.class))
						),
						@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
						@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
						@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
						@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
					}
				)
	public ResponseEntity<BookResponseDTO> update(@PathVariable Long id, @RequestBody BookRequestDTO bookRequest){
		var entity = service.update(id, bookRequest);
		return ResponseEntity.ok().body(entity);
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Deletes a Book",
	description = "Deletes a Book by passing in a JSON, XML or YML representation of the book!",
	tags = {"Books"},
	responses = {
		@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
		@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
		@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
		@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
		@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
	}
)
	public ResponseEntity<Void> delete(Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
