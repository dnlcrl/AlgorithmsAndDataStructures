
//Nodo di un albero binario
class NodoBinario{
	public Object elem;
	public NodoBinario padre;
	public NodoBinario sinistro;
	public NodoBinario destro;
	

	public NodoBinario(Object el){
		elem = el;
		padre = sinistro= destro = null;	
	}
}