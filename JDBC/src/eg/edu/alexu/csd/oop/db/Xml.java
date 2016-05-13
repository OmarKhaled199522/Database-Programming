package eg.edu.alexu.csd.oop.db;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Xml {
	private String loaded[][];
	private String arr[][] = { { "ID int", "Name string", "City string" },
			{ "1", "Ali", "Alex" } };
	private String[] colNames;

	public void setArr(String[][] columns) {
		int row = 0, col = 0, oldRow = 0;
		boolean nullFlag = false;
		for (int i = 0; i < columns.length; i++) {
			if (columns[i][0] != null)
				row++;
			else
				break;
		}
		for (int i = 0; i < columns[0].length; i++) {
			if (columns[0][i] != null)
				col++;
			else
				break;
		}
		oldRow = row;
		if (row == 1) {
			row = 2;
			nullFlag = true;
			arr = new String[row][col];
		}
		arr = new String[row][col];
		if (!nullFlag)
			arr = new String[row][col];
		for (int i = 0; i < oldRow; i++) {

			for (int j = 0; j < col; j++) {
				arr[i][j] = columns[i][j];
			}
		}
		if (nullFlag)
			for (int i = 0; i < col; i++)
				arr[1][i] = "NULL";
	}

	public String[][] getLoaded() {
		if (loaded[1][0].equalsIgnoreCase("NULL")) {
			for (int i = 0; i < loaded[0].length; i++)
				loaded[1][0] = null;
		}
		return loaded;
	}

	public void saveXml(String xml) throws ParserConfigurationException {
		xml = xml.toLowerCase();
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			docFactory.setValidating(true);
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			Element root = doc.createElement("Table");
			doc.appendChild(root);
			for (int i = 1; i < arr.length && arr[i][0] != null; i++) {
				Element row = doc.createElement("Row");
				for (int j = 0; j < arr[0].length && arr[i][j] != null; j++) {
					String k[] = arr[0][j].split("\\s+");
					Element e = doc.createElement(k[0] + "TYPE" + k[1]);
					String value = arr[i][j];
					e.appendChild(doc.createTextNode(value));
					row.appendChild(e);
				}
				root.appendChild(row);
			}

			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(xml));
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			 /*transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM,
			 xml.split("\\.")[0] + ".dtd");
			transformer.setOutputProperty(
					"{http://xml.apache.org/xslt}indent-amount", "2");*/
			transformer.transform(source, result);

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void readXml(String xml) throws ParserConfigurationException,
			SAXException, IOException, SQLException {
		xml = xml.toLowerCase();
	//	DTD d = new DTD();
	 //   d.parseXML(xml);
		int row, col;
		File fXmlFile = new File(xml);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();
		NodeList list = doc.getElementsByTagName("Row");
		row = list.getLength() + 1;
		col = this.getNoChildren(list.item(0).getChildNodes());
		loaded = new String[1010][1010];
		for (int i = 0; i < col; i++) {
			String k[] = colNames[i].split("TYPE");
			loaded[0][i] = k[0] + " " + k[1];
		}
		for (int i = 0; i < row - 1; i++) {
			NodeList n = list.item(i).getChildNodes();
			int k = 0;
			for (int j = 0; j < n.getLength(); j++) {
				if (n.item(j).getNodeName().equalsIgnoreCase("#text")) {
					continue;
				}
				Element e = (Element) n;
				loaded[i + 1][k++] = e
						.getElementsByTagName(n.item(j).getNodeName()).item(0)
						.getTextContent();

			}
		}

	}

	public int getNoChildren(NodeList x) {
		int n = 0;
		colNames = new String[x.getLength()];
		for (int i = 0; i < x.getLength(); i++) {
			if (!x.item(i).getNodeName().equalsIgnoreCase("#text")) {
				n++;
				colNames[n - 1] = x.item(i).getNodeName();
			}
		}

		return n;

	}

}
