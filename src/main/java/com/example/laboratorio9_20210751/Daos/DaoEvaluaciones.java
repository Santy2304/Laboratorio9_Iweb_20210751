package com.example.laboratorio9_20210751.Daos;

import com.example.laboratorio9_20210751.Beans.Curso;
import com.example.laboratorio9_20210751.Beans.Evaluaciones;
import com.example.laboratorio9_20210751.Beans.Facultad;
import com.example.laboratorio9_20210751.Beans.Semestre;

import java.sql.*;
import java.util.ArrayList;

public class DaoEvaluaciones extends DaoBase {

    public ArrayList<Evaluaciones> listarEvaluaciones() {
        ArrayList<Evaluaciones> listaEvaluaciones = new ArrayList<>();

        String sql = "SELECT * FROM evaluaciones e \n"
                + "left join curso c on (c.idcurso = e.idcurso) \n" +
                "left join semestre s on (e.idsemestre = s.idsemestre)";

        try (Connection conn = this.getConection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {


            while (rs.next()) {
                Evaluaciones evaluaciones = new Evaluaciones();
                fetchEvaluacionData(evaluaciones, rs);

                listaEvaluaciones.add(evaluaciones);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return listaEvaluaciones;
    }


    private void fetchEvaluacionData(Evaluaciones evaluaciones, ResultSet rs) throws SQLException {
        evaluaciones.setIdEvaluaciones(rs.getInt(1));
        evaluaciones.setNombreEstudiante(rs.getString(2));
        evaluaciones.setCodigoEstudiante(rs.getString(3));
        evaluaciones.setCorreoEstudiante(rs.getString(4));
        evaluaciones.setNota(rs.getInt(5));

        Curso curso = new Curso();
        curso.setIdCurso(rs.getInt(6));
        curso.setNombre(rs.getString("c.nombre"));
        evaluaciones.setCurso(curso);

        Semestre semestre = new Semestre();
        semestre.setIdSemestre(rs.getInt(7));
        semestre.setNombre(rs.getString("s.nombre"));
        evaluaciones.setSemestre(semestre);

        evaluaciones.setFechaRegistro(rs.getString(8));
        evaluaciones.setFechaEdicion(rs.getString(9));

    }


    public Evaluaciones obtenerEvaluacion(int employeeId) {

        Evaluaciones evaluaciones = null;

        String sql = "SELECT * FROM evaluaciones e \n"
                + "left join curso c ON (c.idcurso = e.idcurso) \n"
                + "left join semestre s ON (s.idsemestre = e.idsemestre)\n"
                + "WHERE e.idevaluaciones = ?";

        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, employeeId);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    evaluaciones = new Evaluaciones();
                    fetchEvaluacionData(evaluaciones, rs);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return evaluaciones;
    }


    public void actualizarEvaluacion(Evaluaciones evaluacion) throws SQLException {

        String sql = "UPDATE evaluaciones SET nombre_estudiantes = ?, codigo_estudiantes = ?, correo_estudiantes = ?, nota = ?,fecha_edicion= Now() WHERE idevaluaciones = ?";

        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, evaluacion.getNombreEstudiante());
            pstmt.setString(2, evaluacion.getCodigoEstudiante());
            pstmt.setString(3, evaluacion.getCorreoEstudiante());
            pstmt.setInt(4, evaluacion.getNota());
            pstmt.setInt(5, evaluacion.getIdEvaluaciones());
            pstmt.executeUpdate();

        }

    }

    public void borrar(int idEvaluacion) throws SQLException {
        String sql = "DELETE FROM evaluaciones WHERE idevaluaciones = ?";
        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setInt(1, idEvaluacion);
            pstmt.executeUpdate();
        }
    }


    public void guardarEvaluacion(Evaluaciones evaluacion) throws SQLException {

        String sql = "INSERT INTO evaluaciones (nombre_estudiantes, codigo_estudiantes, correo_estudiantes, nota, idcurso,idsemestre, fecha_registro, fecha_edicion) "
                + "VALUES (?, ?, ?, ?, ?, 1, Now(), Now())";

        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {

            pstmt.setString(1,evaluacion.getNombreEstudiante());
            pstmt.setString(2, evaluacion.getCodigoEstudiante());
            pstmt.setString(3,evaluacion.getCorreoEstudiante());
            pstmt.setInt(4,evaluacion.getNota());
            pstmt.setInt(5,evaluacion.getCurso().getIdCurso());

            pstmt.executeUpdate();
        }
    }



}
