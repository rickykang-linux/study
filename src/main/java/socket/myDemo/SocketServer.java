package socket.myDemo;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
public class SocketServer {
/**
* @param args
*/
public static void main(String[] args) {
// TODO Auto-generated method stub
try {
System.out.println("Server run...");
ServerSocket socketServer = new ServerSocket(2121);
Socket socket = socketServer.accept();
InputStream inputStream = socket.getInputStream();
OutputStream outputStream = socket.getOutputStream();
DataInputStream dataInputStream = new DataInputStream(inputStream);
String readUTF = dataInputStream.readLine();
DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
dataOutputStream.writeBytes("server: " + readUTF);
dataOutputStream.flush();
System.out.println(readUTF);
inputStream.close(); // 关闭Socket输出�?
outputStream.close(); // 关闭Socket输入�?
socket.close(); // 关闭Socket
} catch (Exception e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
}
}
