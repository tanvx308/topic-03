package fis.java.topic3.server;

public class RunServer {
	 public static void main(String[] args) {
	      
	        int port = 7890;
	 
	        ChatServer server = new ChatServer(port);
	        server.execute();
	    }
}
