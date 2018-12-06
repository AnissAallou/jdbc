package jdbc.dev;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Dao {


	

		public static void getMatiere() {
			Connection connection = null;
			Statement statement = null;
			ResultSet resultSet = null;
			
			try{
				connection = Connect.getConnection();
				statement = connection.createStatement();
				
		
				resultSet = statement.executeQuery("SELECT * FROM matiere");
				

				ResultSetMetaData resultMetaData = resultSet.getMetaData();
				
				System.out.println("\r\n====");

				for (int i = 1; i <= resultMetaData.getColumnCount(); i++){
					System.out.print("\t" + resultMetaData.getColumnName(i).toUpperCase() + "\t");
				}
				
				
				System.out.println();
				
				while (resultSet.next()){
					System.out.print("\t" + resultSet.getInt("mat_id") + "\t\t" + resultSet.getString("mat_nom") + "\r\n");
				}
				
				System.out.println("\r\n====");
				
				
			}
			catch (Exception e){
				e.printStackTrace();
			}
			finally{
				try {
					resultSet.close();
					statement.close();
					connection.close();
				}
				catch (SQLException e){

					e.printStackTrace();
				}
				
			}
		}
		
		public static void deleteMatiere(int mat_id) throws Exception{
			Connection connection = null;
			PreparedStatement preparestatement = null;
			try{
				connection = Connect.getConnection();

				preparestatement = connection.prepareStatement("DELETE FROM matiere WHERE mat_id=?");
				preparestatement.setInt(1, mat_id);
				
				int option = preparestatement.executeUpdate();
				if(option == 0) System.out.println("pas de matière à supprimer");
				
			}
			catch (Exception e){
				throw new Exception("la suppression de la matière n'est pas possible");
			}
			finally{
					
					preparestatement.close();
					connection.close();
			}
		}


		public static void insertMatiere(String mat_nom) throws Exception{
			Connection connection = null;
			PreparedStatement preparestatement = null;
			try{
				connection = Connect.getConnection();

				preparestatement = connection.prepareStatement("INSERT INTO matiere (mat_nom) values (?)");
				preparestatement.setString(1, mat_nom);
				
				int option = preparestatement.executeUpdate();
				if(option ==0) System.out.println("la matière n'a pas été ajoutée");
				
			}
			catch (Exception e){
				throw new Exception("l'ajout de la matière n'a pas fonctionné");
			}
			finally{
					
					preparestatement.close();
					connection.close();
			}
		}
		public static void updateMatiere(int mat_id , String mat_nom) throws Exception{
			Connection connection = null;
			PreparedStatement preparestatement = null;
			try{
				connection = Connect.getConnection();

				preparestatement = connection.prepareStatement("UPDATE matiere SET mat_nom =? WHERE mat_id = ?");
				preparestatement.setInt(2, mat_id);
				preparestatement.setString(1, mat_nom);
				int option = preparestatement.executeUpdate();
				if(option ==0) System.out.println("la matière n'a pas été référencée");
				
			}
			catch (Exception e){
				throw new Exception("la matière n'a pas pu être mise à jour");
			}
			finally{
					
					preparestatement.close();
					connection.close();
			}
		}
	}
