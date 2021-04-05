package Test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

/** 
 * 프로젝트명: DataSendReceive
 * 작성자: 박도성
 * 작성일자: 2021/04/05 16:24
 * 설명: Socket 타입의 Client를 서버에 연결하고,
 * 		사용자에게 데이터를 키보드로 입력받은 후
 * 		OutputStream을 통해 서버에 전송한다.
**/
public class Client {

	public static void main(String[] args) {
		Socket client = null;
		OutputStream os = null;
		Scanner sc = null;
		
		try {
			client = new Socket();
			client.connect(new InetSocketAddress("localhost",1234));
			sc = new Scanner(System.in);
			System.out.println("서버에게 전송할 메시지를 입력하세요 >>> ");
			String send = sc.nextLine();
			InetSocketAddress isa = (InetSocketAddress)client.getRemoteSocketAddress();
			os = client.getOutputStream();
			os.write(send.getBytes("UTF-8"));
			os.flush();
			System.out.println("서버(" +isa.getAddress() + ")로 \"" + send + "\" 데이터를 전송했습니다.");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
				try {
					if(os != null)	os.close();
					if(!client.isClosed()) client.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

}
