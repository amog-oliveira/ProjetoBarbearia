/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Preco;

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
public class PrecoDAO {
     public void inserir(Preco preco) {
        try {
            Conexao conexao = new Conexao();
            Connection conn = conexao.getConnection();
            String sql = "insert into PRECOS"
                    + " (SEQ_PRECO,NOME,PRECO)"
                    + " values "
                    + " (seq_preco.nextval,?,?)";
            
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, preco.getNome());
            ps.setString(2, preco.getPreco());
            
            ps.execute();
            ps.close();
            System.out.println("insert");
        } catch (SQLException ex) {
            Logger.getLogger(PrecoDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
    }

    public Preco alterar(Preco preco) {
        try {
            Conexao conexao = new Conexao();
            Connection conn = conexao.getConnection();
            String sql = "update PRECOS set NOME = ?, PRECO = ? where SEQ_PRECO = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, preco.getNome());
            ps.setString(2, preco.getPreco());
            ps.setInt(3, preco.getSeq_preco());
            
            ps.execute();
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(PrecoDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }

        return preco;
    }
    
    public ArrayList<Preco> ListaPreco() {

     ArrayList<Preco> listaPreco = new ArrayList<Preco>();
     String sql = "select * from PRECOS";       
     try {
        Conexao conexao = new Conexao();
        Connection conn = conexao.getConnection();
    
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery(); //Retorna resultado da consulta

        while(rs.next()){
            Preco preco = new Preco();
            preco.setSeq_preco(rs.getInt("seq_preco"));
            preco.setNome(rs.getString("nome"));
            preco.setPreco(rs.getString("preco"));
            listaPreco.add(preco);
        }
        ps.close();
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
     return listaPreco;
 }
   public void deletePreco (String nome){

     String sql = "delete from PRECOS where nome like ?";
     try {
        Conexao conexao = new Conexao();
        Connection conn = conexao.getConnection();
    
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, nome);
        ResultSet rs = ps.executeQuery(); //Retorna resultado da consulta

        ps.close();
         System.out.println("foi");
     } catch (SQLException e) {
        System.out.println(e);
     }
       
   }
}