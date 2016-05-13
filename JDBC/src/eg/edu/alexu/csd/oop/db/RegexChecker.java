package eg.edu.alexu.csd.oop.db;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexChecker {

	
	 private String col [] = new String [100000 + 10];
	
	 public String regexChecker1(String theRegex, String str2Check) throws SQLException{
         
         Pattern checkRegex = Pattern.compile(theRegex , Pattern.CASE_INSENSITIVE);
       
	        Matcher regexMatcher = checkRegex.matcher( str2Check );
	         
	        if ( regexMatcher.find() ){
	        	String s = regexMatcher.group();   
	        	if( regexMatcher.find() ) throw new SQLException();
	        	return s;
	        }
	        
	        throw new SQLException();
	 }
	
	 public String [] regexChecker2(String theRegex, String str2Check) throws SQLException{
         
         Pattern checkRegex = Pattern.compile(theRegex , Pattern.CASE_INSENSITIVE);
       
	        Matcher regexMatcher = checkRegex.matcher( str2Check );
	        
	        int i = 0;
	        String sm [];
	         
	        while ( regexMatcher.find() ){
	        	
	        	String s = regexMatcher.group();   
	        	col[i++] = s;
	        }
	        
	        sm = new String [i];
	        int j = 0;
	        while(j < i){
	        	sm[j] = col[j++];
	        }
	        
	        if (i == 0) throw new SQLException();
	        else return sm;
	 }
	 
	 public String [][] adjust_array(String  input [][] , int tru_rows, int index , int colnum){
		
		 String [][] output = new  String [tru_rows][];
		 int put = 0;
		 
		 for(int i = 0; i < index; i++){
			 if(input[0][i] != null){
				 for(int j = 0; j < colnum; j++){
					 output[put][j] = input[i][j];
				 }
				 put++;
			 }
		 }
		 
		 return output;
	 }
	 public int stop (String [][] s){
		for(int i = 0; i < 1000 + 10; i++){
			if(s[i][0] == null) return i;
		}
		return 0;
		 
	 }
}
