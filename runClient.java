/*
* @Author: Ade Akingbade	
* @Date: 1st May 2017
* @IDE: Eclipse Java EE IDE Neon.3
* 
* 
*/

package test;

public class runClient {
	public static void main(String[] args){
		MulticastSocketClient clientRec = new MulticastSocketClient("rec");
		clientRec.start();
		
		MulticastSocketClient clientSend = new MulticastSocketClient("send");
		clientSend.start();
	}
}
