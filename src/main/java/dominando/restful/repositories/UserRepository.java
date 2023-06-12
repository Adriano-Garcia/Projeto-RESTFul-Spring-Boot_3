package dominando.restful.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dominando.restful.model.User;

public interface UserRepository extends JpaRepository<User, Long>{}
