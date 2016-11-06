package thread.threadLock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//线程同步
public class ThreadLockDemo implements Runnable {
	private Ticket ticket;

	public ThreadLockDemo(Ticket ticket) {
		super();
		this.ticket = ticket;
	}

	public void run() {
		String curThreadName = Thread.currentThread().getName();
		for (int i = 0; i < 100; i++) {
			if (ticket.getTicket() > 0) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				ticket.sell(curThreadName);
				Thread.yield();
			}
		}
	}

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		// 统计十次�?看是否会出现线程不安全的次数�?
		int noSafeNum = 0;
		for (int i = 0; i < 10; i++) {
			if (!isSafe()) {
				noSafeNum++;
			}
		}
		System.out.println("\nnoSafeNum:" + noSafeNum);
	}

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static boolean isSafe() throws InterruptedException {
		Ticket ticket = new Ticket(1000);// 存放在堆中（new出来的）, 即主存中
		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
		for (int i = 0; i < 10; i++) {
			cachedThreadPool.execute(new ThreadLockDemo(ticket));
		}
		cachedThreadPool.shutdown();
		System.out.println("cachedThreadPool shut down");
		cachedThreadPool.awaitTermination(20, TimeUnit.SECONDS);
		int size = ticket.getTicketMap().keySet().size();
		System.out.println("ticketMap size:" + size);
		boolean isSafe = true;
		for (Integer key : ticket.getTicketMap().keySet()) {
			// System.out.println("** map key:"+key +
			// "; value:"+ticket.getTicketMap().get(key));
			Integer integer = ticket.getTicketMap().get(key);
			if (integer > 1) {
				System.out.println("** map key:" + key + "; value:" + integer);
			}
		}
		return isSafe;
	}
}

class Ticket {
	private int ticketNum;
	private Map<Integer, Integer> ticketMap = new HashMap<Integer, Integer>();

	public Ticket(int ticketNum) {
		super();
		this.ticketNum = ticketNum;
	}

	public synchronized void sell(String curThreadName) {
		System.out.println("卖出�?��");
		// ticketNum = ticketNum-1;
		ticketNum--;
		ticketMap.put(ticketNum, ticketMap.get(ticketNum) == null ? 1
				: ticketMap.get(ticketNum) + 1);
		System.out.println(curThreadName + " 卖票剩余�? + ticketNum");
	}

	public synchronized int getTicket() {
		return ticketNum;
	}

	public synchronized void setTicket(int ticketNum) {
		this.ticketNum = ticketNum;
	}

	public synchronized Map<Integer, Integer> getTicketMap() {
		return ticketMap;
	}

	public synchronized void setTicketMap(Map<Integer, Integer> ticketMap) {
		this.ticketMap = ticketMap;
	}
}
