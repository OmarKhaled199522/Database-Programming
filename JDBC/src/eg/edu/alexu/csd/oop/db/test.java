package eg.edu.alexu.csd.oop.db;

import java.sql.SQLException;

import junit.framework.Assert;

import org.junit.Test;

public class test {

	
	
	/*
	@Test
	public void test1() throws SQLException {
		Database mine = new MySQL();
		mine.createDatabase("test", true);   // returns "C:/workspace/eclipse/db/test"
		mine.executeStructureQuery("create table students (id int ,name  varchar , grade int)");   // returns  true
		mine.executeUpdateQuery("insert into students (id,name,grade) VALUES(3, 'Ahmed Eid', 19)");   // returns 1
		mine.executeUpdateQuery("insert into students (id,name,grade) VALUES(5, 'Sherouq', 20)");   // returns 1
		mine.executeUpdateQuery("insert into students (id,name,grade) VALUES(7, 'Shehab', 15)");	// returns 1
		mine.executeUpdateQuery("update students set id = 8 where id > 4");  // returns 2
//		System.out.println(Arrays.deepToString(mine.executeQuery("select * from students"))); // returns  new String[][] {{ 3, "Ahmed Eid", 19 }, { 8, "Sherouq", 20 }, { 8, "Shehab", 15 }}
	//	System.out.println(Arrays.deepToString(mine.executeQuery("select name from students where id=8")));
	}
	
	@Test
	public void test2() throws SQLException {
		Database mine = new MySQL();
		mine.executeStructureQuery("CREATE DATABASE TestDB");
		mine.executeStructureQuery("CREATE TABLE table_name7(column_name1 varchar, column_name2 int, column_name3 varchar)");
		mine.executeUpdateQuery("INSERT INTO table_name7(column_NAME1, COLUMN_name3, column_name2) VALUES ('value1', 'value3', 4)");
		mine.createDatabase("testdb", false);
	}
	
	
	@Test
	public void tes3() throws SQLException {
		Database mine = new MySQL();
		boolean e = mine.executeStructureQuery("drop database sample;");
		mine.executeStructureQuery("create database sample;");
		mine.executeStructureQuery("CREATE TABLE table_name13(column_name1 varchar, column_name2 int, column_name3 varchar)");
		int e1 = mine.executeUpdateQuery("INSERT INTO table_name13(column_NAME1, COLUMN_name3, column_name2) VALUES ('value1', 'value3', 4)");
		int e2 = mine.executeUpdateQuery("INSERT INTO table_name13(column_NAME1, column_name2, COLUMN_name3) VALUES ('value1', 4, 'value3')");
		int e3 = mine.executeUpdateQuery("INSERT INTO table_name13(column_name1, COLUMN_NAME3, column_NAME2) VALUES ('value2', 'value4', 5)");
		int e4 = mine.executeUpdateQuery("INSERT INTO table_name13(column_name1, COLUMN_NAME3, column_NAME2) VALUES ('value5', 'value6', 6)");
		mine.executeQuery("SELECT column_name1 FROM table_name13 WHERE coluMN_NAME2 < 5");
	}
	
	
	@Test
	public void tes4() throws SQLException {
		Database mine = new MySQL();
		boolean e = mine.executeStructureQuery("drop database sample;");
		mine.executeStructureQuery("create database sample;");
		mine.executeStructureQuery("CREATE TABLE table_name8(column_name1 varchar, column_name2 int, column_name3 varchar)");
		int e1 = mine.executeUpdateQuery("INSERT INTO table_name8(column_NAME1, COLUMN_name3, column_name2) VALUES ('value1', 'value3', 4)");
		int e2 = mine.executeUpdateQuery("INSERT INTO table_name8(column_NAME1, COLUMN_name3, column_name2) VALUES ('value1', 'value3', 4)");
		int e3 = mine.executeUpdateQuery("INSERT INTO table_name8(column_name1, COLUMN_NAME3, column_NAME2) VALUES ('value2', 'value4', 5)");
		int e4 = mine.executeUpdateQuery("UPDATE table_name8 SET column_name1='11111111', COLUMN_NAME2=22222222, column_name3='333333333' WHERE coLUmn_NAME3='VALUE3'");
		
	}
	
	@Test
	public void tes5() throws SQLException {
		Database mine = new MySQL();
		boolean e = mine.executeStructureQuery("drop database sample;");
		mine.executeStructureQuery("create database sample;");
		mine.executeStructureQuery("CREATE TABLE table_name9(column_name1 varchar, column_name2 int, column_name3 varchar)");
		int e1 = mine.executeUpdateQuery("UPDATE table_name9 SET column_name1='value1', column_name2=15, column_name3='value2'");
		
		//try{
			//int e2 = mine.executeUpdateQuery("UPDATE wrong_table_name9 SET column_name1='value1', column_name2=15, column_name3='value2'");
		//} 
	}
	
	@Test
	public void tes6() throws SQLException {
		Database mine = new MySQL();
		boolean e = mine.executeStructureQuery("drop database sample;");
		mine.executeStructureQuery("create database sample;");
		mine.executeStructureQuery("CREATE TABLE table_name1(column_name1 varchar, column_name2 int, column_name3 varchar)");
		mine.executeStructureQuery("CREATE TABLE table_name2(column_name1 varchar, column_name2 int, column_name3 varchar)");
		mine.executeUpdateQuery("INSERT INTO table_name1(column_NAME1, COLUMN_name3, column_name2) VALUES ('value1', 'value3', 4)");
	}
	*/
	
