package com.example.laboratorio9_20210751.Servlets;

import com.example.laboratorio9_20210751.Beans.Evaluaciones;
import com.example.laboratorio9_20210751.Beans.Usuario;
import com.example.laboratorio9_20210751.Daos.DaoEvaluaciones;
import com.example.laboratorio9_20210751.Daos.DaoUsuario;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ListaDocentesServlet", value = "/ListaDocentesServlet")
public class ListaDocentesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Usuario usuario =  new Usuario();
        usuario = (Usuario) request.getSession().getAttribute("usuarioLogueado");

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        DaoUsuario daoUsuario = new DaoUsuario();


        switch (action){

            case "lista":
                request.setAttribute("listaDocentes",daoUsuario.listarDocentes());
                HttpSession httpSession = request.getSession();
                httpSession.setAttribute("usuarioLogueado",usuario);
                request.getRequestDispatcher("ListaDocentes/ListaDocentes.jsp").forward(request, response);
                break;

            case "crear":

                request.getRequestDispatcher("ListaDocentes/ListaDocentes.jsp").forward(request,response);

                break;

            case "editar":

                String iddocenteStr = request.getParameter("id");

                int iddocente = Integer.parseInt(iddocenteStr);

                Usuario docente = daoUsuario.obtenerDocentePorId(iddocente);

                request.setAttribute("docente",docente);
                request.getRequestDispatcher("ListaDocentes/EditarDocente.jsp").forward(request,response);
                break;
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

