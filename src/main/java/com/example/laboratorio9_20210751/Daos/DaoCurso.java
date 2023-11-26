package com.example.laboratorio9_20210751.Daos;

import com.example.laboratorio9_20210751.Beans.Curso;
import com.example.laboratorio9_20210751.Beans.Facultad;
import com.example.laboratorio9_20210751.Beans.Rol;
import com.example.laboratorio9_20210751.Beans.Usuario;

import java.sql.*;
import java.util.ArrayList;

public class DaoCurso extends DaoBase{


    public ArrayList<Curso> listarCursosPorFacultad() {
        ArrayList<Curso> listaCursos = new ArrayList<>();

        String sql = "SELECT * FROM curso c \n"
                + "left join facultad f on (f.idfacultad = c.idfacultad)";

        try (Connection conn = this.getConection();
             Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    Curso curso = new Curso();
                    fetchCursoData(curso, rs);

                    listaCursos.add(curso);
                }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return listaCursos;
    }


    private void fetchCursoData(Curso curso, ResultSet rs) throws SQLException {
        curso.setIdCurso(rs.getInt(1));
        curso.setCodigo(rs.getString(2));
        curso.setNombre(rs.getString(3));
        Facultad facultad = new Facultad();

        facultad.setIdFacultad(rs.getInt(4));
        facultad.setNombre(rs.getString("f.nombre"));
        curso.setFacultad(facultad);

        curso.setFechaRegistro(rs.getString(5));
        curso.setFechaEdicion(rs.getString(6));

    }


}
