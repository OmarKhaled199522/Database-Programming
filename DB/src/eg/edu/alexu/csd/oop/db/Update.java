package eg.edu.alexu.csd.oop.db;

import java.sql.SQLException;

public class Update {

	String arr [] = new String [100000 + 10];
	RegexChecker r = new RegexChecker();
	boolean without_con = false;
	String name = "";
	
	public String [] take_order(String query) throws SQLException{
		
		String search = "Update\\s\\w+\\sSEt\\s";
		String g = r.regexChecker1 (search, query);
		name = "";
		for(int j = 6; j < g.length() ; j++){
			if( (g.charAt(j) == 's' || g.charAt(j) == 'S') && (g.charAt(j + 1) == 'e' || g.charAt(j + 1) == 'E') &&  (g.charAt(j + 2) == 't' || g.charAt(j + 2) == 'T')  ){
				break;
			}
			if(g.charAt(j) != ' ') name += g.charAt(j);
		}
		name = "table " +  name;
		// now name is updated
		int i = 0;
		String c = "";
		
		while(true){
			if(i == g.length()) break;
			if(g.charAt(i) != query.charAt(i++)) throw new SQLException();
		}
		int j = 0;
		for(j = 0; j < query.length(); j++){
			if(j + 4 >= query.length()) break;
			else if ( (query.charAt(j) == 'w' || query.charAt(j) == 'W') && (query.charAt(j + 1) == 'h' || query.charAt(j) == 'H') && ( query.charAt(j + 2) == 'e' || query.charAt(j + 2) == 'E') && (query.charAt(j + 3) == 'R' || query.charAt(j + 3) == 'r') && (query.charAt(j + 4) == 'e' || query.charAt(j + 4) == 'E')  ){
				break;
			}
		}
		if(j + 4 == query.length()) j = query.length();
		c += query.substring(i , j);
		search = "(\\w+\\s*=\\s*'\\w+',\\s)|(\\w+\\s*=\\s*'\\w+'\\s)|(\\w+\\s*=\\s*\\w+,\\s)|(\\w+\\s*=\\s*\\w+\\s)|(\\w+\\s*=\\s*\\w+)|(\\w+\\s*=\\s*'\\w+')";
		String [] colm = r.regexChecker2(search, c);
		//System.out.println(colm[0].charAt(colm[0].length() - 1));
		int id = 0;
		String d = "";
	    int ne = i + 1;
	    while(id != colm.length){
	    	if(id + 1 != colm.length) arr[id] = colm[id].substring(0,  colm[id].length() - 2 );
	    	else if(colm[id].charAt(colm[id].length() - 1) == ' ' ) arr[id] = colm[id].substring(0,  colm[id].length() - 1);
	    	else arr[id] = colm[id].substring(0,  colm[id].length() );
			g += colm[id++];
		}
	    if(g.equals(query)){
	    	without_con = true;
	    	arr[id++] = "\\//";
	    	return arr;
	    }
		
		c = query.substring(g.length());
		arr[id++] = "\\//";
		//System.out.println(ne + " " + query.length());
		if(ne == query.length()) return arr;
		search = "WheRe\\s";
		String h = r.regexChecker1(search, c);
		g += h;
		//System.out.println(query.charAt(42) + " " + query.charAt(43));
		
		c = query.substring(g.length());
		
		search = "(\\w+\\s*=\\s*'\\w+',\\s)|(\\w+\\s*=\\s*'\\w+')|(\\w+\\s*=\\s*\\w+,\\s)|(\\w+\\s*=\\s*\\w+)|(\\w+\\s*<\\s*'\\w+',\\s)|(\\w+\\s*<\\s*'\\w+')|(\\w+\\s*<\\s*\\w+,\\s)|(\\w+\\s*<\\s*\\w+)|(\\w+\\s*>\\s*'\\w+',\\s)|(\\w+\\s*>\\s*'\\w+')|(\\w+\\s*>\\s*\\w+,\\s)|(\\w+\\s*>\\s*\\w+)";
		colm = r.regexChecker2(search, c);
		//System.out.println(colm[0].charAt(colm[0].length() - 1));
		int st = 0;
		d = "";
	
	    while(st != colm.length){
			arr[id] = colm[st].substring(0,  colm[st].length() );
			g += colm[st++];
			id++;
		
		}
	    
	    if(!g.equals(query)) throw new SQLException();
		
		return arr;
	}
	
}