	@Test
	public void tes7() throws SQLException {
		Database mine = new MySQL("C:\\");
		mine.executeStructureQuery("CREATE DATABASE TestDB2");
		mine.executeStructureQuery("CREATE TABLE table_name1(column_name1 varchar, column_name2 int, column_name3 varchar)");
		int e1 = mine.executeUpdateQuery("INSERT INTO table_name1(column_NAME1, COLUMN_name3, column_name2) VALUES ('value1', 'value3', 4)");
		int e2 = mine.executeUpdateQuery("INSERT INTO table_name1(column_NAME1, COLUMN_name3, column_name2) VALUES ('value1', 'value3', 4)");
		int e3 = mine.executeUpdateQuery("INSERT INTO table_name1(column_name1, COLUMN_NAME3, column_NAME2) VALUES ('value2', 'value4', 5)");
		mine.executeStructureQuery("DROP TABLE table_name1");
		
		/*try{
			int e4 = mine.executeUpdateQuery("UPDATE table_name1 SET column_name1='11111111', COLUMN_NAME2=22222222, column_name3='333333333' WHERE coLUmn_NAME3='VALUE3'");
		} catch(Exception e){
			System.out.println("You should go here!");
		}*/
		
		boolean r1 = mine.executeStructureQuery("CREATE DATABASE TestDB2");
		
		
		boolean r2 = mine.executeStructureQuery("DROP DATABASE TestDB2");
		//mine.executeStructureQuery("CREATE TABLE table_name1(column_name1 varchar, column_name2 int, column_name3 varchar)");
		// How we create the table after drop the data ?? ??????
		 
		
		/*
		mine.executeStructureQuery("CREATE TABLE table_name2(column_name1 varchar, column_name2 int, column_name3 varchar)");
		int e11 = mine.executeUpdateQuery("INSERT INTO table_name2(column_NAME1, COLUMN_name3, column_name2) VALUES ('value1', 'value3', 4)");
		int e22 = mine.executeUpdateQuery("INSERT INTO table_name2(column_NAME1, COLUMN_name3, column_name2) VALUES ('value1', 'value3', 5)");
		int e33 = mine.executeUpdateQuery("INSERT INTO table_name2(column_name1, COLUMN_NAME3, column_NAME2) VALUES ('value2', 'value4', 6)");
		int e44 = mine.executeUpdateQuery("DELETE From table_name2  WHERE coLUmn_NAME1='VAluE1'");
		int e55 = mine.executeUpdateQuery("UPDATE table_name2 SET column_name1='11111111', COLUMN_NAME2=22222222, column_name3='333333333' WHERE coLUmn_NAME2=4");
		*/
	}
	
