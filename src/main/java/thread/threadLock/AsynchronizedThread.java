package thread.threadLock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class AsynchronizedThread {
	public static void main(String[] args) {
		Input i = new Input();
		try {
			i.listen();
		} catch (Exception ioe) {
		}
	}
}

class Input {
	public void listen() throws Exception {
		String ch;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("get and out");
		while (!(ch = in.readLine()).equals("EOF")) {
			System.out.println("read");
			new Output(ch).start();
		}
	}
}

class Output extends Thread {
	String msg;

	public Output(String ch) {
		msg = ch;
	}

	public void run() {
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(msg);
	}
}
