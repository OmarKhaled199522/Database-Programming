package eg.edu.alexu.csd.oop.jdbc;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.junit.Assert;


public class test {

	@org.junit.Test
	public void testCreateAndOpenAndDropDatabase() throws SQLException {
		File dummy = null;
		Driver driver = new MyDriver();
		Properties info = new Properties();
		File dbDir = new File("C:\\ffff");
		info.put("path", dbDir.getAbsoluteFile());
		Connection connection = driver.connect("jdbc:xmldb://localhost", info);
		{
			Statement statement = connection.createStatement();
			statement.execute("DROP DATABASE SaMpLe");
			statement.execute("CREATE DATABASE SaMpLe");
			dbDir =  new File("C:\\ffff\\sample");
			String files[] = dbDir.list();
			Assert.assertTrue("Database directory is not empty!", files == null || files.length == 0);
			dummy = new File(dbDir,"dummy");
			System.out.println(dbDir);
	
			try {
				boolean created = dummy.createNewFile();
				System.out.println(created);
				Assert.assertTrue("Failed t create file into DB", created && dummy.exists());
			} catch (IOException e) {
				Assert.fail("Failed t create file into DB");
			}
			statement.close();
		}
		{
			Statement statement = connection.createStatement();
			statement.execute("CREATE DATABASE sAmPlE");
			String files[] = dbDir.list();
			Assert.assertTrue("Database directory is empty after opening!", files.length > 0);
			Assert.assertTrue("Failed t create find a previously created file into DB", dummy.exists());
			statement.close();
		}
	}
	
}	