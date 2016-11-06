package thread.threadLock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountThread implements Runnable {
	private int ticket = 5;

	CountThread(int ticket) {
		this.ticket = ticket;
	}

	public void run() {
		String curThreadName = Thread.currentThread().getName();
		for (int i = 0; i < 10; i++) {
			if (ticket > 0) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(curThreadName + " 卖票：ticket" + ticket);
				ticket--;
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
		for (int i = 0; i < 10; i++) {
			cachedThreadPool.execute(new CountThread(5));
		}
		
		// CountThread countThread = new CountThread();
		// CountThread countThread1= new CountThread();
		// CountThread countThread2 = new CountThread();
		// new Thread(countThread).start();
		// new Thread(countThread).start();
		// new Thread(countThread).start();
	}
}