	@Test
	public void tes8() throws SQLException {
		Database mine = new MySQL("C:\\");
		boolean e = mine.executeStructureQuery("drop database sample;");
		mine.executeStructureQuery("create database sample;");
		boolean r = mine.executeStructureQuery("CREATE TABLE table_name1(column_name1 varchar, column_name2 int, column_name3 varchar)");
		mine.executeUpdateQuery("INSERT INTO table_name1(column_NAME1, COLUMN_name3, column_name2) VALUES ('value1', 'value3', 4)");
		mine.executeUpdateQuery("INSERT INTO table_name1(column_NAME1, COLUMN_name3, column_name2) VALUES ('value1', 'value3', 5)");
		mine.executeUpdateQuery("INSERT INTO table_name1(column_name1, COLUMN_NAME3, column_NAME2) VALUES ('value2', 'value4', 6)");
		mine.executeUpdateQuery("DELETE From table_name1  WHERE coLUmn_NAME2=4");
		mine.executeQuery("SELECT * FROM table_name1 WHERE coluMN_NAME2 < 6");
		mine.executeUpdateQuery("UPDATE table_name1 SET column_name1='11111111', COLUMN_NAME2=10, column_name3='333333333' WHERE coLUmn_NAME2=5");
		mine.executeQuery("SELECT * FROM table_name1 WHERE coluMN_NAME2 > 4");
		
	}
	
	@Test
	public void tes9() throws SQLException {
		Database mine = new MySQL("C:\\");
		System.out.println("result : "+mine.executeStructureQuery("create database happy;"));
		System.out.println("result : "+mine.executeStructureQuery("CREATE TABLE table_name13(column_name1 varchar, column_name2 int, column_name3 varchar)"));
		System.out.println("result : "+mine.executeUpdateQuery("INSERT INTO table_name13(column_NAME1, COLUMN_name3, column_name2) VALUES ('value1', 'value3', 4)"));
		System.out.println("result : "+mine.executeUpdateQuery("INSERT INTO table_name13(column_NAME1, column_name2, COLUMN_name3) VALUES ('value1', 4, 'value3')"));
		System.out.println("result : "+mine.executeUpdateQuery("Delete * from  table_name13"));
		
	}
	
	@Test
	public void tes10() throws SQLException {
		
		Database mine = new MySQL("C:\\");
		boolean e = mine.executeStructureQuery("drop database sample;");
		mine.executeStructureQuery("create database sample;");
		boolean r = mine.executeStructureQuery("Create TABLE table_name1(column_name1 varchar, column_name2 int, column_name3 varchar)");
		mine.executeUpdateQuery("INSERT INTO table_name1(column_NAME1, COLUMN_name3, column_name2) VALUES ('value1', 'value3', 4)");
		mine.executeUpdateQuery("INSERt INTO table_name1(column_NAME1, COLUMN_name3, column_name2) VALUES ('value1', 'value3', 4)");
		mine.executeUpdateQuery("INSERT INTO table_name1(column_name1, COLUMN_NAME3, column_NAME2) VAlUES ('value2', 'value4', 5)");
		int rr = mine.executeUpdateQuery("UPDATE table_namE1 SET column_name1='11111111', COLUMN_NAME2=22222222, column_name3='333333333' WHERE coLUmn_NAME3='VALUE3'");
		System.out.println(rr);
		
		
		mine.executeStructureQuery("CREATE DATABASE TestDB");
		boolean r2 = mine.executeStructureQuery("CREATE TABLE table_name1(column_name1 varchar, column_name2 int, column_name3 varchar)");
		//mine.executeUpdateQuery("INSERT INTO table_name1(column_NAME1, COLUMN_name3, column_name2) VALUES ('value1', 'value3', 4)");
		//mine.executeUpdateQuery("INSERT INTO table_name1(column_NAME1, COLUMN_name3, column_name2) VALUES ('value1', 'value3', 4)");
		//mine.executeUpdateQuery("INSERT INTO table_name1(column_name1, COLUMN_NAME3, column_NAME2) VAlUES ('value2', 'value4', 5)");
		//mine.executeUpdateQuery("UPDATE table_name1 SET column_name1='11111111', COLUMN_NAME2=22222222, column_name3='333333333' WHERE coLUmn_NAME3='VALUE3'");
		
	}
	
