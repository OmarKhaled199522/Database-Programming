package eg.edu.alexu.csd.oop.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.util.ArrayList;

import eg.edu.alexu.csd.oop.db.*;


public class MyStatement implements java.sql.Statement {

	private int pos = 0;
	private Database db;
	private String batch [] = new String [1000 + 10]; 
	private Connection connection;
	private Object [][]  select;
	private int update;


	public int getUpdate() {
		return update;
	}


	public Object[][] getSelect() {
		return select;
	}


	public MyStatement(Connection connection, String path){
		this.connection = connection;
		db = new MySQL(path);
	}
	
	
	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		throw new  UnsupportedOperationException();
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		throw new  UnsupportedOperationException();
	}

	@Override
	public void addBatch(String arg0) throws SQLException {
		arg0 = arg0.toLowerCase();
		batch[pos++] = arg0;
	}

	@Override
	public void cancel() throws SQLException {
		throw new  UnsupportedOperationException();
	}

	@Override
	public void clearBatch() throws SQLException {
		for(int i = 0; i < pos; i++) batch[i] = null;
	}

	@Override
	public void clearWarnings() throws SQLException {
		throw new  UnsupportedOperationException();
	}

	@Override
	public void close() throws SQLException {
		return;
	}

	@Override
	public void closeOnCompletion() throws SQLException {
		throw new  UnsupportedOperationException();
	}

	@Override
	public boolean execute(String arg0) throws SQLException {
		
		arg0 = arg0.toLowerCase();
		String[] temp = arg0.split("\\s");
		if(temp[0].equalsIgnoreCase("insert") || temp[0].equalsIgnoreCase("update") || temp[0].equalsIgnoreCase("delete")){
			
			update = db.executeUpdateQuery(arg0);
			if(update == 0) return false;
			else return true;
			
		} else if (temp[0].equalsIgnoreCase("select")){

			select  = db.executeQuery(arg0);
			if(select.length == 0) return false;
			else return true;
			
		} else if (temp[0].equalsIgnoreCase("create") ||temp[0].equalsIgnoreCase("drop") ){
			boolean create = db.executeStructureQuery(arg0);
			return create;
		}
		
		return false;
	}

	@Override
	public boolean execute(String arg0, int arg1) throws SQLException {
		throw new  UnsupportedOperationException();
	}

	@Override
	public boolean execute(String arg0, int[] arg1) throws SQLException {
		throw new  UnsupportedOperationException();
	}

	@Override
	public boolean execute(String arg0, String[] arg1) throws SQLException {
		throw new  UnsupportedOperationException();
	}

	@Override
	public int[] executeBatch() throws SQLException {
		
		int update [] = new int [pos];
		for(int i = 0; i < pos; i++){
			update[i] = db.executeUpdateQuery(batch[i]);
		}
		
		return update;
	}

	@Override
	public ResultSet executeQuery(String arg0) throws SQLException {
		arg0 = arg0.toLowerCase();	
		select = db.executeQuery(arg0);
		ArrayList<String> list = ((MySQL) db).getLis();
		String name = ((MySQL) db).getTable();
		if (name.contains("\\s")){
		   String[] SplitStr = name.split("\\s+");
		   name = SplitStr[1];
		}
		
		ArrayList<String> list2 = new ArrayList<String>();
		
		for(int i = 0; i < list.size(); i++){
			String column = list.get(i);
			if(column == null)continue;
			if (column.contains("\\s")){
			String[] SplitStr = column.split("\\s+");
			list2.add(SplitStr[0]);
			}
			else{
				list2.add(column);
			}
		}
		ResultSet res = new MyResultSet(select , list2 , name, this);                                                                                            
		return res;
	}

	@Override
	public int executeUpdate(String arg0) throws SQLException {
		arg0 = arg0.toLowerCase();
		int value = db.executeUpdateQuery(arg0);
		return value;
	}

	@Override
	public int executeUpdate(String arg0, int arg1) throws SQLException {
		throw new  UnsupportedOperationException();
	}

	@Override
	public int executeUpdate(String arg0, int[] arg1) throws SQLException {
		throw new  UnsupportedOperationException();
	}

	@Override
	public int executeUpdate(String arg0, String[] arg1) throws SQLException {
		throw new  UnsupportedOperationException();
	}

	@Override
	public Connection getConnection() throws SQLException {
		
		return connection;
	}

	@Override
	public int getFetchDirection() throws SQLException {
		throw new  UnsupportedOperationException();
	}

	@Override
	public int getFetchSize() throws SQLException {
		throw new  UnsupportedOperationException();
	}

	@Override
	public ResultSet getGeneratedKeys() throws SQLException {
		throw new  UnsupportedOperationException();
	}

	@Override
	public int getMaxFieldSize() throws SQLException {
		throw new  UnsupportedOperationException();
	}

	@Override
	public int getMaxRows() throws SQLException {
		throw new  UnsupportedOperationException();
	}

	@Override
	public boolean getMoreResults() throws SQLException {
		throw new  UnsupportedOperationException();
	}

	@Override
	public boolean getMoreResults(int arg0) throws SQLException {
		throw new  UnsupportedOperationException();
	}

	@Override
	public int getQueryTimeout() throws SQLException {
		throw new  UnsupportedOperationException();
	}

	@Override
	public ResultSet getResultSet() throws SQLException {
		throw new  UnsupportedOperationException();
	}

	@Override
	public int getResultSetConcurrency() throws SQLException {
		throw new  UnsupportedOperationException();
	}

	@Override
	public int getResultSetHoldability() throws SQLException {
		throw new  UnsupportedOperationException();
	}

	@Override
	public int getResultSetType() throws SQLException {
		throw new  UnsupportedOperationException();
	}

	@Override
	public int getUpdateCount() throws SQLException {
		throw new  UnsupportedOperationException();
	}

	@Override
	public SQLWarning getWarnings() throws SQLException {
		throw new  UnsupportedOperationException();
	}

	@Override
	public boolean isCloseOnCompletion() throws SQLException {
		throw new  UnsupportedOperationException();
	}

	@Override
	public boolean isClosed() throws SQLException {
		throw new  UnsupportedOperationException();
	}

	@Override
	public boolean isPoolable() throws SQLException {
		throw new  UnsupportedOperationException();
	}

	@Override
	public void setCursorName(String arg0) throws SQLException {
		throw new  UnsupportedOperationException();
	}

	@Override
	public void setEscapeProcessing(boolean arg0) throws SQLException {
		throw new  UnsupportedOperationException();
	}

	@Override
	public void setFetchDirection(int arg0) throws SQLException {
		throw new  UnsupportedOperationException();
	}

	@Override
	public void setFetchSize(int arg0) throws SQLException {
		throw new  UnsupportedOperationException();
	}

	@Override
	public void setMaxFieldSize(int arg0) throws SQLException {
		throw new  UnsupportedOperationException();
	}

	@Override
	public void setMaxRows(int arg0) throws SQLException {
		throw new  UnsupportedOperationException();
	}

	@Override
	public void setPoolable(boolean arg0) throws SQLException {
		throw new  UnsupportedOperationException();
	}

	@Override
	public void setQueryTimeout(int arg0) throws SQLException {
		throw new  UnsupportedOperationException();
	}

}
