package thread.threadLock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import thread.pojo.TicketBox;

//线程同步
public class ReentrantLockDemo {
	public static int num = 10;
	public static TicketBox ticket = new TicketBox(num);

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		ExecutorService threadPool = Executors.newCachedThreadPool();
		Lock lock = new ReentrantLock();
		threadPool.execute(new SellTick2(ticket, lock));
		threadPool.execute(new GetMoney2(ticket, lock));
		threadPool.shutdown();
		threadPool.awaitTermination(20, TimeUnit.SECONDS);
		System.out.println("卖出" + (num - ticket.getTicket()) + ",剩余"
				+ ticket.getTicket());
		System.out.println("赚钱�?" + ticket.getMoney());
	}
}

// 收钱
class GetMoney2 implements Runnable {
	private TicketBox ticketBox;
	private Lock lock;

	public GetMoney2(TicketBox ticketBox, Lock lock) {
		super();
		this.ticketBox = ticketBox;
		this.lock = lock;
	}

	public void run() {
		String curThreadName = Thread.currentThread().getName();
		// synchronized (ReentrantLockDemo.ticket) {
		while (ticketBox.getTicket() > 0) {
			try {
				lock.lock();
				Thread.sleep(100);
				ticketBox.collectMoney(curThreadName);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				lock.unlock(); // 释放锁， 之后执行抢占�? 也可能还是该线程获取到锁
			}
		}
		ticketBox.collectMoney(curThreadName);
		System.out.println("GetMoney run over");
		// ReentrantLockDemo.ticket.notify();
		// }
	}
}

// 卖票
class SellTick2 implements Runnable {
	private TicketBox ticketBox;
	private Lock lock;

	public SellTick2(TicketBox ticketBox, Lock lock) {
		super();
		this.ticketBox = ticketBox;
		this.lock = lock;
	}

	public void run() {
		String curThreadName = Thread.currentThread().getName();
		// synchronized (ReentrantLockDemo.ticket) {
		while (ticketBox.getTicket() > 0) {
			try {
				lock.lock();
				Thread.sleep(100);
				ticketBox.sell(curThreadName);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}
		System.out.println("SellTick run over");
		// }
	}
}
