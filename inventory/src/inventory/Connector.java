package inventory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector{
    static public final String driver = "com.mysql.jdbc.Driver";
    static public final String connection = "jdbc:mysql://localhost:3306";
    static public final String user = "root";
    static public final String password = "herohero";
    static Connection con = null;
    public static Connection con(){
    	try {
			Class.forName(driver);
			con = DriverManager.getConnection(connection, user, password);
       	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
    }
}
