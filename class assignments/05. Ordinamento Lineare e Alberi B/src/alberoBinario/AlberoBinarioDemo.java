package alberoBinario;
import java.util.LinkedList;

/**
 * Co-Author: Enrico Bacis
 */

public class AlberoBinarioDemo {
	public static void main(String argc[]) {
		System.out.println("alb1=nodo1=1");
		AlberoBinario<Integer> alb1 = new AlberoBinarioImpl<Integer>(1);
		NodoBinario<Integer> nodo1 = alb1.radice();

		System.out.println("alb2=nodo2=2");
		AlberoBinario<Integer> alb2 = new AlberoBinarioImpl<Integer>(2);
		NodoBinario<Integer> nodo2 = alb2.radice();

		System.out.println("alb3=nodo3=3");
		AlberoBinario<Integer> alb3 = new AlberoBinarioImpl<Integer>(3);
		NodoBinario<Integer> nodo3 = alb3.radice();

		System.out.println("alb4=nodo4=4");
		AlberoBinario<Integer> alb4 = new AlberoBinarioImpl<Integer>(4);
		NodoBinario<Integer> nodo4 = alb4.radice();

		System.out.println("alb5=nodo5=5");
		AlberoBinario<Integer> alb5 = new AlberoBinarioImpl<Integer>(5);
		NodoBinario<Integer> nodo5 = alb5.radice();

		System.out.println("alb6=nodo6=6");
		AlberoBinario<Integer> alb6 = new AlberoBinarioImpl<Integer>(6);
		@SuppressWarnings("unused")
		NodoBinario<Integer> nodo6 = alb6.radice();

		System.out.println("alb1.innestaDes(nodo1,alb3)"); alb1.innestaDes(nodo1,alb3);
		System.out.println("alb1.innestaSin(nodo1,alb2)"); alb1.innestaSin(nodo1,alb2);
		System.out.println("alb1.innestaDes(nodo3,alb4)"); alb1.innestaDes(nodo3,alb4);
		System.out.println("alb1.innestaSin(nodo2,alb5)"); alb1.innestaSin(nodo2,alb5);
		System.out.println("alb1.innestaDes(nodo2,alb6)"); alb1.innestaDes(nodo2,alb6);
			
		System.out.println("alb1.visitaDFS()"); 
		LinkedList<Integer> visita = (LinkedList<Integer>) alb1.visitaDFS();
		System.out.println(visita.toString());

		System.out.println("alb1.visitaBFS()"); 
		visita = (LinkedList<Integer>) alb1.visitaBFS();
        System.out.println(visita.toString());
        
        System.out.println("alb1.inorder()"); 
		visita = (LinkedList<Integer>) alb1.inorder();
        System.out.println(visita.toString());
        
        System.out.println("alb1.altezza()");
        System.out.println(alb1.altezza());
        
        System.out.println("alb1.numFoglie()");
        System.out.println(alb1.numFoglie());
        
        System.out.println("alb1.numNodiInterni()");
        System.out.println(alb1.numNodiInterni());
        
        System.out.println("alb1.isBalanced()");
        System.out.println(alb1.isBalanced());
        
        System.out.println("alb1.isPerfectlyBalanced()");
        System.out.println(alb1.isPerfectlyBalanced());
        
        System.out.println("alb1.isCompleted()");
        System.out.println(alb1.isCompleted());
        
        System.out.println("alb1.search(3)");
        System.out.println(alb1.search(3));
        
        System.out.println("alb1.search(12)");
        System.out.println(alb1.search(12));
        
        System.out.println("alb1.max()");
        System.out.println(alb1.max());
        
        System.out.println("alb1.min()");
        System.out.println(alb1.min());
        
        System.out.println("alb1.minmax()");
        visita = (LinkedList<Integer>) alb1.minmax();
        System.out.println(visita.toString());
        
        System.out.println("alb1.isBST()");
        System.out.println(alb1.isBST());
        
		System.out.println("alb8=alb1.potaSinistro(nodo1)"); 
		AlberoBinario<Integer> alb8 = alb1.potaSinistro(nodo1);
		System.out.println("alb1.visitaDFS()"); 
		visita = (LinkedList<Integer>) alb1.visitaDFS();
		System.out.println(visita.toString());
		System.out.println("alb8.visitaDFS()"); 
		visita = (LinkedList<Integer>) alb8.visitaDFS();
		System.out.println(visita.toString());
		
		System.out.println("");
        System.out.println("Albero BST");

		alb1 = new AlberoBinarioImpl<Integer>(1);
		nodo1 = alb1.radice();

		alb2 = new AlberoBinarioImpl<Integer>(2);
		nodo2 = alb2.radice();

		alb3 = new AlberoBinarioImpl<Integer>(3);
		nodo3 = alb3.radice();

		alb4 = new AlberoBinarioImpl<Integer>(4);
		nodo4 = alb4.radice();

		alb5 = new AlberoBinarioImpl<Integer>(5);
		nodo5 = alb5.radice();

		alb6 = new AlberoBinarioImpl<Integer>(6);
		nodo6 = alb6.radice();
		
		System.out.println("alb4.innestaSin(nodo4,alb2)"); alb4.innestaSin(nodo4,alb2);
		System.out.println("alb4.innestaDes(nodo4,alb5)"); alb4.innestaDes(nodo4,alb5);
		System.out.println("alb4.innestaSin(nodo2,alb1)"); alb4.innestaSin(nodo2,alb1);
		System.out.println("alb4.innestaDes(nodo2,alb3)"); alb4.innestaDes(nodo2,alb3);
		System.out.println("alb4.innestaDes(nodo5,alb6)"); alb4.innestaDes(nodo5,alb6);
		
		System.out.println("alb4.inorder()");
		visita = (LinkedList<Integer>) alb4.inorder();
        System.out.println(visita.toString());
        
		System.out.println("alb4.isBST()");
        System.out.println(alb4.isBST());		
	}	
}