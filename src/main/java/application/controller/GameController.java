package application.controller;

import application.DAO.GameDAO;
import application.DAO.GenreDAO;
import application.DAO.PlatformDAO;
import application.model.Game;
import application.model.Genre;
import application.model.Platform;
import application.model.GameMode;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/game")
public class GameController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        GameDAO dao = new GameDAO();
        GenreDAO genreDAO = new GenreDAO();
        PlatformDAO platformDAO = new PlatformDAO();

        if (action == null) action = "listar";

        switch (action) {
            case "novo":
                request.setAttribute("generos", genreDAO.getAll());
                request.setAttribute("plataformas", platformDAO.getAll());
                request.getRequestDispatcher("jsp/jogo/cadastrar.jsp").forward(request, response);
                break;
            case "editar":
                Long idEditar = Long.parseLong(request.getParameter("id"));
                request.setAttribute("jogo", dao.getById(idEditar.longValue())); 
                request.setAttribute("generos", genreDAO.getAll());
                request.setAttribute("plataformas", platformDAO.getAll());
                request.getRequestDispatcher("jsp/jogo/editar.jsp").forward(request, response);
                break;
            case "excluir":
                Long idExcluir = Long.parseLong(request.getParameter("id"));
                dao.delete(idExcluir.longValue()); 
                response.sendRedirect("game?action=listar");
                break;
            default:
                request.setAttribute("jogos", dao.getAll());
                request.getRequestDispatcher("jsp/jogo/listar.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Game jogo = new Game();

        // Corrigindo nomes dos parâmetros conforme sua entidade
        jogo.setName(request.getParameter("titulo"));

        // Convertendo string para enum GameMode
        String modoJogoParam = request.getParameter("modoJogo");
        if (modoJogoParam != null && !modoJogoParam.isEmpty()) {
            try {
                jogo.setMode(GameMode.valueOf(modoJogoParam));
            } catch (IllegalArgumentException e) {
                jogo.setMode(null); // ou algum valor default
            }
        }

        // Apenas um gênero, não uma lista
        String generoSelecionado = request.getParameter("genero"); // só um id esperado
        if (generoSelecionado != null && !generoSelecionado.isEmpty()) {
            GenreDAO genreDAO = new GenreDAO();
            Genre genre = genreDAO.getById(Long.parseLong(generoSelecionado));
            jogo.setGenre(genre);
        }

        // Apenas uma plataforma, não uma lista
        String plataformaSelecionada = request.getParameter("plataforma"); // só um id esperado
        if (plataformaSelecionada != null && !plataformaSelecionada.isEmpty()) {
            PlatformDAO platformDAO = new PlatformDAO();
            Platform platform = platformDAO.getById(Long.parseLong(plataformaSelecionada));
            jogo.setPlatform(platform);
        }

        GameDAO dao = new GameDAO();

        String id = request.getParameter("id");
        if (id == null || id.isEmpty()) {
            dao.insert(jogo);
        } else {
            jogo.setId(Long.parseLong(id));
            dao.update(jogo);
        }

        response.sendRedirect("game?action=listar");
    }
}
