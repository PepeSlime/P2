package application.DAO;

import application.Util.Conexao;
import application.model.Genre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenreDAO {

    public void insert(Genre genre) {
        String sql = "INSERT INTO genre (name) VALUES (?)";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, genre.getName());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Genre getById(Long id) {
        String sql = "SELECT * FROM genre WHERE id = ?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Genre genre = new Genre();
                genre.setId(rs.getLong("id"));
                genre.setName(rs.getString("name"));
                return genre;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Genre> getAll() {
        List<Genre> genres = new ArrayList<>();
        String sql = "SELECT * FROM genre";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Genre genre = new Genre();
                genre.setId(rs.getLong("id"));
                genre.setName(rs.getString("name"));
                genres.add(genre);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return genres;
    }

    public void update(Genre genre) {
        String sql = "UPDATE genre SET name = ? WHERE id = ?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, genre.getName());
            stmt.setLong(2, genre.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Long id) {
        String sql = "DELETE FROM genre WHERE id = ?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
