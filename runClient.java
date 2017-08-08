package test;

public class runClient {
	public static void main(String[] args){
		MulticastSocketClient clientRec = new MulticastSocketClient("rec");
		clientRec.start();
		
		MulticastSocketClient clientSend = new MulticastSocketClient("send");
		clientSend.start();
	}
}
