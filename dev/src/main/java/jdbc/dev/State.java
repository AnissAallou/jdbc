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
		Connection conn2 = null;
		Statement statement = null;
		Statement statement2 = null;
		ResultSet resultSet = null;
		ResultSet resultSet2 = null;
		
		try
		{
			conn = Connect.getConnectionMatiere();
			conn2 = Connect.getConnectionLivre();
			// Création d'un objet Statement permettant de réaliser des requêtes sur la base de données
			statement = conn.createStatement();
			statement2 = conn2.createStatement();
			// L'objet ResultSet contient le résultat de la requête SQL
			resultSet = statement.executeQuery("SELECT * FROM matiere");
			resultSet2 = statement2.executeQuery("SELECT * FROM livre");
			// On récupère les MetaData dans le ResultSet
			ResultSetMetaData resultMetaData = resultSet.getMetaData();
			ResultSetMetaData resultMetaData2 = resultSet2.getMetaData();

			System.out.println("\r\n====");
			
			// On affiche le nom des colonnes
			for (int i = 1; i <= resultMetaData.getColumnCount(); i++)
			{
				System.out.print("\t" + resultMetaData.getColumnName(i).toUpperCase() + "\t");
			}
			
			for (int i = 1; i <= resultMetaData2.getColumnCount(); i++)
			{
				System.out.print("\t" + resultMetaData2.getColumnName(i).toUpperCase() + "\t");
			}
			

			
			System.out.println();
			
			while (resultSet.next())
			{
				System.out.print("\t" + resultSet.getInt("mat_id") + "\t\t" + resultSet.getString("mat_nom") + "\r\n");
			}
			
			while (resultSet2.next())
			{
				System.out.print("\t" + resultSet2.getInt("id") + "\t\t" + resultSet2.getString("titre") + "\r\n");
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
				resultSet2.close();
				statement.close();
				statement2.close();
				conn.close();
				conn2.close();
			}
			catch (SQLException e)
			{
				// do nothing
				e.printStackTrace();
			}
			
		}
	}
}