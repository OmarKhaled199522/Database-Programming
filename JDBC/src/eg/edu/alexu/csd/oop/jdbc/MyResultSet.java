package eg.edu.alexu.csd.oop.jdbc;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MyResultSet implements java.sql.ResultSet{

	private final int BEFORE_FIRST = -1;
	private final int AFTER_LAST = -2;
	
	private int type = 0;
	
	private Object[][] data = null;
	private List<String> columnsLabel ;
	private Statement statement;
	private String tableName ;
	
	private int cursor =  BEFORE_FIRST;
	private boolean isClosed = false;

	public MyResultSet(Object[][] data , List<String> columnsLabel ,String tableName, Statement statement){
		this.data = data;
		this.columnsLabel = columnsLabel;
		this.statement = statement;
		this.tableName = tableName;
	}
	
	//not used
	public MyResultSet(Object[][] data , List<String> columnsLabel,Statement statement ,int type){
		this.data = data;
		this.columnsLabel = columnsLabel;
		this.type = type;
		this.statement = statement;
	}
	
	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public boolean absolute(int row) throws SQLException {
		// TODO Auto-generated method stub
		if(type == TYPE_FORWARD_ONLY|| isClosed()){
			throw new SQLException();
		}
		if(Math.abs(row) > data.length){
			return false;
		}
		if(row == 0){
			cursor = 0;
		}
		else if(row > 0){
			cursor = row -1;
		}
		else{
			cursor = data.length + row ;
		}
		return true;
	}

	@Override
	public void afterLast() throws SQLException {
		// TODO Auto-generated method stub
		if(type == TYPE_FORWARD_ONLY || isClosed()){
			throw new SQLException();
		}
		cursor = AFTER_LAST;
	}

	@Override
	public void beforeFirst() throws SQLException {
		// TODO Auto-generated method stub
		if(type == TYPE_FORWARD_ONLY || isClosed()){
			throw new SQLException();
		}
		cursor = BEFORE_FIRST;
	}

	@Override
	public void cancelRowUpdates() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void clearWarnings() throws SQLException {
		throw new java.lang.UnsupportedOperationException();		
	}

	@Override
	public void close() throws SQLException {
		// TODO Auto-generated method stub
		isClosed = true;
		
	}

	@Override
	public void deleteRow() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public int findColumn(String columnLabel) throws SQLException {
		// TODO Auto-generated method stub
		Iterator<String> iter = columnsLabel.iterator();
		int i = 0;
		while(iter.hasNext()){
			if(iter.next() == columnLabel){
				return i;
			}
			i++;
		}
		throw new SQLException();
	}

	@Override
	public boolean first() throws SQLException {
		// TODO Auto-generated method stub
		if(type == TYPE_FORWARD_ONLY || isClosed()){
			throw new SQLException();
		}
		if(data.length == 0)
			return false;
		cursor = 0;
		return true;
	}

	@Override
	public Array getArray(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Array getArray(String arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public InputStream getAsciiStream(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public InputStream getAsciiStream(String arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public BigDecimal getBigDecimal(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public BigDecimal getBigDecimal(String arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public BigDecimal getBigDecimal(int arg0, int arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public BigDecimal getBigDecimal(String arg0, int arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public InputStream getBinaryStream(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public InputStream getBinaryStream(String arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Blob getBlob(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Blob getBlob(String arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public boolean getBoolean(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public boolean getBoolean(String arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public byte getByte(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public byte getByte(String arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public byte[] getBytes(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public byte[] getBytes(String arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Reader getCharacterStream(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Reader getCharacterStream(String arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Clob getClob(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Clob getClob(String arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public int getConcurrency() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public String getCursorName() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Date getDate(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Date getDate(String arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Date getDate(int arg0, Calendar arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Date getDate(String arg0, Calendar arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public double getDouble(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public double getDouble(String arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public int getFetchDirection() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public int getFetchSize() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public float getFloat(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public float getFloat(String arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public int getHoldability() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public int getInt(int index) throws SQLException {
		// TODO Auto-generated method stub
		if(isClosed()|| index > data[0].length || index <= 0 || cursor < 0){
			throw new SQLException();
		}
		Integer value = (Integer) data[cursor][index-1];
		if(value == null)
			return 0;
		else
			return value;
	}

	@Override
	public int getInt(String columnLabel) throws SQLException {
		// TODO Auto-generated method stub
		int index = findColumn(columnLabel)+1;
		if(isClosed()|| index > data[0].length || index <= 0 || cursor < 0){
			throw new SQLException();
		}
		Integer value = (Integer) data[cursor][index-1];
		if(value == null)
			return 0;
		else
			return value;
	}

	@Override
	public long getLong(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public long getLong(String arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public ResultSetMetaData getMetaData() throws SQLException {
		// TODO Auto-generated method stub
		if(isClosed()){
			throw new SQLException();
		}
		return new MyResultSetMetaData(columnsLabel ,data ,tableName);
	}

	@Override
	public Reader getNCharacterStream(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Reader getNCharacterStream(String arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public NClob getNClob(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public NClob getNClob(String arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public String getNString(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public String getNString(String arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Object getObject(int index) throws SQLException {
		if(isClosed()|| index > data[0].length || index <= 0 || cursor < 0){
			throw new SQLException();
		}
		Object value = data[cursor][index-1];
		return value;
	}

	@Override
	public Object getObject(String arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Object getObject(int arg0, Map<String, Class<?>> arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Object getObject(String arg0, Map<String, Class<?>> arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public <T> T getObject(int arg0, Class<T> arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public <T> T getObject(String arg0, Class<T> arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Ref getRef(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Ref getRef(String arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public int getRow() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public RowId getRowId(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public RowId getRowId(String arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public SQLXML getSQLXML(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public SQLXML getSQLXML(String arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public short getShort(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public short getShort(String arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Statement getStatement() throws SQLException {
		// TODO Auto-generated method stub
		if(isClosed()){
			throw new SQLException();
		}
		return statement;
	}

	@Override
	public String getString(int index) throws SQLException {
		// TODO Auto-generated method stub
		if(isClosed()|| index > data[0].length || index <= 0 || cursor < 0){
			throw new SQLException();
		}
		String value = (String) data[cursor][index-1];
		return value;
	}

	@Override
	public String getString(String columnLabel) throws SQLException {
		// TODO Auto-generated method stub
		int index = findColumn(columnLabel)+1;
		if(isClosed()|| index > data[0].length || index <= 0 || cursor < 0){
			throw new SQLException();
		}
		String value = (String) data[cursor][index-1];
		return value;
	}

	@Override
	public Time getTime(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Time getTime(String arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Time getTime(int arg0, Calendar arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Time getTime(String arg0, Calendar arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Timestamp getTimestamp(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Timestamp getTimestamp(String arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Timestamp getTimestamp(int arg0, Calendar arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Timestamp getTimestamp(String arg0, Calendar arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public int getType() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public URL getURL(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public URL getURL(String arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public InputStream getUnicodeStream(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public InputStream getUnicodeStream(String arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public SQLWarning getWarnings() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void insertRow() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public boolean isAfterLast() throws SQLException {
		// TODO Auto-generated method stub
		if(isClosed()){
			throw new SQLException();
		}
		if(cursor == AFTER_LAST)
			return true;
		return false;
	}

	@Override
	public boolean isBeforeFirst() throws SQLException {
		// TODO Auto-generated method stub
		if(isClosed()){
			throw new SQLException();
		}
		if(cursor == BEFORE_FIRST)
			return true;
		return false;
	}

	@Override
	public boolean isClosed() throws SQLException {
		// TODO Auto-generated method stub
		return isClosed;
	}

	@Override
	public boolean isFirst() throws SQLException {
		// TODO Auto-generated method stub
		if(isClosed()){
			throw new SQLException();
		}
		if(cursor == 0)
			return true;
		return false;
	}

	@Override
	public boolean isLast() throws SQLException {
		// TODO Auto-generated method stub
		if(isClosed()){
			throw new SQLException();
		}
		if(cursor == data.length)
			return true;
		return false;
	}

	@Override
	public boolean last() throws SQLException {
		// TODO Auto-generated method stub
		if(type == TYPE_FORWARD_ONLY || isClosed()){
			throw new SQLException();
		}
		if(data.length == 0)
			return false;
		cursor = data.length;
		return true;
	}

	@Override
	public void moveToCurrentRow() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void moveToInsertRow() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public boolean next() throws SQLException {
		// TODO Auto-generated method stub
		if(type == TYPE_FORWARD_ONLY || isClosed()){
			throw new SQLException();
		}
		if(cursor == AFTER_LAST || data.length == 0){
			return false;
		}
		else if(cursor == BEFORE_FIRST){
			cursor = 0;
		}else if(cursor == data.length - 1){
			cursor = AFTER_LAST;
			return false;
		}else{
			cursor++;
		}
		return true;
	}

	@Override
	public boolean previous() throws SQLException {
		// TODO Auto-generated method stub
		if(type == TYPE_FORWARD_ONLY || isClosed()){
			throw new SQLException();
		}
		if(cursor == BEFORE_FIRST || data.length == 0){

			return false;
		}
		else if(cursor == AFTER_LAST){
			cursor = data.length - 1;
		}else if(cursor == 0){
			cursor = BEFORE_FIRST;
			return false;
		}else{
			cursor--;
		}
		return true;
	}

	@Override
	public void refreshRow() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public boolean relative(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public boolean rowDeleted() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public boolean rowInserted() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public boolean rowUpdated() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void setFetchDirection(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void setFetchSize(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void updateArray(int arg0, Array arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void updateArray(String arg0, Array arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void updateAsciiStream(int arg0, InputStream arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void updateAsciiStream(String arg0, InputStream arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void updateAsciiStream(int arg0, InputStream arg1, int arg2) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void updateAsciiStream(String arg0, InputStream arg1, int arg2) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void updateAsciiStream(int arg0, InputStream arg1, long arg2) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void updateAsciiStream(String arg0, InputStream arg1, long arg2) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void updateBigDecimal(int arg0, BigDecimal arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void updateBigDecimal(String arg0, BigDecimal arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void updateBinaryStream(int arg0, InputStream arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void updateBinaryStream(String arg0, InputStream arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void updateBinaryStream(int arg0, InputStream arg1, int arg2) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void updateBinaryStream(String arg0, InputStream arg1, int arg2) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void updateBinaryStream(int arg0, InputStream arg1, long arg2) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void updateBinaryStream(String arg0, InputStream arg1, long arg2) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void updateBlob(int arg0, Blob arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void updateBlob(String arg0, Blob arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void updateBlob(int arg0, InputStream arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void updateBlob(String arg0, InputStream arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void updateBlob(int arg0, InputStream arg1, long arg2) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void updateBlob(String arg0, InputStream arg1, long arg2) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void updateBoolean(int arg0, boolean arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void updateBoolean(String arg0, boolean arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void updateByte(int arg0, byte arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void updateByte(String arg0, byte arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void updateBytes(int arg0, byte[] arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void updateBytes(String arg0, byte[] arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void updateCharacterStream(int arg0, Reader arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void updateCharacterStream(String arg0, Reader arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void updateCharacterStream(int arg0, Reader arg1, int arg2) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void updateCharacterStream(String arg0, Reader arg1, int arg2) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void updateCharacterStream(int arg0, Reader arg1, long arg2) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void updateCharacterStream(String arg0, Reader arg1, long arg2) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void updateClob(int arg0, Clob arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void updateClob(String arg0, Clob arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void updateClob(int arg0, Reader arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void updateClob(String arg0, Reader arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void updateClob(int arg0, Reader arg1, long arg2) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void updateClob(String arg0, Reader arg1, long arg2) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void updateDate(int arg0, Date arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void updateDate(String arg0, Date arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void updateDouble(int arg0, double arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void updateDouble(String arg0, double arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void updateFloat(int arg0, float arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void updateFloat(String arg0, float arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void updateInt(int arg0, int arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void updateInt(String arg0, int arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void updateLong(int arg0, long arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void updateLong(String arg0, long arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void updateNCharacterStream(int arg0, Reader arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateNCharacterStream(String arg0, Reader arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateNCharacterStream(int arg0, Reader arg1, long arg2) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateNCharacterStream(String arg0, Reader arg1, long arg2) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateNClob(int arg0, NClob arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateNClob(String arg0, NClob arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateNClob(int arg0, Reader arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateNClob(String arg0, Reader arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateNClob(int arg0, Reader arg1, long arg2) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateNClob(String arg0, Reader arg1, long arg2) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateNString(int arg0, String arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateNString(String arg0, String arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateNull(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateNull(String arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateObject(int arg0, Object arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateObject(String arg0, Object arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateObject(int arg0, Object arg1, int arg2) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateObject(String arg0, Object arg1, int arg2) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateRef(int arg0, Ref arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateRef(String arg0, Ref arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateRow() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateRowId(int arg0, RowId arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateRowId(String arg0, RowId arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateSQLXML(int arg0, SQLXML arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateSQLXML(String arg0, SQLXML arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateShort(int arg0, short arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateShort(String arg0, short arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateString(int arg0, String arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateString(String arg0, String arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateTime(int arg0, Time arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateTime(String arg0, Time arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateTimestamp(int arg0, Timestamp arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateTimestamp(String arg0, Timestamp arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public boolean wasNull() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

}
