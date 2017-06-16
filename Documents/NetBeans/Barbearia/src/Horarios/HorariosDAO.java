/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Horarios;

import Util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Amanda de Oliveira
 */
public class HorariosDAO {

    public void inserir(Horarios horario) {
        try {
            Conexao conexao = new Conexao();
            Connection conn = conexao.getConnection();
            String sql = "insert into HORARIOS"
                    + " (SEQ_HORARIOS, DIA, HORARIO, DESCRICAO, ID_CLIENTE)"
                    + " values "
                    + " (seq_horarios.nextval,?,?,?,?)";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, horario.getDia());
            ps.setString(2, horario.getHorario());
            ps.setString(3, horario.getDescricao());
            ps.setInt(4, horario.getIdCliente());

            ps.execute();
            ps.close();
            System.out.println("insert");
        } catch (SQLException ex) {
            Logger.getLogger(HorariosDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
    }

    public Horarios alterar(Horarios horario) {
        try {
            Conexao conexao = new Conexao();
            Connection conn = conexao.getConnection();
            String sql = "update HORARIOS set DIA = ?, HORARIO = ?, DESCRICAO = ?, ID_CLIENTE = ? where SEQ_HORARIOS = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, horario.getDia());
            ps.setString(2, horario.getHorario());
            ps.setString(3, horario.getDescricao());
            ps.setInt(4, horario.getIdCliente());
            ps.setInt(5, horario.getSeqHorarios());

            ps.execute();
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(HorariosDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }

        return horario;
    }

    public ArrayList<Horarios> ListaHorarios() {

        ArrayList<Horarios> listaHorarios = new ArrayList<Horarios>();
        String sql = "select * from HORARIOS";
        try {
            Conexao conexao = new Conexao();
            Connection conn = conexao.getConnection();

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(); //Retorna resultado da consulta

            while (rs.next()) {
                Horarios horario = new Horarios();
                horario.setSeqHorarios(rs.getInt("seq_horarios"));
                horario.setIdCliente(rs.getInt("id_cliente"));
                horario.setDescricao(rs.getString("descricao"));
                horario.setHorario(rs.getString("horario"));
                horario.setDia(rs.getString("dia"));

                listaHorarios.add(horario);
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
        return listaHorarios;
    }

    public ArrayList<Horarios> buscaHorarios(String pesquisa1, String pesquisa2) {
        ArrayList<Horarios> listaHorarios = new ArrayList<Horarios>();
        String sql = "select * from HORARIOS where dia like ? and horario like ?";
        try {
            Conexao conexao = new Conexao();
            Connection conn = conexao.getConnection();

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, pesquisa1);
            ps.setString(2, pesquisa2);

            ResultSet rs = ps.executeQuery(); //Retorna resultado da consulta
            
            while (rs.next()) {
                Horarios horario = new Horarios();
                horario.setDia(rs.getString("dia"));
                horario.setDescricao(rs.getString("descricao"));
                horario.setHorario(rs.getString("horario"));
                horario.setIdCliente(rs.getInt("id_cliente"));
                horario.setSeqHorarios(rs.getInt("SEQ_HORARIOS"));
                listaHorarios.add(horario);
            }
            ps.close();
            
        } catch (SQLException e) {
            System.out.println(e);
        }
        return listaHorarios;
    }
}
