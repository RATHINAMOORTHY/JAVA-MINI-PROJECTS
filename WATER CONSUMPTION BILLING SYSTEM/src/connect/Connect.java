package connect;

import java.sql.Connection;
import java.sql.DriverManager;
public class Connect {
	public static Connection getCon()
	{
		Connection con=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=(DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","hr","hr"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}

}
