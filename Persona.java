import java.io.*;
import java.util.*;
public class Persona {

	private static int length;
	public  int estudios=0;
	public  int edad=0;
	public  float ingresos=0;
	public  String nombre="";
	
	public Persona(int st,float ing, int ed, String nb){
		estudios=st;
		edad=ed;
		ingresos=ing;
		nombre=nb;
	}
	
	public static Persona [] listaPersonas(String fichero1){
		int edad=0;
		int estudios=0;
		float ingresos = 0;
		int contador=1;
		String nombre="";
		Persona [] personas=null;
		String linea;
		Persona persona=null;
		try{
			Scanner entrada=new Scanner(new FileReader(fichero1));
			linea=entrada.nextLine();
			while(entrada.hasNextLine()){
				linea=entrada.nextLine();
				contador++;
			}
			personas=new Persona[contador];
			contador=0;
			entrada.close();
			entrada=new Scanner(new FileReader(fichero1));
			while(entrada.hasNext()){
				estudios=entrada.nextInt();
				ingresos=entrada.nextFloat();
				edad=entrada.nextInt();
				nombre=entrada.nextLine();
				persona=new Persona(estudios,ingresos,edad,nombre);
				personas[contador]=persona;
				contador++;
			}
			entrada.close();
		}catch(java.io.IOException ioex){
			System.out.println(ioex);
		}
		return personas;
	}
	
	public static String [] hombreIdeal(Persona[] hombres,Persona [] mujeres){
		int contador1=0;
		int contador2=0;
		int minimo=110;
		int puntero=0;
		int indicador=0;
		int valor=0;
		String ideal="";
		String[] ideales=new String [mujeres.length];
		for(int i=0;i<mujeres.length;i++){
			while(contador1<hombres.length){
				if(contador1==hombres.length-1 && indicador==0){
					ideales[contador2]="";
				}
				if(hombres[contador1].ingresos>=60000){
					if(Math.abs(hombres[contador1].estudios-mujeres[i].estudios)<=1){
						valor=((hombres[contador1].edad/2)+10-mujeres[i].edad);
						if(valor<minimo){
							minimo=valor;
							ideal=hombres[contador1].nombre;
							ideales[contador2]=ideal;
							puntero=contador1;
							contador1++;
							indicador++;
						}else{
							contador1++;
						}
					}else{
						contador1++;
					}
				}else{
					contador1++;
				}
			}
			hombres[puntero].ingresos=0;
			ideal="";
			minimo=110;
			contador2++;
			contador1=0;
			indicador=0;
		}
		return ideales;
	}

	public static void parejas(Persona[] mujeres,String[] ideal){
		int contador =0;
		for(int i=0;i<mujeres.length;i++){
			while(contador==i){
				if(ideal[contador]==""){
					contador++;
				}else{
					System.out.print(mujeres[i].nombre+"  & ");
					System.out.print(ideal[contador]);
					contador++;
				}
			}
			System.out.println();
		}
	}
	
	public static void main(String [] args){
		Persona [] mujeres=listaPersonas("FicheroMujeres.txt");
		Persona [] hombres=listaPersonas("FicheroHombres.txt");
		String [] linea=hombreIdeal(hombres,mujeres);
		parejas(mujeres,linea);
	}
	
	
}
