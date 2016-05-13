package eg.edu.alexu.csd.oop.db;

import java.sql.SQLException;


public class Delete {

	private RegexChecker r = new RegexChecker();
	private String name = "";
	
	public String delete_order(String query) throws SQLException{
		
	
		if(query.contains("*")){
			
			String search = "DeLeTe\\s+\\*\\s+FroM\\s+\\w+";
			String g1 = r.regexChecker1 (search, query);
			setName("");
			
			int e = query.indexOf("from");
			e += 4;
			for(int j = e; j < g1.length(); j++){
				if(query.charAt(j) != ' ' && query.charAt(j) != ';') setName(getName() + query.charAt(j));
			}
			setName("table "+ getName());
			
			// now name is stored
			
			int i = 0;

			
			while(true){
				if(i == g1.length()) break;
				if(g1.charAt(i) != query.charAt(i++)) throw new SQLException();
			}
			if(i != query.length()) throw new SQLException();
			
			return null;
		}
		

		if(!query.contains("where")){
			String search = "DeLeTE\\s+From\\s+\\w+";
			setName("");
			String g = r.regexChecker1(search, query);
			if(!g.equals(query)) throw new SQLException();
			for(int i = g.length() - 1; g.charAt(i) != ' '; i--){
				if(g.charAt(i) != ';') setName(getName() + g.charAt(i));
			}
			setName(new StringBuffer(getName()).reverse().toString());
			setName("table " + getName());
			
			return null;
		}
		
		int e = query.indexOf("from");
		int www = query.indexOf("where");
		e += 4;
		for(int j = e; j < www; j++){
			if(query.charAt(j) != ' ') setName(getName() + query.charAt(j));
		}
		setName("table "+ getName());
		
		String search = "DeLeTE\\s+From\\s+\\w+\\s+WherE\\s+";
		String g = r.regexChecker1(search, query);
		search = "(\\w+\\s*=\\s*\\w+)|(\\w+\\s*<\\s*\\w+)|(\\w+\\s*>\\s*\\w+)|(\\w+\\s*=\\s*'\\w+')";
		String h = r.regexChecker1(search, query);
		if(!query.equals(g + h)) throw new SQLException();
		
		return h;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
