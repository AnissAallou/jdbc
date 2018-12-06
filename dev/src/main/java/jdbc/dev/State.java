package jdbc.dev;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class State
{
	public static void main(String[] args)
	{
		readData();
	}
	
	public static void readData()
	{
		Connection conn = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try
		{
			conn = Connect.getConnection();

			// Création d'un objet Statement permettant de réaliser des requêtes sur la base de données
			statement = conn.createStatement();
			
			// L'objet ResultSet contient le résultat de la requête SQL
			resultSet = statement.executeQuery("SELECT * FROM matiere");
			
			// On récupère les MetaData dans le ResultSet
			ResultSetMetaData resultMetaData = resultSet.getMetaData();
			
			System.out.println("\r\n====");
			
			// On affiche le nom des colonnes
			for (int i = 1; i <= resultMetaData.getColumnCount(); i++)
			{
				System.out.print("\t" + resultMetaData.getColumnName(i).toUpperCase() + "\t");
			}
			
			
			System.out.println();
			
			while (resultSet.next())
			{
				System.out.print("\t" + resultSet.getInt("mat_id") + "\t\t" + resultSet.getString("mat_nom") + "\r\n");
			}
			
			System.out.println("\r\n====");
			
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				resultSet.close();
				statement.close();
				conn.close();
			}
			catch (SQLException e)
			{
				// do nothing
				e.printStackTrace();
			}
			
		}
	}
}