package eg.edu.alexu.csd.oop.db;


import java.sql.SQLException;

public class Select extends Object {
	
	private RegexChecker r = new RegexChecker();
	private String ret [] = new String [1000 + 10];
	private boolean ast_where = false;
	private String name = "";
	
	public String [] select_order(String query) throws SQLException{
		
		
		if( query.contains("*") ){
			
			
			if(query.contains("where")){
				
				String search = "SeLeCt\\s\\*\\sFroM\\s\\w+\\s+";
				String g1 = r.regexChecker1 (search, query);
				String c = "";
				
				setName("");
				
				int eee = query.indexOf("from");
				
				int wh = query.indexOf("where");
				for(int j = eee + 4; j < wh; j++){
					if(query.charAt(j) != ' ') setName(getName() + query.charAt(j));
				}
				
				setName("table " + getName());
				// now name is stored
				
				c = query.substring(g1.length());
				search = "WhERe\\s+";
				g1 += r.regexChecker1(search, c);
				c = query.substring(g1.length());
				search = "(\\w+\\s*=\\s*\\w+)|(\\w+\\s*<\\s*\\w+)|(\\w+\\s*>\\s*\\w+)|(\\w+\\s*=\\s*'\\w+')";
				String h = r.regexChecker1(search, c);
				g1 += h;
				ret[0] = "\\//";
				ret[1] = h;
				setAst_where(true);
				if(!g1.equals(query)){
					throw new SQLException();
				}
				return ret;
			}
			
			
			String search = "SeLeCt\\s\\*\\sFroM\\s\\w+(;|\\s*)";
			String g1 = r.regexChecker1 (search, query);
			int i = 0;
			setName("");
			
			int ee = query.indexOf("from");
			for(int j = ee + 4; j < query.length(); j++){
				if(query.charAt(j) != ' ' && query.charAt(j) != ';') setName(getName() + query.charAt(j));
			}
			
			setName("table " + getName());
			// now name is stored
			
			while(true){
				if(i == g1.length()) break;
				if(g1.charAt(i) != query.charAt(i++)) throw new SQLException();
			}
			if(i != query.length()) throw new SQLException();
			
			return null;
			
			
		}
		
		
		String search = "SeleCT\\s+";
		String g = r.regexChecker1(search, query);
		
		int w = 0;
		for(w = 0; w < query.length(); w++){
			if(w + 4 >= query.length()) break;
			else if ( (query.charAt(w) == 'f' || query.charAt(w) == 'F') && (query.charAt(w + 1) == 'r' || query.charAt(w) == 'R') && ( query.charAt(w + 2) == 'o' || query.charAt(w + 2) == 'O') && (query.charAt(w + 3) == 'm' || query.charAt(w + 3) == 'M')  ){
				break;
			}
		}
		if(w + 3 == query.length()) w = query.length();
		int eee = query.indexOf("from");
		
		boolean is_there = false;
		for(int i = g.length(); i < eee; i++){
			if(query.charAt(i) != ' '){
				is_there = true;
				break;
			}
		}
		String c = "";
		int id = 0;
		
		if(is_there){
			search = "(\\w+,)|(\\w+\\s+)";
			 c = query.substring(g.length(), eee);
			String colm[] = r.regexChecker2(search, c);
			
			id = 0;
			for(int j = 0; j < colm.length; j++){
				ret[id++] = colm[j].substring(0, colm[j].length() - 1); 
				g += colm[j];
			}
		} else setAst_where(true);
		c = query.substring(g.length());
		search = "from\\s+\\w+\\s+";
		g += r.regexChecker1(search, c);
		
		setName("");
		int wh = query.indexOf("where");
		for(int j = eee + 4; j < wh; j++){
			if(query.charAt(j) != ' ') setName(getName() + query.charAt(j));
		}
		setName("table " + getName());
		
		ret[id++] = "\\//";
		c = query.substring(g.length());
		search = "WhERe\\s+";
		g += r.regexChecker1(search, c);
		c = query.substring(g.length());
		search = "(\\w+\\s*=\\s*\\w+)|(\\w+\\s*<\\s*\\w+)|(\\w+\\s*>\\s*\\w+)|(\\w+\\s*=\\s*'\\w+')";
		String h = r.regexChecker1(search, c);
		g += h;
		ret[id++] = h;
		if(!g.equals(query)) throw new SQLException();
		
		return ret;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isAst_where() {
		return ast_where;
	}

	public void setAst_where(boolean ast_where) {
		this.ast_where = ast_where;
	}
	
}
