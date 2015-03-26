import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author Enrico Bacis
 */
public class SaxParserBook extends DefaultHandler {
	
	private Libro libro;
	
	private String tempValue;
	
	// Questa variabile ci serve per sapere se siamo nel tag "indice".
	// in questo modo possiamo controllare che gli elementi "titolo" siano
	// inseriti correttamente nel tag "indice". Infatti usiamo un DTD che non
	// definisce come sono indentati i tag. Questo non sarebbe necessario se
	// avessimo usato XML Schema.
	private boolean isInsideIndiceTag;
	
	public SaxParserBook() {
		libro = null;
		isInsideIndiceTag = false;
	}
	
	public Libro getLibro() {
		return libro;
	}

	public void parseDocument(String path) {
		SAXParserFactory spf = SAXParserFactory.newInstance();
		try {
			// Inizio a impostare la validazione anche se poi non controllo.
			spf.setValidating(true);
			SAXParser sp = spf.newSAXParser();
			
			sp.parse(path, this);
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch (IOException ie) {
			ie.printStackTrace();
		}
	}
	
	public void printData() {
		if (libro != null)
			System.out.print(libro.toString());
	}
	
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		tempValue = "";
		if(qName.equalsIgnoreCase("libro")) {
			libro = new Libro(attributes.getValue("titolo"),
							  attributes.getValue("autore"),
							  attributes.getValue("editore"));
		} else if (qName.equalsIgnoreCase("indice")) {
			isInsideIndiceTag = true;
		} else if (qName.equalsIgnoreCase("prefazione")) {
			libro.setPrefazioneAutore(attributes.getValue("autore"));
		}
	}
	
	public void characters(char[] ch, int start, int length) throws SAXException {
		tempValue = new String(ch, start, length);
	}
	
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equalsIgnoreCase("prefazione")) {
			libro.setPrefazioneTesto(tempValue);
		} else if (qName.equalsIgnoreCase("indice")) {
			isInsideIndiceTag = false;
		} else if (qName.equalsIgnoreCase("titolo")) {
			if (isInsideIndiceTag)
				libro.addTitoloToIndice(tempValue);
			else
				System.err.println("Warning: Tag titolo fuori dal tag indice.");
		} else if (qName.equalsIgnoreCase("capitolo")) {
			libro.addCapitolo(tempValue);
		}
	}
	
	
}
