package echo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) throws IOException {
		
		ServerSocket serverSocket = new ServerSocket();
		serverSocket.bind(new InetSocketAddress("192.168.35.205",10001));//IP,포트 번호
		
		//192.168.35.205
		
		System.out.println("<서버 시작>");
		System.out.println("-----------------------------------------------");
		System.out.println("[연결을 기다리고 있습니다...]");
		
		
		while(true) {
			
			Socket socket = serverSocket.accept();//자동으로 소켓 생성, 클라이언트와 연결
			
			Thread thread = new ServerThread(socket);//전화번호 전달, 출장 코드, 회선 강화 3단, 메세지 주고받기
			thread.start();
			
			/*
			if() {
				break;
			}
			*/
		}//while
		
		
		/*
		System.out.println("================================");
		System.out.println("<서버종료>");

		serverSocket.close();
		*/
	}

}
