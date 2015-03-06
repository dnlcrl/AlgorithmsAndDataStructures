/**
 * @author Enrico Bacis
 */
public class ParserBookDemo {
	
	public static void main(String[] args) {
		String nomefile = "libro.xml";
		
		if (args.length < 1) {
			System.out.println("Sintassi: SaxParserBook nomefile.xml");
			System.out.println("Utilizzo file di esempio libro.xml\n");
		} else {
			nomefile = args[0];
		}
		
		SaxParserBook spb = new SaxParserBook();
		spb.parseDocument(nomefile);
		spb.printData();
	}
}
