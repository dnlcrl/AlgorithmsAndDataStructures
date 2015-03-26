package alberoBinario;
//Nodo di un albero binario

class NodoBinario<T> {
	public T elem;
	public NodoBinario<T> padre;
	public NodoBinario<T> sinistro;
	public NodoBinario<T> destro;

	public NodoBinario(T elem) {
		this.elem = elem;
		padre = sinistro = destro = null;	
	}
}