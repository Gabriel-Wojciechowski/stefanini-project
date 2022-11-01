package java.dao.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory implements AutoCloseable {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:5432/stefanini-test";
    private static final String LOGIN = "mysql";
    private static final String SENHA = "mysql";

    private Connection con = null;

    public Connection getConnection() {
        if (con == null) {
            try {
                Class.forName(DRIVER);
                con = DriverManager.getConnection(URL, LOGIN, SENHA);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("Driver do banco não encontrado: "
                        + DRIVER, e);

            } catch (SQLException e) {
                throw new RuntimeException("Erro conectando ao BD: " + URL + "/"
                        + LOGIN + "/" + SENHA, e);
            }
        }
        return con;
    }

    @Override
    public void close() {
        if (con != null) {
            try {
                con.close();
                con = null;
            } catch (Exception e) {
                System.out.println("Erro fechando a conexão.");
                e.printStackTrace();
            }
        }
    }
}
