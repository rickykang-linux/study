package socket.tcp_udp;
/*
��ʾtcp���䡣


1,tcp�ֿͻ��˺ͷ���ˡ�
2���ͻ��˶�Ӧ�Ķ�����Socket��
	����˶�Ӧ�Ķ�����ServerSocket��


*/


/*
�ͻ��ˣ�
ͨ������socket���󣬷����ڸö�����ʱ���Ϳ���ȥ����ָ��������
��Ϊtcp���������ӵġ������ڽ���socket����ʱ��
��Ҫ�з���˴��ڣ������ӳɹ����γ�ͨ·���ڸ�ͨ���������ݵĴ��䡣


���󣺸�����˷��͸�һ���ı����ݡ�

���裺
1������Socket���񡣲�ָ��Ҫ���ӵ������Ͷ˿ڡ�

*/
import java.io.*;
import java.net.*;
class  TcpClient
{
	public static void main(String[] args) throws Exception 
	{
		//�����ͻ��˵�socket����ָ��Ŀ�������Ͷ˿�
		Socket s = new Socket("192.168.1.105",10003);
		
		//Ϊ�˷������ݣ�Ӧ�û�ȡsocket���е��������
		OutputStream out = s.getOutputStream();

		out.write("tcp ge men lai le ".getBytes());


		s.close();
	}
}


/*
���󣺶���˵�������ݲ���ӡ�ڿ���̨�ϡ�

�����:
1����������˵�socket����ServerSocket();
	������һ���˿ڡ�
2����ȡ���ӹ����Ŀͻ��˶���
	ͨ��ServerSokcet�� accept������û�����Ӿͻ�ȣ����������������ʽ�ġ�
3���ͻ���������������ݣ���ô�����Ҫʹ�ö�Ӧ�Ŀͻ��˶��󣬲���ȡ���ÿͻ��˶���Ķ�ȡ������ȡ�����������ݡ�
	����ӡ�ڿ���̨��

4���رշ���ˡ�����ѡ��


*/
class  TcpServer
{
	public static void main(String[] args) throws Exception
	{
		//���������socket���񡣲�����һ���˿ڡ�
		ServerSocket ss = new ServerSocket(10003);

		//ͨ��accept������ȡ���ӹ����Ŀͻ��˶���
		while(true)
		{
		Socket s = ss.accept();

		String ip = s.getInetAddress().getHostAddress();
		System.out.println(ip+".....connected");

		//��ȡ�ͻ��˷��͹��������ݣ���ôҪʹ�ÿͻ��˶���Ķ�ȡ������ȡ���ݡ�
		InputStream in = s.getInputStream();
		
		byte[] buf = new byte[1024];
		int len = in.read(buf);

		System.out.println(new String(buf,0,len));

		s.close();//�رտͻ���.
		}
		//ss.close();
	}
}
