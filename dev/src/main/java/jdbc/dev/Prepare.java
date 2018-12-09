package jdbc.dev;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Prepare
{
	public static void main(String[] args)
	{
		readData(1, 6);
	}
	
	public static void readData(Integer id1, Integer id2)
	{
		Connection conn = null;
		Connection conn2 = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatement2 = null;

		try
		{
			conn = Connect.getConnectionMatiere();
			conn2 = Connect.getConnectionLivre();
			// On crée la requête
			String query = "SELECT * FROM matiere WHERE mat_id = ? OR mat_nom = ?";
			String query2 = "SELECT * FROM livre WHERE id = ? OR titre = ?";
						
			// On crée l'objet avec la requête en paramètre
			preparedStatement = conn.prepareStatement(query);
			preparedStatement2 = conn2.prepareStatement(query2);
			// On remplace le premier paramètre (dans cette requête il n'y a qu'un seul praramètre) par le nom de la classe
			preparedStatement.setInt(1, id1);
			preparedStatement.setInt(2, id2);
			
			preparedStatement2.setInt(1, id1);
			preparedStatement2.setInt(2, id2);

			ResultSet resultSet = preparedStatement.executeQuery();
			ResultSet resultSet2 = preparedStatement2.executeQuery();

			// On affiche la requête exécutée
			System.out.println(preparedStatement.toString());
			
			while (resultSet.next())
			{
				System.out.print("\t" + resultSet.getInt("id") + "\t\t" + resultSet.getString("titre") + "\r\n");
			}
			
			System.out.println(preparedStatement2.toString());
			
			while (resultSet2.next())
			{
				System.out.print("\t" + resultSet2.getInt("id") + "\t\t" + resultSet2.getString("titre") + "\r\n");
			}
			

			
			
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				preparedStatement.close();
				preparedStatement2.close();
				conn.close();
				conn2.close();
			} catch (SQLException e) {
				// ne rien faire
				e.printStackTrace();
			}
		}
		
		
	
	}
}
