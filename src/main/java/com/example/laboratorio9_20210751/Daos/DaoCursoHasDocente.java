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

}
