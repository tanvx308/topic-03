package fis.java.topic3.client;

public class RunClient {
	  public static void main(String[] args) {
	        String hostname = "localhost";
	        int port = 7890;
	 
	        ChatClient client = new ChatClient(hostname, port);
	        client.execute();
	    }
}
