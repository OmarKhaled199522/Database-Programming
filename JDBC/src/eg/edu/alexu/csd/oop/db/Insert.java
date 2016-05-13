package eg.edu.alexu.csd.oop.db;

import java.sql.SQLException;

public class Insert {

	private RegexChecker r = new RegexChecker();
	private String col [][] = new String [1000 + 10][1000 + 10];
	private String row [];
	private String  name = "";
	
	public String [] insert_order(String query, int index, String arr []) throws SQLException{
		if(query.contains("values")){
			
		
			// INSERT INTO table_name10(column_NAME1, COLUMN_name3, column_name2) VALUES ('value1', 'value3', 4)
			
			int ee = query.indexOf("(");
			int rr = query.indexOf("values");
			row = new String [ arr.length ];
			
			if(rr < ee){
				
				// new insert
				String search = "InSert\\s+InTo\\s+\\w+\\s+values\\s*\\(";
				String g = r.regexChecker1 (search, query);

				setName("");
				int eee = query.indexOf("into");
				int rrr = query.indexOf("values");
				for(int j = eee + 4; j < rrr; j++){
					if(query.charAt(j) != ' ') setName(getName() + query.charAt(j));
				}
				
				setName("table " + getName());
				// name is stored now
				
				int i = 0;
				String c = "";
				
				while(true){
					if(g.charAt(i) != query.charAt(i)) throw new SQLException();
					if(query.charAt(i++) == '(') break;
				}
				
				
				c += query.substring(i);
				search = "(\\w+,\\s*)|('\\w+',\\s*)|(\\w+\\s*\\))|('\\w+'\\s*\\))";
				
				String [] colm = r.regexChecker2(search, c);
				//System.out.println(colm[0].charAt(colm[0].length() - 1));
				int id = 0;

				
				
				while(id != colm.length){
					col[index][id] = colm[id].substring(0,  colm[id].length() - 1 );
					if(id + 1 != colm.length) col[index][id] = colm[id].substring(0,  colm[id].length() - 2 );
					g += colm[id];
					id++;
				}
				row = new String [id];
				for(int k = 0; k < id; k++){
					row[k] = col[index][k];
				}
				
				if(!g.equals(query)) throw new SQLException();
				return row;
				
				
				
				
			}
			
			
			
			String search = "InSert\\s+IntO\\s+\\w+\\s*\\(";
			String g = r.regexChecker1(search, query);
			boolean flag = false;
			setName("");
			for(int j = 0; g.charAt(j) != '('; j++){
				if(g.charAt(j) != ' ' && flag == true){
					setName(getName() + g.charAt(j));
				}
				if(j < 4) continue;
				if( (g.charAt(j) == 'O' || g.charAt(j) == 'o') && (g.charAt(j - 1) == 'T' || g.charAt(j - 1) == 't') && (g.charAt(j - 2) == 'N' || g.charAt(j - 2) == 'n') && (g.charAt(j - 3) == 'I' || g.charAt(j - 3) == 'i') ){
					flag = true;
				}
			}
			setName("table " + getName());
			// name is stored now
			
			int i = 0;
			String c = "";
			
			while(true){
				if(g.charAt(i) != query.charAt(i)) throw new SQLException();
				if(query.charAt(i++) == '(') break;
			}
			
			
			c += query.substring(i, query.indexOf("values"));
			search = "(\\w+,\\s*)|(\\w+\\))";
			String [] colm = r.regexChecker2(search, c);
			//System.out.println(colm[0].charAt(colm[0].length() - 1));
			int id = 0;

			
			//int ne = i;
			
			while(id != colm.length){
				col[index][id] = colm[id].substring(0,  colm[id].length() - 1 );
				if(id + 1 != colm.length) col[index][id] = colm[id].substring(0,  colm[id].length() - 2 );
				g += colm[id++];
				//ne += colm[id++].length();
			}
			
			int where [] = new int [ arr.length ]; 
			
			for(int k = 0; k < colm.length; k++){
				for(int f = 0;f < colm.length; f++){
					if(arr[f].contains(col[index][k])){
						where[f] = k;
						break;
					}
				}
			}
			
			c = query.substring(g.length());
			search = "\\s*VALUES\\s*\\(";
			String h = r.regexChecker1(search, c);
			g += h;
			c = query.substring(g.length());
			search = "(\\w+\\s*,\\s*)|('\\w+\\s*\\w*'\\s*,\\s*)|(\\w+\\s*\\))|('\\w+\\s*\\w*'\\s*\\))";
			colm = r.regexChecker2(search, c);
			int begin = 0;
			
			while(begin != colm.length){
				col[index][id] = colm[begin].substring(0,  colm[begin].length() - 1 );
				if(begin + 1 != colm.length) col[index][id] = colm[begin].substring(0,  colm[begin].length() - 2 );
				g += colm[begin++];
				id++;
			}
			for(int k = 0; k < colm.length; k++){
				row[k] = col[index][ where[k] + colm.length ];
			}
			
			
			if(!g.equals(query)) throw new SQLException();
			return row;
		} else {
			
			String search = "InSert\\sInTo\\s+\\w+\\s*\\(";
			String g = r.regexChecker1 (search, query);
			boolean flag = false;
			setName("");
			for(int j = 0; g.charAt(j) != '('; j++){
				if(g.charAt(j) != ' ' && flag == true){
					setName(getName() + g.charAt(j));
				}
				if(j < 4) continue;
				if( (g.charAt(j) == 'O' || g.charAt(j) == 'o') && (g.charAt(j - 1) == 'T' || g.charAt(j - 1) == 't') && (g.charAt(j - 2) == 'N' || g.charAt(j - 2) == 'n') && (g.charAt(j - 3) == 'I' || g.charAt(j - 3) == 'i') ){
					flag = true;
				}
			}
			setName("table " + getName());
			// name is stored now
			
			int i = 0;
			String c = "";
			
			while(true){
				if(g.charAt(i) != query.charAt(i)) throw new SQLException();
				if(query.charAt(i++) == '(') break;
			}
			
			
			c += query.substring(i);
			search = "(\\w+,\\s*)|('\\w+',\\s*)|(\\w+\\))|('\\w+\\)')";
			
			String [] colm = r.regexChecker2(search, c);
			//System.out.println(colm[0].charAt(colm[0].length() - 1));
			int id = 0;
			
			while(id != colm.length){
				col[index][id] = colm[id].substring(0,  colm[id].length() - 1 );
				if(id + 1 != colm.length) col[index][id] = colm[id].substring(0,  colm[id].length() - 2 );
				g += colm[id];
				id++;
			}
			row = new String [id];
			for(int k = 0; k < id; k++){
				row[k] = col[index][k];
			}
			
			if(!g.equals(query)) throw new SQLException();
			
		}
		return row;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
