package Chaeda_spring.domain.textbook.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TextbookRespository extends JpaRepository<Textbook, Long> {

    Optional<Textbook> findByName(String name);
}
