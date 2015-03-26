class Record<D, K extends Comparable<? super K>> {
	public D data;  // dati satellite
	public K key;   // campo chiave
	
	public Record() {}
	
	public Record(D data, K key) {
		this.data = data;
		this.key = key;
	}
	
	public K getKey() {
		return key;
	}
	
	public D getData() {
		return data;
	}
	
	public String toString() {
		return "(data:" + data + ",key:" + key + ") ";
	}
}