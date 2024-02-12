package cuatroRayaCliente.cuatroRayaCliente;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

public class Leer {
	static String HOST;
	static int Puerto;
	static Socket canal;
	static InputStream streamEntrada;
	
	Leer(String host,int puerto,Socket canal){
		this.HOST=host;
		this.Puerto=puerto;
		this.canal=canal;
		try {
			this.streamEntrada=canal.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	Boolean confirma() {
		var entrada = new DataInputStream(streamEntrada);	            
        Boolean valorEntrada=false;
		//recibe datos
		try {
			valorEntrada = entrada.readBoolean();
		}catch (Exception e) {
			System.out.println("Se callo el servidor");
		}
		return valorEntrada;
	}
	
	int tamaño() {
			int tamaño=0;
			var entrada = new DataInputStream(streamEntrada);
			try {
				tamaño=Integer.parseInt(entrada.readUTF());
			} catch (IOException e) {
				System.out.println("Se callo el servidor");
			}
			return tamaño;
		}
		
	String[] users() {
		String[] lista=null;
		var entrada = new DataInputStream(streamEntrada);
		String cadena="";
		try {
			cadena=entrada.readUTF();
		} catch (IOException e) {
			System.out.println("Se callo el servidor");
		}
		//eliminar de la cadena la palabra null
		cadena = cadena.replaceAll("null", "");
		lista=cadena.split("&");
		return lista;
	}	
	 
	String[] cadenas() {
		String[] lista=null;
		var entrada = new DataInputStream(streamEntrada);
		String cadena="";
		try {
			cadena=entrada.readUTF();
		} catch (IOException e) {
			System.out.println("Se callo el servidor");
		}
		lista=cadena.split("_");
		return lista;
	}
	 
	String[] replay() {
			String[] lista=null;
			var entrada = new DataInputStream(streamEntrada);
			String cadena="";
			try {
				cadena=entrada.readUTF();
			} catch (IOException e) {
				System.out.println("Se callo el servidor");
			}
			lista=cadena.split("&");
			return lista;
	}
}
