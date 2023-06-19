package dominando.restful.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dominando.restful.model.Book;

public interface BookRepository extends JpaRepository<Book, Long>{}
