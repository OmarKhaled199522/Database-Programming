package eg.edu.alexu.csd.oop.db;

import java.sql.SQLException;

public class Mymain {

	
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		MySQL  sql = new MySQL();
		System.out.println("result : "+sql.executeStructureQuery("drop database sample;"));
		System.out.println("result : "+sql.executeStructureQuery("create database sampleee;"));
		System.out.println("result : "+sql.executeStructureQuery("CREATE TABLE table_name13(column_name1 varchar, column_name2 int, column_name3 varchar)"));
		System.out.println("result : "+sql.executeUpdateQuery("INSERT INTO table_name13(column_NAME1, COLUMN_name3, column_name2) VALUES ('value1', 'value3', 4)"));
		System.out.println("result : "+sql.executeUpdateQuery("INSERT INTO table_name13(column_NAME1, column_name2, COLUMN_name3) VALUES ('value1', 4, 'value3')"));
		System.out.println("result : "+sql.executeUpdateQuery("INSERT INTO table_name13(column_name1, COLUMN_NAME3, column_NAME2) VALUES ('value2', 'value4', 5)"));
		System.out.println("result : "+sql.executeUpdateQuery("INSERT INTO table_name13(column_name1, COLUMN_NAME3, column_NAME2) VALUES ('value5', 'value6', 6)"));
		Object arr[][]= sql.executeQuery("SELECT column_name1 FROM table_name13 WHERE coluMN_NAME2 < 5");

		System.out.println("ddd"+arr.length);

		
}}
