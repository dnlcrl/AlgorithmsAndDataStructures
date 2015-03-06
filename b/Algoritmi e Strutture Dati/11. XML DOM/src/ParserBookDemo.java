import java.io.PrintWriter;
import java.io.Writer;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class ParserBookDemo {
	
	public static void main(String[] args) {
		String nomefile = "libro.xml";
		
		if (args.length < 1) {
			System.out.println("Sintassi: DOMDocumentParser nomefile.xml");
			System.out.println("Utilizzo file di esempio libro.xml");
		} else {
			nomefile = args[0];
		}
		
        DOMDocumentParser instance = new DOMDocumentParser();
        Document doc = instance.loadDocument(nomefile);
        
        if (doc != null) {
        	Writer output = new PrintWriter(System.out);
            NodeList risultati = instance.indice_lookup(doc);
            
            // Stampa dell'indice
            for (int i = 0; i < risultati.getLength(); ++i) {
                System.out.println("\n--- RISULTATO #" + (i + 1) + " ---");
                instance.saveNode(risultati.item(i), output);
            }
            
            System.out.println("--------------------");
        }
	}
}
