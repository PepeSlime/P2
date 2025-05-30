package application.DAO;

import application.Util.Conexao;
import application.model.Platform;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlatformDAO {

    public void insert(Platform platform) {
        String sql = "INSERT INTO platform (name) VALUES (?)";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, platform.getName());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Platform getById(Long id) {
        String sql = "SELECT * FROM platform WHERE id = ?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Platform platform = new Platform();
                platform.setId(rs.getLong("id"));
                platform.setName(rs.getString("name"));
                return platform;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Platform> getAll() {
        List<Platform> platforms = new ArrayList<>();
        String sql = "SELECT * FROM platform";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Platform platform = new Platform();
                platform.setId(rs.getLong("id"));
                platform.setName(rs.getString("name"));
                platforms.add(platform);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return platforms;
    }

    public void update(Platform platform) {
        String sql = "UPDATE platform SET name = ? WHERE id = ?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, platform.getName());
            stmt.setLong(2, platform.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Long id) {
        String sql = "DELETE FROM platform WHERE id = ?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
