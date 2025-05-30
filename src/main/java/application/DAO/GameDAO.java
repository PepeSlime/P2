package application.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import application.Util.Conexao;
import application.model.Game;
import application.model.Genre;
import application.model.Platform;

public class GameDAO {

    public void insert(Game game) {
        String sql = "INSERT INTO jogo (nome, genero_id, plataforma_id) VALUES (?, ?, ?)";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, game.getName());
            stmt.setLong(2, game.getGenre().getId());
            stmt.setLong(3, game.getPlatform().getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Game> getAll() {
        List<Game> games = new ArrayList<>();
        String sql = "SELECT j.id AS jogo_id, j.nome AS jogo_nome, " +
                     "g.id AS genre_id, g.nome AS genre_name, " +
                     "p.id AS platform_id, p.nome AS platform_name " +
                     "FROM jogo j " +
                     "JOIN genero g ON j.genero_id = g.id " +
                     "JOIN plataforma p ON j.plataforma_id = p.id";

        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Game game = new Game();
                game.setId(rs.getLong("jogo_id"));
                game.setName(rs.getString("jogo_nome"));

                Genre genre = new Genre(rs.getLong("genre_id"), rs.getString("genre_name"));
                Platform platform = new Platform(rs.getLong("platform_id"), rs.getString("platform_name"));

                game.setGenre(genre);
                game.setPlatform(platform);

                games.add(game);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return games;
    }

    public Game getById(Long id) {
        Game game = null;
        String sql = "SELECT * FROM jogo WHERE id = ?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                game = new Game();
                game.setId(rs.getLong("id"));
                game.setName(rs.getString("nome"));

                GenreDAO genreDAO = new GenreDAO();
                PlatformDAO platformDAO = new PlatformDAO();

                game.setGenre(genreDAO.getById(rs.getLong("genero_id")));
                game.setPlatform(platformDAO.getById(rs.getLong("plataforma_id")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return game;
    }

    public void update(Game game) {
        String sql = "UPDATE jogo SET nome = ?, genero_id = ?, plataforma_id = ? WHERE id = ?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, game.getName());
            stmt.setLong(2, game.getGenre().getId());
            stmt.setLong(3, game.getPlatform().getId());
            stmt.setLong(4, game.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Long id) {
        String sql = "DELETE FROM jogo WHERE id = ?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
