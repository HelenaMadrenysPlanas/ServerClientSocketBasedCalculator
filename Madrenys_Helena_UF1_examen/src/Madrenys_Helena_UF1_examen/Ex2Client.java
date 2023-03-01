package Madrenys_Helena_UF1_examen;

//@author: Helena Madrenys Planas

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Ex2Client {
	public static void main( String args[] )throws IOException{
		//Establim el port i la IP del servidor
		int port = 3333;
		InetAddress serverHost = InetAddress.getLocalHost();
		
		//Creem el socket per connectar-nos al servidor
		Socket clientSocket = new Socket(serverHost, port);
		
		//Creem els canals de Input i de Output
		DataInputStream in = new DataInputStream(clientSocket.getInputStream());
		DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
		
		//Comprovem la connexió
		System.out.println(in.readUTF());
		
		//Rebem els dos nombres del servidor i enviem resposta
		int num1 = in.readInt();
		int num2 = in.readInt();
		System.out.print(num1 + " + " + num2 + " = ");
		Scanner sc = new Scanner (System.in);
		int resposta = sc.nextInt();
		
		//Enviem el resultat i rebem resposta del servidor
		out.writeInt(resposta);
		System.out.println(in.readUTF());
		
		//Tanquem el socket
		in.close();
		out.close();
		clientSocket.close();
		sc.close();
	}
}
