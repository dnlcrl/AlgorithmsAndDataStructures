/**
 * Classe Studente utilizzata come esempio per le liste.
 * @author Enrico Bacis
 */
public class Studente implements Comparable<Studente>
{
	private int matricola;
	private String nome;
	private String cognome;
	
	public Studente(int matricola, String nome, String cognome)
	{
		this.setMatricola(matricola);
		this.setNome(nome);
		this.setCognome(cognome);
	}
	
	public Studente(int matricola)
	{
		this(matricola, "", "");
	}
	
	public Studente()
	{
		this(0);
	}

	/**
	 * @return the matricola
	 */
	public int getMatricola()
	{
		return matricola;
	}

	/**
	 * @param matricola the matricola to set
	 */
	public void setMatricola(int matricola)
	{
		this.matricola = matricola;
	}

	/**
	 * @return the nome
	 */
	public String getNome()
	{
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome)
	{
		this.nome = nome;
	}

	/**
	 * @return the cognome
	 */
	public String getCognome()
	{
		return cognome;
	}

	/**
	 * @param cognome the cognome to set
	 */
	public void setCognome(String cognome)
	{
		this.cognome = cognome;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		
		if (!(obj instanceof Studente))
			return false;
		
		Studente other = (Studente)obj;
		
		// Ammettiamo che questa sia una classe Studente che venga
		// usata per un database che comprende piu' universita'.
		// In questo caso possono quindi esserci due studenti con
		// la stessa matricola (possono essere di due universita' diverse),
		// si controlla che a uguale matricola non corrisponda anche
		// uguale nome e cognome.
		
		return ((this.matricola == other.getMatricola()) &&
			     this.cognome.equalsIgnoreCase(other.getCognome()) &&
			     this.nome.equalsIgnoreCase(other.getNome()));
	}

	@Override
	public int compareTo(Studente other)
	{
		int diff = this.matricola - other.getMatricola();
		if (diff != 0)
			return diff;
		
		diff = this.cognome.compareToIgnoreCase(other.getCognome());
		if (diff != 0)
			return diff;
		
		return this.nome.compareToIgnoreCase(other.getNome());
	}
}
