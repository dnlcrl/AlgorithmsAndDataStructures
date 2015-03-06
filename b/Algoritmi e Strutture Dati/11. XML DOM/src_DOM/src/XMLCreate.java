/*
 * XMLCreate.java
 *
 * Questa classe mostra come è posibile
 * creare in memoria un documento XML e
 * successivamente serializzarlo in un file.
 *
 * Per eseguirlo è necessario che i jar di
 * Xerces siano nel classpath.
 */

import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.w3c.dom.ls.*;

public class XMLCreate {
    
    /* Crea un elemento "corso" con le caratteristiche date come parametri*/
    public Document createCorso(Document d, String nome, String codice, int[] crediti) {
       
    	//Creiamo l'elemento radice "corso"
        Element radice = d.createElement("corso"); //versione senza DTD
        //Element radice = d.getDocumentElement();  //versione con DTD
        
        //Impostiamo l'attributo "codice"
        radice.setAttribute("codice",codice);
        
        //Creiamo l'elemento "nome"
        Element _nome = d.createElement("nome");
        _nome.setTextContent(nome);
        //lo appendiamo alla radice come nodo figlio
        radice.appendChild(_nome);
        
        //Creiamo altri elementi con dei metodi ausiliari opportuni 
        //e li appendiamo alla radice come figli
        radice.appendChild(createDescrizione(d));
        radice.appendChild(createSillabo(d,crediti));
        radice.appendChild(createDocenti(d));
        radice.appendChild(createTesti(d));
        radice.appendChild(createErogazione(d,crediti));
        
        //Infine, attacchiamo al nodo documento la radice 
        d.appendChild(radice); //versione senza DTD
        
        return d;
    }
    
    /* Crea un template (senza elementi concreti) per l'elemento descrizione */
    public Element createDescrizione(Document d) {
        Element descrizione = d.createElement("descrizione");
        
        Element obiettivi = d.createElement("obiettivi");
        Element esame = d.createElement("esame");
        obiettivi.appendChild(d.createComment("Obiettivi formativi del corso"));
        esame.appendChild(d.createComment("Modalità di esame"));
        descrizione.appendChild(obiettivi);
        descrizione.appendChild(esame);
        
        return descrizione;
    }
    
    /* crea un template per l'elemento sillabo con le caratteristiche date */
    public Element createSillabo(Document d, int[] crediti) {
        Element sillabo = d.createElement("sillabo");
        
        for(int i=0; i<crediti.length;++i) {
            for(int j=0; j<crediti[i];++j){
                Element argomento = d.createElement("argomento");
                argomento.appendChild(d.createComment("Descrizione del credito"));
                sillabo.appendChild(argomento);
            }
        }
        
        return sillabo;
        
    }
    
    /* crea un template per l'elemento docenti */
    public Element createDocenti(Document d) {
        Element docenti = d.createElement("docenti");
        
        Element docente = d.createElement("docente");
        docente.setAttribute("rif","IDdocente");
        Element nome = d.createElement("nome");
        Element cognome = d.createElement("cognome");
        nome.appendChild(d.createComment("Nome del docente"));
        cognome.appendChild(d.createComment("Cognome del docente"));
        docente.appendChild(nome);
        docente.appendChild(cognome);
        docenti.appendChild(docente);
        
        return docenti;
    }
    
    /* crea un template per l'elemento testi */
    public Element createTesti(Document d) {
        Element testi = d.createElement("testi");
        
        Element testo = d.createElement("testo");
        Element autore = d.createElement("autore");
        Element titolo = d.createElement("titolo");
        Element editore = d.createElement("editore");
        autore.appendChild(d.createComment("Autore del testo"));
        titolo.appendChild(d.createComment("Titolo del testo"));
        editore.appendChild(d.createComment("Editore del testo"));
        testo.appendChild(autore);
        testo.appendChild(titolo);
        testo.appendChild(editore);
        testi.appendChild(testo);
        
        return testi;
    }
    
    /* crea un template per l'elemento erogazione con le caratteristiche date */
    public Element createErogazione(Document d, int[] crediti) {
        Element erogazione = d.createElement("erogazione");
        
        String[] tipologie = new String[] {"A","B","C","D","E","F"};
        for(int i=0; i<tipologie.length && i<crediti.length;++i) {
            Element credito = d.createElement("crediti");
            credito.setAttribute("numero",String.valueOf(crediti[i]));
            credito.setAttribute("tipologia",tipologie[i]);
            erogazione.appendChild(credito);
        }
        Element periodo = d.createElement("periodo");
        Element cdl = d.createElement("cdl");
        Element anno = d.createElement("anno");
        periodo.appendChild(d.createComment("Quadrimestre o semestre di erogazione"));
        cdl.appendChild(d.createComment("Nome del corso di laurea"));
        anno.appendChild(d.createComment("Anno di corso"));
        erogazione.appendChild(periodo);
        erogazione.appendChild(cdl);
        erogazione.appendChild(anno);
        
        return erogazione;
    }
    
 //versione senza DTD
    public Document createDocument() {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating(false);
        dbf.setNamespaceAware(false);
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document d = db.newDocument();            
            return d;
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
            System.exit(10);
        }
        return null;
    }
    
 //versione con DTD
  public Document createDocumentDTD(String radice, String pubid, String sysid) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating(false);
        dbf.setNamespaceAware(false);
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            DOMImplementation dbi = db.getDOMImplementation();
            DocumentType doctype = dbi.createDocumentType(radice,pubid,sysid);
            Document d = dbi.createDocument("",radice,doctype);
            return d;
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
            System.exit(10);
        }
        return null;
    }
    
  
  public boolean saveDocument(Document d, Writer w) {
        
        DOMImplementationLS ls = (DOMImplementationLS)d.getImplementation();
        LSOutput lso = ls.createLSOutput();
        LSSerializer lss = ls.createLSSerializer();
        try {
            lso.setCharacterStream(w);
            lso.setEncoding("ISO-8859-1");
            //Formatta l'output aggiungendo spazi per produrre una stampa 
            //"graziosa" (pretty-print) e indentata
            lss.getDomConfig().setParameter("format-pretty-print",true);
            lss.write(d,lso);
            return true;
        } catch (LSException lse)   {
            return false;
        }     
    }
    
    public static void main(String[] args) {
        
        if (args.length<4) {
            System.err.println("SINTASSI: XMLCreate nome_corso codice creditiA creditiB CreditiC ...");
            System.exit(1);
        }
        
        int[] crediti = new int[args.length-2];
        for(int i=4; i<args.length; ++i) crediti[i-2]= (Integer.parseInt(args[i]));
        
        //Creiamo un oggetto della classe XMLCreate, che è user-defined
        XMLCreate instance = new XMLCreate();
        
        //Creiamo un oggetto Document XML
        //Document d = instance.createDocumentDTD("corso","","corso.dtd");  //versione con DTD
        Document d = instance.createDocument();  //versione senza DTD
        
        //Creiamo il contenuto dell'albero XML specifico ad un corso
        //createCorso(...) è user-defined!
        instance.createCorso(d,args[0],args[1],crediti);
        
        //Salviamo il documento XML
        try {
            instance.saveDocument(d,new FileWriter("Corso_"+args[1]+".xml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
