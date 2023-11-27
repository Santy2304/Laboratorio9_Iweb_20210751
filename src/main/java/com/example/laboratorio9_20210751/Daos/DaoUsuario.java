package com.example.laboratorio9_20210751.Daos;

import com.example.laboratorio9_20210751.Beans.Curso;
import com.example.laboratorio9_20210751.Beans.Evaluaciones;
import com.example.laboratorio9_20210751.Beans.Rol;
import com.example.laboratorio9_20210751.Beans.Usuario;

import java.sql.*;
import java.util.ArrayList;

public class DaoUsuario extends DaoBase {

    public boolean validarCorreoContrasena(String correo, String contrasena){

        String sql = "SELECT * FROM Usuario where correo = ? and password = sha2(?,256)";

        boolean exito = false;

        try(Connection connection = getConection();
            PreparedStatement pstmt = connection.prepareStatement(sql)){

            pstmt.setString(1,correo);
            pstmt.setString(2,contrasena);

            try(ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
                    exito = true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return exito;
    }


    public Usuario obtenerUsuarioPorCorreo(String correo) {

        Usuario usuario = null;

        String sql = "SELECT * FROM usuario u \n"
                + "left join rol r ON (r.idrol = u.idrol) \n"
                + "WHERE u.correo = ?";

        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, correo);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    usuario = new Usuario();
                    fetchUsuarioData(usuario, rs);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return usuario;
    }


    public ArrayList<Usuario> listarDocentes() {
        ArrayList<Usuario> listaDocentes = new ArrayList<>();

        String sql = "SELECT * FROM usuario u where idrol = 4";

        try (Connection conn = this.getConection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Usuario usuario = new Usuario();
                fetchUsuarioData(usuario, rs);

                listaDocentes.add(usuario);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return listaDocentes;
    }


    private void fetchUsuarioData(Usuario usuario, ResultSet rs) throws SQLException {
        usuario.setIdUsuario(rs.getInt(1));
        usuario.setNombre(rs.getString(2));
        usuario.setCorreo(rs.getString(3));
        usuario.setContrasena(rs.getString(4));

        Rol rol = new Rol();
        rol.setIdRol(rs.getInt(5));
        usuario.setRol(rol);

        usuario.setUltimoIngreso(rs.getString(6));
        usuario.setCantIngresos(rs.getInt(7));
        usuario.setFechaRegistro(rs.getString(8));
        usuario.setFechaEdicion(rs.getString(9));

    }



    public Usuario obtenerDocentePorId(int idUsuario) {

        Usuario usuario = null;

        String sql = "SELECT * FROM usuario u \n"
                +  "WHERE u.idusuario = ? and u.idrol=4";

        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idUsuario);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    usuario = new Usuario();
                    fetchUsuarioData(usuario, rs);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return usuario;
    }


    public void actualizar(Usuario docente) throws SQLException {

        String sql = "UPDATE usuario SET nombre= ?, fecha_edicion= Now() WHERE idusuario = ?";

        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, docente.getNombre());
            pstmt.setInt(2,docente.getIdUsuario());
            pstmt.executeUpdate();

        }

    }

    public void actualizarFechas(Usuario docente) throws SQLException {

        String sql = "UPDATE usuario SET cantidad_ingresos = cantidad_ingresos + 1, \n" +
                " ultimo_ingreso = Now()\n" +
                "WHERE idusuario=?;";

        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setInt(1,docente.getIdUsuario());
            pstmt.executeUpdate();

        }

    }

    public void borrarDocente(int idUsuario) throws SQLException {
        String sql = "DELETE FROM usuario WHERE idusuario = ?";
        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setInt(1, idUsuario);
            pstmt.executeUpdate();
        }
    }

    public void guardarDocente(Usuario docente) throws SQLException {

        String sql = "INSERT INTO usuario (idusuario,nombre, correo,password ,idrol,cantidad_ingresos,fecha_registro, fecha_edicion) "
                + "VALUES (?, ?, ?, SHA2(?, 256),4 ,0,Now(), Now())";

        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {

            pstmt.setInt(1,nuevoId());
            pstmt.setString(2, docente.getNombre());
            pstmt.setString(3,docente.getCorreo());
            pstmt.setString(4,docente.getContrasena());

            pstmt.executeUpdate();
        }
    }


    public int nuevoId(){
        int idNuevo = 0;

        String sql  = "SELECT idusuario\n" +
                "FROM usuario\n" +
                "ORDER BY idusuario DESC\n" +
                "LIMIT 1";

        try (Connection conn = getConection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                idNuevo = rs.getInt(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

        return idNuevo+1;
    }

}
