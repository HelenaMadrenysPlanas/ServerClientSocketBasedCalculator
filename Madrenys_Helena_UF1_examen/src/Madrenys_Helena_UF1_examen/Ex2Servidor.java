package Madrenys_Helena_UF1_examen;

//@author: Helena Madrenys Planas

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Ex2Servidor {
	public static void main( String args[] )throws IOException{
		//Establim el port
		int port = 3333;
		
		//Creem el socket per a acceptar peticions
		ServerSocket serverSocket = new ServerSocket(port);
		
		//Creem el socket per a gestionar els missatges del client
		Socket socketServei = serverSocket.accept();
		
		//Creem els canals de Input i de Output
		DataInputStream in = new DataInputStream(socketServei.getInputStream());
		DataOutputStream out = new DataOutputStream(socketServei.getOutputStream());
		
		//Enviem missatge confirmant la connexió
		out.writeUTF("S'ha connectat correctament");
		
		//Diguem quin serà el valor màxim i quin serà el mínim
		int min = 5;
		int max = 20;
		
		//Generem un número aleatori entre el min i el max
		int random_int1 = (int)Math.floor(Math.random()*(max-min+1)+min);
		int random_int2 = (int)Math.floor(Math.random()*(max-min+1)+min);
		
		//Enviem els nombres al client
		out.writeInt(random_int1);
		out.writeInt(random_int2);
		
		//Rebem la resposta del client i comprovem
		int resposta = in.readInt();
		int resultat = random_int1 + random_int2;
		if (resposta == resultat)
		{
			out.writeUTF("Resultat correcte");
		} else
		{
			out.writeUTF("Resultat incorrecte, la resposta és " + Integer.toString(resultat));
		}
		
		//Tanquem el socket
		in.close();
		out.close();
		serverSocket.close();
		socketServei.close();
	}
}
