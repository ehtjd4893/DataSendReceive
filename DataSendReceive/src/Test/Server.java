package Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/** 
 * ������Ʈ��: DataSendReceive
 * �ۼ���: �ڵ���
 * �ۼ�����: 2021/04/05 16:26
 * ����: ServerSocket Ÿ���� server�� ����� localhost�� ���� ��
 * 		����loop�� ���� ������ ���Ӿ��� ���۽�Ų��.
 * 		Client�� ���ӽ� InputStream�� ���� �����͸� �޾ƿ� ����Ѵ�.
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
					System.out.print("Ŭ���̾�Ʈ" + "(" + isa.getAddress() + ")�κ��� \"" );
					System.out.print(get);
					System.out.println("\" �����͸� �޾ҽ��ϴ�.");
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
