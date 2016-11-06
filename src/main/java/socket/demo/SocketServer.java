package socket.demo;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务器端Socket
 *
 * @author Administrator
 *
 */
public class SocketServer {
	/**
	 * 服务器端Socket构�?方法
	 */
	public SocketServer() {
		try {
			int clientcount = 0; // 统计客户端�?�?
			boolean listening = true; // 是否对客户端进行监听
			ServerSocket server = null; // 服务器端Socket对象
			try {
				// 创建�?��ServerSocket在端�?121监听客户请求
				server = new ServerSocket(2121);
				System.out.println("Server starts...");
			} catch (Exception e) {
				System.out.println("Can not listen to. " + e);
			}
			while (listening) {
				// 客户端计�?
				print("1");
				clientcount++;
				print("2");
				Socket accept = server.accept();
				print("3");
				// 监听到客户请�?根据得到的Socket对象和客户计数创建服务线�?并启动之
				new ServerThread(accept, clientcount).start();
			}
		} catch (Exception e) {
			System.out.println("Error. " + e);
		}
	}

	public static void print(String info) {
		System.out.println("***************" + info);
	}

	/**
	 * 主方�?
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		new SocketServer();
	}
}
