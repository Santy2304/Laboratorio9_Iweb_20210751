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

        //Usuario usuario =  new Usuario();
        //usuario = (Usuario) request.getSession().getAttribute("usuarioLogueado");


        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        DaoUsuario daoUsuario = new DaoUsuario();


        switch (action){

            case "lista":
                request.setAttribute("listaDocentes",daoUsuario.listarDocentes());
                request.getRequestDispatcher("ListaDocentes/ListaDocentes.jsp").forward(request, response);
                break;

            case "crear":

                request.getRequestDispatcher("ListaDocentes/ListaDocentes.jsp").forward(request,response);

                break;



        }






        request.getRequestDispatcher("ListaDocentes/ListaDocentes.jsp").forward(request, response);




    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

