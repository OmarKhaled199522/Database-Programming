package eg.edu.alexu.csd.oop.db;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class MySQL implements Database {

	private RegexChecker r = new RegexChecker();
	private String directory = "";
	private int index = 0;
	private String col[][] = new String[1000 + 10][1000 + 10];
	private Set<String> se = new HashSet<String>();
	private String path;

	public MySQL(String path) {
		this.path = path;
		// Print.log(path, true);
		directory = path;
	}

	private String upda[];
	private String sele[];
	private Xml xml = new Xml();
	private DTD dtd = new DTD();

	private ArrayList<String> lis = new ArrayList<String>();
	private String table = "";

	public String getDirectory() {
		return directory;
	}

	private static final String FILE_NAME = "/debug/omar.log";

	private static boolean delete = true;

	@Override
	public String createDatabase(String databaseName, boolean dropIfExists) {

		if (delete) {
			log("", true);
			delete = false;
		}

		log("create db: " + databaseName + "\n", false);

		String query = "";
		directory = directory + File.separatorChar + databaseName;

		if (dropIfExists) {
			if (Files.exists(Paths.get(directory))) {
				query = "DROP DATABASE " + databaseName;
				try {
					this.executeStructureQuery(query);
				} catch (SQLException e) {
				}
			}
			query = "CREATE DATABASE " + databaseName;
			try {
				this.executeStructureQuery(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log(exceptionToString(e), false);
			}

		} else {

			if (!Files.exists(Paths.get(directory))) {
				query = "CREATE DATABASE " + databaseName;
				try {
					this.executeStructureQuery(query);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					log(exceptionToString(e), false);
				}
			}

		}

		//Print.log(directory, true);

		return directory;

	}

	@Override
	public boolean executeStructureQuery(String query) throws SQLException {

		log("executeStructureQuery: " + query + "\n", false);

		query = query.toLowerCase();
		if (query.length() < 4)
			throw new SQLException();
		String who = query.substring(0, 4).toLowerCase();
		if (who.equals("drop")) {

			if (query.contains("database")) {

				String search = "(drop\\s+database\\s+\\w+;?)";
				String g = r.regexChecker1(search, query);
				if (!g.equals(query))
					throw new SQLException();
				String name = "";

				for (int i = g.length() - 1; g.charAt(i) != ' '; i--) {
					if (g.charAt(i) != ';')
						name += g.charAt(i);
				}
				// name is avaialabale
				name = new StringBuffer(name).reverse().toString();
				directory = path + File.separator + name.toLowerCase();
				File f = new File(directory);
				if (!f.exists()) {
					//Print.log(directory, true);
					return true;
				}
				String[] entries = f.list();
				for (String s : entries) {
					File currentFile = new File(f.getPath(), s);
					s = "table " + s.split("\\.")[0];
					currentFile.delete();
					if (se.contains(s)) {
						se.remove(s);

					}
				}
				try {

					f.delete();
					directory = path;
					se.clear();
					return true;
				} catch (Exception e) {
					// return false;
				}
			}

			String search = "drop\\s+TaBlE\\s+\\w+";
			String g = r.regexChecker1(search, query);
			if (!query.equals(g))
				throw new SQLException();
			search = "table\\s+\\w+";
			String name = r.regexChecker1(search, query);
			// now table name is stored

			if (!se.contains(name))
				return false;
			name = name.split("\\s")[1];
			// delete the table from file xml_delete()

			File f = new File(directory + File.separatorChar + name + ".xml");
			f.delete();
			File m = new File(directory + File.separatorChar + name + ".dtd");
			m.delete();
			return true;

		}

		if (query.contains("database")) {

			String search = "(create\\s+database\\s+\\w+;?)";
			String g = r.regexChecker1(search, query);
			if (!g.equals(query))
				throw new SQLException();
			String name = "";
			for (int i = g.length() - 1; g.charAt(i) != ' '; i--) {
				if (g.charAt(i) != ';')
					name += g.charAt(i);
			}
			name = new StringBuffer(name).reverse().toString();
			// name is available now
			directory = path + File.separatorChar + name;
			File f = new File(directory);
			f.mkdirs();

			return true;

		}
		this.setEmpty();
		String search = "creatE\\s+TaBlE\\s+\\w+\\s*\\(";

		String g = r.regexChecker1(search, query);
		int i = 0;
		String c = "";

		while (true) {
			if (g.charAt(i) != query.charAt(i))
				throw new SQLException();
			if (query.charAt(i++) == '(')
				break;
		}

		c += query.substring(i);

		search = "(\\w+\\s+\\w+\\s*,\\s*)|(\\w+\\s+\\w+\\s*\\))";

		String[] colm = r.regexChecker2(search, c);
		int id = 0;

		while (id != colm.length) {
			col[0][id] = colm[id].substring(0, colm[id].length() - 1);
			if (id + 1 != colm.length)
				col[0][id] = colm[id].substring(0, colm[id].length() - 2);
			g += colm[id++];
		}

		if (!g.equals(query))
			return false;

		search = "table\\s+\\w+";
		String name = r.regexChecker1(search, query);
		name = name.substring(0, name.length());

		if (se.contains(name))
			return false;
		se.add(name);
		if (directory.equalsIgnoreCase(""))
			throw new SQLException();
		this.save(name);

		return true;
	}

	@Override
	public Object[][] executeQuery(String query) throws SQLException {

		log("executeQuery: " + query + "\n", false);

		query = query.toLowerCase();

		Select sel = new Select();
		sele = sel.select_order(query);
		String name = sel.getName();

		this.load(name);

		if (sele == null) {

			int row = 0, column = 0;
			for (int i = 0; i < col.length; i++) {
				if (col[i][0] != null)
					row++;
				else
					break;
			}
			for (int i = 0; i < col[0].length; i++) {
				if (col[0][i] != null)
					column++;
				else
					break;
				lis.add(col[0][i]);
			}
			String str[][] = new String[row][column];
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < column; j++) {
					str[i][j] = col[i][j];
				}
			}

			Object[][] ob = this.sel(col, row, column);
			return ob;
		}

		boolean arra[] = new boolean[sele.length];
		int to_what = 0;

		if (sel.isAst_where() == true) {
			for (int i = 0;; i++) {
				if (col[0][i] == null)
					break;
				arra[i] = true;
				// lis.add(col[0][i]);
			}
		}

		for (int i = 0; i < sele.length; i++) {

			to_what++;

			if (sele[i].equals("\\//")) {
				break;
			}
			for (int j = 0;; j++) {
				if (col[0][j] == null) {
					break;
				}
				if (col[0][j].contains(sele[i]) || sel.isAst_where() == true) {
					arra[j] = true;
				}

			}
		}

		int num_col = 0;

		for (int i = 0; i < col[0].length; i++) {
			num_col++;
			if (col[0][i] == null)
				break;
		}

		String gg = sele[to_what];

		boolean eq = false, big = false, sm = false;
		String che = "";

		for (int i = 0; i < gg.length(); i++) {
			if (gg.charAt(i) == '=') {
				{
					eq = true;
					continue;
				}
			} else if (gg.charAt(i) == '<') {
				sm = true;
				continue;
			} else if (gg.charAt(i) == '>') {
				big = true;
				continue;
			}

			if (sm == true || big == true || eq == true) {
				if (gg.charAt(i) != ' ')
					che += gg.charAt(i);
			}
		}

		int num = -1;

		String g = "";
		for (int j = 0; j < gg.length(); j++) {
			if (gg.charAt(j) == '=' || gg.charAt(j) == '>'
					|| gg.charAt(j) == '<')
				break;
			if (gg.charAt(j) != ' ')
				g += gg.charAt(j);
		}

		int num_of_sele = 0;

		for (int i = 0; i < sele.length; i++) {
			num_of_sele++;
			if (sele[i].equals("\\//") && !sel.isAst_where()) {
				break;
			}
			if (sel.isAst_where() == true && i == 1)
				break;

			for (int j = 0;; j++) {
				if (col[0][j] == null) {
					break;
				}
				if (col[0][j].contains(g)) {
					num = j;
					break;
				}
			}
		}
		if (num == -1)
			throw new SQLException();
		String ret_select[][] = new String[10 + 10][num_col];
		int id = 0;

		index = r.stop(col);

		for (int i = 1; i < index; i++) {
			if (eq == true) {
				if (col[i][num].equals(che)) {
					int zy = 0;
					for (int j = 0; j < num_col; j++) {
						if (arra[j] == true) {
							ret_select[id][zy++] = col[i][j];
							lis.add(col[0][j]);
						}
					}
					id++;
				}
			} else if (sm == true) {
				int chek = Integer.parseInt(che);
				int inside = Integer.parseInt(col[i][num]);
				if (inside < chek) {

					int zy = 0;
					for (int j = 0; j < num_col; j++) {
						if (arra[j] = true) {
							ret_select[id][zy++] = col[i][j];
							getLis().add(col[0][j]);
						}
					}
					id++;

				}

			} else if (big == true) {
				int chek = Integer.parseInt(che);
				int inside = Integer.parseInt(col[i][num]);
				if (inside > chek) {

					int zy = 0;
					for (int j = 0; j < num_col; j++) {
						if (arra[j] = true) {
							ret_select[id][zy++] = col[i][j];
							lis.add(col[0][j]);
						}
					}
					id++;
				}
			}
		}

		if (sel.isAst_where() == true)
			num_of_sele = num_col;
		String select_fin[][] = new String[id + 1][num_of_sele - 1];

		for (int i = 1; i < id + 1; i++) {
			for (int j = 0; j < num_of_sele - 1; j++) {
				select_fin[i][j] = ret_select[i - 1][j];
				select_fin[i][j] = select_fin[i][j].replaceAll("'", "");
			}
		}
		return sel(select_fin, select_fin.length, select_fin[0].length);
		// elmafrood tb2a object el owel bel types
	}

	@Override
	public int executeUpdateQuery(String query) throws SQLException {

		log("executeUpdateQuery" + query + "\n", false);

		if (query.length() < 6)
			throw new SQLException();
		String s = query.substring(0, 6);
		s = s.toLowerCase();
		query = query.toLowerCase();

		if (s.equals("insert")) {

			Insert ins = new Insert();

			String[] sss = ins.insert_order(query, index, col[0]);

			String name = ins.getName();
			this.load(name);
			index = r.stop(col);
			for (int i = 0; col[0][i] != null; i++) {
				col[index][i] = sss[i];
			}
			this.save(name);
			return 1;

		} else if (s.equals("delete")) {
			Delete del = new Delete();
			String gg = del.delete_order(query);
			String name = del.getName();
			this.load(name);
			index = r.stop(col);
			if (gg == null) {
				for (int i = 1; i < col.length && col[i][0] != null; i++) {
					for (int j = 0; j < col[0].length && col[i][j] != null; j++)
						col[i][j] = null;
				}

				this.save(name);
				return index - 1;
			}

			int ret = 0;

			String g = "";
			for (int j = 0; j < gg.length(); j++) {
				if (gg.charAt(j) == '=' || gg.charAt(j) == '>'
						|| gg.charAt(j) == '<')
					break;
				if (gg.charAt(j) != ' ')
					g += gg.charAt(j);
			}
			int num = -1;

			for (int j = 0; j < col[0].length; j++) {
				String e = col[0][j];
				if (e == null) {
					break;
				}
				String coparee = "";
				for (int k = 0; e.charAt(k) != ' '; k++) {
					coparee += e.charAt(k);
				}
				if (coparee.equals(g)) {
					num = j;
					break;
				}
			}

			if (num == -1)
				throw new SQLException();
			boolean eq = false, big = false, sm = false;
			String che = "";

			for (int i = 0; i < gg.length(); i++) {
				if (gg.charAt(i) == '=') {
					{
						eq = true;
						continue;
					}
				} else if (gg.charAt(i) == '<') {
					sm = true;
					continue;
				} else if (gg.charAt(i) == '>') {
					big = true;
					continue;
				}

				if (sm == true || big == true || eq == true) {
					if (gg.charAt(i) != ' ')
						che += gg.charAt(i);
				}
			}

			index = r.stop(col);
			for (int i = 1; i < index; i++) {
				if (eq == true) {
					if (col[i][num].equals(che)) {
						col[i][0] = null;
						ret++;
					}
				} else if (sm == true) {
					int chek = Integer.parseInt(che);

					int inside = Integer.parseInt(col[i][num]);
					if (inside < chek) {
						col[i][0] = null;
						ret++;
					}
				} else if (big == true) {
					int chek = Integer.parseInt(che);

					int inside = Integer.parseInt(col[i][num]);
					if (inside > chek) {
						col[i][0] = null;
						ret++;
					}
				}
			}

			// col = r.adjust_array(col, index - ret, index, addelcol);
			for (int i = 0; i < index; i++) {
				if (col[i][0] == null) {
					for (int j = 1; col[i][j] != null; j++) {
						col[i][j] = null;
					}
				}
			}

			// now you ca use col [][]
			name = del.getName();
			this.shiftRows(name);
			// this.delete(name);

			return ret;

			// if (arr == null) for(i < n)
		} else if (s.equals("update")) {
			Update updo = new Update();
			upda = updo.take_order(query);
			String name = updo.getName();
			this.load(name);

			boolean arra[] = new boolean[upda.length];
			String settt[] = new String[upda.length];

			int to_what = 0;

			for (int i = 0; i < upda.length; i++) {

				to_what++;

				if (upda[i].equals("\\//")) {
					break;
				}

				String befo = "", aft = "";
				boolean sign = false;

				for (int j = 0; j < upda[i].length(); j++) {
					if (upda[i].charAt(j) != ' ') {
						if (upda[i].charAt(j) == '=') {
							sign = true;
							continue;
						}
						if (!sign)
							befo += upda[i].charAt(j);
						else
							aft += upda[i].charAt(j);
					}
				}

				for (int j = 0;; j++) {
					if (col[0][j] == null) {
						break;
					}
					if (col[0][j].contains(befo)) {
						arra[j] = true;
						settt[j] = aft;
					}

				}
			}

			if (updo.isWithout_con() == true) {

				int num_col = 0;
				for (int i = 0; i < col[0].length; i++) {
					num_col++;
					if (col[0][i] == null)
						break;
				}

				int ret = 0;

				index = r.stop(col);
				for (int i = 1; i < index; i++) {

					ret++;
					for (int j = 0; j < num_col; j++) {
						if (arra[j] == true) {
							col[i][j] = settt[j];
						}
					}
				}

				name = updo.getName();
				this.save(name);
				return ret;

			}

			String gg = upda[to_what];

			boolean eq = false, big = false, sm = false;
			String che = "";

			for (int i = 0; i < gg.length(); i++) {
				if (gg.charAt(i) == '=') {
					{
						eq = true;
						continue;
					}
				} else if (gg.charAt(i) == '<') {
					sm = true;
					continue;
				} else if (gg.charAt(i) == '>') {
					big = true;
					continue;
				}

				if (sm == true || big == true || eq == true) {
					if (gg.charAt(i) != ' ')
						che += gg.charAt(i);
				}
			}

			int num = -1;

			String g = "";
			for (int j = 0; j < gg.length(); j++) {
				if (gg.charAt(j) == '=' || gg.charAt(j) == '>'
						|| gg.charAt(j) == '<')
					break;
				if (gg.charAt(j) != ' ')
					g += gg.charAt(j);
			}

			int num_col = 0;

			for (int i = 0; i < upda.length; i++) {
				if (upda[i].equals("\\//")) {
					break;
				}
				for (int j = 0;; j++) {
					if (col[0][j] == null) {
						break;
					}
					if (col[0][j].contains(g)) {
						num = j;
						break;
					}
				}
			}
			for (int i = 0; i < col[0].length; i++) {
				num_col++;
				if (col[0][i] == null)
					break;
			}

			if (num == -1)
				throw new SQLException();

			int ret = 0;

			index = r.stop(col);
			for (int i = 1; i < index; i++) {
				if (eq == true) {
					if (col[i][num].equals(che)) {
						ret++;
						for (int j = 0; j < num_col; j++) {
							if (arra[j] == true) {
								col[i][j] = settt[j];
							}
						}
					}
				} else if (sm == true) {
					int chek = Integer.parseInt(che);
					int inside = Integer.parseInt(col[i][num]);
					if (inside < chek) {
						ret++;

						for (int j = 0; j < num_col; j++) {
							if (arra[j] == true) {
								col[i][j] = settt[j];
							}
						}
					}

				} else if (big == true) {
					int chek = Integer.parseInt(che);
					int inside = Integer.parseInt(col[i][num]);
					if (inside > chek) {
						ret++;
						for (int j = 0; j < num_col; j++) {
							if (arra[j] == true) {
								col[i][j] = settt[j];
							}
						}
					}
				}
			}
			name = updo.getName();
			this.save(name);
			return ret;

		} else
			throw new SQLException();

	}

	public void save(String name) {
		if (name.contains(" "))
			name = name.split("\\s")[1];
		dtd.setArr(col);
		try {
			dtd.writeDTD(directory + File.separatorChar + name + ".dtd");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log(exceptionToString(e), false);
		}
		xml.setArr(col);
		try {
			xml.saveXml(directory + File.separatorChar + name + ".xml");
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			log(exceptionToString(e), false);
		}

	}

	public void load(String name) throws SQLException {
		if (name.contains(" "))
			name = name.split("\\s")[1];
		try {
			xml.readXml(directory + File.separatorChar + name + ".xml");
		} catch (ParserConfigurationException | SAXException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new SQLException("useful information", e1);
		}
		col = xml.getLoaded();
	}

	Object[][] sel(String retu[][], int rows, int colums) {
		Object selected[][] = new Object[rows - 1][colums];

		for (int i = 1; i < rows; i++) {
			for (int j = 0; j < colums; j++) {
				char letters[] = retu[i][j].toCharArray();
				int intCnt = 0;
				for (int k = 0; k < letters.length; k++) {
					if (letters[k] >= '0' && letters[k] <= '9')
						intCnt++;
				}
				if (intCnt == letters.length) {
					selected[i - 1][j] = (Integer) selected[i - 1][j];
					selected[i - 1][j] = Integer.parseInt(retu[i][j]);
				} else {
					selected[i - 1][j] = (String) selected[i - 1][j];
					selected[i - 1][j] = retu[i][j];
					selected[i - 1][j] = ((String) selected[i - 1][j])
							.replaceAll("'", "");

				}
			}
		}
		return selected;

	}

	public void setEmpty() {
		for (int i = 0; i < col.length && col[i][0] != null; i++) {
			for (int j = 0; j < col[0].length && col[i][j] != null; j++) {
				col[i][j] = null;
			}
		}
		index = 0;
	}

	private static void log(String str, boolean delete) {
		try {
			if (delete)
				new File(FILE_NAME).delete();

			java.nio.file.Files.write(java.nio.file.Paths.get(FILE_NAME), str
					.getBytes(),
					new File(FILE_NAME).exists() ? StandardOpenOption.APPEND
							: StandardOpenOption.CREATE);
		} catch (Throwable e1) {
			// e1.printStackTrace();
		}
	}

	private static String exceptionToString(Throwable e) {
		String str = "";
		for (StackTraceElement ee : e.getStackTrace())
			str += ee.getFileName() + " " + ee.getMethodName() + " "
					+ ee.getLineNumber() + "\n";
		if (e.getCause() != null)
			str += exceptionToString(e.getCause());
		return str;
	}

	public void shiftRows(String name) {
		String temp[][] = new String[1010][1010];
		int r = -1, c = 0;
		for (int i = 0; i < 1000; i++) {
			c = 0;
			if (col[i][0] != null)
				r++;
			for (int j = 0; j < 500 && col[i][j] != null; j++) {
				temp[r][c++] = col[i][j];
			}
		}
		col = temp;
		this.save(name);
	}

	public ArrayList<String> getLis() {
		return lis;
	}

	public void setLis(ArrayList<String> lis) {
		this.lis = lis;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

}
