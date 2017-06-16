/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import Util.Conexao;
//import com.sun.xml.internal.ws.client.ContentNegotiation;
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
public class ClienteDAO {

    public Cliente inserir(Cliente cliente) {
        try {
            Conexao conexao = new Conexao();
            Connection conn = conexao.getConnection();
            String sql = "insert into CLIENTES"
                    + " (ID_CLIENTE,NOME,NASCIMENTO,ENDERECO,EMAIL,TELEFONE, CPF)"
                    + " values "
                    + " (seq_clientes.nextval,?,?,?,?,?,?)";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getNascimento());
            ps.setString(3, cliente.getEndereco());
            ps.setString(4, cliente.getEmail());
            ps.setString(5, cliente.getTelefone());
            ps.setString(6, cliente.getCpf());

            ps.execute();
            ps.close();
            System.out.println("insert");
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        return cliente;
    }

    public Cliente alterar(Cliente cliente) {
        try {
            Conexao conexao = new Conexao();
            Connection conn = conexao.getConnection();
            String sql = "update CLIENTES set NOME = ?, NASCIMENTO = ?, ENDERECO = ?, EMAIL = ?, TELEFONE= ? where ID_CLIENTES = ?, CPF = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getNascimento());
            ps.setString(3, cliente.getEndereco());
            ps.setString(4, cliente.getEmail());
            ps.setString(5, cliente.getTelefone());
            ps.setLong(6, cliente.getIdCliente());
            ps.setString(7, cliente.getCpf());

            ps.execute();
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }

        return cliente;
    }

    public Cliente buscaCliente(String colunaComparar, String pesquisa) {

        Cliente cliente1 = new Cliente();
        String sql = "select * from CLIENTES where " + colunaComparar + " like ?";
        try {
            Conexao conexao = new Conexao();
            Connection conn = conexao.getConnection();

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, pesquisa);

            ResultSet rs = ps.executeQuery(); //Retorna resultado da consulta

            while (rs.next()) {
                cliente1.setCpf(rs.getString("cpf"));
                cliente1.setNome(rs.getString("nome"));
                cliente1.setEmail(rs.getString("email"));
                cliente1.setTelefone(rs.getString("telefone"));
                cliente1.setEndereco(rs.getString("endereco"));
                cliente1.setNascimento(rs.getString("nascimento"));
                cliente1.setIdCliente(rs.getInt("id_cliente"));

            }
            ps.close();
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
        return cliente1;
    }

    public ArrayList<Cliente> ListaClientes() {

        ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
        String sql = "select * from clientes";
        try {
            Conexao conexao = new Conexao();
            Connection conn = conexao.getConnection();

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(); //Retorna resultado da consulta

            while (rs.next()) {
                Cliente cliente1 = new Cliente();
                cliente1.setCpf(rs.getString("cpf"));
                cliente1.setNome(rs.getString("nome"));
                cliente1.setEmail(rs.getString("email"));
                cliente1.setTelefone(rs.getString("telefone"));
                cliente1.setEndereco(rs.getString("endereco"));
                cliente1.setNascimento(rs.getString("nascimento"));
                cliente1.setIdCliente(rs.getInt("id_cliente"));

                listaClientes.add(cliente1);
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
        return listaClientes;
    }

}
