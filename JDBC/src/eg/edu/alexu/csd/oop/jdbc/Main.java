package eg.edu.alexu.csd.oop.jdbc;

import java.io.File;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		
		//get path
		System.out.println("Enter the path :");
		String path = scanner.nextLine();
		
		Driver driver = new MyDriver();
		Properties info = new Properties();
		File dbDir = new File(path);
		info.put("path", dbDir.getAbsoluteFile());
		Connection connection = driver.connect("jdbc:xmldb://localhost", info);
		
		//get statement
		MyStatement statement = (MyStatement) connection.createStatement();
		String query;
		
		while(true){
			System.out.println("Enter SQL query: ");
			query = scanner.nextLine();
			if(query.equals("Exit")) break;
			String [] temp = query.split(" ");
			boolean flag = statement.execute(query);
			if(temp[0].equalsIgnoreCase("select")){
				if(flag){
					Object[][] temp2 = statement.getSelect();
					for(int i = 0; i < temp2.length; i++){
						for(int j = 0; j < temp2[0].length; j++){
							System.out.print(temp2[i][j] + " ");
						}
						System.out.println("");
					}
				}
				else{System.out.println("Error !");}
				
			} else {
				if(! flag){
					System.out.println("Error !");
				}
				else {
					if (temp[0].equalsIgnoreCase("insert") || temp[0].equalsIgnoreCase("update") || temp[0].equalsIgnoreCase("delete")){
						System.out.println(statement.getUpdate());
					} 
					System.out.println("Query Executed");
				}
			}	
		}
		
		scanner.close();
	}
}
