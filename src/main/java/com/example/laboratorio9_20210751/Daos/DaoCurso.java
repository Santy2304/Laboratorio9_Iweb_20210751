package com.example.laboratorio9_20210751.Daos;

import com.example.laboratorio9_20210751.Beans.*;

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


    public void actualizarCurso(Curso curso) throws SQLException {

        String sql = "UPDATE curso SET nombre= ?, fecha_edicion= Now() WHERE idcurso = ?";

        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, curso.getNombre());
            pstmt.setInt(2,curso.getIdCurso());
            pstmt.executeUpdate();

        }

    }

    public Curso obtenerCurso(int idCurso) {

        Curso curso = null;

        String sql = "SELECT * FROM curso c \n"
                + "left join facultad f on (f.idfacultad = c.idfacultad)"
                + "WHERE c.idcurso = ?";

        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idCurso);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    curso = new Curso();
                    fetchCursoData(curso, rs);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return curso;
    }


    public void guardarCurso(Curso curso) throws SQLException {

        String sql = "INSERT INTO curso (idcurso, codigo, nombre, idfacultad, fecha_registro, fecha_edicion) "
                + "VALUES (?, ?, ?, ?, Now(), Now())";

        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {

            pstmt.setInt(1,curso.getIdCurso());
            pstmt.setString(2, curso.getCodigo());
            pstmt.setString(3,curso.getNombre());
            pstmt.setInt(4,curso.getFacultad().getIdFacultad());

            pstmt.executeUpdate();
        }
    }

    public int nuevoId(){
        int idNuevo = 0;

        String sql  = "SELECT idcurso\n" +
                "FROM curso\n" +
                "ORDER BY idcurso DESC\n" +
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

    public void borrar(int idCurso) throws SQLException {
        String sql = "DELETE FROM curso WHERE idcurso = ?";
        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setInt(1, idCurso);
            pstmt.executeUpdate();
        }
    }


    public boolean validarCursoBorrar(int idCurso){

        String sql = "SELECT * FROM evaluaciones where idcurso = ?";

        boolean exito = true;

        try(Connection connection = getConection();
            PreparedStatement pstmt = connection.prepareStatement(sql)){

            pstmt.setInt(1,idCurso);

            try(ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
                    exito = false;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return exito;
    }



}
