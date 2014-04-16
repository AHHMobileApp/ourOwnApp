import java.sql.*;

public class connector {

	/**
	 * @param args
	 */
    static public final String driver = "com.mysql.jdbc.Driver";
    static public final String connection = "jdbc:mysql://localhost:3306";
    static public final String user = "root";
    static public final String password = "abcd1234";
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        try {
            Class.forName(driver);
            Connection con =
                    DriverManager.getConnection(connection, user, password);

            System.out.println("Jdbc Mysql Connection String :");
            System.out.println(connection);

            System.out.println("User Name :" + user);
            System.out.println("Password  :" + password);

            if (!con.isClosed()) {
                con.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

	}

}
