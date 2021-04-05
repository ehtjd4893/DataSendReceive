package Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/** 
 * 프로젝트명: DataSendReceive
 * 작성자: 박도성
 * 작성일자: 2021/04/05 16:26
 * 설명: ServerSocket 타입의 server를 만들어 localhost에 연결 후
 * 		무한loop를 통해 서버를 끊임없이 동작시킨다.
 * 		Client가 접속시 InputStream을 통해 데이터를 받아와 출력한다.
 * 		
**/

public class Server {

	public static void main(String[] args) {
		Socket client = null;
		ServerSocket server = null;
		InputStream is = null;

		try {
			server = new ServerSocket();
			server.bind(new InetSocketAddress("localhost",1234));
			while(true) {
				client = server.accept();
				is = client.getInputStream();
				InetSocketAddress isa = (InetSocketAddress)client.getRemoteSocketAddress();
				byte[] b = new byte[1024];
				int length = is.read(b);
				
				if(length != -1){
					String get = new String(b,0,length,"UTF-8");
					System.out.print("클라이언트" + "(" + isa.getAddress() + ")로부터 \"" );
					System.out.print(get);
					System.out.println("\" 데이터를 받았습니다.");
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(is != null) is.close();
				if(!client.isClosed()) client.close();
				if(!server.isClosed()) server.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
