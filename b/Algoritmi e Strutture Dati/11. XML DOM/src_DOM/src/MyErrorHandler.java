/*
 * MyErrorHandler.java
 *
 * Created on 27 gennaio 2006, 12.22
 *
 */

import org.xml.sax.SAXException;


public class MyErrorHandler implements org.xml.sax.ErrorHandler {
    private int nErrors,nWarnings,nFatals;
    
    public MyErrorHandler() {
        reset();
    }
    
    public void reset() {
        nErrors=0;
        nWarnings=0;
        nFatals=0;
    }
    
    public void warning(org.xml.sax.SAXParseException e) throws org.xml.sax.SAXException {
        nWarnings++;
        System.out.println("WARNING ("+e.getSystemId()+":"+e.getLineNumber()+","+e.getColumnNumber()+") " + e.getMessage());
    }
    
    public void fatalError(org.xml.sax.SAXParseException e) throws org.xml.sax.SAXException {
        nFatals++;
        System.out.println("ERRORE FATALE ("+e.getSystemId()+":"+e.getLineNumber()+","+e.getColumnNumber()+") " + e.getMessage());
        throw e;
    }
    
    public void error(org.xml.sax.SAXParseException e) throws org.xml.sax.SAXException {
        nErrors++;
        System.out.println("ERRORE ("+e.getSystemId()+":"+e.getLineNumber()+","+e.getColumnNumber()+") " + e.getMessage());
        
    }
    
    public int getNWarnings() {return nWarnings;}
    public int getNErrors() {return nErrors;}
    public int getNFatals() {return nFatals;}
    public boolean hadProblems() {return (nErrors+nFatals > 0);}
    
    
    
}
