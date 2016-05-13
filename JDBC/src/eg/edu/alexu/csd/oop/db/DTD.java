package eg.edu.alexu.csd.oop.db;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class DTD {
	private String arr[][] = { { "ID int", "Name string", "City string" },
			{ "1", "Ali", "Alex" } };

	public void setArr(String[][] columns) {
		int row = 0, col = 0;
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
		arr = new String[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				arr[i][j] = columns[i][j];
			}
		}
	}

	public void parseXML(String xml) throws ParserConfigurationException,
			IOException, SQLException {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			factory.setValidating(true);
			factory.setNamespaceAware(true);

			DocumentBuilder builder = factory.newDocumentBuilder();

			builder.setErrorHandler(new ErrorHandler() {
				public void warning(SAXParseException e) throws SAXException {
					System.out.println("WARNING : " + e.getMessage()); // do
																		// nothing
				}

				public void error(SAXParseException e) throws SAXException {
					System.out.println("ERROR : " + e.getMessage());
					throw e;
				}

				public void fatalError(SAXParseException e) throws SAXException {
					System.out.println("FATAL : " + e.getMessage());
					throw e;
				}
			});
			builder.parse(new InputSource(xml));

			System.out.println("Done!");
		} catch (ParserConfigurationException pce) {
			throw pce;
		} catch (IOException io) {
			throw io;
		} catch (SAXException se) {
			throw new SQLException();
		}
	}

	public void writeDTD(String path) throws IOException {
		BufferedWriter w = new BufferedWriter(new FileWriter(path));
		String head = "<!ELEMENT " + "Table" + " (Row*)>";
		w.write(head);
		w.newLine();
		String par = "";
		for (int j = 0; j < arr[0].length && arr[0][j] != null; j++) {
			String k[] = arr[0][j].split("\\s");
			par += k[0] + "TYPE" + k[1];
			if (j != arr[0].length - 1)
				par += ",";
		}
		w.write("<!ELEMENT Row ( " + par + " )>");
		w.newLine();
		for (int j = 0; j < arr[0].length && arr[0][j] != null; j++) {
			String k[] = arr[0][j].split("\\s");
			w.write("<!ELEMENT " + k[0] + "TYPE" + k[1] + " (#PCDATA)>");
			w.newLine();
		}
		w.close();

		// for (int i = 1; i < arr.length; i++) {
		// w.write("<!ELEMENT Row" + i + " (" + par + ")>");
		// w.newLine();
		// for (int j = 0; j < arr[0].length; j++) {
		// w.write("<!ELEMENT " + arr[0][j].split("\\s")[0]
		// + " (#PCDATA)>");
		// w.newLine();
		// }
		//
		// }

	}

}
