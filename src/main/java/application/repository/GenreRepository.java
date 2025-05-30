package application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import application.model.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
