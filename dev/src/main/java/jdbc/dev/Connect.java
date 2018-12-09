package jdbc.dev;

import java.io.PrintWriter;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect
{
	public static void main(String[] args)
	{
		Connection connection = null;

		try
		{
			connection = Connect.getConnectionMatiere();
			connection = Connect.getConnectionLivre();

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
				connection.close();
			}
			catch (SQLException e)
			{
				// do nothing
			}
		}
	}

	public static Connection getConnectionMatiere() throws Exception
	{
		DriverManager.setLogWriter(new PrintWriter(System.out));
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/enseignement?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
		String user = "root";
		String passwd = "root";

		return DriverManager.getConnection(url, user, passwd);

	}
	
	
	public static Connection getConnectionLivre() throws Exception
	{
		DriverManager.setLogWriter(new PrintWriter(System.out));
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/livre?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
		String user = "root";
		String passwd = "root";

		return DriverManager.getConnection(url, user, passwd);
		

	}
	
	
}
