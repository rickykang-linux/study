package socket.demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread {
	private static int number = 0; // 保存本进程的客户计数
	Socket socket = null; // 保存与本线程相关的Socket对象

	public ServerThread(Socket socket, int clientnum) {
		this.socket = socket;
		number = clientnum;
		System.out.println("当前在线的用户数: " + number);
	}

	public void run() {
		try {
			print("thread run...");
			// 由Socket对象得到输入�?并构造相应的BufferedReader对象
			BufferedReader in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			// 由Socket对象得到输出�?并构造PrintWriter对象
			PrintWriter out = new PrintWriter(socket.getOutputStream());
			// 由系统标准输入设备构造BufferedReader对象
			BufferedReader sysin = new BufferedReader(new InputStreamReader(
					System.in));
			print("thread 1");
			// 在标准输出上打印从客户端读入的字符串
			System.out.println("[Client " + number + "]: " + in.readLine());
			print("thread 2");
			String line; // 保存�?��内容
			// 从标准输入读入一字符�?
			line = sysin.readLine();
			print("thread 3");
			while (!line.equals("bye")) { // 如果该字符串�?"bye",则停止循�?
				print("thread 4");
				// 向客户端输出该字符串
				out.println(line);
				print("thread 5");
				// 刷新输出�?使Client马上收到该字符串
				out.flush();
				print("thread 6");
				// 在系统标准输出上打印读入的字符串
				System.out.println("[Server]: " + line);
				// 从Client读入�?��符串,并打印到标准输出�?
				System.out.println("[Client " + number + "]: " + in.readLine());
				print("thread 7");
				// 从系统标准输入读入一字符�?
				line = sysin.readLine();
				print("thread 8");
			}
			out.close(); // 关闭Socket输出�?
			in.close(); // 关闭Socket输入�?
			socket.close(); // 关闭Socket
		} catch (Exception e) {
			System.out.println("Error. " + e);
		}
	}

	public static void print(String info) {
		System.out.println("***************" + info);
	}
}
