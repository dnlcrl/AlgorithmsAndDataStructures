import java.util.ArrayList;
import java.util.List;

/**
 * @author Enrico Bacis
 */
public class Libro {
	
	private String titolo;
	private String autore;
	private String editore;
	private String prefazioneAutore;
	private String prefazioneTesto;
	private List<String> indice;
	private List<String> capitoli;
	
	public Libro(String titolo, String autore, String editore) {
		this.setTitolo(titolo);
		this.setAutore(autore);
		this.setEditore(editore);
		
		indice = new ArrayList<String>();
		capitoli = new ArrayList<String>();
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = setStringOrEmptyIfNull(titolo);
	}

	public String getAutore() {
		return autore;
	}

	public void setAutore(String autore) {
		this.autore = setStringOrEmptyIfNull(autore);
	}

	public String getEditore() {
		return editore;
	}

	public void setEditore(String editore) {
		this.editore = setStringOrEmptyIfNull(editore);
	}

	public String getPrefazioneAutore() {
		return prefazioneAutore;
	}

	public void setPrefazioneAutore(String prefazioneAutore) {
		this.prefazioneAutore = setStringOrEmptyIfNull(prefazioneAutore);
	}

	public String getPrefazioneTesto() {
		return prefazioneTesto;
	}

	public void setPrefazioneTesto(String prefazioneTesto) {
		this.prefazioneTesto = setStringOrEmptyIfNull(prefazioneTesto);
	}

	public List<String> getIndice() {
		return indice;
	}

	public void addTitoloToIndice(String titolo) {
		this.indice.add(titolo);
	}

	public List<String> getCapitoli() {
		return capitoli;
	}

	public void addCapitolo(String capitolo) {
		this.capitoli.add(capitolo);
	}
	
	@Override
	public String toString() {
		String result = "[[Libro]]\n"
				 	  + "Titolo:  " + this.getTitolo()  + "\n"
				 	  + "Autore:  " + this.getAutore()  + "\n"
					  + "Editore: " + this.getEditore() + "\n";
		
		if (!prefazioneTesto.isEmpty()) {
			result += "\n[Prefazione"
			       + (this.getPrefazioneAutore().isEmpty() ?
			    		   "" : " di " + this.getPrefazioneAutore())
			       + "]\n"
			       + this.getPrefazioneTesto() + "\n";
		}
				
		if (!indice.isEmpty()) {
			result += "\n[Indice]\n";
			for (String titolo : this.indice) {
				result += "· " + titolo + "\n";
			}
		}
		
		if (!capitoli.isEmpty()) {
			for (String capitolo : this.capitoli) {
				result += "\n[Capitolo]\n" + capitolo + "\n";
			}
		}
		
		return result;
	}
	
	private String setStringOrEmptyIfNull(String string) {
		return string == null ? "" : string;
	}
	
	
}
