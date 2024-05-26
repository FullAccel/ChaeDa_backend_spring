package Chaeda_spring.domain.File.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {

    Optional<Image> findByImageKey(String imageKey);
}
