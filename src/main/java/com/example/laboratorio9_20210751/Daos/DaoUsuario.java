package com.example.laboratorio9_20210751.Daos;

import com.example.laboratorio9_20210751.Beans.Curso;
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


}
