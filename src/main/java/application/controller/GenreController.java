package application.controller;

import application.DAO.GenreDAO;
import application.model.Genre;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/genre")
public class GenreController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        GenreDAO dao = new GenreDAO();

        if (action == null) action = "listar";

        switch (action) {
            case "novo":
                request.getRequestDispatcher("jsp/genero/cadastrar.jsp").forward(request, response);
                break;
            case "editar":
                Long idEditar = Long.parseLong(request.getParameter("id"));
                request.setAttribute("genero", dao.getById(idEditar));
                request.getRequestDispatcher("jsp/genero/editar.jsp").forward(request, response);
                break;
            case "excluir":
                Long idExcluir = Long.parseLong(request.getParameter("id"));
                dao.delete(idExcluir);
                response.sendRedirect("genre?action=listar");
                break;
            default:
                request.setAttribute("generos", dao.getAll());
                request.getRequestDispatcher("jsp/genero/listar.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Genre genero = new Genre();
        genero.setName(request.getParameter("nome"));

        GenreDAO dao = new GenreDAO();

        String id = request.getParameter("id");
        if (id == null || id.isEmpty()) {
            dao.insert(genero);
        } else {
            genero.setId(Long.parseLong(id));
            dao.update(genero);
        }

        response.sendRedirect("genre?action=listar");
    }
}
