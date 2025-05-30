package application.controller;

import application.DAO.PlatformDAO;
import application.model.Platform;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/platform")
public class PlatformController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        PlatformDAO dao = new PlatformDAO();

        if (action == null) action = "listar";

        switch (action) {
            case "novo":
                request.getRequestDispatcher("jsp/plataforma/cadastrar.jsp").forward(request, response);
                break;
            case "editar":
                Long idEditar = Long.parseLong(request.getParameter("id"));
                request.setAttribute("plataforma", dao.getById(idEditar));
                request.getRequestDispatcher("jsp/plataforma/editar.jsp").forward(request, response);
                break;
            case "excluir":
                Long idExcluir = Long.parseLong(request.getParameter("id"));
                dao.delete(idExcluir);
                response.sendRedirect("platform?action=listar");
                break;
            default:
                request.setAttribute("plataformas", dao.getAll());
                request.getRequestDispatcher("jsp/plataforma/listar.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Platform plataforma = new Platform();
        plataforma.setName(request.getParameter("nome"));

        PlatformDAO dao = new PlatformDAO();

        String id = request.getParameter("id");
        if (id == null || id.isEmpty()) {
            dao.insert(plataforma);
        } else {
            plataforma.setId(Long.parseLong(id));
            dao.update(plataforma);
        }

        response.sendRedirect("platform?action=listar");
    }
}
