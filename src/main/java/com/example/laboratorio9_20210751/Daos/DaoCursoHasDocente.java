package com.example.laboratorio9_20210751.Daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoCursoHasDocente extends DaoBase{


    public int cursoPorIdDocente(int idUsuario){
        String sql="select idcurso from curso_has_docente where iddocente=?";
        try(Connection conn=this.getConection();
            PreparedStatement pstmt= conn.prepareStatement(sql)){

            pstmt.setInt(1,idUsuario);

            try(ResultSet rs=pstmt.executeQuery()){
                if(rs.next()){
                    return rs.getInt(1);
                }else
                    return 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void actualizarTabla(int iddocente,int idcurso){


        String sql = "INSERT INTO curso_has_docente (idcurso,iddocente) "
                + "VALUES (?, ?)";

        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {

            pstmt.setInt(1,idcurso);
            pstmt.setInt(2, iddocente);


            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void borrar(int idCurso) throws SQLException {
        String sql = "DELETE FROM curso_has_docente WHERE idcurso = ?";
        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setInt(1, idCurso);
            pstmt.executeUpdate();
        }
    }


}
