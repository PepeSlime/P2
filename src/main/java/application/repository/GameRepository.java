package application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import application.model.Game;

public interface GameRepository extends JpaRepository<Game, Long> {
}
