package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Statement;

/**
 *
 * @author maquina04
 */
public class Conexao {

    static Connection conn;
    private Statement stm;
    public ResultSet resultset;

    public static void main(String[] args) throws SQLException {
        Connection con = Conexao.getConnection();
        if(conn != null){
            System.out.println("Sim");
        }else{
            System.out.println("Não");
        }

    }

    public static Connection getConnection() {
        try {
            if (conn != null) {
                if (!conn.isClosed()) {
                    return conn;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            //String url = "jdbc:oracle:thin:@187.108.194.64:1521:XE";
            String url = "jdbc:oracle:thin:@localhost:1521:XE";

            String usuario = "root";
            String senha = "ROOT";
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = null;
            con = DriverManager.getConnection(url, usuario, senha);
            conn = con;
            return con;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }
    
    public void executaSQL(String sql){
        try {
            stm = conn.createStatement(resultset.TYPE_SCROLL_INSENSITIVE, resultset.CONCUR_READ_ONLY);
            resultset = stm.executeQuery(sql);
        } catch (SQLException sQLException) {
            System.out.println(sQLException);
        }
    }
}