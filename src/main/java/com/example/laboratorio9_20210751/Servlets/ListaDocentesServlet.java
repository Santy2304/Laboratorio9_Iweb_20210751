package com.example.laboratorio9_20210751.Servlets;

import com.example.laboratorio9_20210751.Beans.Curso;
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

                request.getRequestDispatcher("ListaDocentes/CrearDocente.jsp").forward(request,response);

                break;

            case "editar":

                String iddocenteStr = request.getParameter("id");

                int iddocente = Integer.parseInt(iddocenteStr);

                Usuario docente = daoUsuario.obtenerDocentePorId(iddocente);

                request.setAttribute("docente",docente);
                request.getRequestDispatcher("ListaDocentes/EditarDocente.jsp").forward(request,response);
                break;


            case "borrar":

                String iddStr = request.getParameter("id");
                int idd = Integer.parseInt(iddStr);

                Usuario docenteParaBorrar = daoUsuario.obtenerDocentePorId(idd);

                if(docenteParaBorrar != null){
                    try {
                        daoUsuario.borrarDocente(idd);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                }
                response.sendRedirect(request.getContextPath() + "/ListaDocentesServlet");

                break;
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        DaoUsuario daoUsuario = new DaoUsuario();

        switch (action){

            case "crear":

                String nombreC = request.getParameter("nombre");
                String correo = request.getParameter("correo");
                String contrasena = request.getParameter("contrasena");

                Usuario docente = new Usuario();

                docente.setNombre(nombreC);
                docente.setCorreo(correo);
                docente.setContrasena(contrasena);

                try {
                    daoUsuario.guardarDocente(docente);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                response.sendRedirect(request.getContextPath() + "/ListaDocentesServlet");

                break;

            case "editar":

                String iddocente = request.getParameter("iddocente");
                String nombre = request.getParameter("nombre");

                Usuario usuario = new Usuario();

                usuario.setIdUsuario(Integer.parseInt(iddocente));
                usuario.setNombre(nombre);


                try {
                    daoUsuario.actualizar(usuario);
                    response.sendRedirect(request.getContextPath() + "/ListaDocentesServlet");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                break;

        }


    }
}

