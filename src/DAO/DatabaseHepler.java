package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

    public class DatabaseHepler {
        public static Connection openConnection() throws Exception {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionURL = "jdbc:sqlserver://localhost:1433;databaseName=QLHS";
            String user = "sa";
            String password = "040403";
            Connection con = DriverManager.getConnection(connectionURL, user, password);
            return con;

        }
    }



