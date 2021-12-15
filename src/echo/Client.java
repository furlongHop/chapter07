package echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {

	public static void main(String[] args) throws IOException {
		
		//java 1.8 사용(java project 생성시 버전 주의)
		Socket socket = new Socket();
		
		System.out.println("<클라이언트 시작>");
		System.out.println("-------------------------------------------");
		
		System.out.println("[서버에 연결을 요청합니다.]");
		socket.connect(new InetSocketAddress("192.168.35.205",10001));
		
		
		System.out.println("[서버에 연결되었습니다.]");
		
		//메세지 보내기 스트림
		OutputStream os = socket.getOutputStream(); //주 스트림
		OutputStreamWriter osw = new OutputStreamWriter(os,"UTF-8"); //보조 스트림,(연결고리,언어 형식)
		BufferedWriter bw = new BufferedWriter(osw); //보조 스트림
		
		//메세지 받기 스트림
		InputStream is = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(is,"UTF-8");
		BufferedReader br = new BufferedReader(isr);
		
		//Scanner (키보드 입력용)
		//Scanner sc = new Scanner(System.in);
		
		InputStream in = System.in;//in>필드. 필드에 접속
		InputStreamReader sin = new InputStreamReader(in);
		BufferedReader sbr = new BufferedReader(sin);
		
		//~여기부터 반복구간~
		while(true) {
			String str = sbr.readLine();
			/*입력받은 str 주소가 null인 상황을 방지하기 위해
			문자열이 이미 정해져 주소가 확실하게 존재하는 /q를 기준으로 equals() 메소드 사용*/
			if("/q".equals(str)) {
				System.out.println("[종료키 입력]");
				break;
			}
			//메세지 보내기
			bw.write(str);
			bw.newLine();
			bw.flush();//남은 값까지 탈탈 털어오는 코드
			
			//메세지 받기
			String reMsg = br.readLine();
			System.out.println("server: ["+reMsg+"]");
		}

		
		System.out.println("================================");
		//System.out.println("<클라이언트 종료>");
		
		OutputStream out  = System.out;
		OutputStreamWriter posr = new OutputStreamWriter(out);
		BufferedWriter pbw = new BufferedWriter(posr);
		
		pbw.write("<클라이언트 종료> 스트림 사용 구현");
		bw.newLine();
		bw.flush();
		
		
		//sc.close();
		bw.close();
		socket.close();
	}

}
