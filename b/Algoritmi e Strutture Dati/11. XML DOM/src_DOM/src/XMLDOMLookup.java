/*
 * XMLLookup.java
 *
 *@author Patrizia Scandurra
 * (originariamente preso da G. Della Penna 
 * http://www.di.univaq.it/gdellape/students.php?crs=mwtxml08)
 *
 * Questa classe mostra un esempio riassuntivo in cui
 * - un documento XML viene caricato in memoria
 * - si effettua una navigazione nel DOM alla ricerca di un particolare elemento
 * - si serializzano (su video) gli elementi individuati
 *
 * Per eseguirla è necessario che i jar di
 * Xerces siano nel classpath.
 */

import java.io.*;
import java.util.ArrayList;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.w3c.dom.ls.*;
import org.xml.sax.SAXException;

public class XMLDOMLookup {
    
    public XMLDOMLookup() {
    }
    
    //Fa il load di un documento
    public Document loadDocument(String path) {
    	//Creiamo un'istanza del nostro gestore di errori 
        MyErrorHandler h = new MyErrorHandler();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating(true);
        dbf.setNamespaceAware(false);
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            db.setErrorHandler(h);
            Document d = db.parse(new File(path));
            if (h.hadProblems()) {
                System.out.println("Il documento contiene errori non fatali.");
            }
            return d;
            
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
    
    //Filtra i docenti di un corso
    public NodeList docenti_lookup(Document d) {
        
        NodeList droot = d.getElementsByTagName("docenti");
        NodeList docenti = ((Element)droot.item(0)).getElementsByTagName("docente");
        return docenti;
    }
    
    //Save di un nodo su uno stream "Writer"
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
        } catch (LSException lse)   {
            return false;
        }
    }
    
    
    public static void main(String[] args) {
        if (args.length<1) {
            System.out.println("Sintassi: XMLDOMLookup nomefile.xml");
            System.exit(1);
        }
        //Crea un'istanza della classe XMLDOMLookup
        XMLDOMLookup instance = new XMLDOMLookup();
        
        //Carica in memoria il documento indicato dal primo argomento
        Document d = instance.loadDocument(args[0]);
        if (d != null) {
        	//Cerchiamo la lista di docenti
            NodeList risultati = instance.docenti_lookup(d);
           //Impostiamo il video come stream di output 
            Writer output = new PrintWriter(System.out);
            //Stampa la lista dei docenti trovati
            for(int i=0; i<risultati.getLength(); ++i) {
                System.out.println("--- RISULTATO #"+(i+1));
                instance.saveNode(risultati.item(i), output);
            }
            System.out.println("------------------");
        }
    }
}
