package connections;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyConnection {
    public static Connection getConnection(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url="jdbc:sqlserver://LINHNDHSE61828\\SQLEXPRESS:1433;"
                    + "databaseName=HospitalDB;user=sa;password=hienlinh123;";
            Connection conn=DriverManager.getConnection(url);
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
