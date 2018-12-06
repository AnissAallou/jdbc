package jdbc.dev;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Transact
{
	public static void main(String[] args)
	{
		try {
			updateData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void updateData() throws SQLException
	{
		Connection conn = null;
		Statement statement = null;
		
		try
		{
			conn = Connect.getConnection();
			conn.setAutoCommit(false);
			
			// Création d'un objet Statement permettant de réaliser des requêtes
			// sur la base de données
			statement = conn.createStatement();

			// On crée la requête
			String query = "UPDATE matiere set mat_nom = 'ALLEMAND' WHERE mat_id = 1";
						
			Integer update = statement.executeUpdate(query);
			System.out.println("Résultat de la requête UPDATE => " + update.intValue());
			
			// On crée la requête
			query = "UPDATE matiere set mat_nom = 'MATHEMATIQUES' WHERE mat_id = 1";
					
			update = statement.executeUpdate(query);
			System.out.println("Résultat de la requête UPDATE => " + update.intValue());
						
			// les requêtes qui n'ont pas été annulées sont validées
			conn.commit();
			
			
		}
		catch (Exception e)
		{
			conn.rollback();
			e.printStackTrace();
		}
		finally
		{
			statement.close();
			conn.close();
			
		}
	}
}