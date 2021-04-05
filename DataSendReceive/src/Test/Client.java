package Test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

/** 
 * ������Ʈ��: DataSendReceive
 * �ۼ���: �ڵ���
 * �ۼ�����: 2021/04/05 16:24
 * ����: Socket Ÿ���� Client�� ������ �����ϰ�,
 * 		����ڿ��� �����͸� Ű����� �Է¹��� ��
 * 		OutputStream�� ���� ������ �����Ѵ�.
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
			System.out.println("�������� ������ �޽����� �Է��ϼ��� >>> ");
			String send = sc.nextLine();
			InetSocketAddress isa = (InetSocketAddress)client.getRemoteSocketAddress();
			os = client.getOutputStream();
			os.write(send.getBytes("UTF-8"));
			os.flush();
			System.out.println("����(" +isa.getAddress() + ")�� \"" + send + "\" �����͸� �����߽��ϴ�.");
			
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
