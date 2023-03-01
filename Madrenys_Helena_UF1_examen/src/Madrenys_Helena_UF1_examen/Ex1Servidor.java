package Madrenys_Helena_UF1_examen;

//@author: Helena Madrenys Planas

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Ex1Servidor {
	public static void main( String args[] )throws IOException{
		//Establim el port
		int port = 2222;
		
		//Creem el socket per a acceptar peticions
		ServerSocket serverSocket = new ServerSocket(port);
		
		//Creem el socket per a gestionar els missatges del client
		Socket socketServei = serverSocket.accept();
		
		//Creem els canals de Input i de Output
		DataInputStream in = new DataInputStream(socketServei.getInputStream());
		DataOutputStream out = new DataOutputStream(socketServei.getOutputStream());
		
		//Enviem missatge confirmant la connexió
		out.writeUTF("S'ha connectat correctament");
		
		//Rebem missatges i calculem
		String missatge = "";
		int nombre = 0;
		int resultat = 0;
		Boolean calcular = false;
		do {
			//Llegim el missatge
			missatge = in.readUTF();
			if (missatge.equals("=")) {
				//Si és =, sortim
				calcular = true;
			} else if (missatge.equals("-")) {
				//Si és -, restem l'últim nombre enviat
				resultat = resultat - nombre;
			} else {
				//Si és un nombre, el sumem i guardem la variable per si es decideix restar
				nombre = Integer.parseInt(missatge);
				resultat = resultat + nombre;
			}
		}while (!calcular);
		
		//Enviem el resultat final
		out.writeUTF(Integer.toString(resultat));
		
		//Tanquem el socket
		in.close();
		out.close();
		serverSocket.close();
		socketServei.close();
	}
}
