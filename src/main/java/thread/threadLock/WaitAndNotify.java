package thread.threadLock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import thread.pojo.TicketBox;

//线程同步
public class WaitAndNotify {
	public static int num = 10;
	public static TicketBox ticket = new TicketBox(num);

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		ExecutorService threadPool = Executors.newCachedThreadPool();
		threadPool.execute(new SellTick(ticket));
		threadPool.execute(new GetMoney(ticket));
		threadPool.shutdown();
		threadPool.awaitTermination(20, TimeUnit.SECONDS);
		System.out.println("卖出" + (num - ticket.getTicket()) + ",剩余"
				+ ticket.getTicket());
		System.out.println("赚钱�?" + ticket.getMoney());
	}
}

// 收钱
class GetMoney implements Runnable {
	private TicketBox ticketBox;

	public GetMoney(TicketBox ticketBox) {
		super();
		this.ticketBox = ticketBox;
	}

	public void run() {
		String curThreadName = Thread.currentThread().getName();
		synchronized (WaitAndNotify.ticket) {
			while (ticketBox.getTicket() > 0) {
				ticketBox.collectMoney(curThreadName);
				WaitAndNotify.ticket.notify();// 该方�?
				try {
					WaitAndNotify.ticket.wait(); // 陷入等待�?直到别的线程通知
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			ticketBox.collectMoney(curThreadName);
			System.out.println("GetMoney run over");
			WaitAndNotify.ticket.notify();
		}
	}
}

// 卖票
class SellTick implements Runnable {
	private TicketBox ticketBox;

	public SellTick(TicketBox ticketBox) {
		super();
		this.ticketBox = ticketBox;
	}

	public void run() {
		String curThreadName = Thread.currentThread().getName();
		// for (int i = 0; i < 100; i++) {
		synchronized (WaitAndNotify.ticket) {
			while (ticketBox.getTicket() > 0) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				ticketBox.sell(curThreadName);
				WaitAndNotify.ticket.notify();
				try {
					WaitAndNotify.ticket.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("SellTick run over");
		}
		// }
	}
}
