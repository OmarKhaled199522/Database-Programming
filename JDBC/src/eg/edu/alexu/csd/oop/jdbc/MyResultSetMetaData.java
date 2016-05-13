package eg.edu.alexu.csd.oop.jdbc;

import java.sql.SQLException;
import java.sql.Types;
import java.util.List;


public class MyResultSetMetaData implements java.sql.ResultSetMetaData {
	private List<String> columnsLabel ;
	private Object[][] data ;
	private String tableName;
	private int noOfCol = 0;
	
	public MyResultSetMetaData( List<String> columnsLabell ,Object[][] data , String tableName) {
		this.data = data;
		this.tableName = tableName;
		columnsLabel = columnsLabell;
		for (int i=0;i<columnsLabell.size();i++){
			if (columnsLabell.get(i) != null || !columnsLabell.get(i).contains("\\s") || !columnsLabell.get(i).equalsIgnoreCase("")){
				columnsLabel.set(noOfCol, columnsLabell.get(i));
				noOfCol++;
			}
		}
	}
	
	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public String getCatalogName(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public String getColumnClassName(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public int getColumnCount() throws SQLException {
		// TODO Auto-generated method stub
		return data[0].length;
	}

	@Override
	public int getColumnDisplaySize(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public String getColumnLabel(int index) throws SQLException {
		// TODO Auto-generated method stub
		return columnsLabel.get(index );
	}

	@Override
	public String getColumnName(int index) throws SQLException {
		// TODO Auto-generated method stub
		return columnsLabel.get(index );
	}

	@Override
	public int getColumnType(int index) throws SQLException {
		// TODO Auto-generated method stub
		if (data.length == 0 || index+1 > data[0].length)
			throw new SQLException();
		String s = data[0][index].toString();
		char[] letters = s.toCharArray();
		int cnt = 0;
		for (int i=0;i<s.length();i++){
			if (letters[i] >= '0' && letters[i]<= '9')
				cnt++;
		}
		if (cnt == s.length())
			return Types.INTEGER;
		return Types.VARCHAR;
	}

	@Override
	public String getColumnTypeName(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public int getPrecision(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public int getScale(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public String getSchemaName(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public String getTableName(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		return tableName;
	}

	@Override
	public boolean isAutoIncrement(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isCaseSensitive(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isCurrency(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isDefinitelyWritable(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public int isNullable(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isReadOnly(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isSearchable(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isSigned(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isWritable(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

}
