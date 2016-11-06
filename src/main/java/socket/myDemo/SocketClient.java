package socket.myDemo;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
public class SocketClient {
/**
* @param args
*/
public static void main(String[] args) {
// TODO Auto-generated method stub
try {
Socket socket = new Socket("localhost",2121);
System.out.println("Cleant run...");
InputStream inputStream = socket.getInputStream();
OutputStream outputStream = socket.getOutputStream();
DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
dataOutputStream.writeBytes("hello");
dataOutputStream.flush();
// dataOutputStream.writeUTF("hello");
DataInputStream dataInputStream = new DataInputStream(inputStream);
String readUTF = dataInputStream.readUTF();
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
