package socket.demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 客户端Socket
 *
 * @author Administrator
 *
 */
public class SocketClient {
	/**
	 * 客户端Socket构�?方法
	 */
	public SocketClient() {
		try {
			// 向本机的2121端口发出客户请求
			Socket socket = new Socket("localhost", 2121);
			System.out.println("Established a connection...");
			print("client step 1");
			// 由系统标准输入设备构造BufferedReader对象
			BufferedReader sysin = new BufferedReader(new InputStreamReader(
					System.in));
			print("client step 2");
			// 由Socket对象得到输出�?并构造PrintWriter对象
			PrintWriter out = new PrintWriter(socket.getOutputStream());
			print("client step 3");
			// 由Socket对象得到输入�?并构造相应的BufferedReader对象
			BufferedReader in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			print("client step 4");
			String line; // 保存�?��内容
			// 从系统标准输入读入一字符�?
			line = sysin.readLine();
			print("client step 5");
			while (!line.equals("bye")) { // 若从标准输入读入的字符串�?"bye"则停止循�?
				print("client step 6");
				// 将从系统标准输入读入的字符串输出到Server
				out.println(line);
				print("client step 7");
				// 刷新输出�?使Server马上收到该字符串
				out.flush();
				print("client step 8");
				// 在系统标准输出上打印读入的字符串
				System.out.println("[Client]: " + line);
				// 从Server读入�?��符串，并打印到标准输出上
				System.out.println("[Server]: " + in.readLine());
				print("client step 9");
				// 从系统标准输入读入一字符�?
				line = sysin.readLine();
				print("client step 10");
			}
			out.close(); // 关闭Socket输出�?
			in.close(); // 关闭Socket输入�?
			socket.close(); // 关闭Socket
		} catch (Exception e) {
			System.out.println("Error. " + e);
		}
	}

	/**
	 * 主方�?
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		new SocketClient();
	}

	public static void print(String info) {
		System.out.println("***************" + info);
	}
}
