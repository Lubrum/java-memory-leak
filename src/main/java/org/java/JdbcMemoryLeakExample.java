package org.java;

import java.sql.*;

public class JdbcMemoryLeakExample {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/root";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000000; i++) {
            System.out.println("Connection " + i);
            memoryLeak();
            if (i % 1000 == 0) {
                Thread.sleep(100);
            }
        }
    }

    public static void memoryLeak() {
        Connection conn;
        Statement stmt;
        ResultSet rs;

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            stmt = conn.createStatement();

            rs = stmt.executeQuery("SELECT * FROM alunos ORDER BY id");

            while (rs.next()) {
                System.out.print(rs.getString("id"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void noMemoryLeak() throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            stmt = conn.createStatement();

            rs = stmt.executeQuery("SELECT * FROM alunos ORDER BY id");

            while (rs.next()) {
//                System.out.println(
//                    rs.getString("id") + " - " +rs.getString("created_at") + " - " +
//                    rs.getString("updated_at") + " - " +rs.getString("deleted_at") + " - " +
//                    rs.getString("nome") + " - " + rs.getString("rg") + " - " + rs.getString("cpf")
//                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }
}