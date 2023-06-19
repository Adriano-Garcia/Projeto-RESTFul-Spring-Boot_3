package dominando.restful.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dominando.restful.controllers.BookController;
import dominando.restful.exceptions.RequiredObjectIsNullException;
import dominando.restful.exceptions.ResourceNotFoundException;
import dominando.restful.mapper.BookMapper;
import dominando.restful.model.DTO.BookRequestDTO;
import dominando.restful.model.DTO.BookResponseDTO;
import dominando.restful.repositories.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository repository;

	@Autowired
	private BookMapper mapper;

	public List<BookResponseDTO> findAll() {
		var listBook = repository.findAll();
		List<BookResponseDTO> listResponse = listBook.stream().map(p -> mapper.toBookResponse(p))
				.collect(Collectors.toList());
		listResponse
		.stream()
		.forEach(p -> p.add(linkTo(methodOn(BookController.class).findById(p.getId())).withSelfRel()));
		return listResponse;
	}
	
	public BookResponseDTO findById(Long id) {
		var book = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para este id!"));
		BookResponseDTO bookResponse = mapper.toBookResponse(book);
		bookResponse.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
		return bookResponse;
	}
	
	public BookResponseDTO create(BookRequestDTO bookRequest) {
		if (bookRequest == null) throw new RequiredObjectIsNullException();
		var book = mapper.toBookInRequest(bookRequest);
		var bookResponse = mapper.toBookResponse(repository.save(book));
		bookResponse.add(linkTo(methodOn(BookController.class).findById(bookResponse.getId())).withSelfRel());
		return bookResponse;
	}
	
	public BookResponseDTO update(Long id, BookRequestDTO bookRequest) {
		var book = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para este id!"));
		if (bookRequest == null) throw new RequiredObjectIsNullException();
		book.setAuthor(bookRequest.getAuthor());
		book.setLaunchDate(bookRequest.getLaunchDate());
		book.setPrice(bookRequest.getPrice());
		book.setTitle(bookRequest.getTitle());
		var bookResponse = mapper.toBookResponse(repository.save(book));
		bookResponse.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
		return bookResponse;
	}
	
	public void delete(Long id) {
		var book = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para este id!"));
		repository.delete(book);
	}
}
