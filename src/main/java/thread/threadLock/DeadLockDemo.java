package thread.threadLock;

import java.lang.management.ManagementFactory;

public class DeadLockDemo implements Runnable {
	private int flag;
	static Object o1 = new Object(), o2 = new Object(); // 静�?的对象，被DeadLockTest的所有实例对象所公用

	public void run() {
		System.out.println(flag);
		if (flag == 0) {
			synchronized (o1) {
				System.out.println("i have o1");
				try {
					Thread.sleep(500);
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("waiting for o2");
				
				synchronized (o2) {
					System.out.println("i have o1,o2");
				}
			}
		}
		if (flag == 1) {
			synchronized (o2) {
				System.out.println("i have o2");
				try {
					Thread.sleep(500);
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("waiting for o1");
				
				synchronized (o1) {
					System.out.println("i have o1,o2");
				}
			}
		}
	}

	public static void main(String[] args) {
		DeadLockDemo test1 = new DeadLockDemo();
		DeadLockDemo test2 = new DeadLockDemo();
		String name = ManagementFactory.getRuntimeMXBean().getName();  
		System.out.println("pid:"+name); 
		test1.flag = 1;
		test2.flag = 0;
		Thread thread1 = new Thread(test1);
		Thread thread2 = new Thread(test2);
		thread1.start();
		thread2.start();
	}
}
