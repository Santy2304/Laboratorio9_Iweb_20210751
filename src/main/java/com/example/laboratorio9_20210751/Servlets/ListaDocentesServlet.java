package com.example.laboratorio9_20210751.Servlets;

import com.example.laboratorio9_20210751.Beans.Usuario;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ListaDocentesServlet", value = "/ListaDocentesServlet")
public class ListaDocentesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Usuario usuario =  new Usuario();
        usuario = (Usuario) request.getSession().getAttribute("usuarioLogueado");



        request.getRequestDispatcher("ListaDocentes/ListaDocentes.jsp").forward(request, response);




    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

