package jdbc.dev;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {
        // System.out.println( "Hello World!" );
        
    	Scanner sc = new Scanner(System.in);
    	
    	int mat_id;
    	int id;
        String mat_nom;
        String titre = null;
    	
    	boolean getOut = false;
    	while (!getOut) {
    		System.out.println("What would you doing ? \n1) Create matter \n2) Read matters \n3) Update matter \n4) Delete matter \n5) Create book \n6) Read books \n7) Update book \n8) Delete book \n99) Get out ! ");
        	int option = sc.nextInt();

    		if (option == 1) {
    			System.out.println("New matter : ");
    			mat_nom = sc.nextLine();
    			try {
    				Dao.insertMatiere(mat_nom);
    		       } catch (Exception e) {
    		    	   System.out.println(e.getMessage());
    		       }
    		} else if (option == 2) {
    			Dao.getMatiere();
    		} else if (option == 3) { 
    			System.out.println("Update mat_id : ");
    			mat_id  = sc.nextInt();
    			System.out.println("New mat_nom : ");
    			mat_nom = sc.nextLine();

    			try {
    				Dao.updateMatiere(mat_id, mat_nom);
    		    } catch (Exception e) {
    		    	System.out.println(e.getMessage());
    		    }
    		} else if (option == 4) { 
    			System.out.println("Delete mat_id : ");
    			mat_id  = sc.nextInt();
    			try {
    				Dao.deleteMatiere(mat_id);
				} catch (Exception e) {
					 System.out.println(e.getMessage());
				} 
    			
    		} else if (option == 5) {
    			System.out.println("New book : ");
    			mat_nom = sc.nextLine();
    			try {
    				Dao.insertBook(titre);
    		       } catch (Exception e) {
    		    	   System.out.println(e.getMessage());
    		       }
   
    		} else if (option == 6) { 
    			Dao.getBook();
    		} else if (option == 7) {
    			
    			System.out.println("Update id : ");
    			id  = sc.nextInt();
    			System.out.println("New titre : ");
    			titre = sc.nextLine();
    			
    			try {
    				Dao.updateBook(id, titre);
    		    } catch (Exception e) {
    		    	System.out.println(e.getMessage());
    		    }
    			
    		} else if (option == 8) {
    			System.out.println("Delete id : ");
    			id  = sc.nextInt();
    			try {
    				Dao.deleteBook(id);
				} catch (Exception e) {
					 System.out.println(e.getMessage());
				}
    		} else if (option == 99) { 
    			System.out.println("Arrivederci");
    			getOut = true;
    		}
			
		}

       sc.close();
    }
}
