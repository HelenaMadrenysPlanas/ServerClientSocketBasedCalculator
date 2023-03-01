package Madrenys_Helena_UF1_examen;

//@author: Helena Madrenys Planas

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Ex1Client {
	public static void main( String args[] )throws IOException{
		//Establim el port i la IP del servidor
		int port = 2222;
		InetAddress serverHost = InetAddress.getLocalHost();
		
		//Creem el socket per connectar-nos al servidor
		Socket clientSocket = new Socket(serverHost, port);
		
		//Creem els canals de Input i de Output
		DataInputStream in = new DataInputStream(clientSocket.getInputStream());
		DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
		
		//Comprovem la connexió
		System.out.println(in.readUTF());
		
		//Enviem operacions fins que el caràcter sigui =
		System.out.println("Envia un nombre enter, - o igual:");
		Scanner sc = new Scanner (System.in);
		String enviar = "";
		do {
			enviar = sc.nextLine();
			out.writeUTF(enviar);
		}while (!enviar.equals("="));
		
		//Rebem el resultat
		System.out.println(in.readUTF());
		
		//Tanquem el socket
		in.close();
		out.close();
		clientSocket.close();
		sc.close();
	}
}
