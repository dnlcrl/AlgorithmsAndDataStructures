// Driver class for Coda implementation tests
public class ProvaCoda {


  public static void main(String[] args) {
    
    //Creo una coda inizialmente vuota
	 Coda coda = new CodaCollegata(); //Nota l'uso del polimorfismo con l'interfaccia Coda
	
	 //Provo ad eliminare un elemento da una coda vuota (dovrei ottenere un'eccezione)
	  try {
         Integer n = (Integer) coda.dequeue();      
      } catch (EccezioneStrutturaVuota e) {
    	  System.out.println(e.getMessage());
          
       }
	 
	 //Inserisco gli interi passati da riga di comando
	 for (int i=0; i< args.length; i++)
        coda.enqueue(Integer.valueOf(args[i]));
	 
  
    //Stampo il primo elemento (senza eliminarlo)
     try {
    	 System.out.println("Primo elemento della coda: "+coda.first());      
     } catch (EccezioneStrutturaVuota e) {
   	  System.out.println(e.getMessage());
         
      }
    
   //Svuoto la coda (se non vuota) e man mano stampo a video gli elementi
	if (! coda.isEmpty()){
      System.out.println("Svuoto la coda e man mano stampo a video gli elementi.");
      while (! coda.isEmpty())
    	try {
    		System.out.println(coda.dequeue());     
        } catch (EccezioneStrutturaVuota e) {
      	  System.out.println(e.getMessage());
         }
	} 	
    
  }  		
}
