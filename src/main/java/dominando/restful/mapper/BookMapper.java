package dominando.restful.mapper;

import org.mapstruct.Mapper;

import dominando.restful.model.Book;
import dominando.restful.model.DTO.BookRequestDTO;
import dominando.restful.model.DTO.BookResponseDTO;

@Mapper(componentModel = "spring")
public interface BookMapper {
	
	BookResponseDTO toBookResponse(Book book);
	BookRequestDTO toBookRequest(Book book);
	Book toBookInResponse(BookResponseDTO bookResponse);
	Book toBookInRequest(BookRequestDTO bookRequest);
	
}
