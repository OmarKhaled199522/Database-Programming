package eg.edu.alexu.csd.oop.jdbc;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

public class MyDriver implements java.sql.Driver {
	private String rootPath;
	
	@Override
	public boolean acceptsURL(String str) throws SQLException {
		// TODO Auto-generated method stub
		if(str == null){return false;}
		if(str.equals("")){return false;}
		if(str.equals(" ")){return false;}
		return true;
	}

	@Override
	public Connection connect(String arg0, Properties property) throws SQLException {
		// TODO Auto-generated method stub
		File file = (File) property.get("path");
		rootPath = file.getAbsolutePath();
		System.out.println(rootPath);
		new File(rootPath).mkdirs();
		return new MyConnection(rootPath);
	}

	@Override
	public int getMajorVersion() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMinorVersion() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DriverPropertyInfo[] getPropertyInfo(String arg0, Properties arg1) throws SQLException {
		DriverPropertyInfo[] driverProperties = new DriverPropertyInfo[1];
		driverProperties[0] = new DriverPropertyInfo("path", rootPath);
		return driverProperties;
	}

	@Override
	public boolean jdbcCompliant() {
		// TODO Auto-generated method stub
		return false;
	}

}