	@Test
	public void test11() throws SQLException {
		MySQL  sql = new MySQL("C:\\");
		sql.executeStructureQuery("drop database sample;");
		sql.executeStructureQuery("create database sample;");
		sql.executeStructureQuery("CREATE TABLE table_name3(column_name1 varchar, column_name2 int, column_name3 varchar)");
		sql.executeStructureQuery("CREATE TABLE table_name5(column_name1 varchar, column_name2 int, column_name3 varchar)");

		sql.executeUpdateQuery("INSERT INTO table_name3(column_NAME1, COLUMN_name3, column_name2) VALUES ('value1', 'value3', 4)");
		
		sql.executeStructureQuery("drop database sample;");
		sql.executeStructureQuery("create database sample;");
		sql.executeStructureQuery("CREATE TABLE table_name4(column_name1 varchar, column_name2 int, column_name3 varchar)");
		sql.executeUpdateQuery("INSERT INTO table_name4(column_NAME1, COLUMN_name3, column_name2) VALUES ('value1', 'value3', 4)");
		sql.executeUpdateQuery("UPDATE table_name4 SET column_name1='1111111111', COLUMN_NAME2=2222222, column_name3='333333333'");
		
	}
	
	@Test
	public void test12() throws SQLException {
		
		MySQL  sql = new MySQL("C:\\");
		System.out.println("result : "+sql.executeStructureQuery("drop database sample;"));
		System.out.println("result : "+sql.executeStructureQuery("create database sampleee;"));
		System.out.println("result : "+sql.executeStructureQuery("CREATE TABLE table_name13(column_name1 varchar, column_name2 int, column_name3 varchar)"));
		System.out.println("result : "+sql.executeUpdateQuery("INSERT INTO table_name13(column_NAME1, COLUMN_name3, column_name2) VALUES ('value1', 'value3', 4)"));
		System.out.println("result : "+sql.executeUpdateQuery("INSERT INTO table_name13(column_NAME1, column_name2, COLUMN_name3) VALUES ('value1', 4, 'value3')"));
		System.out.println("result : "+sql.executeUpdateQuery("INSERT INTO table_name13(column_name1, COLUMN_NAME3, column_NAME2) VALUES ('value2', 'value4', 5)"));
		System.out.println("result : "+sql.executeUpdateQuery("INSERT INTO table_name13(column_name1, COLUMN_NAME3, column_NAME2) VALUES ('value5', 'value6', 6)"));
		//System.out.println("result : "+sql.executeUpdateQuery("INSERT INTO table_name13 VALUES ('value1', 3,'value3')"));
		Object arr[][]= sql.executeQuery("SELECT column_name1 FROM table_name13 WHERE coluMN_NAME2 < 5");
		sql.executeUpdateQuery("DELETE From table_name13");
		
		System.out.println("ddd "+arr.length);
		
	}
	
	@Test
	public void test13() throws SQLException {
		
		MySQL  sql = new MySQL("C:\\");
		System.out.println("result : "+sql.executeStructureQuery("drop database sampleee;"));
		System.out.println("result : "+sql.executeStructureQuery("create database sampleee;"));
		System.out.println("result : "+sql.executeStructureQuery("CREATE TABLE table_name1(column_name1 varchar, column_name2 int, column_name3 varchar)"));
		System.out.println("result : "+sql.executeUpdateQuery("INSERT INTO table_name1(column_NAME1, COLUMN_name3, column_name2) VALUES ('value1', 'value3', 4)"));
		System.out.println("result : "+sql.executeUpdateQuery("INSERT INTO table_name1(column_NAME1, COLUMN_name3, column_name2) VALUES ('value1', 'value3', 5)"));
		System.out.println("result : "+sql.executeUpdateQuery("INSERT INTO table_name1(column_name1, COLUMN_NAME3, column_NAME2) VALUES ('value2', 'value4', 6)"));
		System.out.println("result : "+sql.executeUpdateQuery("DELETE From table_name1  WHERE coLUmn_NAME2=4"));
		
	}
	
	
}
