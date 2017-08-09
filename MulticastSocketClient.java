/*
* @Author: Ade Akingbade	
* @Date: 1st May 2017
* @IDE: Eclipse Java EE IDE Neon.3
* 
* 
*/

package test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class MulticastSocketClient extends Thread {
	final static String INET_ADDR = "224.0.0.3";
    final static int PORT = 8888; //Same add and port of the server
    private Thread t;
    private String threadOp;
    
    MulticastSocketClient(String op) {
    	threadOp = op;
    }
    
    public void run() {
    	if(threadOp.equals("rec")){
    		// Create a new Multicast socket which is the same as a DatagramSocket but with more capabilities
    		// it will allow other sockets/programs to join it as well.
    		// Basically we want to use multicastsocket instead of datagramsocket here as it will allow
    		// messages sent here to be sent to all subscribers of the addr and port
    		try (MulticastSocket clientSocket = new MulticastSocket(PORT)){
    			//Get the address that we are going to listen on.
            	InetAddress address = InetAddress.getByName(INET_ADDR);
            	
            	//associates socket to an address
                clientSocket.joinGroup(address);
         
                while (true) { 
                	// Create a buffer of bytes, which will be used to store
                	// the incoming bytes containing the information from the server.
                	byte[] buf = new byte[256];
                    DatagramPacket msgPacket = new DatagramPacket(buf, buf.length);
                    clientSocket.receive(msgPacket);
                    String msg = new String(buf, 0, buf.length);
                    System.out.println(msg);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
    	}else if(threadOp.equals("send")){
    		try (MulticastSocket clientSocket = new MulticastSocket(PORT)){
    			Scanner scan = new Scanner(System.in);
    			System.out.println("Enter a name");
    			String name = scan.nextLine();
    			InetAddress address = InetAddress.getByName(INET_ADDR);
                //clientSocket.joinGroup(address);
                while (true) { 	
                	String myLine = "";
                	myLine = scan.nextLine();
                    if(myLine.length() > 1){
                    	myLine = name + " sent: " + myLine;
                    	DatagramPacket msgPacket = new DatagramPacket(myLine.getBytes(), myLine.getBytes().length, address, PORT);
                    	clientSocket.send(msgPacket);
                    	//System.out.println("Client sent to port: " + myLine);
                    }
                }
                
            } catch (IOException ex) {
                ex.printStackTrace();
            }
    	}
       
    }
   
    
}

