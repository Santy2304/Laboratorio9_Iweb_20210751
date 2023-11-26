package com.example.laboratorio9_20210751.Servlets;

import com.example.laboratorio9_20210751.Beans.Usuario;
import com.example.laboratorio9_20210751.Daos.DaoUniversidad;
import com.example.laboratorio9_20210751.Daos.DaoUsuario;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", value = "")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") == null ? "login" : request.getParameter("action");

        HttpSession session = request.getSession();
        switch (action) {
            case "login": //Caso por default cuando no mandan el parametro action
                request.getRequestDispatcher("Login/Login.jsp").forward(request, response);

                break;
            case "logout":
                session.invalidate();
                response.sendRedirect(request.getContextPath());
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String correo = request.getParameter("correo");
        String password = request.getParameter("contrasena");

        DaoUsuario daoUsuario = new DaoUsuario();

        boolean valido = daoUsuario.validarCorreoContrasena(correo,password);

        if (valido){
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("usuarioLogueado",daoUsuario.obtenerUsuarioPorCorreo(correo));

            int rol = daoUsuario.obtenerUsuarioPorCorreo(correo).getRol().getIdRol();

            try {
                daoUsuario.actualizarFechas(daoUsuario.obtenerUsuarioPorCorreo(correo));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if (rol==3){

                response.sendRedirect("ListaCursosServlet");

            }else if(rol==4){
                response.sendRedirect("ListaEvaluacionesServlet");

            }

        }else{
            request.getRequestDispatcher("Login/Login.jsp").forward(request, response);
        }

    }
}

