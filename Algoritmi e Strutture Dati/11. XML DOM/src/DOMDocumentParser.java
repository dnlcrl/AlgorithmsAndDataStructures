import java.io.File;
import java.io.IOException;
import java.io.Writer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSException;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.SAXException;


public class DOMDocumentParser {

	public DOMDocumentParser() {
	}

	public Document loadDocument(String path) {

		MyErrorHandler h = new MyErrorHandler();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(true);
		dbf.setNamespaceAware(false);

		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			db.setErrorHandler(h);
			Document doc = db.parse(new File(path));

			if (h.hadProblems()) {
				System.out.println("Il documento contiene errori non fatali.");
			}

			return doc;

		} catch (ParserConfigurationException ex) {
			ex.printStackTrace();
			System.exit(10);
		} catch (SAXException sxe) {
			System.out.println("Il documento contiene errori fatali!");
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		return null;
	}

	public NodeList indice_lookup(Document doc) {

		NodeList docRoot = doc.getElementsByTagName("indice");
		NodeList docenti = ((Element)docRoot.item(0)).getElementsByTagName("titolo");
		return docenti;
	}
	
	public boolean saveNode(Node n, Writer w) {

		DOMImplementationLS ls = (DOMImplementationLS)n.getOwnerDocument().getImplementation();
		LSOutput lso = ls.createLSOutput();
		LSSerializer lss = ls.createLSSerializer();
		try {
			lso.setCharacterStream(w);
			lso.setEncoding("ISO-8859-1");
			lss.getDomConfig().setParameter("format-pretty-print",true);
			lss.getDomConfig().setParameter("xml-declaration",false);
			lss.write(n,lso);
			return true;
		} catch (LSException lse) {
			return false;
		}
	}
}
