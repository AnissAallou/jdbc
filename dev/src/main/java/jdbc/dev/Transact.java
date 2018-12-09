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
		Connection conn2 = null;
		
		Statement statement = null;
		Statement statement2 = null;
		
		try
		{
			conn = Connect.getConnectionMatiere();
			conn2 = Connect.getConnectionLivre();
			conn.setAutoCommit(false);
			conn2.setAutoCommit(false);
			
			// Création d'un objet Statement permettant de réaliser des requêtes
			// sur la base de données
			statement = conn.createStatement();
			statement2 = conn2.createStatement();
			// On crée la requête
			String query = "UPDATE matiere set mat_nom = 'ALLEMAND' WHERE mat_id = 1";
			String query2 = "UPDATE livre set titre = 'Chambre 203' WHERE id = 1";
			Integer update = statement.executeUpdate(query);
			Integer update2 = statement.executeUpdate(query);
			System.out.println("Résultat de la requête UPDATE => " + update.intValue());
			System.out.println("Résultat de la requête UPDATE => " + update2.intValue());

			// On crée la requête
			query = "UPDATE matiere set mat_nom = 'MATHEMATIQUES' WHERE mat_id = 1";
			update = statement.executeUpdate(query);
			query2 = "UPDATE livre set titre = 'Chambre 203' WHERE id = 1";
			update2 = statement2.executeUpdate(query2);
			System.out.println("Résultat de la requête UPDATE => " + update.intValue());
			System.out.println("Résultat de la requête UPDATE => " + update2.intValue());
						
			// les requêtes qui n'ont pas été annulées sont validées
			conn.commit();
			conn2.commit();

			
		}
		catch (Exception e)
		{
			conn.rollback();
			conn2.rollback();
			e.printStackTrace();
		}
		finally
		{
			statement.close();
			statement2.close();
			conn.close();
			conn2.close();
		}
	}
}