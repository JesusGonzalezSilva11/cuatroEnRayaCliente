package cuatroRayaCliente.cuatroRayaCliente;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Escribir {
	static String HOST;
	static int Puerto;
	static Socket canal;
	static OutputStream streamSalida;
	
	Escribir(String host,int puerto,Socket canal){
		this.HOST=host;
		this.Puerto=puerto;
		this.canal=canal;
		try {
			this.streamSalida=canal.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void signUp(String usuario,String contraseña) {  
        try {
        	var salida = new DataOutputStream(streamSalida);
	        //Creamos un objeto PrintWriter a partir del Stream de salidadel socket o canal de comunicación
	        //El objeto PrintWriter, nos permitirá utilizar los métodos
	        //print y write para mandar datos al proceso que está escuchando al otro lado del canal.
	        String datos="signUp"+"-"+usuario+"-"+contraseña;
	        salida.writeUTF(datos); //Mandamos los datos
	        salida.flush();
	    }catch(Exception ex){
	        System.err.println("El cliente cerro");
	        System.err.print(ex.toString());
	    }
	}
	
	void signIn(String usuario,String contraseña) {  
        try {
        	var salida = new DataOutputStream(streamSalida);
	        String datos="signIn"+"-"+usuario+"-"+contraseña;
	        salida.writeUTF(datos);
	        salida.flush();
	    }catch(Exception ex){
	        System.err.println("El cliente cerro");
	        System.err.print(ex.toString());
	    }
	}
	
	void updateUser(String usuario1,String usuario2,String contraseña2) {  
        try {
        	var salida = new DataOutputStream(streamSalida);
	        String datos="updateUser"+"-"+usuario1+"-"+usuario2+"-"+contraseña2;
	        salida.writeUTF(datos);
	        salida.flush();
	    }catch(Exception ex){
	        System.err.println("El cliente cerro");
	        System.err.print(ex.toString());
	    }
	}
	
	void turno(int columna,int fila) {
		try {
        	var salida = new DataOutputStream(streamSalida);
	        String datos="turno"+"-"+columna+"-"+fila;
	        salida.writeUTF(datos);
	        salida.flush();
	    }catch(Exception ex){
	        System.err.println("El cliente cerro");
	        System.err.print(ex.toString());
	    }
	}
	
	void isTerminada() {
		try {
        	var salida = new DataOutputStream(streamSalida);
	        String datos="isTerminada";
	        salida.writeUTF(datos);
	        salida.flush();
	    }catch(Exception ex){
	        System.err.println("El cliente cerro");
	        System.err.print(ex.toString());
	    }
	}
	
	void getUltimoTurno() {
		try {
        	var salida = new DataOutputStream(streamSalida);
	        String datos="getUltimoTurno";
	        salida.writeUTF(datos);
	        salida.flush();
	    }catch(Exception ex){
	        System.err.println("El cliente cerro");
	        System.err.print(ex.toString());
	    }
	}
	
	void dimitir(int partida) {
		try {
        	var salida = new DataOutputStream(streamSalida);
	        String datos="dimitir"+"-"+partida;
	        salida.writeUTF(datos);
	        salida.flush();
	    }catch(Exception ex){
	        System.err.println("El cliente cerro");
	        System.err.print(ex.toString());
	    }
	}
	
	void dimitir() {
		try {
        	var salida = new DataOutputStream(streamSalida);
	        String datos="dimite";
	        salida.writeUTF(datos);
	        salida.flush();
	    }catch(Exception ex){
	        System.err.println("El cliente cerro");
	        System.err.print(ex.toString());
	    }
	}
	
	void getActiveUsers() {
		try {
        	var salida = new DataOutputStream(streamSalida);
	        String datos="activos";
	        salida.writeUTF(datos);
	        salida.flush();
	    }catch(Exception ex){
	        System.err.println("El cliente cerro");
	        System.err.print(ex.toString());
	    }
	}
	
	void desafiar(String user2) {
		try {
        	var salida = new DataOutputStream(streamSalida);
	        String datos="desafio"+"-"+user2;
	        salida.writeUTF(datos);
	        salida.flush();
	    }catch(Exception ex){
	        System.err.println("El cliente cerro");
	        System.err.print(ex.toString());
	    }
	}
	
	void getPartidas() {
		try {
        	var salida = new DataOutputStream(streamSalida);
	        String datos="getPartidas";
	        salida.writeUTF(datos);
	        salida.flush();
	    }catch(Exception ex){
	        System.err.println("El cliente cerro");
	        System.err.print(ex.toString());
	    }
	}
	
	void continuaPartida(int partida) {
		try {
        	var salida = new DataOutputStream(streamSalida);
	        String datos="continuaPartida"+"-"+partida;
	        salida.writeUTF(datos);
	        salida.flush();
	    }catch(Exception ex){
	        System.err.println("El cliente cerro");
	        System.err.print(ex.toString());
	    }
	}
	
	void refrescarPartida() {
		try {
        	var salida = new DataOutputStream(streamSalida);
	        String datos="refrescarPartida";
	        salida.writeUTF(datos);
	        salida.flush();
	    }catch(Exception ex){
	        System.err.println("El cliente cerro");
	        System.err.print(ex.toString());
	    }
	}
	
	void getPartidasAcabadas() {
		try {
        	var salida = new DataOutputStream(streamSalida);
	        String datos="getPartidasAcabadas";
	        salida.writeUTF(datos);
	        salida.flush();
	    }catch(Exception ex){
	        System.err.println("El cliente cerro");
	        System.err.print(ex.toString());
	    }
	}
	
	void replay(int partida) {
		try {
        	var salida = new DataOutputStream(streamSalida);
	        String datos="replay"+"-"+partida;
	        salida.writeUTF(datos);
	        salida.flush();
	    }catch(Exception ex){
	        System.err.println("El cliente cerro");
	        System.err.print(ex.toString());
	    }
	}

}
