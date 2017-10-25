package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conn {
	public static Connection getConn()
	{
		Connection con=null;
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","hr","hr");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return con;
	}

}
