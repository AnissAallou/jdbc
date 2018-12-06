package jdbc.dev;

import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect
{
	public static void main(String[] args)
	{
		Connection conn = null;

		try
		{
			conn = Connect.getConnection();
			

			System.out.println("Connexion effective !");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				conn.close();
			}
			catch (SQLException e)
			{
				// do nothing
			}
		}
	}

	public static Connection getConnection() throws Exception
	{
		DriverManager.setLogWriter(new PrintWriter(System.out));
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/enseignement?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
		String user = "root";
		String passwd = "root";

		return DriverManager.getConnection(url, user, passwd);
	}
}
